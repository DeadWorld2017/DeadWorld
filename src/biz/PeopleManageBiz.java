package biz;

import java.util.List;

import po.People;
import po.Position;

//����ӿ�
public interface PeopleManageBiz {
	public void initNormalPeopleRandom(int row, int col, List<People> plist);
	// �����ʼ���������෽��,����row,col��Ϊ����������Χ������plist��Ϊ����

	public void initDeadPeopleRandom(List<People> plist);
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

	public void turnToDead(int pid, List<People> plist);
	// ��������ת��Ϊɥʬ

	public void turnToNormal(List<People> plist);

	public int countNormalPeople(List<People> plist);
	// ��������������

	public int countDeadPeople(List<People> plist);
	// ����ɥʬ����

	public int countPeople(List<People> plist);
	// ������������

}
