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
	int row;// ��ͼ�ĸ�
	int col;// ��ͼ�ĳ�

	List<People> plist;// ���༯��
	List<Tool> tlist;// ��������
	List<Cell> clist;// ��ͼ���Ӽ���
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
		pmb.initNormalPeopleRandom(row, col, plist, clist);// ��ʼ��������������
		pmb.initDeadPeopleRandom(col, plist, clist);// ��������ת��Ϊɥʬ
		updateNumber();// ������ʾ������Label
		Iterator<People> it = plist.iterator();
	
	}

	public void nextWorld() {
		mb.randomMove(row, col, plist, clist);
		updateNumber();// ������ʾ������Label
		
		
		Iterator<People> it = plist.iterator();
		/*while (it.hasNext()) {
			People p = it.next();// �洢it.next()��ֵ����ֹ��Ծ
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
                		System.out.println("��" + (i) + "������"
                	+ (j) + "���ظ���ֵ�ǣ�" + n2.getPpos().getX()+
                	","+n2.getPpos().getY());
                	}
            	}
            }
        }
		
	}

	// ������ʾ������Label
	public void updateNumber() {
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
	 * private void paintMap(Graphics g) { for (int i = 0; i < row; i++) { for
	 * (int j = 0; j < col; j++) { // g.setColor(Color.GRAY); //
	 * g.setColor(Color.black); g.fillRect(j * 10, i * 10, 10, 10);
	 * g.setColor(Color.red); g.drawRect(j * 10, i * 10, 10, 10); } } }
	 */

	// ������ֲ�����
	public void paintPeople(Graphics g) {
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();// �洢it.next()��ֵ����ֹ��Ծ
			if (p.getPtype() == 1) // ���������ˣ���ʾ��ɫ
			{
				g.setColor(Color.green);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			} else if (p.getPtype() == 0) // ����ɥʬ����ʾ��ɫ
			{
				g.setColor(Color.red);
				g.fillRect(p.getPpos().getX() * 10, p.getPpos().getY() * 10, 10, 10);
			}
		}
	}

}
