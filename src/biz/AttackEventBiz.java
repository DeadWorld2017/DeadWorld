package biz;

import java.util.List;
import po.Cell;
import po.People;

//���㹥�����ӿ�
public interface AttackEventBiz {
	
	//���㴫����󹥻�����������Ӧ�Ĺ�������ֵ
	public double CalculateAttack(People p, List<Cell> clist);
}
