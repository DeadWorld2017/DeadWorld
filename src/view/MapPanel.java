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
import po.DeadPeople;
import po.Land;
import po.NormalPeople;
import po.People;
import po.Tool;

public class MapPanel extends JPanel implements Runnable {
	int row;// 地图的长（由于后面代码所以做出更改）
	int col;// 地图的高（由于后面代码）

	List<People> plist;// 人类集合
	List<Tool> tlist;// 武器集合
	List<Cell> clist;// 地图格子集合
	List<Land> llist;// 地形
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
		setPeopleLabel();// 调用显示人类数量的Label
		setYearLabel();// 年份
		setToolLabel();// 道具

		isChanging = true;
		mmb.initMapList(row, col, clist);
		mmb.initLand(row, col, llist, clist);
		mmb.initTool(row, col, tlist, clist);
		pmb.initNormalPeopleRandom(row, col, plist, clist);// 初始化生成正常人类
		pmb.initDeadPeopleRandom(col, plist, clist);// 部分人类转化为丧尸
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
				numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);
		updateLand();// 调用显示地形数量的Label
		updateTool(numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl);// 调用显示地形数量的Label
		
	}

	public void startWorld() {
		isChanging = true;
		mmb.initMapList(row, col, clist);
		mmb.initLand(row, col, llist, clist);
		mmb.initTool(row, col, tlist, clist);
		pmb.initNormalPeopleRandom(row, col, plist, clist);// 初始化生成正常人类
		pmb.initDeadPeopleRandom(col, plist, clist);// 部分人类转化为丧尸
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
				numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);
		updateLand();// 调用显示地形数量的Label
		updateTool(numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl);// 调用显示地形数量的Label

		/*
		 * synchronized(this) { isChanging=true; this.notifyAll(); }
		 */
	}

	public void nextWorld() {
		neb.AdjustNormalPeopleAttr(plist);// 调整年龄，停留时间，怀孕标记
		mb.randomMove(row, col, plist, clist);// 随机移动
		leb.beforeAttackEvent(col, plist, clist, tlist);// 攻击前地形事件，庇护所和死亡陷阱
		// 攻击事件
		aeb.ManageAttackEvent(plist, clist, row, col, tlist);
		neb.PregnantManage(plist, clist, row, col);// 判断怀孕
		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, number0to19lbl, number0to19lbl,
				number0to19lbl, number0to19lbl, number0to19lbl, number0to19lbl, number0to19lbl, number0to19lbl);
		leb.afterAttackEvent(col, plist, clist);// 攻击后地形事件，困阵
	}

	public void stopWorld() {
		isChanging = false;
	}

	// 调用显示数量的Label
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
		numberPeoplelbl.setBounds(1500, 40, 300, 20);
		numberNormalPeoplelbl.setBounds(1500, 60, 300, 20);
		numberDeadPeoplelbl.setBounds(1500, 80, 300, 20);
		numberAntibodylbl.setBounds(1500, 100, 300, 20);
		numberManlbl.setBounds(1500, 120, 300, 20);
		numberWomanlbl.setBounds(1500, 140, 300, 20);
		number0to19lbl.setBounds(1500, 160, 300, 20);
		number20to39lbl.setBounds(1500, 180, 300, 20);
		number40to59lbl.setBounds(1500, 200, 300, 20);
		number60to79lbl.setBounds(1500, 220, 300, 20);
		number80to99lbl.setBounds(1500, 240, 300, 20);

		updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
				numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);

	}

	private void updatePeople(JLabel p, JLabel np, JLabel dp, JLabel antibody, JLabel man, JLabel women,
			JLabel number0to19lbl, JLabel number20to39lbl, JLabel number40to59lbl, JLabel number60to79lbl,
			JLabel number80to99lbl) {
		int numberPeople = pmb.countPeople(plist);// 所有人数量
		int numberNormalPeople = pmb.countNormalPeople(plist);// 正常人数量
		int numberDeadPeople = pmb.countDeadPeople(plist);// 丧尸数量
		double numberAntibody = pmb.countAntibody(plist);
		double numberMan = pmb.countMan(plist);
		double numberWoman = pmb.countWomen(plist);
		double number0to20 = pmb.count0to19(plist);
		double number20to40 = pmb.count20to39(plist);
		double number40to60 = pmb.count40to59(plist);
		double number60to80 = pmb.count60to79(plist);
		double number80to100 = pmb.count80to99(plist);

		p.setText("物种数量：" + numberPeople);
		np.setText("正常人数量：" + numberNormalPeople);
		dp.setText("丧尸数量：" + numberDeadPeople);
		antibody.setText("抗体比例："+ numberAntibody);
		man.setText("男性比例："+numberMan);
		women.setText("女性比例："+numberWoman);
		number0to19lbl.setText("0-19岁比例："+number0to20);
		number20to39lbl.setText("20-39岁比例："+number20to40);
		number40to59lbl.setText("40-59岁比例："+number40to60);
		number60to79lbl.setText("60-79岁比例："+number60to80);
		number80to99lbl.setText("80-99岁比例："+number80to100);


	}

	public void setYearLabel() {
		yearlbl = new JLabel("年份：" + year);
		add(yearlbl);
		yearlbl.setBounds(1500, 0, 300, 20);

	}

	public void updataYear(JLabel yearlbl) {
		year++;
		yearlbl.setText("年份：" + year);
	}

	public void setToolLabel() {
		add(numberKnifelbl);
		add(numberGunlbl);
		add(numberBazookalbl);
		add(numberBomblbl);
		add(numberEscapeShoeslbl);

		setLayout(null);
		numberKnifelbl.setBounds(1500, 280, 300, 20);
		numberGunlbl.setBounds(1500, 300, 300, 20);
		numberBazookalbl.setBounds(1500, 320, 300, 20);
		numberBomblbl.setBounds(1500, 340, 340, 20);
		numberEscapeShoeslbl.setBounds(1500, 360, 300, 20);

		updateTool(numberKnifelbl, numberGunlbl, numberBazookalbl, numberBomblbl, numberEscapeShoeslbl);
	}

	private void updateTool(JLabel numberKnifelbl2, JLabel numberGunlbl2, JLabel numberBazookalbl2,
			JLabel numberBomblbl2, JLabel numberEscapeShoeslbl2) {
		int countKnife = 0;// 1,刀
		int countGun = 0;// 2,枪
		int coutBazooka = 0;// 3,火箭筒
		int coutBomb = 0;// 4,自杀弹
		int coutEscapeShoes = 0;// 5，逃跑鞋

		int countKnifeByPeople = 0;// 1,刀
		int countGunByPeople = 0;// 2,枪
		int coutBazookaByPeople = 0;// 3,火箭筒
		int coutBombByPeople = 0;// 4,自杀弹
		int coutEscapeShoesByPeople = 0;// 5，逃跑鞋

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

		numberKnifelbl.setText("刀数量(人持有/总计)：" + countKnifeByPeople + " / " + countKnife);
		numberGunlbl.setText("枪数量(人持有/总计)：" + countGunByPeople + " / " + countGun);
		numberBazookalbl.setText("火箭筒数量(人持有/总计)：" + coutBazookaByPeople + " / " + coutBazooka);
		numberBomblbl.setText("自杀弹数量(人持有/总计)：" + coutBombByPeople + " / " + coutBomb);
		numberEscapeShoeslbl.setText("逃跑鞋数量(人持有/总计)：" + coutEscapeShoesByPeople + " / " + coutEscapeShoes);

	}

	public void updateLand() {
		int countShelter = 0;// 1,庇护所
		int countRadient = 0;// 2,辐射地
		int coutSwampland = 0;// 3,沼泽地
		int coutDMRiver = 0;// 4,子母河
		int coutDeathtrap = 0;// 5，死亡陷阱
		int coutTrappedLand = 0;// 6，困阵

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

		JLabel numberShelterlbl = new JLabel("庇护所数量：" + countShelter);
		JLabel numberRadientlbl = new JLabel("辐射地数量：" + countRadient);
		JLabel numberSwamplandlbl = new JLabel("沼泽地数量：" + coutSwampland);
		JLabel numberDMRiverlbl = new JLabel("子母河数量：" + coutDMRiver);
		JLabel numberDeathtraplbl = new JLabel("死亡陷阱数量：" + coutDeathtrap);
		JLabel numberTrappedLandlbl = new JLabel("困阵数量：" + coutTrappedLand);

		add(numberShelterlbl);
		add(numberRadientlbl);
		add(numberSwamplandlbl);
		add(numberDMRiverlbl);
		add(numberDeathtraplbl);
		add(numberTrappedLandlbl);

		setLayout(null);
		numberShelterlbl.setBounds(1500, 400, 300, 20);
		numberRadientlbl.setBounds(1500, 420, 300, 20);
		numberSwamplandlbl.setBounds(1500, 440, 300, 20);
		numberDMRiverlbl.setBounds(1500, 460, 300, 20);
		numberDeathtraplbl.setBounds(1500, 480, 300, 20);
		numberTrappedLandlbl.setBounds(1500, 500, 300, 20);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paintMap(g);//画地图格子的线条和填充，暂时不需要
		paintLand(g);// 画地形分布
		paintTool(g);// 画道具分布
		paintPeople(g);// 画人类分布
	}

	/*
	 * 画地图格子的线 private void paintMap(Graphics g) { for (int i = 0; i < row; i++)
	 * { for (int j = 0; j < col; j++) { // g.setColor(Color.GRAY); //
	 * g.setColor(Color.black); g.fillRect(j * 10, i * 10, 10, 10);
	 * g.setColor(Color.red); g.drawRect(j * 10, i * 10, 10, 10); } } }
	 */

	private void paintTool(Graphics g) {
		Iterator<Tool> it = tlist.iterator();
		while (it.hasNext()) {
			Tool t = it.next();// 存储it.next()的值，防止跳跃
			if (t.isUsage() == false) {
				g.setColor(Color.yellow);
				g.fillRect(t.getTpos().getY() * 10, t.getTpos().getX() * 10, 5, 5);
			}
		}

	}

	// 画人类分布方法
	public void paintPeople(Graphics g) {
		Color colorNormalPeople = new Color(160, 239, 47);// 绿色
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();// 存储it.next()的值，防止跳跃
			if (p.getClass() == NormalPeople.class &&p.getPtype()!=2) // 若是正常人，显示绿色
			{
				g.setColor(colorNormalPeople);
				// g.fillOval(p.getPpos().getX() * 10, p.getPpos().getY() * 10,
				// 10, 10);
				g.fillRect(p.getPpos().getY() * 10, p.getPpos().getX() * 10, 10, 10);
			} else if (p.getClass() == DeadPeople.class &&p.getPtype()!=2) // 若是丧尸，显示红色
			{
				g.setColor(Color.red);
				// g.fillOval(p.getPpos().getX() * 10, p.getPpos().getY() * 10,
				// 10, 10);
				g.fillRect(p.getPpos().getY() * 10, p.getPpos().getX() * 10, 10, 10);
			}
		}
	}

	// 画地形分布（不变）
	public void paintLand(Graphics g) {
		Iterator<Land> it = llist.iterator();
		Color colorShelter = new Color(252, 230, 201);// 蛋壳色
		Color colorRadient = new Color(255, 255, 0);// 黄色
		Color colorSwampland = new Color(240, 230, 140);// 黄褐色
		Color colorDMRiver = new Color(176, 224, 230);// 浅灰蓝
		Color colorDeathtrap = new Color(255, 192, 203);// 粉色
		Color colorTrappedLand = new Color(220, 220, 220);// gainsboro
		while (it.hasNext()) {
			Land l = it.next();
			switch (l.getLtype()) {
			case 1:// 庇护所绿色
				g.setColor(colorShelter);
				g.drawRect(l.getLpos().getY() * 10, l.getLpos().getX() * 10, 10, 10);
				break;
			case 2:// 辐射地黄色
				g.setColor(colorRadient);
				g.drawRect(l.getLpos().getY() * 10, l.getLpos().getX() * 10, 10, 10);
				break;
			case 3:// 沼泽地黑色
				g.setColor(colorSwampland);
				g.drawRect(l.getLpos().getY() * 10, l.getLpos().getX() * 10, 10, 10);
				break;
			case 4:// 子母河粉色
				g.setColor(colorDMRiver);
				g.drawRect(l.getLpos().getY() * 10, l.getLpos().getX() * 10, 10, 10);
				break;
			case 5:// 死亡陷阱红色
				g.setColor(colorDeathtrap);
				g.drawRect(l.getLpos().getY() * 10, l.getLpos().getX() * 10, 10, 10);
				break;
			case 6:// 困阵灰色
				g.setColor(colorTrappedLand);
				g.drawRect(l.getLpos().getY() * 10, l.getLpos().getX() * 10, 10, 10);
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
				updataYear(yearlbl);// 更新年份
				neb.AdjustNormalPeopleAttr(plist);// 调整年龄，停留时间，怀孕标记
				mb.randomMove(row, col, plist, clist);// 随机移动
				leb.beforeAttackEvent(col, plist, clist, tlist);// 攻击前地形事件，庇护所和死亡陷阱
				tmb.pickTool(plist, clist, tlist, col);// 捡武器
				// 攻击事件
				aeb.ManageAttackEvent(plist, clist, row, col, tlist);
				neb.PregnantManage(plist, clist, row, col);// 判断怀孕
				updatePeople(numberPeoplelbl, numberNormalPeoplelbl, numberDeadPeoplelbl, numberAntibodylbl, numberManlbl,
						numberWomanlbl, number0to19lbl, number20to39lbl, number40to59lbl, number60to79lbl, number80to99lbl);
				updateTool(numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl, numberBazookalbl);
				leb.afterAttackEvent(col, plist, clist);// 攻击后地形事件，困阵
  
				repaint();
			}
		}

	}

}
