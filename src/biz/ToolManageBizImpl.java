package biz;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//���߹���ӿ�ʵ��
public class ToolManageBizImpl implements ToolManageBiz {

	//private double paramAttackByDoping = 1.5;

	@Override
	public void pickTool(List<People> plist,List<Cell> clist, List<Tool> tlist, int col) {
		int index;
		Cell c;
		Tool t = new Tool();
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				index = p.getPpos().getY() * col + p.getPpos().getX();// Y���Գ�+X���õ�����
				c = clist.get(index);
				if(c.getTid()==-1)
					continue;
				else
					if(compareTool(np,c.getttype()))//�е���������
						continue;
					else{
						Iterator<Tool> tit = tlist.iterator();
						while(tit.hasNext()){
							t = tit.next();
							if(t.getTid()==c.getTid())
								break;
						}
						np.getTlist().add(t);
						c.setTid(-1);
						c.setttype(-1);
						t.setUsage(true);
					}
			}
		}
		
	}
	public boolean compareTool(NormalPeople np,int ttype){	
		if(np.getTlist()!=null){
			Iterator<Tool> it = np.getTlist().iterator();
			while(it.hasNext()){
				Tool t = it.next();
				if(ttype==1||ttype==2||ttype==3)
					if(isWeopon(np)!=-1)
						return true;//�иõ���
					else
						return false;//û�иõ���
				else
					if(ttype==t.getTid())
						return true;//�е���
					else
						return false;//û�иõ���
			}
		}
		return false;
	}
	
	// ������ߵ�tid����ȫ��tlist������
	public void destoryTool(List<Tool> tlist, int tid) {
		tlist.remove(tid);
	}
	
	public int isWeopon(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();
			if (t.getTid() == 1|| t.getTid() == 2|| t.getTid() == 3) // ���ѭ���ҵ�������
				return t.getTid();
		}
		return -1;//û�ҵ�����-1
		 
	}
	
	
	
	// ��������ս�ܵ�ʱ��ѭ���ж��Ƿ���ը��
	public int isBomb(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();
			if (t.getTtype() == 4) // ���ѭ���ҵ���ը��
				return t.getTid();
		}
		return -1;//û�ҵ�����-1
	}
	
	// ���뷢��ս����˫����ʵ��,����ʵ��������list��ɾ��
	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp,List<Tool> tlist) {
		int tempid = dp.getPid();// �ݴ�ɥʬid
		plist.remove(plist.remove(plist.indexOf(np)));// ɾ����ը����������
		plist.remove(plist.remove(plist.indexOf(dp)));// ɾ��ɥʬ
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPid() == tempid) {
				plist.remove(plist.indexOf(p));// ɾ��ɥʬ��Ӧ������
				break;
			}
		}
		Iterator<Tool> tit = np.getTlist().iterator();//������������������Ӧ��tlist������
		while(tit.hasNext()){
			tlist.remove(tit.next().getTid());
		}
	}
	
	
	
	
	//����Ь
	public int isEscapeShoes(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();
			if (t.getTtype() == 5) // ���ѭ���ҵ�������Ь
				return t.getTid();
		}
		return -1;//û�ҵ�����-1
	}
	
	// ����Ь����������ж��Ƿ����ܣ��������true��ʲô�����������������false�����ִ�в���
	public boolean escapeShoeFunction(List<People> plist, NormalPeople np,List<Tool> tlist,int tid) {
		Random rd = new Random();
		boolean isEscape = rd.nextBoolean();//����ж��Ƿ�����
		Tool t = tlist.get(tid);
		if(isEscape){
			np.getTlist().remove(np.getTlist().indexOf(t));
			tlist.remove(tid);
			}
		return isEscape;
	}
 


	
	
	
	public int addWeoponDamage(int attackValue) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isTool() {
		// TODO Auto-generated method stub
		return false;
	}


	public void initTool(List<Tool> tlist) {
		
	}

	


	

 


	
	
	
	/*δ��ɵ��˷ܼ�
	 
	// �ж��Ƿ����˷ܼ�,��û�з���-1�������򷵻�tid
	public int isDoping(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();// �ݴ�
			if (t.getTid() == 7) // ���ѭ���ҵ����˷ܼ�
				return t.getTid();// ����tid
		}
		return -1;// û���򷵻�-1
	}

	@Override
	public double attackByDoping(double attackValue) {
		attackValue *= paramAttackByDoping;
		return attackValue;
	}
	
	@Override
	public void dopingDuration(NormalPeople np, List<Tool> tlist) {
		int tid = isDoping(np); //���tid
		Tool t = tlist.get(tid);//���ʵ��
		t.setDuration(t.getDuration()-1);//ÿ��һ��duration
		if(t.getDuration()<=0){
			tlist.remove(tlist.indexOf(t));//�ӵ��߼���tlist��ɾ��
			np.getTlist().remove(tlist.indexOf(t));//������ɾ��
		}
	}  
	*  
	*/	
		
	

}
