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
	public void DeadEvent(People p, List<People> plist,List<People> deadList);
	
	//存在抗体前提下，触发抗体随机事件
	public int AntibodyRandomEvent();
	
	
	public void DestroyCell(int col, List<Cell> clist, People p);
	
	/*
	//调整全部人的停留时间
	public void AdjustTrapYear(List<People> plist);
	
	//调整全部人的怀孕标签
	public void AdjustPregnancy(List<People> plist);
	
	//调整全部人的年龄
	public void AdjustAge(List<People> plist);
	*/
	
	//调整全部人的停留时间、怀孕标签、年龄，在一个方法里面可以节省三倍的循环
	public void AdjustNormalPeopleAttr(List<People> plist);
	
	//死亡事件，将People对象从list中移除同时更改clist中对应的值
	//public void DeadEvent(People p, List<People> plist, List<Cell> clist);
	

}
