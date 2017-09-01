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
import po.People;
import po.Position;
import po.Tool;

public class MapPanel extends JPanel {
	int row;// 地图的高
	int col;// 地图的长

	List<People> plist;// 人类集合
	List<Tool> tlist;// 武器集合
	List<Cell> clist;// 地图格子集合
	int px[] = new int[200];
	int py[] = new int[200];

	PeopleManageBiz pmb = new PeopleManageBizImpl();
	MapManageBiz mmb = new MapManageBizImpl();
	ManageBiz mb = new ManageBizImpl();

	public MapPanel(int row, int col, List<People> tempplist, List<Tool> temptlist, List<Cell> tempclist) {
		super();
		this.row = row;
		this.col = col;
		this.plist = tempplist;
		this.tlist = temptlist;
		this.clist = tempclist;
	}

	public void startWorld() {
		mmb.initMapList(row, col, clist);
		pmb.initNormalPeopleRandom(row, col, plist, clist);// 初始化生成正常人类
		pmb.initDeadPeopleRandom(col, plist, clist);// 部分人类转化为丧尸
		updateNumber();// 调用显示数量的Label
		Iterator<People> it = plist.iterator();
	
	}

	public void nextWorld() {
		mb.randomMove(row, col, plist, clist);
		updateNumber();// 调用显示数量的Label
		
		
		Iterator<People> it = plist.iterator();
		/*while (it.hasNext()) {
			People p = it.next();// 存储it.next()的值，防止跳跃
			System.out.println(p.toString());
		}*/
		
		for (int i = 0; i < plist.size() - 1; i++)
        {
            People n1 = plist.get(i);
            for (int j = i + 1; j < plist.size(); j++)
            {
                People n2=plist.get(j);
                if(n1.getPpos()!=null&&n2.getPpos()!=null)
            	{
                	if (n1.getPpos().getX()==n2.getPpos().getX()&&n1.getPpos().getY()==n2.getPpos().getY())
                	{
                		System.out.println("第" + (i) + "个跟第"
                	+ (j) + "个重复，值是：" + n2.getPpos().getX()+
                	","+n2.getPpos().getY());
                	}
            	}
            }
        }
		
	}

	// 调用显示数量的Label
	public void updateNumber() {
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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// paintMap(g);//画地图格子的线条和填充，暂时不需要
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
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();// 存储it.next()的值，防止跳跃
			if (p.getPtype() == 1) // 若是正常人，显示绿色
			{
				g.setColor(Color.green);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			} else if (p.getPtype() == 0) // 若是丧尸，显示红色
			{
				g.setColor(Color.red);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			}
		}
	}

}
