package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.Land;
import po.NormalPeople;
import po.People;
import po.Position;


//正常事件接口实现
public class NormalEventBizImpl implements NormalEventBiz{

	PeopleManageBiz pmb = new PeopleManageBizImpl(); //接口类
	
	public void PregnantManage(List<People> plist, List<Cell> clist, int row,
			int col) {
		// 统筹怀孕事件
		List<People> newchildlist = new ArrayList<People>();
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			if(p.getPtype() == 1){
				NormalPeople np1 = (NormalPeople)p;
				if(np1.getPregnancyFlag()){
					NormalPeople np2 = PregnantMatch(np1, plist, clist, row, col);
					if(np2 !=null){
						int num = pmb.countPeople(plist);                  //获取数组元素个数
						People newp = CreateChild(np1, np2, num, clist, row, col);
						newchildlist.add(newp);
					}
				}
			}
		}
		if(newchildlist.size()!=0){
			for(int i=0;i<newchildlist.size();i++)
				plist.add(newchildlist.get(i));
		}
		
		
	}

	public NormalPeople PregnantMatch(People p, List<People> plist, List<Cell> clist,
			int row, int col) {
		//产生配对正常人，返回匹配的正常人的值
		//判断正常人类对象周围的
		int x, y;
		Position ppos = p.getPpos();
		//Iterator<People> it = plist.iterator();
		List<People> markPList = new ArrayList<People>();	//用来记录满足条件的人
		
	
		//左 --1
		x = ppos.getX();
		y = ppos.getY();
		if( (y - 1) > -1 ){
			y -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//上  --2
		x = ppos.getX();
		y = ppos.getY();
		if( (x - 1) > -1 ){
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//右  --3
		x = ppos.getX();
		y = ppos.getY();
		if( (y + 1) < col ){
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//下 --4
		x = ppos.getX();
		y = ppos.getY();
		if( (x + 1) < row ){
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//左上  --5
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x - 1) > -1) ){
			y -= 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//右上  --6
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < col) && ((x - 1) > -1) ){
			y += 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//右下  --7
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < col) && ((x + 1) < row ) ){
			y += 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//左下  --8
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x + 1) < row) ){
			y -= 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//判断完之后，其实markPList里面有所有满足条件的People对象
		if(markPList.size()==0) {
			return null;}
		int ram = markPList.size();     //多少个满足条件的数量
		Random rd = new Random();
		int target = rd.nextInt(ram);   //根据有多少个满足的条件来从之中选择 0-ram
		NormalPeople np = (NormalPeople)markPList.get(target);
		
		return np;
	}
	
	public void JudgeCell(int x, int y, List<People> markPList, List<People> plist){
		//将坐标传进来判断是够达成怀孕条件
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			if(p.getPtype() == 1)  //一定要分开判断，因为如果这个地方不存在人，position判断为null，但这样效率下降
				if(p.getPpos().getX() == x && p.getPpos().getY() == y){
					NormalPeople np = (NormalPeople)p;
					if(np.getPregnancyFlag()){
						markPList.add(np);
				}
			}
		}
		
	}
	public People CreateChild(NormalPeople p1, NormalPeople p2, int num, List<Cell> clist, int row, int col) {
		int pid;
		Position ppos;
		boolean gender;
		int age;
		double survivability;
		boolean antibody;
		boolean pregnancyFlag;
		int index;
		Cell c;
		//将相匹配的两个人做处理
		p1.setPregnancyFlag(false);
		p2.setPregnancyFlag(false);
		//综合一下
		pid = num+1;
		do{
			ppos = pmb.initPositionRandom(row, col);
			index=ppos.getY()*col+ppos.getX();//Y乘以高+X，得到clist位置
			c = clist.get(index);
		}while( c.getPid()!= -1 );//格子上不存在人，重新生成	
		
		c.setPid(pid);
		c.setPtype(1);//格子加入正常人类
		
		gender = pmb.initGenderRandom();
		age = 0;
		//新生婴儿会根据父母的存活能力值来改变
		survivability = pmb.initSurvivabilityRandom() 
				+ p1.getSurvivability() * 0.1+ p2.getSurvivability() * 0.1;
		//新生婴儿携带抗体的情况和父母有关
		if( p1.isAntibody() && p2.isAntibody() ) antibody = true;
		else if( p1.isAntibody() || p2.isAntibody() ){
			Random rd = new Random();
			int mark = rd.nextInt(2);
			if( mark == 0) antibody = false;
			else antibody = true;
		}
		else antibody = false;
		pregnancyFlag = true;

		NormalPeople np = new NormalPeople(pid, ppos, gender, age, survivability, antibody, pregnancyFlag);
		
		return np;
	}

	public void DeadEvent(People p, List<People> plist) {
		//死亡事件
		plist.remove(p);
	}

	public int AntibodyRandomEvent() {
		//判断正常人类是否有抗体
		Random rd = new Random();
		int ram = rd.nextInt(2);
		return ram;
	}

	/*public void AgeEvent(List<People> plist) {
		//每次刷新的时候，将每个正常人的年龄增长一岁(不需要可以删除)
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			if( p.getPtype() == 1 ){
				NormalPeople np = (NormalPeople)p;
				int age = np.getAge();
				np.setAge(age);
			}
		}
	}*/
	
	

}
