package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import biz.ManageBiz;
import biz.ManageBizImpl;
import po.People;


public class MapPanel extends JPanel{
	int row;
	int col;
	ManageBiz mb = new ManageBizImpl();
	List<People> plist;
	
	public MapPanel(int row, int col,List<People> tempplist) {
		super();
		this.row = row;
		this.col = col;
		mb.setPeopleRandom(tempplist);
		plist = tempplist;
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().ppos.getX());
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);	
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
					g.setColor(Color.GRAY);
					g.fillRect(j*10, i*10, 10, 10);
					g.setColor(Color.black);
					g.drawRect(j*10, i*10, 10, 10);
			}
		}
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			if(it.next().ptype==1)
			{
				g.setColor(Color.red);
				g.fillRect(it.next().getPpos().getX()*10, it.next().getPpos().getY()*10, 10, 10);
			}
			else
			{
				g.setColor(Color.yellow);
				g.fillRect(it.next().getPpos().getX()*10, it.next().getPpos().getY()*10, 10, 10);
			}
		}
		
	}
	

	
	
}
