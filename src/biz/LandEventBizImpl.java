package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.NormalPeople;
import po.People;

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
			System.out.println(np.getSurvivability() + "ԭ����");
			survivability = np.getSurvivability();
			survivability += 0.2; // ���趨ͳһ����0.2�Ĵ��ֵ
			np.setSurvivability(survivability);
			System.out.println(np.getSurvivability() + "�ı��");
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

	public void beforeAttackEvent(List<People> plist, List<Cell> clist) {
		List<People> DeadList = new ArrayList<People>();
		int pid;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			People tempp = null;
			if (p.getPtype() != 2) {
				int ltype = findLandByPeople(p, clist);
				switch (ltype) {
				case 1:
					isShelter(p);
					break;
				case 5:
					if (p.getPtype() == 1)
						DeadList.add(p);// ɾ��������
					else if (p.getPtype() == 2) {
						pid = plist.get(plist.indexOf(p)).getPid();// �ҵ�ɥʬ��pid
						Iterator<People> pit = plist.iterator();
						while (pit.hasNext()) {
							tempp = pit.next();
							if (tempp.getPid() == pid) // �ҵ�pid��ͬ��
							{
								DeadList.add(tempp);//ɾ��ɥʬ����Ӧ��������
								break;
							}
						}
						DeadList.add(p);//ɾ��ɥʬ
					}
					break;
				default:
					break;
				}
			}
		}
		for(int i = 0;i<DeadList.size();i++){
			System.out.println(DeadList.get(i).getPid()+"����");
			plist.remove(DeadList.get(i));//ѭ��ɾ�����б�ǵ�
			
			}
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

}
