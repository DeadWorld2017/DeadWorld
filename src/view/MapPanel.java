package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import biz.ManageBiz;
import biz.ManageBizImpl;
import biz.MapManageBiz;
import biz.MapManageBizImpl;
import biz.PeopleManageBiz;
import biz.PeopleManageBizImpl;
import po.Cell;
import po.Land;
import po.People;
import po.Position;
import po.Tool;

public class MapPanel extends JPanel {
	int row;// 地图的高
	int col;// 地图的长

	List<People> plist;// 人类集合
	List<Tool> tlist;// 武器集合
	List<Cell> clist;// 地图格子集合
	List<Land> llist;// 地形
	int px[] = new int[200];
	int py[] = new int[200];

	PeopleManageBiz pmb = new PeopleManageBizImpl();
	MapManageBiz mmb = new MapManageBizImpl();
	ManageBiz mb = new ManageBizImpl();

	public MapPanel(int row, int col, List<People> tempplist, List<Tool> temptlist, List<Cell> tempclist,
			List<Land> templlist) {
		super();
		this.row = row;
		this.col = col;
		this.plist = tempplist;
		this.tlist = temptlist;
		this.clist = tempclist;
		this.llist = templlist;
	}

	public void startWorld() {
		mmb.initMapList(row, col, clist);
		mmb.initLand(row, col, llist, clist);
		pmb.initNormalPeopleRandom(row, col, plist, clist);// 初始化生成正常人类
		pmb.initDeadPeopleRandom(col, plist, clist);// 部分人类转化为丧尸

		updatePeople();// 调用显示人类数量的Label
		updateLand();//	调用显示地形数量的Label
		Iterator<People> it = plist.iterator();

	}

	public void nextWorld() {
		mb.randomMove(row, col, plist, clist);
		updatePeople();// 调用显示数量的Label

		Iterator<People> it = plist.iterator();
		/*
		 * while (it.hasNext()) { People p = it.next();// 存储it.next()的值，防止跳跃
		 * System.out.println(p.toString()); }
		 */

		for (int i = 0; i < plist.size() - 1; i++) {
			People n1 = plist.get(i);
			for (int j = i + 1; j < plist.size(); j++) {
				People n2 = plist.get(j);
				if (n1.getPpos() != null && n2.getPpos() != null) {
					if (n1.getPpos().getX() == n2.getPpos().getX() && n1.getPpos().getY() == n2.getPpos().getY()) {
						System.out.println(
								"第" + (i) + "个跟第" + (j) + "个重复，值是：" + n2.getPpos().getX() + "," + n2.getPpos().getY());
					}
				}
			}
		}

	}

	// 调用显示数量的Label
	public void updatePeople() {
		int numberPeople = pmb.countPeople(plist);// 所有人数量
		int numberNormalPeople = pmb.countNormalPeople(plist);// 正常人数量
		int numberDeadPeople = pmb.countDeadPeople(plist);// 丧尸数量

		JLabel numberPeoplelbl = new JLabel("物种数量：" + numberPeople);
		JLabel numberNormalPeoplelbl = new JLabel("正常人数量：" + numberNormalPeople);
		JLabel numberDeadPeoplelbl = new JLabel("丧尸数量：" + numberDeadPeople);
		
		add(numberPeoplelbl);
		add(numberNormalPeoplelbl);
		add(numberDeadPeoplelbl);

		setLayout(null);
		numberPeoplelbl.setBounds(1020, 20, 300, 20);
		numberNormalPeoplelbl.setBounds(1020, 40, 300, 20);
		numberDeadPeoplelbl.setBounds(1020, 60, 300, 20);

	}

	public void updateLand(){
		int countShelter = 0;// 1,庇护所
		int countRadient = 0;// 2,辐射地
		int coutSwampland = 0;// 3,沼泽地
		int coutDMRiver = 0;// 4,子母河
		int coutDeathtrap = 0;// 5，死亡陷阱
		int coutTrappedLand = 0;// 6，困阵
		
		Iterator<Land> it = llist.iterator();
		while(it.hasNext()){
			switch (it.next().getLtype()){
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
		numberShelterlbl.setBounds(1020, 100, 300, 20);
		numberRadientlbl.setBounds(1020, 120, 300, 20);
		numberSwamplandlbl.setBounds(1020, 140, 300, 20);
		numberDMRiverlbl.setBounds(1020, 160, 300, 20);
		numberDeathtraplbl.setBounds(1020, 180, 300, 20);
		numberTrappedLandlbl.setBounds(1020, 200, 300, 20);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// paintMap(g);//画地图格子的线条和填充，暂时不需要
		paintLand(g);// 画地形分布
		paintPeople(g);// 画人类分布
		

	}

	/*
	 * private void paintMap(Graphics g) { for (int i = 0; i < row; i++) { for
	 * (int j = 0; j < col; j++) { // g.setColor(Color.GRAY); //
	 * g.setColor(Color.black); g.fillRect(j * 10, i * 10, 10, 10);
	 * g.setColor(Color.red); g.drawRect(j * 10, i * 10, 10, 10); } } }
	 */

	// 画人类分布方法
	public void paintPeople(Graphics g) {
		Color colorNormalPeople = new Color(160,239,47);//绿色
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();// 存储it.next()的值，防止跳跃
			if (p.getPtype() == 1) // 若是正常人，显示绿色
			{
				g.setColor(colorNormalPeople);
				//g.fillOval(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			} else if (p.getPtype() == 0) // 若是丧尸，显示红色
			{
				g.setColor(Color.red);
				//g.fillOval(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			}
		}
	}

	public void paintLand(Graphics g) {
		Iterator<Land> it = llist.iterator();
		Color colorShelter = new Color(252, 230, 201);//蛋壳色
		Color colorRadient = new Color(255,255,0);//黄色
		Color colorSwampland = new Color(240,230,140);//黄褐色
		Color colorDMRiver = new Color(176,224,230);//浅灰蓝
		Color colorDeathtrap = new Color(255,192,203);//粉色
		Color colorTrappedLand = new Color(220,220,220);//gainsboro
		while (it.hasNext()) {
			Land l = it.next();
			switch (l.getLtype()) {
			case 1:// 庇护所绿色
				g.setColor(colorShelter);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 2:// 辐射地黄色
				g.setColor(colorRadient);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 3:// 沼泽地黑色
				g.setColor(colorSwampland);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 4:// 子母河粉色
				g.setColor(colorDMRiver);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 5:// 死亡陷阱红色
				g.setColor(colorDeathtrap);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			case 6:// 困阵灰色
				g.setColor(colorTrappedLand);
				g.drawRect(l.getLpos().getX() * 10, l.getLpos().getY() * 10, 10, 10);
				break;
			}

		}
	}

}
