package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.DeadPeople;
import po.Land;
import po.NormalPeople;
import po.People;
import po.Position;
import po.Tool;

//正常事件接口实现
public class NormalEventBizImpl implements NormalEventBiz {

	PeopleManageBiz pmb = new PeopleManageBizImpl(); // 接口类

	public void PregnantManage(List<People> plist, List<Cell> clist, int row, int col) {
		// 统筹怀孕事件
		List<People> newchildlist = new ArrayList<People>();
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() == 1) {
				if(p.getClass() == NormalPeople.class){
					NormalPeople np1 = (NormalPeople) p;
					//System.out.println("怀孕1"+p.getClass()+"    " +p.getPtype()+"   "+p.getPid());
					if (np1.getPregnancyFlag()) {
						NormalPeople np2 = PregnantMatch(np1, plist, clist, row, col);
						if (np2 != null) {
							int num = pmb.countPeople(plist); // 获取数组元素个数
							People newp = CreateChild(np1, np2, num, clist, row, col);
							newchildlist.add(newp);
						}
					}
				}
				else{
					System.out.println("出错类型： " + p.getPtype()+" "+p.getClass());
				}
			}
		}
		if (newchildlist.size() != 0) {
			for (int i = 0; i < newchildlist.size(); i++)
				plist.add(newchildlist.get(i));
		}

	}

	public NormalPeople PregnantMatch(People p, List<People> plist, List<Cell> clist, int row, int col) {
		// 产生配对正常人，返回匹配的正常人的值
		// 判断正常人类对象周围的
		int x, y;
		Position ppos = p.getPpos();
		// Iterator<People> it = plist.iterator();
		List<People> markPList = new ArrayList<People>(); // 用来记录满足条件的人

		// 左 --1
		x = ppos.getX();
		y = ppos.getY();
		if ((y - 1) > -1) {
			y -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 上 --2
		x = ppos.getX();
		y = ppos.getY();
		if ((x - 1) > -1) {
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 右 --3
		x = ppos.getX();
		y = ppos.getY();
		if ((y + 1) < row) {
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 下 --4
		x = ppos.getX();
		y = ppos.getY();
		if ((x + 1) < col) {
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 左上 --5
		x = ppos.getX();
		y = ppos.getY();
		if (((y - 1) > -1) && ((x - 1) > -1)) {
			y -= 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 右上 --6
		x = ppos.getX();
		y = ppos.getY();
		if (((y + 1) < row) && ((x - 1) > -1)) {
			y += 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 右下 --7
		x = ppos.getX();
		y = ppos.getY();
		if (((y + 1) < row) && ((x + 1) < col)) {
			y += 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 左下 --8
		x = ppos.getX();
		y = ppos.getY();
		if (((y - 1) > -1) && ((x + 1) < col)) {
			y -= 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// 判断完之后，其实markPList里面有所有满足条件的People对象
		if (markPList.size() == 0) {
			return null;
		}
		int ram = markPList.size(); // 多少个满足条件的数量
		Random rd = new Random();
		int target = rd.nextInt(ram); // 根据有多少个满足的条件来从之中选择 0-ram
		NormalPeople np = (NormalPeople) markPList.get(target);

		return np;
	}

	public void JudgeCell(int x, int y, List<People> markPList, List<People> plist) {
		// 将坐标传进来判断是否达成怀孕条件
		int mark = -1;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() == 1) // 一定要分开判断，因为如果这个地方不存在人，position判断为null，但这样效率下降
				if (p.getPpos().getX() == x && p.getPpos().getY() == y) {
					//!!!!!!!!!!!!!!!!!!!!!!!!
					System.out.println("怀孕"+p.getClass()+"    " +p.getPtype()+"   "+p.getPid());
					if(p.getClass() != DeadPeople.class){
						NormalPeople np = (NormalPeople) p;
						if (np.getPregnancyFlag()) {
							markPList.add(np);
						}
					}
					else{
						mark = p.getPid();
					}
					
				}
		}
		Iterator<People> it2 = plist.iterator();
		while(it2.hasNext()){
			People p = it2.next();
			if(p.getPid() == mark && p.getClass() == NormalPeople.class){
				System.out.println("存在正常人 : "+p.getPtype());
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
		// 将相匹配的两个人做处理
		p1.setPregnancyFlag(false);
		p2.setPregnancyFlag(false);
		// 综合一下
		pid = num + 1;
		do {
			ppos = pmb.initPositionRandom(row, col);
			index = ppos.getY() * col + ppos.getX();// Y乘以高+X，得到clist位置
			c = clist.get(index);
		} while (c.getPid() != -1);// 格子上不存在人，重新生成

		c.setPid(pid);
		c.setPtype(1);// 格子加入正常人类

		gender = pmb.initGenderRandom();
		age = 0;
		// 新生婴儿会根据父母的存活能力值来改变
		survivability = pmb.initSurvivabilityRandom() + p1.getSurvivability() * 0.1 + p2.getSurvivability() * 0.1;
		// 新生婴儿携带抗体的情况和父母有关
		if (p1.isAntibody() && p2.isAntibody())
			antibody = true;
		else if (p1.isAntibody() || p2.isAntibody()) {
			Random rd = new Random();
			int mark = rd.nextInt(2);
			if (mark == 0)
				antibody = false;
			else
				antibody = true;
		} else
			antibody = false;
		pregnancyFlag = true;

		NormalPeople np = new NormalPeople(pid, ppos, gender, age, survivability, antibody, pregnancyFlag);

		return np;
	}

	public void DeadEvent(People p, List<People> plist, List<People> deadList) {
		// 死亡事件
		//通过判断传进来的People类来判断是删去一个对象还是两个对象
		int pid;// 暂存pid
		People tempp;// 暂存丧尸对应的正常人
		if (p.getPtype() == 1)
			{deadList.add(p);// 删除正常人
			//System.out.println("正常人"+p.getPid());
			}
		else if (p.getPtype() == 0) {
			pid = plist.get(plist.indexOf(p)).getPid();// 找到丧尸的pid
			Iterator<People> pit = plist.iterator();// 在总的plist找到符合死亡条件的
			while (pit.hasNext()) {
				tempp = pit.next();
				
				if (tempp.getPid() == pid && tempp != p) // 找到pid相同的
					{deadList.add(tempp);// 删除丧尸所对应的正常人
					//System.out.println("丧尸"+p.getPid());
					}
			}
			deadList.add(p);// 删除丧尸
		}
		

	}

	public int AntibodyRandomEvent() {
		// 判断正常人类是否有抗体
		Random rd = new Random();
		int ram = rd.nextInt(2);
		return ram;
	}

	 
	public void DestroyCell(int col, List<Cell> clist, People p) {
		if(p.getPtype()!= 2){   //因为被转化的人位置已经置为null了不需要改变
			int index = p.getPpos().getY() * col + p.getPpos().getX();
			Cell c = clist.get(index);
			c.setPid(-1);// 人死了，就没了
			c.setPtype(-1);// 人死了，就没了
			//System.out.println();
		}
		
	}
	
	public void DestroyTool(List<Tool> tlist, People p){
		NormalPeople np = (NormalPeople)p;
		if(np.getTlist()!=null)
		{
			Iterator<Tool> it = np.getTlist().iterator();
			while(it.hasNext()){
				Tool t = it.next();
				tlist.remove(t);
			}
		}
	}
	
	/*
	//调整全部人的停留时间
	public void AdjustTrapYear(List<People> plist) {
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			int trapYear = p.getTrapYear();
			if(trapYear>0)
				p.setTrapYear(trapYear--);//自减1
		}
	}
	
	
	//调整全部人的怀孕标签
	public void AdjustPregnancy(List<People> plist){
		//每年刷新地图时候调整正常人类怀孕标签
		People p;
		NormalPeople np;
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if( p.getPtype() == 1 ){
				np = (NormalPeople)p;
				np.setPregnancyFlag(true);
			}
		}
	}
	
	public void AdjustAge(List<People> plist){
		//每年刷新地图时候调整正常人类年龄
		People p;
		NormalPeople np;
		List<People> deadPList = new ArrayList<People>();
		int age;
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if( p.getPtype() == 1 ){
				np = (NormalPeople)p;
				age = np.getAge();
				age++;
				if(age > 99){
					deadPList.add(np);  //将年龄达到死亡要求的正常人类加入死亡list中处理
				}
				else{
					np.setAge(age);
				}
			}
		}
		//将满足死亡条件的人类从plist中删除
		Iterator<People> it2 = deadPList.iterator();
		while(it2.hasNext()){
			p = it2.next();
			plist.remove(p);  //将对象从list中删除
		}
		
	}*/

	 
	public void AdjustNormalPeopleAttr(List<People> plist) {
		List<People> deadlist = new ArrayList<People>();
		Iterator<People> it = plist.iterator();
		People p;
		NormalPeople np;
		while(it.hasNext()){
			p = it.next();
			if(p.getPtype()==1){
				//调整停留时间
				int trapYear = p.getTrapYear();
				trapYear--;
				if(trapYear>0)
					p.setTrapYear(trapYear);//自减1
				//调整为正常人类怀孕标签
				np = (NormalPeople)p;
				np.setPregnancyFlag(true);
				//调整正常人的年龄
				int age = np.getAge();
				age++;
				if(age>99)
					deadlist.add(np);
				else
					np.setAge(age);
				//提高正常人的存活能力值
				double survivability;
				np = (NormalPeople)p;
				survivability = np.getSurvivability();
				survivability += 0.1;
				np.setSurvivability(survivability);
			}	
		}
		//将满足死亡条件的人类从plist中删除
		Iterator<People> it2 = deadlist.iterator();
		while(it2.hasNext()){
			p = it2.next();
			plist.remove(p);  //将对象从list中删除
		}
	}
	
	
	
	/*
	 * public void AgeEvent(List<People> plist) {
	 * //每次刷新的时候，将每个正常人的年龄增长一岁(不需要可以删除) Iterator<People> it = plist.iterator();
	 * while(it.hasNext()){ People p = it.next(); if( p.getPtype() == 1 ){
	 * NormalPeople np = (NormalPeople)p; int age = np.getAge(); np.setAge(age);
	 * } } }
	 */

}
