package biz;

import java.util.List;
import po.Cell;
import po.People;

//计算攻击力接口
public interface AttackEventBiz {
	
	//计算传入对象攻击力，返回相应的攻击力数值
	public double CalculateAttack(People p, List<Cell> clist);
}
