package biz;

import java.util.List;

import po.Cell;
import po.People;


//正常事件接口上
public interface NormalEventBiz {
	
	//生孩子事件
	//统筹生孩子的，存在一个大循环
	public void PregnantManage(List<People> plist, List<Cell> clist, int row, int col);
	
	//判断周围满足条件者，返回匹配的对象
	public People PregnantMatch(People p, List<People> plist, List<Cell> clist, int row, int col);
	
	//将匹配好的对象实现交配，返回新产生的孩子实例
	public People CreateChild(People p1, People p2);
	
	//死亡事件
	//战斗死亡或者年老死亡都是调用这个函数
	public void DeadEvent(People p, List<People> plist);
	
	//是否存在抗体
	public int isAntibody(People p);
	
	//年龄增长
	//针对所有正常人类的年龄增长
	public void AgeEvent(List<People> p);
}
