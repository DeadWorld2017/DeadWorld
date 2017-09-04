package biz;

import java.util.List;

import po.Cell;
import po.NormalPeople;
import po.People;


//正常事件接口上
public interface NormalEventBiz {
	
	//生孩子事件
	//统筹生孩子的，存在一个大循环
	public void PregnantManage(List<People> plist, List<Cell> clist, int row, int col);
	
	//判断周围满足条件者，返回匹配的对象
	public NormalPeople PregnantMatch(People p, List<People> plist, List<Cell> clist, int row, int col);
	
	//判断周围格子，满足条件加入list
	public void JudgeCell(int x, int y, List<People> markPList, List<People> plist);
	
	//将匹配好的对象实现交配，返回新产生的孩子实例
	public People CreateChild(NormalPeople p1, NormalPeople p2, int num, List<Cell> clist, int row, int col);
	
	//死亡事件
	//战斗死亡或者年老死亡都是调用这个函数
	public void DeadEvent(People p, List<People> plist);
	
	//是否存在抗体
	public int isAntibody(NormalPeople p);
	
	//年龄增长
	//针对所有正常人类的年龄增长
	public void AgeEvent(List<People> p);
	

}
