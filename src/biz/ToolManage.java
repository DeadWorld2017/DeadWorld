package biz;

import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//道具管理接口
public interface ToolManage {
	
	public boolean isWeopon(int ttype);
	//判断是否有武器，若有武器则返回true,若没有武器则返回false
	
	public int addWeoponDamage(int attackValue);
	//计算武器的伤害,返回值为int型
	
	
	public boolean isBomb(int ttype);
	//判断是否有炸弹，若有炸弹则返回true,若没有炸弹则返回false
	
	public void bombFuction(List<People> plist,
			NormalPeople np,DeadPeople dp);
	//传入发生战斗的双方的实例,销毁实例，并从list中删除
	
	public void destoryBomb(NormalPeople np,List<Tool> tlist,
			int tid);
	//传入该武器的tid并在全局tlist和人物携带tlist中销毁
	
	public boolean isShoes(int ttype);
	//判断是否有鞋子,有则不变返回true，没有则返回false
	
	public boolean escapeShoes();//逃跑鞋方法
	
	public boolean speedShoes();//加速鞋方法
	
	public boolean isDoping();//是否有兴奋剂
	
	public People findPid(int pid,List<People> plist);
	
}
