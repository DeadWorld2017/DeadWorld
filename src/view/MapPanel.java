package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;


import biz.AttackEventBiz;
import biz.AttackEventBizImpl;
import biz.LandEventBiz;
import biz.LandEventBizImpl;
import biz.ManageBiz;
import biz.ManageBizImpl;
import biz.MapManageBiz;
import biz.MapManageBizImpl;
import biz.NormalEventBiz;
import biz.NormalEventBizImpl;
import biz.PeopleManageBiz;
import biz.PeopleManageBizImpl;
import po.Cell;
import po.Land;
import po.People;
import po.Tool;

public class MapPanel extends JPanel implements Runnable {
	int row;// ��ͼ�ĸ�
	int col;// ��ͼ�ĳ�

	List<People> plist;// ���༯��
	List<Tool> tlist;// ��������
	List<Cell> clist;// ��ͼ���Ӽ���
	List<Land> llist;// ����
	int px[] = new int[200];
	int py[] = new int[200];

	JLabel numberPeoplelbl = new JLabel();
	JLabel numberNormalPeoplelbl = new JLabel();
	JLabel numberDeadPeoplelbl = new JLabel();

	PeopleManageBiz pmb = new PeopleManageBizImpl();
	MapManageBiz mmb = new MapManageBizImpl();
	ManageBiz mb = new ManageBizImpl();
	NormalEventBiz neb = new NormalEventBizImpl();
	LandEventBiz leb = new LandEventBizImpl();
	AttackEventBiz aeb = new AttackEventBizImpl();
	
	 public  boolean isChanging = true;

	public MapPanel(int row, int col, List<People> tempplist, List<Tool> temptlist, List<Cell> tempclist,
			List<Land> templlist) {
		super();
		this.row = row;
		this.col = col;
		this.plist = tempplist;
		this.tlist = temptlist;
		this.clist = tempclist;
		this.llist = templlist;
		setPeopleLabel();// ������ʾ����������Label
	}

	public void startWorld() {
		isChanging=true;
		mmb.initMapList(row, col, clist);
		mmb.initLand(row, col, llist, clist);
		mmb.initTool(row, col, tlist, clist);
		pmb.initNormalPeopleRandom(row, col, plist, clist);// ��ʼ��������������
		pmb.initDeadPeopleRandom(col, plist, clist);// ��������ת��Ϊɥʬ
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl);
		updateLand();// ������ʾ����������Label
		updateTool();// ������ʾ����������Label
		/*synchronized(this)
		{
			isChanging=true;
			this.notifyAll();
		}*/
	}

	public void nextWorld() {
		neb.AdjustNormalPeopleAttr(plist);// �������䣬ͣ��ʱ�䣬���б��
		mb.randomMove(row, col, plist, clist);// ����ƶ�
		leb.beforeAttackEvent(col, plist, clist);// ����ǰ�����¼����ӻ�������������
		// �����¼�
		aeb.ManageAttackEvent(plist, clist, row, col);
		neb.PregnantManage(plist, clist, row, col);// �жϻ���
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl);
		leb.afterAttackEvent(col, plist, clist);// ����������¼�������
	}
	public void stopWorld(){
		isChanging = false;
	}

	// ������ʾ������Label
	public void setPeopleLabel() {

		add(numberPeoplelbl);
		add(numberNormalPeoplelbl);
		add(numberDeadPeoplelbl);

		setLayout(null);
		numberPeoplelbl.setBounds(1020, 20, 300, 20);
		numberNormalPeoplelbl.setBounds(1020, 40, 300, 20);
		numberDeadPeoplelbl.setBounds(1020, 60, 300, 20);

		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl);

	}

	public void updatePeople(JLabel p, JLabel np, JLabel dp) {
		int numberPeople = pmb.countPeople(plist);// ����������
		int numberNormalPeople = pmb.countNormalPeople(plist);// ����������
		int numberDeadPeople = pmb.countDeadPeople(plist);// ɥʬ����

		p.setText("����������" + numberPeople);
		np.setText("������������" + numberNormalPeople);
		dp.setText("ɥʬ������" + numberDeadPeople);

	}

	public void updateLand() {
		int countShelter = 0;// 1,�ӻ���
		int countRadient = 0;// 2,�����
		int coutSwampland = 0;// 3,�����
		int coutDMRiver = 0;// 4,��ĸ��
		int coutDeathtrap = 0;// 5����������
		int coutTrappedLand = 0;// 6������

		Iterator<Land> it = llist.iterator();
		while (it.hasNext()) {
			switch (it.next().getLtype()) {
			case 1:
				countShelter++;
				break;
			case 2:
				countRadient++;
				break;
			case 3:
				coutSwampland++;
				break;
			case 4:
				coutDMRiver++;
				break;
			case 5:
				coutDeathtrap++;
				break;
			case 6:
				coutTrappedLand++;
				break;
			}
		}

		JLabel numberShelterlbl = new JLabel("�ӻ���������" + countShelter);
		JLabel numberRadientlbl = new JLabel("�����������" + countRadient);
		JLabel numberSwamplandlbl = new JLabel("�����������" + coutSwampland);
		JLabel numberDMRiverlbl = new JLabel("��ĸ��������" + coutDMRiver);
		JLabel numberDeathtraplbl = new JLabel("��������������" + coutDeathtrap);
		JLabel numberTrappedLandlbl = new JLabel("����������" + coutTrappedLand);

		add(numberShelterlbl);
		add(numberRadientlbl);
		add(numberSwamplandlbl);
		add(numberDMRiverlbl);
		add(numberDeathtraplbl);
		add(numberTrappedLandlbl);

		setLayout(null);
		numberShelterlbl.setBounds(1020, 100, 300, 20);
		numberRadientlbl.setBounds(1020, 120, 300, 20);
		numberSwamplandlbl.setBounds(1020, 140, 300, 20);
		numberDMRiverlbl.setBounds(1020, 160, 300, 20);
		numberDeathtraplbl.setBounds(1020, 180, 300, 20);
		numberTrappedLandlbl.setBounds(1020, 200, 300, 20);
	}

	private void updateTool() {
		int countKnife = 0;// 1,��
		int countGun = 0;// 2,ǹ
		int coutBazooka = 0;// 3,���Ͳ
		int coutBomb = 0;// 4,��ɱ��
		int coutEscapeShoes = 0;// 5������Ь

		Iterator<Tool> it = tlist.iterator();
		while (it.hasNext()) {
			switch (it.next().getTtype()) {
			case 1:
				countKnife++;
				break;
			case 2:
				countGun++;
				break;
			case 3:
				coutBazooka++;
				break;
			case 4:
				coutBomb++;
				break;
			case 5:
				coutEscapeShoes++;
				break;
			}
		}

		JLabel numberKnifelbl = new JLabel("��������" + countKnife);
		JLabel numberGunlbl = new JLabel("ǹ������" + countGun);
		JLabel numberBazookalbl = new JLabel("���Ͳ������" + coutBazooka);
		JLabel numberBomblbl = new JLabel("��ɱ��������" + coutBomb);
		JLabel numberEscapeShoeslbl = new JLabel("����Ь������" + coutEscapeShoes);

		add(numberKnifelbl);
		add(numberGunlbl);
		add(numberBazookalbl);
		add(numberBomblbl);
		add(numberEscapeShoeslbl);

		setLayout(null);
		numberKnifelbl.setBounds(1020, 260, 300, 20);
		numberGunlbl.setBounds(1020, 280, 300, 20);
		numberBazookalbl.setBounds(1020, 300, 300, 20);
		numberBomblbl.setBounds(1020, 320, 300, 20);
		numberEscapeShoeslbl.setBounds(1020, 340, 300, 20);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paintMap(g);//����ͼ���ӵ���������䣬��ʱ����Ҫ
		paintLand(g);// �����ηֲ�
		paintTool(g);// �����߷ֲ�
		paintPeople(g);// ������ֲ�
	}

	/*
	 * ����ͼ���ӵ��� private void paintMap(Graphics g) { for (int i = 0; i < row; i++)
	 * { for (int j = 0; j < col; j++) { // g.setColor(Color.GRAY); //
	 * g.setColor(Color.black); g.fillRect(j * 10, i * 10, 10, 10);
	 * g.setColor(Color.red); g.drawRect(j * 10, i * 10, 10, 10); } } }
	 */

	private void paintTool(Graphics g) {
		Iterator<Tool> it = tlist.iterator();
		while (it.hasNext()) {
			Tool t = it.next();// �洢it.next()��ֵ����ֹ��Ծ
			g.setColor(Color.yellow);
			g.fillRect(t.getTpos().getX() * 10, t.getTpos().getY() * 10, 5, 5);

		}

	}

	// ������ֲ�����
	public void paintPeople(Graphics g) {
		Color colorNormalPeople = new Color(160, 239, 47);// ��ɫ
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();// �洢it.next()��ֵ����ֹ��Ծ
			if (p.getPtype() == 1) // ���������ˣ���ʾ��ɫ
			{
				g.setColor(colorNormalPeople);
				// g.fillOval(p.getPpos().getX() * 10, p.getPpos().getY() * 10,
				// 10, 10);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			} else if (p.getPtype() == 0) // ����ɥʬ����ʾ��ɫ
			{
				g.setColor(Color.red);
				// g.fillOval(p.getPpos().getX() * 10, p.getPpos().getY() * 10,
				// 10, 10);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			}
		}
	}

	// �����ηֲ������䣩
	public void paintLand(Graphics g) {
		Iterator<Land> it = llist.iterator();
		Color colorShelter = new Color(252, 230, 201);// ����ɫ
		Color colorRadient = new Color(255, 255, 0);// ��ɫ
		Color colorSwampland = new Color(240, 230, 140);// �ƺ�ɫ
		Color colorDMRiver = new Color(176, 224, 230);// ǳ����
		Color colorDeathtrap = new Color(255, 192, 203);// ��ɫ
		Color colorTrappedLand = new Color(220, 220, 220);// gainsboro
		while (it.hasNext()) {
			Land l = it.next();
			switch (l.getLtype()) {
			case 1:// �ӻ�����ɫ
				g.setColor(colorShelter);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 2:// ����ػ�ɫ
				g.setColor(colorRadient);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 3:// ����غ�ɫ
				g.setColor(colorSwampland);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 4:// ��ĸ�ӷ�ɫ
				g.setColor(colorDMRiver);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 5:// ���������ɫ
				g.setColor(colorDeathtrap);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 6:// �����ɫ
				g.setColor(colorTrappedLand);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			}

		}
	}
	public void sleep(int x) {
		try {
			Thread.sleep(80 * x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public void run() {
		{
			while(isChanging)
			{
				//synchronized(this)
				/*{
					while(true)
					{
						
						try
						{
							this.wait();
						}catch(InterruptedException e)
						{
							e.printStackTrace();
						}
					}*/
					
					sleep(3);
					
					neb.AdjustNormalPeopleAttr(plist);// �������䣬ͣ��ʱ�䣬���б��
					mb.randomMove(row, col, plist, clist);// ����ƶ�
					leb.beforeAttackEvent(col, plist, clist);// ����ǰ�����¼����ӻ�������������
					// �����¼�
					aeb.ManageAttackEvent(plist, clist, row, col);
					neb.PregnantManage(plist, clist, row, col);// �жϻ���
					updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl);
					leb.afterAttackEvent(col, plist, clist);// ����������¼�������
					repaint();
				//}
			}
		}

	}

}
