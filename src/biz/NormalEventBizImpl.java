package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.NormalPeople;
import po.People;
import po.Position;


//�����¼��ӿ�ʵ��
public class NormalEventBizImpl implements NormalEventBiz{

	PeopleManageBiz pmb = new PeopleManageBizImpl(); //�ӿ���
	
	public void PregnantManage(List<People> plist, List<Cell> clist, int row,
			int col) {
		// ͳ�ﻳ���¼�
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			if(p.getPtype() == 1){
				NormalPeople np1 = (NormalPeople)p;
				if(np1.getPregnancyFlag()){
					NormalPeople np2 = PregnantMatch(np1, plist, clist, row, col);
					if(np2 !=null){
						int num = pmb.countPeople(plist);                  //��ȡ����Ԫ�ظ���
						People newp = CreateChild(np1, np2, num, clist, row, col);
						plist.add(newp);					
					}
				}
				
			}
		}
		
		
	}

	public NormalPeople PregnantMatch(People p, List<People> plist, List<Cell> clist,
			int row, int col) {
		//������������ˣ�����ƥ��������˵�ֵ
		//�ж��������������Χ��
		int x, y;
		Position ppos = p.getPpos();
		//Iterator<People> it = plist.iterator();
		List<People> markPList = new ArrayList<People>();	//������¼������������
		
		//���㳤�ȣ�
		//�� --1
		x = ppos.getX();
		y = ppos.getY();
		if( (y - 1) > -1 ){
			y -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//��  --2
		x = ppos.getX();
		y = ppos.getY();
		if( (x - 1) > -1 ){
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//��  --3
		x = ppos.getX();
		y = ppos.getY();
		if( (y + 1) < col ){
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//�� --4
		x = ppos.getX();
		y = ppos.getY();
		if( (x + 1) < row ){
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//����  --5
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x - 1) > -1) ){
			y -= 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//����  --6
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < col) && ((x - 1) > -1) ){
			y += 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//����  --7
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < col) && ((x + 1) < row ) ){
			y += 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//����  --8
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x + 1) < row) ){
			y -= 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//�ж���֮����ʵmarkPList��������������������People����
		int ram = markPList.size();     //���ٸ���������������
		Random rd = new Random();
		int target = rd.nextInt(ram);   //�����ж��ٸ��������������֮��ѡ�� 0-ram
		NormalPeople np = (NormalPeople)markPList.get(target);
		
		return np;
	}
	
	public void JudgeCell(int x, int y, List<People> markPList, List<People> plist){
		//�����괫�����ж��ǹ���ɻ�������
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People people = it.next();
			if( people.getPpos().getX() == x && people.getPpos().getY() == y && people.getPtype() == 1 ){
				NormalPeople np = (NormalPeople)people;
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
		//����ƥ���������������
		p1.setPregnancyFlag(false);
		p2.setPregnancyFlag(false);
		//�ۺ�һ��
		pid = num+1;
		do{
			ppos = pmb.initPositionRandom(row, col);
			index=ppos.getY()*col+ppos.getX();//Y���Ը�+X���õ�clistλ��
			c = clist.get(index);
		}while(c.getPid()!=-1);//�����ϲ������ˣ���������	
		
		c.setPid(pid);
		c.setPtype(1);//���Ӽ�����������
		
		gender = pmb.initGenderRandom();
		age = pmb.initAgeRandom();
		survivability = pmb.initSurvivabilityRandom();
		antibody = pmb.initAntibodyRandom();
		pregnancyFlag = true;

		NormalPeople np = new NormalPeople(pid, ppos, gender, age, survivability, antibody, pregnancyFlag);
		
		return np;
	}

	public void DeadEvent(People p, List<People> plist) {
		//�����¼�
		plist.remove(p);
	}

	public int isAntibody(NormalPeople np) {
		//�ж����������Ƿ��п���
		if(np.isAntibody()) return 1;
		else return 0;
	}

	public void AgeEvent(List<People> plist) {
		//ÿ��ˢ�µ�ʱ�򣬽�ÿ�������˵���������һ��
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			if( p.getPtype() == 1 ){
				NormalPeople np = (NormalPeople)p;
				int age = np.getAge();
				np.setAge(age);
			}
		}
	}
	
	

}
