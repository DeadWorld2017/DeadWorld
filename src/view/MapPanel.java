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
import biz.ToolManageBiz;
import biz.ToolManageBizImpl;
import po.Cell;
import po.Land;
import po.People;
import po.Tool;

public class MapPanel extends JPanel implements Runnable {
	int row;// ��ͼ�ĳ������ں�����������������ģ�
	int col;// ��ͼ�ĸߣ����ں�����룩

	List<People> plist;// ���༯��
	List<Tool> tlist;// ��������
	List<Cell> clist;// ��ͼ���Ӽ���
	List<Land> llist;// ����
	int px[] = new int[200];
	int py[] = new int[200];
	int year = 0;

	JLabel yearlbl = new JLabel();
	JLabel numberPeoplelbl = new JLabel();
	JLabel numberNormalPeoplelbl = new JLabel();
	JLabel numberDeadPeoplelbl = new JLabel();
	JLabel numberAntibodylbl = new JLabel();
	JLabel numberManlbl = new JLabel();
	JLabel numberWomanlbl = new JLabel();
	JLabel number0to19lbl = new JLabel();
	JLabel number20to39lbl = new JLabel();
	JLabel number40to59lbl = new JLabel();
	JLabel number60to79lbl = new JLabel();
	JLabel number80to99lbl = new JLabel();

	JLabel numberKnifelbl = new JLabel();
	JLabel numberGunlbl = new JLabel();
	JLabel numberBazookalbl = new JLabel();
	JLabel numberBomblbl = new JLabel();
	JLabel numberEscapeShoeslbl = new JLabel();

	PeopleManageBiz pmb = new PeopleManageBizImpl();
	MapManageBiz mmb = new MapManageBizImpl();
	ManageBiz mb = new ManageBizImpl();
	NormalEventBiz neb = new NormalEventBizImpl();
	LandEventBiz leb = new LandEventBizImpl();
	AttackEventBiz aeb = new AttackEventBizImpl();
	ToolManageBiz tmb = new ToolManageBizImpl();

	public boolean isChanging = true;

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
		setYearLabel();// ���
		setToolLabel();// ����

		isChanging = true;
		mmb.initMapList(row, col, clist);
		mmb.initLand(row, col, llist, clist);
		mmb.initTool(row, col, tlist, clist);
		pmb.initNormalPeopleRandom(row, col, plist, clist);// ��ʼ��������������
		pmb.initDeadPeopleRandom(col, plist, clist);// ��������ת��Ϊɥʬ
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
				numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);
		updateLand();// ������ʾ����������Label
		updateTool(numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl);// ������ʾ����������Label
		
	}

	public void startWorld() {
		isChanging = true;
		mmb.initMapList(row, col, clist);
		mmb.initLand(row, col, llist, clist);
		mmb.initTool(row, col, tlist, clist);
		pmb.initNormalPeopleRandom(row, col, plist, clist);// ��ʼ��������������
		pmb.initDeadPeopleRandom(col, plist, clist);// ��������ת��Ϊɥʬ
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
				numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);
		updateLand();// ������ʾ����������Label
		updateTool(numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl);// ������ʾ����������Label

		/*
		 * synchronized(this) { isChanging=true; this.notifyAll(); }
		 */
	}

	public void nextWorld() {
		neb.AdjustNormalPeopleAttr(plist);// �������䣬ͣ��ʱ�䣬���б��
		mb.randomMove(row, col, plist, clist);// ����ƶ�
		leb.beforeAttackEvent(col, plist, clist, tlist);// ����ǰ�����¼����ӻ�������������
		// �����¼�
		aeb.ManageAttackEvent(plist, clist, row, col, tlist);
		neb.PregnantManage(plist, clist, row, col);// �жϻ���
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, number0to19lbl, number0to19lbl,
				number0to19lbl, number0to19lbl, number0to19lbl, number0to19lbl, number0to19lbl, number0to19lbl);
		leb.afterAttackEvent(col, plist, clist);// ����������¼�������
	}

	public void stopWorld() {
		isChanging = false;
	}

	// ������ʾ������Label
	public void setPeopleLabel() {

		add(numberPeoplelbl);
		add(numberNormalPeoplelbl);
		add(numberDeadPeoplelbl);
		add(numberAntibodylbl);
		add(numberManlbl);
		add(numberWomanlbl);
		add(number0to19lbl);
		add(number20to39lbl);
		add(number40to59lbl);
		add(number60to79lbl);
		add(number80to99lbl);

		setLayout(null);
		numberPeoplelbl.setBounds(1020, 40, 300, 20);
		numberNormalPeoplelbl.setBounds(1020, 60, 300, 20);
		numberDeadPeoplelbl.setBounds(1020, 80, 300, 20);
		numberAntibodylbl.setBounds(1020, 100, 300, 20);
		numberManlbl.setBounds(1020, 120, 300, 20);
		numberWomanlbl.setBounds(1020, 140, 300, 20);
		number0to19lbl.setBounds(1020, 160, 300, 20);
		number20to39lbl.setBounds(1020, 180, 300, 20);
		number40to59lbl.setBounds(1020, 200, 300, 20);
		number60to79lbl.setBounds(1020, 220, 300, 20);
		number80to99lbl.setBounds(1020, 240, 300, 20);

		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
				numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);

	}

	private void updatePeople(JLabel p, JLabel np, JLabel dp, JLabel antibody, JLabel man, JLabel women,
			JLabel number0to19lbl, JLabel number20to39lbl, JLabel number40to59lbl, JLabel number60to79lbl,
			JLabel number80to99lbl) {
		int numberPeople = pmb.countPeople(plist);// ����������
		int numberNormalPeople = pmb.countNormalPeople(plist);// ����������
		int numberDeadPeople = pmb.countDeadPeople(plist);// ɥʬ����
		double numberAntibody = pmb.countAntibody(plist);
		double numberMan = pmb.countMan(plist);
		double numberWoman = pmb.countWomen(plist);
		double number0to20 = pmb.count0to19(plist);
		double number20to40 = pmb.count20to39(plist);
		double number40to60 = pmb.count40to59(plist);
		double number60to80 = pmb.count60to79(plist);
		double number80to100 = pmb.count80to99(plist);

		p.setText("����������" + numberPeople);
		np.setText("������������" + numberNormalPeople);
		dp.setText("ɥʬ������" + numberDeadPeople);
		antibody.setText("���������"+ numberAntibody);
		man.setText("���Ա�����"+numberMan);
		women.setText("Ů�Ա�����"+numberWoman);
		number0to19lbl.setText("0-19�������"+number0to20);
		number20to39lbl.setText("20-39�������"+number20to40);
		number40to59lbl.setText("40-59�������"+number40to60);
		number60to79lbl.setText("60-79�������"+number60to80);
		number80to99lbl.setText("80-99�������"+number80to100);


	}

	public void setYearLabel() {
		yearlbl = new JLabel("��ݣ�" + year);
		add(yearlbl);
		yearlbl.setBounds(1020, 0, 300, 20);

	}

	public void updataYear(JLabel yearlbl) {
		year++;
		yearlbl.setText("��ݣ�" + year);
	}

	public void setToolLabel() {
		add(numberKnifelbl);
		add(numberGunlbl);
		add(numberBazookalbl);
		add(numberBomblbl);
		add(numberEscapeShoeslbl);

		setLayout(null);
		numberKnifelbl.setBounds(1020, 280, 300, 20);
		numberGunlbl.setBounds(1020, 300, 300, 20);
		numberBazookalbl.setBounds(1020, 320, 300, 20);
		numberBomblbl.setBounds(1020, 340, 340, 20);
		numberEscapeShoeslbl.setBounds(1020, 360, 300, 20);

		updateTool(numberKnifelbl, numberGunlbl, numberBazookalbl, numberBomblbl, numberEscapeShoeslbl);
	}

	private void updateTool(JLabel numberKnifelbl2, JLabel numberGunlbl2, JLabel numberBazookalbl2,
			JLabel numberBomblbl2, JLabel numberEscapeShoeslbl2) {
		int countKnife = 0;// 1,��
		int countGun = 0;// 2,ǹ
		int coutBazooka = 0;// 3,���Ͳ
		int coutBomb = 0;// 4,��ɱ��
		int coutEscapeShoes = 0;// 5������Ь

		int countKnifeByPeople = 0;// 1,��
		int countGunByPeople = 0;// 2,ǹ
		int coutBazookaByPeople = 0;// 3,���Ͳ
		int coutBombByPeople = 0;// 4,��ɱ��
		int coutEscapeShoesByPeople = 0;// 5������Ь

		Iterator<Tool> it = tlist.iterator();
		while (it.hasNext()) {
			Tool t = it.next();
			switch (t.getTtype()) {
			case 1:
				countKnife++;
				if (t.isUsage())
					countKnifeByPeople++;
				break;
			case 2:
				countGun++;
				if (t.isUsage())
					countGunByPeople++;
				break;
			case 3:
				coutBazooka++;
				if (t.isUsage())
					coutBazookaByPeople++;
				break;
			case 4:
				coutBomb++;
				if (t.isUsage())
					coutBombByPeople++;
				break;
			case 5:
				coutEscapeShoes++;
				if (t.isUsage())
					coutEscapeShoesByPeople++;
				break;
			}
		}

		numberKnifelbl.setText("������(�˳���/�ܼ�)��" + countKnifeByPeople + " / " + countKnife);
		numberGunlbl.setText("ǹ����(�˳���/�ܼ�)��" + countGunByPeople + " / " + countGun);
		numberBazookalbl.setText("���Ͳ����(�˳���/�ܼ�)��" + coutBazookaByPeople + " / " + coutBazooka);
		numberBomblbl.setText("��ɱ������(�˳���/�ܼ�)��" + coutBombByPeople + " / " + coutBomb);
		numberEscapeShoeslbl.setText("����Ь����(�˳���/�ܼ�)��" + coutEscapeShoesByPeople + " / " + coutEscapeShoes);

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
		numberShelterlbl.setBounds(1020, 400, 300, 20);
		numberRadientlbl.setBounds(1020, 420, 300, 20);
		numberSwamplandlbl.setBounds(1020, 440, 300, 20);
		numberDMRiverlbl.setBounds(1020, 460, 300, 20);
		numberDeathtraplbl.setBounds(1020, 480, 300, 20);
		numberTrappedLandlbl.setBounds(1020, 500, 300, 20);
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
			if (t.isUsage() == false) {
				g.setColor(Color.yellow);
				g.fillRect(t.getTpos().getX() * 10, t.getTpos().getY() * 10, 5, 5);
			}
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
			while (isChanging) {
				// synchronized(this)
				/*
				 * { while(true) {
				 * 
				 * try { this.wait(); }catch(InterruptedException e) {
				 * e.printStackTrace(); } }
				 */

				sleep(1);
				updataYear(yearlbl);// �������
				neb.AdjustNormalPeopleAttr(plist);// �������䣬ͣ��ʱ�䣬���б��
				mb.randomMove(row, col, plist, clist);// ����ƶ�
				leb.beforeAttackEvent(col, plist, clist, tlist);// ����ǰ�����¼����ӻ�������������
				tmb.pickTool(plist, clist, tlist, col);// ������
				// �����¼�
				aeb.ManageAttackEvent(plist, clist, row, col, tlist);
				neb.PregnantManage(plist, clist, row, col);// �жϻ���
				updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
						numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);
				updateTool(numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl);
				leb.afterAttackEvent(col, plist, clist);// ����������¼�������
 

				repaint();
			}
		}

	}

}
