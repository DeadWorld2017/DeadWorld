package biz;

import java.util.Iterator;
import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//道具管理接口实现
public class ToolManageBizImpl implements ToolManageBiz {

	private double paramAttackByDoping = 1.5;

	// 在正常人战败的时候，循环判断是否有炸弹
	public boolean isBomb(NormalPeople np) {
		Iterator<Tool> it = np.getTlist().iterator();
		while (it.hasNext()) {
			if (it.next().getTid() == 4) // 如果循环找到了炸弹
				return true;
		}
		return false;
	}

	// 传入发生战斗的双方的实例,销毁实例，并从list中删除
	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp) {
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
	}

	public boolean isShoes(int type) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean escapeShoes() {
		// TODO Auto-generated method stub
		return false;
	}
 
 
	public int addWeoponDamage(int attackValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void destoryBomb(NormalPeople np, List<Tool> tlist, int tid) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWeopon(int ttype) {
		// TODO Auto-generated method stub
		return false;
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
