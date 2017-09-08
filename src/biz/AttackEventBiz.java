package biz;

import java.util.List;
import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;
import po.Tool;

//计算攻击力接口
public interface AttackEventBiz {
	
	//计算传入对象攻击力，返回相应的攻击力数值
	public double CalculateAttack(People p, List<Cell> clist);
	
	//统筹管理攻击事件方法
	public void ManageAttackEvent(List<People> plist, List<Cell> clist, int row, int col,List<Tool> tlist);
	
	//以单个丧尸为中心寻找与其相邻的人类并加入list中，返回这个list
	public List<People> JudgeAttackEvent(DeadPeople dp, List<Cell> clist, List<People> plist, int row, int col);
	
	//攻击事件
	public void AttackEvent(NormalPeople np, DeadPeople dp, List<People> plist, List<Cell> clist,
			List<People> deadList, List<People> newList, int row, int col,List<Tool> tlist);
	
	//判断格子类是否为正常人类(感觉可以和怀孕的合并)
	public void JudgeNormalCell(int x, int y, List<People> plist, List<People> fitPList );
	
}
