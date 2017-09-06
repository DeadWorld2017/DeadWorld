package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;
import po.Tool;

//���㹥�����ӿ�ʵ��
public class AttackEventBizImpl implements AttackEventBiz{
	
	double aggressivity = 0;
	double childAttack = 5;
	double adultAttack = 10;
	double oldAttack = 4;
	
	LandEventBiz leb = new LandEventBizImpl();
	WorldEventBiz web = new WorldEventBizImpl();
	NormalEventBiz neb = new NormalEventBizImpl();
	PeopleManageBiz pmb = new PeopleManageBizImpl();
	
	public double CalculateAttack(People p, List<Cell> clist) {
		// ���㴫����󹥻��������ع�����ֵ
		
		int ttype;
		int ltype;
		Tool tool;
		Cell cell;
		if(p.getPtype() == 1){
			//��������
			NormalPeople np = (NormalPeople)p;
			
			//�Ӵ������ֵ
			aggressivity += np.getSurvivability();
			
			//����������
			if(np.getAge() >= 0 && np.getAge() < 18)
				aggressivity += childAttack;
			if(np.getAge() >= 18 && np.getAge() < 70)
				aggressivity += adultAttack;
			if(np.getAge() >=70 && np.getAge() < 100)
				aggressivity += oldAttack;
			
			//��������ӳ�
			List<Tool> tlist = np.getTlist();
			if(tlist == null){
				aggressivity += 0;
			}
			else{
				Iterator<Tool> it = tlist.iterator();
				while(it.hasNext()){
					tool = it.next();
					ttype = tool.getTtype();
					if(ttype == 1){
						aggressivity += 2;
						break;
					}
					else if(ttype == 2){
						aggressivity += 5;
						break;
					}
					else if(ttype == 3){
						aggressivity += 8;
						break;
					}
					else aggressivity += 0;
				}
			}
			
			//���ⲿ����������
			int x = np.getPpos().getX();
			int y = np.getPpos().getY();
			
			Iterator<Cell> it = clist.iterator();
			while(it.hasNext()){
				cell = it.next();
				if( x == cell.getCpos().getX() && y == cell.getCpos().getY() ){
					ltype = cell.getltype();
					if(ltype == 2){
						//����Ƿ����
						aggressivity += leb.isRadient(np);
					}
					else if(ltype == 3){
						//����������
						aggressivity += leb.isSwampland(np);
					}
					else{
						//����������ڵĻ���û�е��ε�
						aggressivity += 0;
					}
				}
			}
		}
		if(p.getPtype() == 0){
			//ɥʬ
			DeadPeople dp = (DeadPeople)p;
			
			//�ӿ�ֵ
			aggressivity += dp.getBaseDamage();
			
			//���ⲿ����������
			int x = dp.getPpos().getX();
			int y = dp.getPpos().getY();
			
			Iterator<Cell> it = clist.iterator();
			while(it.hasNext()){
				cell = it.next();
				if( x == cell.getCpos().getX() && y == cell.getCpos().getY() ){
					ltype = cell.getltype();
					if(ltype == 2){
						//����Ƿ����
						aggressivity += leb.isRadient(dp);
					}
					else if(ltype == 3){
						//����������
						aggressivity += leb.isSwampland(dp);
					}
					else{
						//����������ڵĻ���û�е��ε�
						aggressivity += 0;
					}
				}
			}
		}
		
		return aggressivity;
		
		
	}

	public void ManageAttackEvent(List<People> plist, List<Cell> clist,
			int row, int col) {
		//ͳ�﹥���¼�
		People p;
		NormalPeople np;
		DeadPeople dp;
		List<People> deadList = new ArrayList<People>();   //�����������������
		List<People> fitPList;								//����������Ӧɥʬ������������
		List<People> newList = new ArrayList<People>();    //�������������ɥʬ
		
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if(p.getPtype() == 0){   //��Ϊɥʬ
				dp = (DeadPeople)p;
				fitPList = JudgeAttackEvent(dp, clist, plist, row, col); //��������������
				if(fitPList != null){
					//��������������,���ɹ����¼�
					Iterator<People> it2 = fitPList.iterator();
					while(it2.hasNext()){
						np = (NormalPeople)it2.next();
						AttackEvent(np, dp, plist, clist, deadList, newList, row, col);
					}
				}
			}
		}
		if(deadList != null){
			Iterator<People> it3 = deadList.iterator(); 
			while(it3.hasNext()){
				p = it3.next();
				plist.remove(p);
				neb.DestroyCell(col, clist, p);
			}
		}
		if(newList != null){
			Iterator<People> it4 = newList.iterator(); 
			while(it4.hasNext()){
				p = it4.next();
				plist.add(p);
			}
		}
		
		
	}

	public List<People> JudgeAttackEvent(DeadPeople dp, List<Cell> clist, List<People> plist, 
			int row, int col) {
		//����Ӧ��ɥʬ���������ж��������ڵ���������
		Position ppos = dp.getPpos();
		List<People> fitPList = new ArrayList<People>();        //������������List
		int x = ppos.getX();
		int y = ppos.getY();
		
		
		//�ж���ɥʬλ�������Ƿ��������
		//�� --1
		if( (y - 1) > -1 ){
			y -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//��  --2
		x = ppos.getX();
		y = ppos.getY();
		if( (x - 1) > -1 ){
			x -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//��  --3
		x = ppos.getX();
		y = ppos.getY();
		if( (y + 1) < row ){
			y += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//�� --4
		x = ppos.getX();
		y = ppos.getY();
		if( (x + 1) < col ){
			y += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//����  --5
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x - 1) > -1) ){
			y -= 1;
			x -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//����  --6
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < row) && ((x - 1) > -1) ){
			y += 1;
			x -= 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//����  --7
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < row) && ((x + 1) < col ) ){
			y += 1;
			x += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		//����  --8
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x + 1) < col) ){
			y -= 1;
			x += 1;
			JudgeNormalCell(x, y, plist, fitPList);
		}
		
		return fitPList;
	}

	public void AttackEvent(NormalPeople np, DeadPeople dp, List<People> plist, List<Cell> clist,
			List<People> deadList, List<People> newList, int row, int col) {
		//һ��һʵ�ֹ����¼�
		
		//���������������������
		double NPAttackValue = CalculateAttack(np, clist);
		//�����ⲿ�¼�������
		NPAttackValue = web.attackByWorldEvent(NPAttackValue);
		
		//����ɥʬ����������
		double DPAttackValue = CalculateAttack(dp, clist);
		
		//����ʤ��
		//������ʤ��
		if(NPAttackValue > DPAttackValue){
			if( np.isAntibody() == true && dp.getLevel() == 1 ){
				int ram = neb.AntibodyRandomEvent();
				if( ram == 1 ){
					//�������Ա�ת��������
					pmb.turnToNormal(plist, dp, clist, col);
					//��ת��Ϊ���������ɥʬ����deadList��
					deadList.add(dp);
				}
				if( ram == 0){
					//���������Ա�ת��Ϊ����
					//ɥʬ����
					neb.DeadEvent(dp, plist, deadList);
				}
			}
			else{
				//�����಻Я�����壬��ɥʬֱ������
				neb.DeadEvent(dp, plist, deadList);
			}
		}
		//��ɥʬʤ��
		if( NPAttackValue < DPAttackValue ){
			if( np.isAntibody() == true ){
				//������Я��������ֱ������
				neb.DeadEvent(dp, plist, deadList);
			}
			else{
				//������û��Я��������ת��Ϊɥʬ
				//*************************************�������Ծͱ������
				int id = plist.get(plist.indexOf(np)).getPid();
				DeadPeople newdp = pmb.turnToDead(col, id, plist, clist);
				if(newdp != null)
					newList.add(newdp);
			}
		}
		
	}

	public void JudgeNormalCell(int x, int y, List<People> plist, List<People> fitPList) {
		//�ж�Cell���Ƿ����NormalPeople
		People p;
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if( p.getPtype() == 1 ){
				if( x == p.getPpos().getX() && y == p.getPpos().getY() ){
					//��������λ�õ���������
					fitPList.add(p);
				}
			}
		}
		
	}
	
	
}
