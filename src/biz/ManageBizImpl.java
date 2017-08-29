package biz;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;

//����ӿ�ʵ��
public class ManageBizImpl implements ManageBiz{

	
	
	//100�������ˣ�20��ɥʬ
	public void setPeopleRandom(List<People> plist) {
		Random a=new Random();
		for(int i=0;i<100;i++)
		{
			int x = Math.abs(a.nextInt(50));
			int y = Math.abs(a.nextInt(50));
			Position pos = new Position(x,y);
			NormalPeople np = new NormalPeople(i,pos,true, 
						20, 20, true,null,0);
			plist.add(np);		 
		}
		for(int i=0;i<20;i++)
		{
			int x = Math.abs(a.nextInt(50));
			int y = Math.abs(a.nextInt(50));
			Position pos = new Position(x,y);
			DeadPeople dp = new DeadPeople(i+100,pos,1, 
						20,0);
			plist.add(dp);		 
		}
		
		
	}
	
}
