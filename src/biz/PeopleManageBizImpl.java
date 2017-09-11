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

//����ӿ�ʵ��
public class PeopleManageBizImpl implements PeopleManageBiz {

	int countNormalPeople = 500;// �������������˵�����200
	int countDeadPeople = 300;// ����ɥʬ������30

	// �����ʼ���������෽��������row,col��Ϊ����������Χ������plist��Ϊ����
	// colΪ������x�᷽��rowΪ��,��y�᷽��********����
	public void initNormalPeopleRandom(int row, int col, List<People> plist,List<Cell> clist) {
		Position ppos;
		int index;
		Cell c;
		for (int i = 0; i < countNormalPeople; i++) {
			int pid = i;
			do{
				ppos = initPositionRandom(row, col);
				index = ppos.getX()*row+ppos.getY();//ͬ�����˼���index�ķ�ʽ����������
				c = clist.get(index);
			}while(c.getPid() != -1);//�����ϴ����ˣ�����������	
			
			c.setPid(pid);
			c.setPtype(1);//���Ӽ�������
			
			boolean gender = initGenderRandom();
			int age = initAgeRandom();
			double survivability = initSurvivabilityRandom();
			boolean antibody = initAntibodyRandom();
			boolean pregnancyFlag = true;

			NormalPeople np = new NormalPeople(pid, ppos, gender, age, survivability, antibody, pregnancyFlag);
			plist.add(np); // ����list����
			
			
		}
	}

	// �����ʼ��ɥʬ
	public void initDeadPeopleRandom(int row,List<People> plist,List<Cell> clist) {
		for (int i = 0; i < countDeadPeople; i++) {
			NormalPeople np;
			do {
				Random rd = new Random();
				int pid = rd.nextInt(countNormalPeople);
				np = (NormalPeople) plist.get(pid);
			} while (np.getPtype() == 0 || np.getPtype() == 2);// ���Ѿ���ɥʬ����״̬���ԣ����������һ����***�о������ж�ֻ��Ҫ�ж��ǲ���2�ͺ��ˣ�ɥʬ�������������
			DeadPeople dp = turnToDead(row,np, plist,clist);// ת��Ϊɥʬ
			if( dp != null )
				plist.add(dp); // ����list����
			
		}

	}

	// �������λ�ã���Ҫ����row,col��Ϊ���������ķ�Χ
	public Position initPositionRandom(int row, int col) {
		Random rd = new Random();// �����
		int x = Math.abs(rd.nextInt(col));
		int y = Math.abs(rd.nextInt(row));
		Position pos = new Position(x, y);// �������λ��
		return pos;
	}

	// ��������Ա�trueΪ������falseΪŮ��
	public boolean initGenderRandom() {
		Random rd = new Random();// �����
		Boolean gender = rd.nextBoolean();
		return gender;
	}

	// ����������䣬������0~100֮�䣬������̬�ֲ�
	public int initAgeRandom() {
		Random rd = new Random();// �����
		// Math.sqrt(b)*random.nextGaussian()+a ��ֵΪa������Ϊb�������
		int age = (int) (Math.sqrt(50 / 3) * rd.nextGaussian() + 50);
		// ��̬�ֲ�,3��=50����=50������0~100�ĸ���Ϊ99.74%
		return age;
	}

	// ������ɴ������ֵ����Χ��5~10֮�䣬double��
	public double initSurvivabilityRandom() {
		Random rd = new Random();// �����
		double survivability = rd.nextDouble();// 0~1֮��
		survivability = 5 * survivability + 5;// 5~10֮��
		return survivability;
	}

	// ������ɿ��壬��ʼ�������ɼ���Ϊ10%
	public boolean initAntibodyRandom() {
		Random rd = new Random();// �����
		int probability = rd.nextInt(100);
		if (probability < 10) // 10%�ĸ��ʷ����п���
			return true;
		else
			return false;
	}

	// ��ʼ����ɥʬ��������������ֵ����ʱ�����������Ĭ��Ϊ10
	public int initBaseDamageRandom() {
		return 10;
	}

	// ��������ת��Ϊɥʬ
	public DeadPeople turnToDead(int row, NormalPeople np, List<People> plist,List<Cell> clist) {
		if(np.getPtype()==1){
			if (np.isAntibody()) // ������ڿ��壬������
				return null;
			else// ���û�п�����ת��
			{
				np.setPtype(2);// �����˵����ԣ���״̬��Ϊ���ɲ���
				int pid = np.getPid();
				Position ppos = np.getPpos();
				DeadPeople dp = new DeadPeople(pid, ppos);
				//plist.add(dp); // ����list����
				
				if(ppos != null)
				{
					Cell c = clist.get(ppos.getX()*row+ppos.getY());//Y���Ը�+X���õ�����
					c.setPtype(0);//id���䣬type��ɥʬ
					np.setPpos(null);//������ȡ��
					return dp;
				}
			}
		}
		return null;
		
		//Position nppos = np.getPpos();
		
		
	}

	public void turnToNormal(List<People> plist, DeadPeople dp, List<Cell> clist, int row) {
		//ɥʬת��Ϊ��������
		People p;
		int pid = dp.getPid();
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if( /*pid == p.getPid() &&*/ p.getClass() != dp.getClass() ){
				//�ָ���������,����ǰɥʬ��λ�ø�ֵ���ָ�����������
				//�о��������������⣡��������
				p.setPtype(1);
				Position ppos = dp.getPpos();
				p.setPpos(ppos);
				//����Cell������
				Cell c = clist.get(ppos.getX()*row+ppos.getY());//Y���Ը�+X���õ�����*********����
				c.setPtype(1);//id���䣬type����������
				
				break;
			}
		}
		
	}

	// ��������������
	public int countNormalPeople(List<People> plist) {
		int numberNormalPeople = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getClass() == NormalPeople.class&&p.getPtype()!=2) // ������ptype=1
				numberNormalPeople++;
		}
		return numberNormalPeople;
	}
	
	// ����ɥʬ����
	public int countDeadPeople(List<People> plist) {
		int numberDeadPeople = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getClass() == DeadPeople.class&&p.getPtype()!=2) // ɥʬ��ptypeΪ0
				numberDeadPeople++;
		}
		return numberDeadPeople;
	}
	
	// ������������
	public int countPeople(List<People> plist) {
		int numberPeople = countDeadPeople(plist) + countNormalPeople(plist);
		return numberPeople;
	}
 
 
	public double countAntibody(List<People> plist) {
		int numberAntibody = 0;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
				if(p.getClass() == NormalPeople.class&&p.getPtype()!=2){
					NormalPeople np = new NormalPeople();
					np = (NormalPeople) p;
					if(np.isAntibody())
						numberAntibody++;
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
			if(p.getClass()==NormalPeople.class&&p.getPtype()!=2){
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
			if(p.getClass()==NormalPeople.class&&p.getPtype()!=2){
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
			if(p.getClass()==NormalPeople.class&&p.getPtype()!=2){
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
			if(p.getClass()==NormalPeople.class&&p.getPtype()!=2){
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
			if(p.getClass()==NormalPeople.class&&p.getPtype()!=2){
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
			if(p.getClass()==NormalPeople.class&&p.getPtype()!=2){
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
			if(p.getClass()==NormalPeople.class&&p.getPtype()!=2){
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
