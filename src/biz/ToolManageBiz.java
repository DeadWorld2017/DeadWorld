package biz;

import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//道具管理接口
public interface ToolManageBiz {

	public void destoryTool(List<Tool> tlist, int tid);
	// 传入道具的tid并在全局tlist中销毁
	
	//是否有武器
	public boolean isTool();
	
	public int isWeopon(NormalPeople np);
	// 判断是否有武器，若有武器则返回tid
	
	public int isBomb(NormalPeople np);
	// 在正常人战败的时候，循环判断是否有炸弹 ，若没有返回-1，若有则返回tid

	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp,List<Tool> tlist);
	// 传入发生战斗的双方的实例,销毁实例，并从list中删除

	
	public int isEscapeShoes(NormalPeople np);
	// 判断是否有逃跑鞋子,有则不变返回tid，没有则返回-1

	public boolean escapeShoeFunction(List<People> plist, NormalPeople np,List<Tool> tlist,int tid);
	// 逃跑鞋方法，随机判断是否逃跑，如果返回true则什么都不发生，如果返回false则继续执行操作

 
	
	/*未完成的兴奋剂
	 public int isDoping(NormalPeople np);
	// 判断是否有兴奋剂,若没有返回-1，若有则返回tid
	
	public double attackByDoping(double attackValue);
	//兴奋剂的功能，增加50%的攻击力
	
	public void dopingDuration(NormalPeople np, List<Tool> tlist);
	//判断兴奋剂时间，若超时则销毁
	 */
}
