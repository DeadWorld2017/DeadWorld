package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import biz.MapManageBiz;
import biz.MapManageBizImpl;
import biz.PeopleManageBiz;
import biz.PeopleManageBizImpl;
import po.Cell;
import po.People;
import po.Tool;

public class MapPanel extends JPanel {
	int row;// ��ͼ�ĸ�
	int col;// ��ͼ�ĳ�

	List<People> plist;// ���༯��
	List<Tool> tlist;// ��������
	List<Cell> clist;// ��ͼ���Ӽ���

	PeopleManageBiz pmb = new PeopleManageBizImpl();
	MapManageBiz mmb = new MapManageBizImpl();

	public MapPanel(int row, int col, List<People> tempplist, List<Tool> temptlist, List<Cell> tempclist) {
		super();
		this.row = row;
		this.col = col;
		this.plist = tempplist;
		this.tlist = temptlist;
		this.clist = tempclist;
		mmb.initMapList(row,col,clist);
		pmb.initNormalPeopleRandom(row, col, plist,clist);// ��ʼ��������������
		

		pmb.initDeadPeopleRandom(col,plist,clist);// ��������ת��Ϊɥʬ
		showNumber();// ������ʾ������Label
		
	}

	// ������ʾ������Label
	private void showNumber() {
		int numberPeople = pmb.countPeople(plist);// ����������
		int numberNormalPeople = pmb.countNormalPeople(plist);// ����������
		int numberDeadPeople = pmb.countDeadPeople(plist);// ɥʬ����

		JLabel numberPeoplelbl = new JLabel("����������" + numberPeople);
		JLabel numberNormalPeoplelbl = new JLabel("������������" + numberNormalPeople);
		JLabel numberDeadPeoplelbl = new JLabel("ɥʬ������" + numberDeadPeople);

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

		// paintMap(g);//����ͼ���ӵ���������䣬��ʱ����Ҫ
		paintPeople(g);// ������ֲ�

	}

	/*
	 * ����ͼ���ӵ����� private void paintMap(Graphics g) { for (int i = 0; i < row;
	 * i++) { for (int j = 0; j < col; j++) { // g.setColor(Color.GRAY); //
	 * g.fillRect(j*10, i*10, 10, 10); g.setColor(Color.black); g.drawRect(j *
	 * 10, i * 10, 10, 10); } } }
	 */

	// ������ֲ�����
	public void paintPeople(Graphics g) {
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();// �洢it.next()��ֵ����ֹ��Ծ
			if (p.getPtype()==1) // ���������ˣ���ʾ��ɫ
			{
				g.setColor(Color.green);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			} else if(p.getPtype()==0)// ����ɥʬ����ʾ��ɫ
			{
				g.setColor(Color.red);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			}
		}
	}

}
