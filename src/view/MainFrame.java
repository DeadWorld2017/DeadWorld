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
	
	
	// static MapPanel mp=new MapPanel();  //ʵ����һ�����
	public MainFrame(int row,int col,List<People> plist)
	{
		mp=new MapPanel(row,col,plist);
		mp.setBackground(Color.BLUE);//������屳����ɫ
		add(mp);//�������ӵ������� 
		//mp.setRandom();
		
		
	}
	
	 
	public static void main(String[]args)
	{
		MainFrame mf=new MainFrame(50,50,plist);//ʵ�����������
		
		mf.setSize(1200,800);//���ô����С
		mf.setBackground(Color.WHITE);//���ô���ı�����ɫ
		mf.setLocationRelativeTo(null);//��ʾ��������
		mf.setTitle("Dead World");//���ñ���
		mf.setVisible(true);//ʹ��ɼ�
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر�
		mf.setResizable(false); //���ɸı䴰���С
		//mf.setLayout(null);  //���ô��岼��Ϊ�ղ���
		
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
