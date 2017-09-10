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

//�����¼��ӿ�ʵ��
public class NormalEventBizImpl implements NormalEventBiz {

	PeopleManageBiz pmb = new PeopleManageBizImpl(); // �ӿ���

	public void PregnantManage(List<People> plist, List<Cell> clist, int row, int col) {
		// ͳ�ﻳ���¼�
		List<People> newchildlist = new ArrayList<People>();
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() == 1) {
				if(p.getClass() == NormalPeople.class){
					NormalPeople np1 = (NormalPeople) p;
					//System.out.println("����1"+p.getClass()+"    " +p.getPtype()+"   "+p.getPid());
					if (np1.getPregnancyFlag()) {
						NormalPeople np2 = PregnantMatch(np1, plist, clist, row, col);
						if (np2 != null) {
							int num = pmb.countPeople(plist); // ��ȡ����Ԫ�ظ���
							People newp = CreateChild(np1, np2, num, clist, row, col);
							newchildlist.add(newp);
						}
					}
				}
				else{
					System.out.println("�������ͣ� " + p.getPtype()+" "+p.getClass());
				}
			}
		}
		if (newchildlist.size() != 0) {
			for (int i = 0; i < newchildlist.size(); i++)
				plist.add(newchildlist.get(i));
		}

	}

	public NormalPeople PregnantMatch(People p, List<People> plist, List<Cell> clist, int row, int col) {
		// ������������ˣ�����ƥ��������˵�ֵ
		// �ж��������������Χ��
		int x, y;
		Position ppos = p.getPpos();
		// Iterator<People> it = plist.iterator();
		List<People> markPList = new ArrayList<People>(); // ������¼������������

		// �� --1
		x = ppos.getX();
		y = ppos.getY();
		if ((y - 1) > -1) {
			y -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// �� --2
		x = ppos.getX();
		y = ppos.getY();
		if ((x - 1) > -1) {
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// �� --3
		x = ppos.getX();
		y = ppos.getY();
		if ((y + 1) < row) {
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// �� --4
		x = ppos.getX();
		y = ppos.getY();
		if ((x + 1) < col) {
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// ���� --5
		x = ppos.getX();
		y = ppos.getY();
		if (((y - 1) > -1) && ((x - 1) > -1)) {
			y -= 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// ���� --6
		x = ppos.getX();
		y = ppos.getY();
		if (((y + 1) < row) && ((x - 1) > -1)) {
			y += 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}

		// ���� --7
		x = ppos.getX();
		y = ppos.getY();
		if (((y + 1) < row) && ((x + 1) < col)) {
			y += 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// ���� --8
		x = ppos.getX();
		y = ppos.getY();
		if (((y - 1) > -1) && ((x + 1) < col)) {
			y -= 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}

		// �ж���֮����ʵmarkPList��������������������People����
		if (markPList.size() == 0) {
			return null;
		}
		int ram = markPList.size(); // ���ٸ���������������
		Random rd = new Random();
		int target = rd.nextInt(ram); // �����ж��ٸ��������������֮��ѡ�� 0-ram
		NormalPeople np = (NormalPeople) markPList.get(target);

		return np;
	}

	public void JudgeCell(int x, int y, List<People> markPList, List<People> plist) {
		// �����괫�����ж��Ƿ��ɻ�������
		int mark = -1;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() == 1) // һ��Ҫ�ֿ��жϣ���Ϊ�������ط��������ˣ�position�ж�Ϊnull��������Ч���½�
				if (p.getPpos().getX() == x && p.getPpos().getY() == y) {
					//!!!!!!!!!!!!!!!!!!!!!!!!
					System.out.println("����"+p.getClass()+"    " +p.getPtype()+"   "+p.getPid());
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
				System.out.println("���������� : "+p.getPtype());
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
		// ����ƥ���������������
		p1.setPregnancyFlag(false);
		p2.setPregnancyFlag(false);
		// �ۺ�һ��
		pid = num + 1;
		do {
			ppos = pmb.initPositionRandom(row, col);
			index = ppos.getY() * col + ppos.getX();// Y���Ը�+X���õ�clistλ��
			c = clist.get(index);
		} while (c.getPid() != -1);// �����ϲ������ˣ���������

		c.setPid(pid);
		c.setPtype(1);// ���Ӽ�����������

		gender = pmb.initGenderRandom();
		age = 0;
		// ����Ӥ������ݸ�ĸ�Ĵ������ֵ���ı�
		survivability = pmb.initSurvivabilityRandom() + p1.getSurvivability() * 0.1 + p2.getSurvivability() * 0.1;
		// ����Ӥ��Я�����������͸�ĸ�й�
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
		// �����¼�
		//ͨ���жϴ�������People�����ж���ɾȥһ����������������
		int pid;// �ݴ�pid
		People tempp;// �ݴ�ɥʬ��Ӧ��������
		if (p.getPtype() == 1)
			{deadList.add(p);// ɾ��������
			//System.out.println("������"+p.getPid());
			}
		else if (p.getPtype() == 0) {
			pid = plist.get(plist.indexOf(p)).getPid();// �ҵ�ɥʬ��pid
			Iterator<People> pit = plist.iterator();// ���ܵ�plist�ҵ���������������
			while (pit.hasNext()) {
				tempp = pit.next();
				
				if (tempp.getPid() == pid && tempp != p) // �ҵ�pid��ͬ��
					{deadList.add(tempp);// ɾ��ɥʬ����Ӧ��������
					//System.out.println("ɥʬ"+p.getPid());
					}
			}
			deadList.add(p);// ɾ��ɥʬ
		}
		

	}

	public int AntibodyRandomEvent() {
		// �ж����������Ƿ��п���
		Random rd = new Random();
		int ram = rd.nextInt(2);
		return ram;
	}

	 
	public void DestroyCell(int col, List<Cell> clist, People p) {
		if(p.getPtype()!= 2){   //��Ϊ��ת������λ���Ѿ���Ϊnull�˲���Ҫ�ı�
			int index = p.getPpos().getY() * col + p.getPpos().getX();
			Cell c = clist.get(index);
			c.setPid(-1);// �����ˣ���û��
			c.setPtype(-1);// �����ˣ���û��
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
	//����ȫ���˵�ͣ��ʱ��
	public void AdjustTrapYear(List<People> plist) {
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			int trapYear = p.getTrapYear();
			if(trapYear>0)
				p.setTrapYear(trapYear--);//�Լ�1
		}
	}
	
	
	//����ȫ���˵Ļ��б�ǩ
	public void AdjustPregnancy(List<People> plist){
		//ÿ��ˢ�µ�ͼʱ������������໳�б�ǩ
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
		//ÿ��ˢ�µ�ͼʱ�����������������
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
					deadPList.add(np);  //������ﵽ����Ҫ������������������list�д���
				}
				else{
					np.setAge(age);
				}
			}
		}
		//���������������������plist��ɾ��
		Iterator<People> it2 = deadPList.iterator();
		while(it2.hasNext()){
			p = it2.next();
			plist.remove(p);  //�������list��ɾ��
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
				//����ͣ��ʱ��
				int trapYear = p.getTrapYear();
				trapYear--;
				if(trapYear>0)
					p.setTrapYear(trapYear);//�Լ�1
				//����Ϊ�������໳�б�ǩ
				np = (NormalPeople)p;
				np.setPregnancyFlag(true);
				//���������˵�����
				int age = np.getAge();
				age++;
				if(age>99)
					deadlist.add(np);
				else
					np.setAge(age);
				//��������˵Ĵ������ֵ
				double survivability;
				np = (NormalPeople)p;
				survivability = np.getSurvivability();
				survivability += 0.1;
				np.setSurvivability(survivability);
			}	
		}
		//���������������������plist��ɾ��
		Iterator<People> it2 = deadlist.iterator();
		while(it2.hasNext()){
			p = it2.next();
			plist.remove(p);  //�������list��ɾ��
		}
	}
	
	
	
	/*
	 * public void AgeEvent(List<People> plist) {
	 * //ÿ��ˢ�µ�ʱ�򣬽�ÿ�������˵���������һ��(����Ҫ����ɾ��) Iterator<People> it = plist.iterator();
	 * while(it.hasNext()){ People p = it.next(); if( p.getPtype() == 1 ){
	 * NormalPeople np = (NormalPeople)p; int age = np.getAge(); np.setAge(age);
	 * } } }
	 */

}
