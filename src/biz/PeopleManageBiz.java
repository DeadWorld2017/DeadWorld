package biz;

import java.util.List;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;

//����ӿ�
public interface PeopleManageBiz {
	public void initNormalPeopleRandom(int row, int col, List<People> plist,List<Cell> clist);
	// �����ʼ���������෽��,����row,col��Ϊ����������Χ������plist��Ϊ����

	public void initDeadPeopleRandom(int col,List<People> plist,List<Cell> clist);
	// �����ʼ��ɥʬ

	public Position initPositionRandom(int row, int col);
	// �������λ�ã���Ҫ����row,col��Ϊ���������ķ�Χ

	public boolean initGenderRandom();
	// ��������Ա�trueΪ������falseΪŮ��

	public int initAgeRandom();
	// ����������䣬������0~100֮�䣬������̬�ֲ�

	public double initSurvivabilityRandom();
	// ������ɴ������ֵ����Χ��5~10֮�䣬double��

	public boolean initAntibodyRandom();
	// ������ɿ��壬��ʼ�������ɼ���Ϊ10%

	public int initBaseDamageRandom();
	// ��ʼ����ɥʬ��������������ֵ����ʱ�����������Ĭ��Ϊ10

	public DeadPeople turnToDead(int col, NormalPeople np, List<People> plist,List<Cell> clist);
	// ��������ת��Ϊɥʬ

	public void turnToNormal(List<People> plist, DeadPeople dp, List<Cell> clist, int col);
	//��ɥʬת����������

	public int countNormalPeople(List<People> plist);
	// ��������������

	public int countDeadPeople(List<People> plist);
	// ����ɥʬ����

	public int countPeople(List<People> plist);
	// ������������
	
	public double countAntibody(List<People> plist);
	//���㿹��ٷֱ�
	
	public double countMan(List<People> plist);
	//�������˱���
	
	public double countWomen(List<People> plist);
	//����Ů�˱���
	
	public double count0to19(List<People> plist);
	
	public double count20to39(List<People> plist);
	
	public double count40to59(List<People> plist);
	
	public double count60to79(List<People> plist);
	
	public double count80to99(List<People> plist);

}
