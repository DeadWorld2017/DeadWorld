package biz;

import java.util.Iterator;
import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//���߹���ӿ�ʵ��
public class ToolManageBizImpl implements ToolManageBiz {

	private double paramAttackByDoping = 1.5;

	// ��������ս�ܵ�ʱ��ѭ���ж��Ƿ���ը��
	public boolean isBomb(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			if (it.next().getTid() == 4) // ���ѭ���ҵ���ը��
				return true;
		}
		return false;
	}

	// ���뷢��ս����˫����ʵ��,����ʵ��������list��ɾ��
	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp) {
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
	}

	public boolean isShoes(int type) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean escapeShoes() {
		// TODO Auto-generated method stub
		return false;
	}
 
 
	public int addWeoponDamage(int attackValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void destoryBomb(NormalPeople np, List<Tool> tlist, int tid) {
		// TODO Auto-generated method stub

	}


	public boolean isWeopon(int ttype) {
		// TODO Auto-generated method stub
		return false;
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
