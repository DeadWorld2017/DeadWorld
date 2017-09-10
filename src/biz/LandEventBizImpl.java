

package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.NormalPeople;
import po.People;
import po.Tool;


//��ĸ��4  ��ʱ�޷�ʵ�֣��ȸ���


//�����¼��ӿ�ʵ��
public class LandEventBizImpl implements LandEventBiz {

	NormalEventBiz neb = new NormalEventBizImpl();

	public int findLandByPeople(People p, List<Cell> clist) {
		// ͨ�������People���ͣ��ҵ���������ڵ�λ�ã���������
		Iterator<Cell> it = clist.iterator();
		Cell cell;
		int ltype;
		int x = p.getPpos().getX();
		int y = p.getPpos().getY();
		while (it.hasNext()) {
			cell = it.next();
			if (cell.getCpos().getX() == x && cell.getCpos().getY() == y) {
				ltype = cell.getltype();
				return ltype;
			}
		}
		return -1; // ��û���ҵ��ͷ���-1��ʾ�˴�û����ص���
	}

	public int isShelter(People p) {
		// ������λ��Ϊ�ӻ�����ʱ������1
		double survivability;
		if (p.getPtype() == 1) {
			// ���ж�Ϊ�ɲ��������˵�ʱ��(����)
			NormalPeople np = (NormalPeople) p;
			//System.out.println(np.getSurvivability() + "ԭ����");
			survivability = np.getSurvivability();
			survivability += 0.2; // ���趨ͳһ����0.2�Ĵ��ֵ
			np.setSurvivability(survivability);
			//System.out.println(np.getSurvivability() + "�ı��");
		}
		return 0;
	}

	public int isDeathtrap() {
		// ������λ��Ϊ���������ʱ������5
		return 1;
	}

	public int isRadient(People p) {
		// ������λ��Ϊ����ص�ʱ������2
		// �������๥��������
		if (p.getPtype() == 1) {
			return 2;
		}
		// ɥʬ��������ǿ
		else if (p.getPtype() == 0) {
			return -2;
		}
		// ���ɲ�����
		else
			return 0;
	}

	public int isSwampland(People p) {
		// ������λ��Ϊ����ص�ʱ������3
		// ��Ϊɥʬ��ʱ��
		if (p.getPtype() == 0) {
			return 2;
		}
		// ���ɲ�����
		else
			return 0;
	}

	public int isDMRiver(People p) {
		// ������λ��Ϊ��ĸ�ӵ�ʱ��,����4
		// ��Ϊ�ɲ����������ӵ���������
		if (p.getPtype() == 1) {
			return 50; // �ٷ�֮50���м���
		} else
			return 0;
	}

	public int isTrappedLand(People p) {
		// ������λ��Ϊ�����ʱ������6
		// �����ֵ�ͣ��ʱ���Ϊ3
		if (p.getPtype() == 0 || p.getPtype() == 1) {
			p.setTrapYear(3);
		}
		return 0;
	}
	public int ManageLandEvent(List<People> plist, List<Cell> clist) {
		// ͨ����ȡ��land������������Ӧ���¼�����
		// ���ر�ǩ
		int tag = 0;
		// ��ȡland������
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			int ltype = findLandByPeople(p, clist);
			switch (ltype) {
			case 1:
				tag = isShelter(p);
				break;
			case 2:
				tag = isRadient(p);
				break;
			case 3:
				tag = isSwampland(p);
				break;
			case 4:
				tag = isDMRiver(p);
				break;
			case 5:
				tag = isDeathtrap();
				break;
			case 6:
				tag = isTrappedLand(p);
				break;
			default:
				break;
			}
		}
		return tag;
	}

	 
	public void afterAttackEvent(int col, List<People> plist,List<Cell> clist) {
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() != 2) {
				int ltype = findLandByPeople(p, clist);
				switch (ltype) {
				case 6://����
					isTrappedLand(p);
					break;
				default:
					break;
				}
			}
		}
		
	}

	
	public void beforeAttackEvent(int col, List<People> plist, List<Cell> clist, List<Tool> tlist) {
		List<People> deadlist = new ArrayList<People>();
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() != 2) {
				int ltype = findLandByPeople(p, clist);
				switch (ltype) {
				case 1:
					isShelter(p);
					break;
				case 5:
					neb.DeadEvent(p, plist, deadlist);	
					break;
				default:
					break;
				}
			}
		}
		//��ȫɾ��
		Iterator<People> deadit = deadlist.iterator();
		while(deadit.hasNext()){
			People dead = deadit.next();
			plist.remove(dead);// ѭ��ɾ�����б�ǵ�
			neb.DestroyCell(col,clist,dead);//���������ϵ�����
			if(dead.getPtype()==1){
				neb.DestroyTool(tlist, dead);
				}
		}
		deadlist.clear();
		
	}

}
