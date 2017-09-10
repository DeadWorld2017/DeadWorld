package biz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;

//管理接口实现
public class PeopleManageBizImpl implements PeopleManageBiz {

	int countNormalPeople = 200;// 首先生成正常人的数量200
	int countDeadPeople = 150;// 其中丧尸的数量30

	// 随机初始化正常人类方法，传入row,col作为随机数的最大范围，传入plist作为导入
	// col为长，即x轴方向，row为高,即y轴方向********更改
	public void initNormalPeopleRandom(int row, int col, List<People> plist,List<Cell> clist) {
		Position ppos;
		int index;
		Cell c;
		for (int i = 0; i < countNormalPeople; i++) {
			int pid = i;
			do{
				ppos = initPositionRandom(row, col);
				index = ppos.getX()*row+ppos.getY();//同样改了计算index的方式！！！！！
				c = clist.get(index);
			}while(c.getPid() != -1);//格子上存在人，则重新生成	
			
			c.setPid(pid);
			c.setPtype(1);//格子加入人物
			
			boolean gender = initGenderRandom();
			int age = initAgeRandom();
			double survivability = initSurvivabilityRandom();
			boolean antibody = initAntibodyRandom();
			boolean pregnancyFlag = true;

			NormalPeople np = new NormalPeople(pid, ppos, gender, age, survivability, antibody, pregnancyFlag);
			plist.add(np); // 加入list集合
			
			
		}
	}

	// 随机初始化丧尸
	public void initDeadPeopleRandom(int row,List<People> plist,List<Cell> clist) {
		for (int i = 0; i < countDeadPeople; i++) {
			NormalPeople np;
			do {
				Random rd = new Random();
				int pid = rd.nextInt(countNormalPeople);
				np = (NormalPeople) plist.get(pid);
			} while (np.getPtype() == 0 || np.getPtype() == 2);// 若已经是丧尸或者状态不对，就重新随机一个数***感觉这里判断只需要判断是不是2就好了，丧尸在这里遍历不到
			DeadPeople dp = turnToDead(row,np, plist,clist);// 转化为丧尸
			if( dp != null )
				plist.add(dp); // 加入list集合
			
		}

	}

	// 随机生成位置，需要传入row,col作为最大随机数的范围
	public Position initPositionRandom(int row, int col) {
		Random rd = new Random();// 随机数
		int x = Math.abs(rd.nextInt(col));
		int y = Math.abs(rd.nextInt(row));
		Position pos = new Position(x, y);// 随机生成位置
		return pos;
	}

	// 随机生成性别，true为男生，false为女生
	public boolean initGenderRandom() {
		Random rd = new Random();// 随机数
		Boolean gender = rd.nextBoolean();
		return gender;
	}

	// 随机生成年龄，年龄在0~100之间，符合正态分布
	public int initAgeRandom() {
		Random rd = new Random();// 随机数
		// Math.sqrt(b)*random.nextGaussian()+a 均值为a，方差为b的随机数
		int age = (int) (Math.sqrt(50 / 3) * rd.nextGaussian() + 50);
		// 正态分布,3σ=50，μ=50，落在0~100的概率为99.74%
		return age;
	}

	// 随机生成存活能力值，范围在5~10之间，double型
	public double initSurvivabilityRandom() {
		Random rd = new Random();// 随机数
		double survivability = rd.nextDouble();// 0~1之间
		survivability = 5 * survivability + 5;// 5~10之间
		return survivability;
	}

	// 随机生成抗体，初始抗体生成几率为10%
	public boolean initAntibodyRandom() {
		Random rd = new Random();// 随机数
		int probability = rd.nextInt(100);
		if (probability < 10) // 10%的概率返回有抗体
			return true;
		else
			return false;
	}

	// 初始生成丧尸基本攻击力，狂暴值，暂时不设随机数，默认为10
	public int initBaseDamageRandom() {
		return 10;
	}

	// 将正常人转化为丧尸
	public DeadPeople turnToDead(int row, NormalPeople np, List<People> plist,List<Cell> clist) {
		if(np.getPtype()==1){
			if (np.isAntibody()) // 如果存在抗体，则跳过
				return null;
			else// 如果没有抗体则转化
			{
				np.setPtype(2);// 保持人的属性，将状态设为不可操作
				int pid = np.getPid();
				Position ppos = np.getPpos();
				DeadPeople dp = new DeadPeople(pid, ppos);
				//plist.add(dp); // 加入list集合
				
				if(ppos != null)
				{
					Cell c = clist.get(ppos.getX()*row+ppos.getY());//Y乘以高+X，得到坐标
					c.setPtype(0);//id不变，type变丧尸
					np.setPpos(null);//将坐标取消
					return dp;
				}
			}
		}
		return null;
		
		//Position nppos = np.getPpos();
		
		
	}

	public void turnToNormal(List<People> plist, DeadPeople dp, List<Cell> clist, int row) {
		//丧尸转化为正常人类
		People p;
		int pid = dp.getPid();
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if( /*pid == p.getPid() &&*/ p.getClass() != dp.getClass() ){
				//恢复正常人类,将当前丧尸的位置赋值给恢复的正常人类
				//感觉可能这里有问题！！！！！
				p.setPtype(1);
				Position ppos = dp.getPpos();
				p.setPpos(ppos);
				//更改Cell中设置
				Cell c = clist.get(ppos.getX()*row+ppos.getY());//Y乘以高+X，得到坐标*********更改
				c.setPtype(1);//id不变，type变正常人类
				
				break;
			}
		}
		
	}

	// 计算正常人数量
	public int countNormalPeople(List<People> plist) {
		int numberNormalPeople = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() == 1) // 正常人ptype=1
				numberNormalPeople++;
		}
		return numberNormalPeople;
	}
	
	// 计算丧尸数量
	public int countDeadPeople(List<People> plist) {
		int numberDeadPeople = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			if (it.next().getPtype() == 0) // 丧尸：ptype为0
				numberDeadPeople++;
		}
		return numberDeadPeople;
	}
	
	// 计算人类总数
	public int countPeople(List<People> plist) {
		int numberPeople = countDeadPeople(plist) + countNormalPeople(plist);
		return numberPeople;
	}
 
 
	public double countAntibody(List<People> plist) {
		int numberAntibody = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				//测试
				if(p.getClass() == NormalPeople.class){
					NormalPeople np = new NormalPeople();
					np = (NormalPeople) p;
					if(np.isAntibody())
						numberAntibody++;
				}
				else{
					System.out.println("出错类型： " + p.getPtype() + " " + p.getClass());
				}
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0){
			rate = (double)numberAntibody/(double)numberPeople;
		}
			
		return rate;
	}

 
	public double countMan(List<People> plist) {
		int numberMan = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				if(np.isGender())
					numberMan++;
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0)
			rate = (double)numberMan/(double)numberPeople;
		return rate;
	}

 
	public double countWomen(List<People> plist) {
		int numberWomen = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				if(!np.isGender())
					numberWomen++;
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0)
			rate = (double)numberWomen/(double)numberPeople;
		return rate;
	}

 
	public double count0to19(List<People> plist) {
		int numberAge = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				if(np.getAge()>=0&&np.getAge()<20)
					numberAge++;
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0)
			rate = (double)numberAge/(double)numberPeople;
		return rate;
	}

 
	public double count20to39(List<People> plist) {
		int numberAge = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				if(np.getAge()>=20&&np.getAge()<40)
					numberAge++;
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0)
			rate = (double)numberAge/(double)numberPeople;
		return rate;
	}

 
	public double count40to59(List<People> plist) {
		int numberAge = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				if(np.getAge()>=40&&np.getAge()<60)
					numberAge++;
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0)
			rate = (double)numberAge/(double)numberPeople;
		return rate;
	}

 
	public double count60to79(List<People> plist) {
		int numberAge = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				if(np.getAge()>=60&&np.getAge()<80)
					numberAge++;
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0)
			rate = (double)numberAge/(double)numberPeople;
		return rate;
	}

 
	public double count80to99(List<People> plist) {
		int numberAge = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				if(np.getAge()>=80&&np.getAge()<100)
					numberAge++;
			}
		}
		double rate=0;
		int numberPeople = countNormalPeople(plist);
		if(numberPeople!=0)
			rate = (double)numberAge/(double)numberPeople;
		return rate;
	}
	
	

}
