package biz;

import java.util.List;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;

//管理接口
public interface PeopleManageBiz {
	public void initNormalPeopleRandom(int row, int col, List<People> plist,List<Cell> clist);
	// 随机初始化正常人类方法,传入row,col作为随机数的最大范围，传入plist作为导入

	public void initDeadPeopleRandom(int col,List<People> plist,List<Cell> clist);
	// 随机初始化丧尸

	public Position initPositionRandom(int row, int col);
	// 随机生成位置，需要传入row,col作为最大随机数的范围

	public boolean initGenderRandom();
	// 随机生成性别，true为男生，false为女生

	public int initAgeRandom();
	// 随机生成年龄，年龄在0~100之间，符合正态分布

	public double initSurvivabilityRandom();
	// 随机生成存活能力值，范围在5~10之间，double型

	public boolean initAntibodyRandom();
	// 随机生成抗体，初始抗体生成几率为10%

	public int initBaseDamageRandom();
	// 初始生成丧尸基本攻击力，狂暴值，暂时不设随机数，默认为10

	public DeadPeople turnToDead(int col, NormalPeople np, List<People> plist,List<Cell> clist);
	// 将正常人转化为丧尸

	public void turnToNormal(List<People> plist, DeadPeople dp, List<Cell> clist, int col);
	//将丧尸转换成正常人

	public int countNormalPeople(List<People> plist);
	// 计算正常人数量

	public int countDeadPeople(List<People> plist);
	// 计算丧尸数量

	public int countPeople(List<People> plist);
	// 计算人类总数
	
	public double countAntibody(List<People> plist);
	//计算抗体百分比
	
	public double countMan(List<People> plist);
	//计算男人比例
	
	public double countWomen(List<People> plist);
	//计算女人比例
	
	public double count0to19(List<People> plist);
	
	public double count20to39(List<People> plist);
	
	public double count40to59(List<People> plist);
	
	public double count60to79(List<People> plist);
	
	public double count80to99(List<People> plist);

}
