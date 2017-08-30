package biz;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;

//����ӿ�ʵ��
public class PeopleManageBizImpl implements PeopleManageBiz{

	int countNormalPeople=200;//�������������˵�����200
	int countDeadPeople=30;//����ɥʬ������30
	
	//�����ʼ���������෽��������row,col��Ϊ����������Χ������plist��Ϊ����
	//colΪ������x�᷽��rowΪ��,��y�᷽��
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
			plist.add(np);	//����list����
		}
	}
	
	@Override
	//�����ʼ��ɥʬ
	public void initDeadPeopleRandom(List<People> plist) {
		 for(int i=0;i<countDeadPeople;i++){
			 NormalPeople np;
			 do{
				 Random rd = new Random();
				 int pid = rd.nextInt(countNormalPeople);
				 np = (NormalPeople) plist.get(pid);
			 }while(np.getPtype()==0);//���Ѿ���ɥʬ�˾��������һ����
			 turnToDead(np.pid,plist);//ת��Ϊɥʬ
		 }
		
	}
	@Override
	//�������λ�ã���Ҫ����row,col��Ϊ���������ķ�Χ
	public Position initPositionRandom(int row, int col) {
		Random rd =new Random();//�����
		int x = Math.abs(rd.nextInt(col));
		int y = Math.abs(rd.nextInt(row));
		Position pos = new Position(x,y);//�������λ��
		return pos;
	}

	@Override
	//��������Ա�trueΪ������falseΪŮ��
	public boolean initGenderRandom() {
		Random rd =new Random();//�����	
		Boolean gender = rd.nextBoolean();
		return gender;
	}

	@Override
	//����������䣬������0~100֮�䣬������̬�ֲ�
	public int initAgeRandom() {
		Random rd =new Random();//�����
		//Math.sqrt(b)*random.nextGaussian()+a ��ֵΪa������Ϊb�������
		int age = (int) (Math.sqrt(50/3)*rd.nextGaussian()+50);
		//��̬�ֲ�,3��=50����=50������0~100�ĸ���Ϊ99.74%
		return age;
	}

	@Override
	//������ɴ������ֵ����Χ��5~10֮�䣬double��
	public double initSurvivabilityRandom() {
		Random rd =new Random();//�����
		double survivability = rd.nextDouble();//0~1֮��
		survivability = 5 * survivability + 5;//5~10֮��
		return survivability;
	}

	@Override
	//������ɿ��壬��ʼ�������ɼ���Ϊ10%
	public boolean initAntibodyRandom() {
		Random rd =new Random();//�����	
		int probability = rd.nextInt(100);
		if(probability<10)//10%�ĸ��ʷ����п���
			return true;
		else
			return false;
	}

	@Override
	//��ʼ����ɥʬ��������������ֵ����ʱ�����������Ĭ��Ϊ10
	public int initBaseDamageRandom() {
		return 10;
	}
  

	@Override
	public void turnToDead(int id, List<People> plist) {
		NormalPeople np = (NormalPeople) plist.get(id);
		if(np.isAntibody())//������ڿ��壬������
			return;
		else//���û�п�����ת��
		{
			np.setState(false);//���ɥʬ
			int pid = np.pid;
			Position ppos = np.ppos;
			DeadPeople dp = new DeadPeople(pid,ppos);
			plist.add(dp);	//����list����
		}
		
	}

	@Override
	public void turnToNormal(List<People> plist) {
		 
		
	}

	@Override
	//����
	public int countNormalPeople(List<People> plist) {
		int numberNormalPeople = plist.size();
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			if(it.next().getPtype()==0)//ɥʬ��ptypeΪ0
				numberNormalPeople-=2;
		}
		return numberNormalPeople;
	}

	@Override
	public int countDeadPeople(List<People> plist) {
		int numberDeadPeople=0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			if(it.next().getPtype()==0)//ɥʬ��ptypeΪ0
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
