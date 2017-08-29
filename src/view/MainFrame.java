package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javafx.scene.control.Cell;
import po.People;
import po.Tool;

 

public class MainFrame extends JFrame implements MouseMotionListener{
	private final MapPanel mp;
	public static List<People> plist=new ArrayList<People>();
	public static List<Tool> tlist=new ArrayList<Tool>();
	public static List<Cell> clist=new ArrayList<Cell>();
	
	
	// static MapPanel mp=new MapPanel();  //实例化一个面板
	public MainFrame(int row,int col,List<People> plist)
	{
		mp=new MapPanel(row,col,plist);
		mp.setBackground(Color.BLUE);//设置面板背景颜色
		add(mp);//将面板添加到窗体中 
		//mp.setRandom();
		
		
	}
	
	 
	public static void main(String[]args)
	{
		MainFrame mf=new MainFrame(50,50,plist);//实例化窗体对象
		
		mf.setSize(1200,800);//设置窗体大小
		mf.setBackground(Color.WHITE);//设置窗体的背景颜色
		mf.setLocationRelativeTo(null);//显示在正中央
		mf.setTitle("Dead World");//设置标题
		mf.setVisible(true);//使其可见
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
		mf.setResizable(false); //不可改变窗体大小
		//mf.setLayout(null);  //设置窗体布局为空布局
		
		mf.addMouseMotionListener(mf);
		JMenuBar menu=new JMenuBar();
		mf.setJMenuBar(menu);
		
		JMenu options =new JMenu("Options");
		menu.add(options);
		
		JMenuItem start=options.add("Start");
		

	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 
 
}
