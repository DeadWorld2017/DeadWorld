package biz;

import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//���㹥�����ӿ�ʵ��
public class AttackEventBizImpl implements AttackEventBiz{
	
	double aggressivity = 0;
	double childAttack = 5;
	double adultAttack = 10;
	double oldAttack = 4;
	
	LandEventBiz leb = new LandEventBizImpl();
	
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
		
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			p = it.next();
			if(p.getPtype() == 0){   //��Ϊɥʬ
				dp = (DeadPeople)p;
			}
		}
		
	}

	public List<People> JudgeAttackEvent(DeadPeople dp, List<Cell> clist, List<People> plist, 
			int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	public void AttckEvent(NormalPeople np, DeadPeople dp, List<Cell> clist,
			List<People> deadList) {
		// TODO Auto-generated method stub
		
	}

	public void JudgeNormalCell(int x, int y, People p) {
		// TODO Auto-generated method stub
		
	}
	
	
}
