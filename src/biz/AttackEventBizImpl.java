package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;
import po.Tool;

//计算攻击力接口实现
public class AttackEventBizImpl implements AttackEventBiz{
	
	double aggressivity = 0;
	double childAttack = 5;
	double adultAttack = 10;
	double oldAttack = 4;
	
	LandEventBiz leb = new LandEventBizImpl();
	WorldEventBiz web = new WorldEventBizImpl();
	NormalEventBiz neb = new NormalEventBizImpl();
	PeopleManageBiz pmb = new PeopleManageBizImpl();
	
	public double CalculateAttack(People p, List<Cell> clist) {
		// 计算传入对象攻击力，返回攻击力值
		
		int ttype;
		int ltype;
		Tool tool;
		Cell cell;
		if(p.getPtype() == 1){
			//正常人类
			NormalPeople np = (NormalPeople)p;
			
			//加存活能力值
			aggressivity += np.getSurvivability();
			
			//加年龄因素
			if(np.getAge() >= 0 && np.getAge() < 18)
				aggressivity += childAttack;
			if(np.getAge() >= 18 && np.getAge() < 70)
				aggressivity += adultAttack;
			if(np.getAge() >=70 && np.getAge() < 100)
				aggressivity += oldAttack;
			
			//存放武器加成
			List<Tool> tlist = np.getTlist();
			if(tlist == null){
				aggressivity += 0;
			}
			else{
				Iterator<Tool> it = tlist.iterator();
				while(it.hasNext()){
					tool = it.next();
					ttype = tool.getTtype();
					if(ttype == 1){
						aggressivity += 2;
						break;
					}
					else if(ttype == 2){
						aggressivity += 5;
						break;
					}
					else if(ttype == 3){
						aggressivity += 8;
						break;
					}
					else aggressivity += 0;
				}
			}
			
			//（外部）地形条件
			int x = np.getPpos().getX();
			int y = np.getPpos().getY();
			
			Iterator<Cell> it = clist.iterator();
			while(it.hasNext()){
				cell = it.next();
				if( x == cell.getCpos().getX() && y == cell.getCpos().getY() ){
					ltype = cell.getltype();
					if(ltype == 2){
						//如果是辐射地
						aggressivity += leb.isRadient(np);
					}
					else if(ltype == 3){
						//如果是沼泽地
						aggressivity += leb.isSwampland(np);
					}
					else{
						//如果都不属于的或者没有地形的
						aggressivity += 0;
					}
				}
			}
		}
		if(p.getPtype() == 0){
			//丧尸
			DeadPeople dp = (DeadPeople)p;
			
			//加狂暴值
			aggressivity += dp.getBaseDamage();
			
			//（外部）地形条件
			int x = dp.getPpos().getX();
			int y = dp.getPpos().getY();
			
			Iterator<Cell> it = clist.iterator();
			while(it.hasNext()){
				cell = it.next();
				if( x == cell.getCpos().getX() && y == cell.getCpos().getY() ){
					ltype = cell.getltype();
					if(ltype == 2){
						//如果是辐射地
						aggressivity += leb.isRadient(dp);
					}
					else if(ltype == 3){
						//如果是沼泽地
						aggressivity += leb.isSwampland(dp);
					}
					else{
						//如果都不属于的或者没有地形的
						aggressivity += 0;
					}
				}
			}
		}
		
		return aggressivity;
		
		
	}

	public void ManageAttackEvent(List<People> plist, List<Cell> clist,
			int row, int col) {
		//统筹攻击事件
		People p;
		NormalPeople np;
		DeadPeople dp;
		List<People> deadList = new ArrayList<People>();   //存放满足死亡条件者
		List<People> fitPList;								//存放满足与对应丧尸相邻正常人类
		List<People> newList = new ArrayList<People>();    //存放满足增长的丧尸
		
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if(p.getPtype() == 0){   //若为丧尸
				dp = (DeadPeople)p;
				fitPList = JudgeAttackEvent(dp, clist, plist, row, col); //返回满足条件者
				if(fitPList != null){
					//存在满足条件者,构成攻击事件
					Iterator<People> it2 = fitPList.iterator();
					while(it2.hasNext()){
						np = (NormalPeople)it2.next();
						AttackEvent(np, dp, plist, clist, deadList, newList, row, col);
					}
				}
			}
		}
		if(deadList != null){
			Iterator<People> it3 = deadList.iterator(); 
			while(it3.hasNext()){
				p = it3.next();
				plist.remove(p);
				neb.DestroyCell(col, clist, p);
			}
		}
		if(newList != null){
			Iterator<People> it4 = newList.iterator(); 
			while(it4.hasNext()){
				p = it4.next();
				plist.add(p);
			}
		}
		
		
	}

	public List<People> JudgeAttackEvent(DeadPeople dp, List<Cell> clist, List<People> plist, 
			int row, int col) {
		//将对应的丧尸传进来，判断与其相邻的正常人类
		Position ppos = dp.getPpos();
		List<People> fitPList = new ArrayList<People>();        //满足条件人类List
		int x = ppos.getX();
		int y = ppos.getY();
		
		
		//判断与丧尸位置相邻是否存在人类
		//左 --1
		if( (y - 1) > -1 ){
			y -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//上  --2
		x = ppos.getX();
		y = ppos.getY();
		if( (x - 1) > -1 ){
			x -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//右  --3
		x = ppos.getX();
		y = ppos.getY();
		if( (y + 1) < row ){
			y += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//下 --4
		x = ppos.getX();
		y = ppos.getY();
		if( (x + 1) < col ){
			y += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//左上  --5
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x - 1) > -1) ){
			y -= 1;
			x -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//右上  --6
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < row) && ((x - 1) > -1) ){
			y += 1;
			x -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//右下  --7
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < row) && ((x + 1) < col ) ){
			y += 1;
			x += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//左下  --8
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x + 1) < col) ){
			y -= 1;
			x += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		return fitPList;
	}

	public void AttackEvent(NormalPeople np, DeadPeople dp, List<People> plist, List<Cell> clist,
			List<People> deadList, List<People> newList, int row, int col) {
		//一对一实现攻击事件
		
		//计算正常人类基础攻击力
		double NPAttackValue = CalculateAttack(np, clist);
		//加上外部事件攻击力
		NPAttackValue = web.attackByWorldEvent(NPAttackValue);
		
		//计算丧尸基础攻击力
		double DPAttackValue = CalculateAttack(dp, clist);
		
		//决出胜负
		//若人类胜利
		if(NPAttackValue > DPAttackValue){
			if( np.isAntibody() == true && dp.getLevel() == 1 ){
				int ram = neb.AntibodyRandomEvent();
				if( ram == 1 ){
					//表明可以被转化回人类
					pmb.turnToNormal(plist, dp, clist, col);
					//被转化为正常人类的丧尸加入deadList中
					deadList.add(dp);
				}
				if( ram == 0){
					//表明不可以被转化为人类
					//丧尸死亡
					neb.DeadEvent(dp, plist, deadList);
				}
			}
			else{
				//若人类不携带抗体，则丧尸直接死亡
				neb.DeadEvent(dp, plist, deadList);
			}
		}
		//若丧尸胜利
		if( NPAttackValue < DPAttackValue ){
			if( np.isAntibody() == true ){
				//若此人携带抗体则直接死亡
				neb.DeadEvent(dp, plist, deadList);
			}
			else{
				//若此人没有携带抗体则转化为丧尸
				//*************************************若不可以就遍历获得
				int id = plist.indexOf(np);//这里获得的是索引
				DeadPeople newdp = pmb.turnToDead(col, id, plist, clist);
				if(newdp != null)
					newList.add(newdp);
			}
		}
		
	}

	public void JudgeNormalCell(int x, int y, List<People> plist, List<People> fitPList) {
		//判断Cell中是否存在NormalPeople
		People p;
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if( p.getPtype() == 1 ){
				if( x == p.getPpos().getX() && y == p.getPpos().getY() ){
					//满足相邻位置的正常人类
					fitPList.add(p);
				}
			}
		}
		
	}
	
	
}
