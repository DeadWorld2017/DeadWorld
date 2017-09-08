package biz;

import java.util.List;
import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Position;
import po.Tool;

//���㹥�����ӿ�
public interface AttackEventBiz {
	
	//���㴫����󹥻�����������Ӧ�Ĺ�������ֵ
	public double CalculateAttack(People p, List<Cell> clist);
	
	//ͳ��������¼�����
	public void ManageAttackEvent(List<People> plist, List<Cell> clist, int row, int col,List<Tool> tlist);
	
	//�Ե���ɥʬΪ����Ѱ���������ڵ����ಢ����list�У��������list
	public List<People> JudgeAttackEvent(DeadPeople dp, List<Cell> clist, List<People> plist, int row, int col);
	
	//�����¼�
	public void AttackEvent(NormalPeople np, DeadPeople dp, List<People> plist, List<Cell> clist,
			List<People> deadList, List<People> newList, int row, int col,List<Tool> tlist);
	
	//�жϸ������Ƿ�Ϊ��������(�о����Ժͻ��еĺϲ�)
	public void JudgeNormalCell(int x, int y, List<People> plist, List<People> fitPList );
	
}
