package biz;

import java.util.List;

import po.Cell;
import po.Land;
import po.People;
import po.Position;

public interface MapManageBiz {
	public void initMapList(int row, int col, List<Cell> clist);
	//��ʼ���յ�ͼ
	public void initLand(int row, int col, List<Land> llist,List<Cell> clist);
	
	// �������λ�ã���Ҫ����row,col��Ϊ���������ķ�Χ
	public Position initPositionRandom(int row, int col);
	
	public int initLtypeRandom();
	
}
