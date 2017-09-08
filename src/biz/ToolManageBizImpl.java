package biz;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//道具管理接口实现
public class ToolManageBizImpl implements ToolManageBiz {

	//private double paramAttackByDoping = 1.5;

	@Override
	public void pickTool(List<People> plist,List<Cell> clist, List<Tool> tlist, int col) {
		int index;
		Cell c;
		Tool t = new Tool();
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if(p.getPtype()==1){
				NormalPeople np = (NormalPeople)p;
				index = p.getPpos().getY() * col + p.getPpos().getX();// Y乘以长+X，得到坐标
				c = clist.get(index);
				if(c.getTid()==-1)
					continue;
				else
					if(compareTool(np,c.getttype()))//有道具则跳过
						continue;
					else{
						Iterator<Tool> tit = tlist.iterator();
						while(tit.hasNext()){
							t = tit.next();
							if(t.getTid()==c.getTid())
								break;
						}
						np.getTlist().add(t);
						c.setTid(-1);
						c.setttype(-1);
						t.setUsage(true);
					}
			}
		}
		
	}
	public boolean compareTool(NormalPeople np,int ttype){	
		if(np.getTlist()!=null){
			Iterator<Tool> it = np.getTlist().iterator();
			while(it.hasNext()){
				Tool t = it.next();
				if(ttype==1||ttype==2||ttype==3)
					if(isWeopon(np)!=-1)
						return true;//有该道具
					else
						return false;//没有该道具
				else
					if(ttype==t.getTid())
						return true;//有道具
					else
						return false;//没有该道具
			}
		}
		return false;
	}
	
	// 传入道具的tid并在全局tlist中销毁
	public void destoryTool(List<Tool> tlist, int tid) {
		tlist.remove(tid);
	}
	
	public int isWeopon(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();
			if (t.getTid() == 1|| t.getTid() == 2|| t.getTid() == 3) // 如果循环找到了武器
				return t.getTid();
		}
		return -1;//没找到返回-1
		 
	}
	
	
	
	// 在正常人战败的时候，循环判断是否有炸弹
	public int isBomb(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();
			if (t.getTtype() == 4) // 如果循环找到了炸弹
				return t.getTid();
		}
		return -1;//没找到返回-1
	}
	
	// 传入发生战斗的双方的实例,销毁实例，并从list中删除
	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp,List<Tool> tlist) {
		int tempid = dp.getPid();// 暂存丧尸id
		plist.remove(plist.remove(plist.indexOf(np)));// 删除有炸弹的正常人
		plist.remove(plist.remove(plist.indexOf(dp)));// 删除丧尸
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPid() == tempid) {
				plist.remove(plist.indexOf(p));// 删除丧尸对应的人类
				break;
			}
		}
		Iterator<Tool> tit = np.getTlist().iterator();//销毁手上所有武器对应的tlist的武器
		while(tit.hasNext()){
			tlist.remove(tit.next().getTid());
		}
	}
	
	
	
	
	//逃跑鞋
	public int isEscapeShoes(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();
			if (t.getTtype() == 5) // 如果循环找到了逃跑鞋
				return t.getTid();
		}
		return -1;//没找到返回-1
	}
	
	// 逃跑鞋方法，随机判断是否逃跑，如果返回true则什么都不发生，如果返回false则继续执行操作
	public boolean escapeShoeFunction(List<People> plist, NormalPeople np,List<Tool> tlist,int tid) {
		Random rd = new Random();
		boolean isEscape = rd.nextBoolean();//随机判断是否逃跑
		Tool t = tlist.get(tid);
		if(isEscape){
			np.getTlist().remove(np.getTlist().indexOf(t));
			tlist.remove(tid);
			}
		return isEscape;
	}
 


	
	
	
	public int addWeoponDamage(int attackValue) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isTool() {
		// TODO Auto-generated method stub
		return false;
	}


	public void initTool(List<Tool> tlist) {
		
	}

	


	

 


	
	
	
	/*未完成的兴奋剂
	 
	// 判断是否有兴奋剂,若没有返回-1，若有则返回tid
	public int isDoping(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			Tool t = it.next();// 暂存
			if (t.getTid() == 7) // 如果循环找到了兴奋剂
				return t.getTid();// 返回tid
		}
		return -1;// 没有则返回-1
	}

	@Override
	public double attackByDoping(double attackValue) {
		attackValue *= paramAttackByDoping;
		return attackValue;
	}
	
	@Override
	public void dopingDuration(NormalPeople np, List<Tool> tlist) {
		int tid = isDoping(np); //获得tid
		Tool t = tlist.get(tid);//获得实例
		t.setDuration(t.getDuration()-1);//每过一年duration
		if(t.getDuration()<=0){
			tlist.remove(tlist.indexOf(t));//从道具集合tlist中删除
			np.getTlist().remove(tlist.indexOf(t));//从手中删除
		}
	}  
	*  
	*/	
		
	

}
