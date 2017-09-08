package biz;

import java.util.List;

import po.Cell;
import po.People;
import po.Position;
import po.Tool;

public interface ManageBiz {
	
	public void randomMove(int row, int col, List<People> plist,List<Cell> clist);
	
	public int direction();
	//�жϷ���1-8��8��������0Ϊ������˳ʱ����ת��0Ϊ�̶�����
	
	public Position moveByDirection(int row, int col, int direction,People p,List<Cell> clist);
	//�������ƶ�
	
	public boolean hasPeople(int row, Position pos, List<Cell> clist) ;
	//�ж�ԭ��λ���Ƿ��Ѿ�������
	
	
	
}
