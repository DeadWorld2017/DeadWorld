package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import po.Cell;
import po.People;
import po.Tool;

public class MainFrame extends JFrame implements MouseMotionListener {

	private final MapPanel mp;// 地图面板

	public static List<People> plist = new ArrayList<People>();// 存放人物的list集合
	public static List<Tool> tlist = new ArrayList<Tool>();// 存放道具的list集合
	public static List<Cell> clist = new ArrayList<Cell>();// 存放地图格子的list集合

	public final static int MapRow = 70;// 地图的高
	public final static int MapCol = 100;// 地图的长
	public final static int FrameRow = 800;// 主窗体的高
	public final static int FrameCol = 1300;// 主窗体的长

	public MainFrame(int row, int col) {
		mp = new MapPanel(row, col, plist, tlist, clist);
		mp.setBackground(Color.white);// 设置面板背景颜色
		add(mp);// 将面板添加到窗体中
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame(MapRow, MapCol);// 实例化窗体对象
		mf.setSize(FrameCol, FrameRow);// 设置窗体大小
		mf.setBackground(Color.WHITE);// 设置窗体的背景颜色
		mf.setLocationRelativeTo(null);// 显示在正中央
		mf.setTitle("Dead World");// 设置标题
		mf.setVisible(true);// 使其可见
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭
		mf.setResizable(false); // 不可改变窗体大小
		// mf.setLayout(null); //设置窗体布局为空布局

		mf.addMouseMotionListener(mf);// 添加鼠标监听事件
		JMenuBar menu = new JMenuBar();// 添加菜单栏
		mf.setJMenuBar(menu);

		JMenu options = new JMenu("Options");// 添加菜单
		menu.add(options);

		JMenuItem start = options.add("Start");// 添加“开始”菜单项
		start.addActionListener(mf.new StartActionListener());
		JMenuItem next = options.add("Next");// 添加“下一步”菜单项
		next.addActionListener(mf.new NextActionListener());

	}

	class StartActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mp.startWorld();
			repaint();
		}
	}

	class NextActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mp.nextWorld();
			repaint();
		}
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

}
