package biz;

import java.util.List;

import po.Cell;
import po.People;
import po.Position;
import po.Tool;

public interface ManageBiz {
	
	public void randomMove(int row, int col, List<People> plist,List<Cell> clist);
	
	public int direction();
	//判断方向，1-8共8个方向，以0为正方向顺时针旋转，0为固定不动
	
	public Position moveByDirection(int row, int col, int direction,People p,List<Cell> clist);
	//按方向移动
	
	public boolean hasPeople(int row, Position pos, List<Cell> clist) ;
	//判断原来位置是否已经有人了
	
	
	
}
