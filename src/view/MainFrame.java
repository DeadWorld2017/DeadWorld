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

	private final MapPanel mp;// ��ͼ���

	public static List<People> plist = new ArrayList<People>();// ��������list����
	public static List<Tool> tlist = new ArrayList<Tool>();// ��ŵ��ߵ�list����
	public static List<Cell> clist = new ArrayList<Cell>();// ��ŵ�ͼ���ӵ�list����

	public final static int MapRow = 70;// ��ͼ�ĸ�
	public final static int MapCol = 100;// ��ͼ�ĳ�
	public final static int FrameRow = 800;// ������ĸ�
	public final static int FrameCol = 1300;// ������ĳ�

	public MainFrame(int row, int col) {
		mp = new MapPanel(row, col, plist, tlist, clist);
		mp.setBackground(Color.white);// ������屳����ɫ
		add(mp);// �������ӵ�������
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame(MapRow, MapCol);// ʵ�����������
		mf.setSize(FrameCol, FrameRow);// ���ô����С
		mf.setBackground(Color.WHITE);// ���ô���ı�����ɫ
		mf.setLocationRelativeTo(null);// ��ʾ��������
		mf.setTitle("Dead World");// ���ñ���
		mf.setVisible(true);// ʹ��ɼ�
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �ر�
		mf.setResizable(false); // ���ɸı䴰���С
		// mf.setLayout(null); //���ô��岼��Ϊ�ղ���

		mf.addMouseMotionListener(mf);// ����������¼�
		JMenuBar menu = new JMenuBar();// ��Ӳ˵���
		mf.setJMenuBar(menu);

		JMenu options = new JMenu("Options");// ��Ӳ˵�
		menu.add(options);

		JMenuItem start = options.add("Start");// ��ӡ���ʼ���˵���
		start.addActionListener(mf.new StartActionListener());
		JMenuItem next = options.add("Next");// ��ӡ���һ�����˵���
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
