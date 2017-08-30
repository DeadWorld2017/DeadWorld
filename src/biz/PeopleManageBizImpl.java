package biz;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;

//管理接口实现
public class PeopleManageBizImpl implements PeopleManageBiz{

	int countNormalPeople=200;//首先生成正常人的数量200
	int countDeadPeople=30;//其中丧尸的数量30
	
	//随机初始化正常人类方法，传入row,col作为随机数的最大范围，传入plist作为导入
	//col为长，即x轴方向，row为高,即y轴方向
	public void initNormalPeopleRandom(int row, int col, List<People> plist) {	
		for(int i=0;i<countNormalPeople;i++)
		{
			int pid=i;
			Position ppos = initPositionRandom(row,col);
			boolean gender=initGenderRandom();
			int age = initAgeRandom();
			double survivability = initSurvivabilityRandom();
			boolean antibody = initAntibodyRandom();
			int pregnancyFlag = 0;
			
			NormalPeople np = new NormalPeople(pid,ppos,gender, 
				age,survivability,antibody,pregnancyFlag);
			plist.add(np);	//加入list集合
		}
	}
	
	@Override
	//随机初始化丧尸
	public void initDeadPeopleRandom(List<People> plist) {
		 for(int i=0;i<countDeadPeople;i++){
			 NormalPeople np;
			 do{
				 Random rd = new Random();
				 int pid = rd.nextInt(countNormalPeople);
				 np = (NormalPeople) plist.get(pid);
			 }while(np.getPtype()==0);//若已经是丧尸了就重新随机一个数
			 turnToDead(np.pid,plist);//转化为丧尸
		 }
		
	}
	@Override
	//随机生成位置，需要传入row,col作为最大随机数的范围
	public Position initPositionRandom(int row, int col) {
		Random rd =new Random();//随机数
		int x = Math.abs(rd.nextInt(col));
		int y = Math.abs(rd.nextInt(row));
		Position pos = new Position(x,y);//随机生成位置
		return pos;
	}

	@Override
	//随机生成性别，true为男生，false为女生
	public boolean initGenderRandom() {
		Random rd =new Random();//随机数	
		Boolean gender = rd.nextBoolean();
		return gender;
	}

	@Override
	//随机生成年龄，年龄在0~100之间，符合正态分布
	public int initAgeRandom() {
		Random rd =new Random();//随机数
		//Math.sqrt(b)*random.nextGaussian()+a 均值为a，方差为b的随机数
		int age = (int) (Math.sqrt(50/3)*rd.nextGaussian()+50);
		//正态分布,3σ=50，μ=50，落在0~100的概率为99.74%
		return age;
	}

	@Override
	//随机生成存活能力值，范围在5~10之间，double型
	public double initSurvivabilityRandom() {
		Random rd =new Random();//随机数
		double survivability = rd.nextDouble();//0~1之间
		survivability = 5 * survivability + 5;//5~10之间
		return survivability;
	}

	@Override
	//随机生成抗体，初始抗体生成几率为10%
	public boolean initAntibodyRandom() {
		Random rd =new Random();//随机数	
		int probability = rd.nextInt(100);
		if(probability<10)//10%的概率返回有抗体
			return true;
		else
			return false;
	}

	@Override
	//初始生成丧尸基本攻击力，狂暴值，暂时不设随机数，默认为10
	public int initBaseDamageRandom() {
		return 10;
	}
  

	@Override
	public void turnToDead(int id, List<People> plist) {
		NormalPeople np = (NormalPeople) plist.get(id);
		if(np.isAntibody())//如果存在抗体，则跳过
			return;
		else//如果没有抗体则转化
		{
			np.setState(false);//变成丧尸
			int pid = np.pid;
			Position ppos = np.ppos;
			DeadPeople dp = new DeadPeople(pid,ppos);
			plist.add(dp);	//加入list集合
		}
		
	}

	@Override
	public void turnToNormal(List<People> plist) {
		 
		
	}

	@Override
	//存疑
	public int countNormalPeople(List<People> plist) {
		int numberNormalPeople = plist.size();
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			if(it.next().getPtype()==0)//丧尸：ptype为0
				numberNormalPeople-=2;
		}
		return numberNormalPeople;
	}

	@Override
	public int countDeadPeople(List<People> plist) {
		int numberDeadPeople=0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			if(it.next().getPtype()==0)//丧尸：ptype为0
				numberDeadPeople++;
		}
		return numberDeadPeople;
	}

	@Override
	public int countPeople(List<People> plist) {
		int numberPeople = countDeadPeople(plist)+countNormalPeople(plist);
		return numberPeople;
	}

	
	
}
