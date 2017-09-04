package biz;

import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//计算攻击力接口实现
public class AttackEventBizImpl implements AttackEventBiz{
	
	double aggressivity = 0;
	double childAttack = 5;
	double adultAttack = 10;
	double oldAttack = 4;
	
	LandEventBiz leb = new LandEventBizImpl();
	
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
	
	
}
