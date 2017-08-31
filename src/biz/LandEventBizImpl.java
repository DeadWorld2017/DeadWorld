package biz;

import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.NormalPeople;
import po.People;

//�����¼��ӿ�ʵ��
public class LandEventBizImpl implements LandEventBiz {

	public int findLandByPeople(People p, List<Cell> clist) {
		//ͨ�������People���ͣ��ҵ���������ڵ�λ�ã���������
		Iterator<Cell> it = clist.iterator();
		Cell cell;
		int ltype;
		int x = p.getPpos().getX();
		int y = p.getPpos().getY();
		while(it.hasNext()){
			cell = it.next();
			if(cell.getCpos().getX() == x && cell.getCpos().getY() == y){
				ltype = cell.getltype();
				return ltype;
			}
		}
		return -1;  //��û���ҵ��ͷ���-1��ʾ�˴�û����ص���
	}
	
	
	public int isShelter(People p) {
		//������λ��Ϊ�ӻ�����ʱ��
		double survivability;
		if(p.isPtype()== true && p.isState() == true){
			//���ж�Ϊ�ɲ��������˵�ʱ��(����)
			NormalPeople np = (NormalPeople)p;
			survivability = np.getSurvivability();
			survivability += 3;                          //���趨ͳһ����3�Ĵ��ֵ
			np.setSurvivability(survivability);
		}
		return 0;
	}

	public int isDeathtrap() {
		// ������λ��Ϊ���������ʱ��
		return 1;
	}

	public int isRadient(People p) {
		// ������λ��Ϊ����ص�ʱ��
		//�������๥��������
		if(p.isPtype() == true && p.isState() == true){
			return 2;
		}
		//ɥʬ��������ǿ
		else if(p.isPtype() == false && p.isState() == true){
			return -2;
		}
		//���ɲ�����
		else return 0;
	}

	public int isSwampland(People p) {
		// ������λ��Ϊ����ص�ʱ��
		//��Ϊɥʬ��ʱ��
		if(p.isPtype() == false && p.isState() == true){
			return 2;
		}
		//���ɲ�����
		else return 0;
	}

	public int isDMRiver(People p) {
		// ������λ��Ϊ��ĸ�ӵ�ʱ��
		//��Ϊ�ɲ����������ӵ���������
		if(p.isPtype() == true && p.isState() == true){
			return 50; //�ٷ�֮50���м���
		}
		else return 0;
	}

	public int isTrappedLand(People p) {
		// ������λ��Ϊ�����ʱ��
		//�����ֵ�ͣ��ʱ���Ϊ3
		if(p.isState() == true){
			p.setTrapYear(3);
		}
		return 0;
	}
	
	public int ManageLandEvent(People p, List<Cell> clist){
		//ͨ����ȡ��land������������Ӧ���¼�����
		//���ر�ǩ
		int tag=0;
		//��ȡland������
		int ltype = findLandByPeople(p, clist);
		switch(ltype){
			case 1: tag = isShelter(p); break;
			case 2: tag = isRadient(p); break;
			case 3:	tag = isSwampland(p); break;
			case 4: tag = isDMRiver(p); break;
			case 5: tag = isDeathtrap(); break;
			case 6: tag = isTrappedLand(p); break;
			default: break;
		}
		return tag;
	}

}
