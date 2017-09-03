package biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.NormalPeople;
import po.People;
import po.Position;


//正常事件接口实现
public class NormalEventBizImpl implements NormalEventBiz{

	public void PregnantManage(List<People> plist, List<Cell> clist, int row,
			int col) {
		// 统筹怀孕事件
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People p = it.next();
			if(p.getPtype() == 1){
				NormalPeople np1 = (NormalPeople)p;
				if(np1.getPregnancyFlag()){
					NormalPeople np2 = PregnantMatch(np1, plist, clist, row, col);
					if(np2 !=null){
						People newp = CreateChild(np1, np2);
						plist.add(newp);					
					}
				}
				
			}
		}
		
		
	}

	public NormalPeople PregnantMatch(People p, List<People> plist, List<Cell> clist,
			int row, int col) {
		//产生配对正常人，返回匹配的正常人的值
		//判断正常人类对象周围的
		int x, y;
		Position ppos = p.getPpos();
		//Iterator<People> it = plist.iterator();
		List<People> markPList = new ArrayList<People>();	//用来记录满足条件的人
		
		//计算长度，
		//左 --1
		x = ppos.getX();
		y = ppos.getY();
		if( (y - 1) > -1 ){
			y -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//上  --2
		x = ppos.getX();
		y = ppos.getY();
		if( (x - 1) > -1 ){
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//右  --3
		x = ppos.getX();
		y = ppos.getY();
		if( (y + 1) < col ){
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//下 --4
		x = ppos.getX();
		y = ppos.getY();
		if( (x + 1) < row ){
			y += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//左上  --5
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x - 1) > -1) ){
			y -= 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//右上  --6
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < col) && ((x - 1) > -1) ){
			y += 1;
			x -= 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//右下  --7
		x = ppos.getX();
		y = ppos.getY();
		if( ((y + 1) < col) && ((x + 1) < row ) ){
			y += 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//左下  --8
		x = ppos.getX();
		y = ppos.getY();
		if( ((y - 1) > -1) && ((x + 1) < row) ){
			y -= 1;
			x += 1;
			JudgeCell(x, y, markPList, plist);
		}
		
		//判断完之后，其实markPList里面有所有满足条件的People对象
		int ram = markPList.size();     //多少个满足条件的数量
		Random rd = new Random();
		int target = rd.nextInt(ram);   //根据有多少个满足的条件来从之中选择 0-ram
		NormalPeople np = (NormalPeople)markPList.get(target);
		
		return np;
	}
	
	public void JudgeCell(int x, int y, List<People> markPList, List<People> plist){
		//将坐标传进来判断是够达成怀孕条件
		Iterator<People> it = plist.iterator();
		while(it.hasNext()){
			People people = it.next();
			if( people.getPpos().getX() == x && people.getPpos().getY() == y && people.getPtype() == 1 ){
				NormalPeople np = (NormalPeople)people;
				if(np.getPregnancyFlag()){
					markPList.add(np);
				}
			}
		}
		
	}
	public People CreateChild(NormalPeople p1, NormalPeople p2) {
		//将相匹配的两个人做处理
		p1.setPregnancyFlag(false);
		p2.setPregnancyFlag(false);
		//综合一下
		//NormalPeople newNP = new NormalPeople()
		
		return null;
	}

	public void DeadEvent(People p, List<People> plist) {
		//死亡事件
		plist.remove(p);
	}

	public int isAntibody(NormalPeople np) {
		//判断正常人类是否有个体
		if(np.isAntibody()) return 1;
		else return 0;
	}

	public void AgeEvent(List<People> p) {
		//
		
	}

}
