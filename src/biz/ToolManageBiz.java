package biz;

import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//道具管理接口
public interface ToolManageBiz {

	
	public boolean isWeopon(int ttype);
	// 判断是否有武器，若有武器则返回true,若没有武器则返回false

	public int addWeoponDamage(int attackValue);
	// 计算武器的伤害,返回值为int型

	public boolean isBomb(NormalPeople np);
	// 在正常人战败的时候，循环判断是否有炸弹 ，若有则返回true
	

	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp);
	// 传入发生战斗的双方的实例,销毁实例，并从list中删除

	public void destoryBomb(NormalPeople np, List<Tool> tlist, int tid);
	// 传入该武器的tid并在全局tlist和人物携带tlist中销毁

	public boolean isShoes(int ttype);
	// 判断是否有鞋子,有则不变返回true，没有则返回false

	public boolean escapeShoes();
	// 逃跑鞋方法

	
	/*未完成的兴奋剂
	 public int isDoping(NormalPeople np);
	// 判断是否有兴奋剂,若没有返回-1，若有则返回tid
	
	public double attackByDoping(double attackValue);
	//兴奋剂的功能，增加50%的攻击力
	
	public void dopingDuration(NormalPeople np, List<Tool> tlist);
	//判断兴奋剂时间，若超时则销毁
	 */
}
