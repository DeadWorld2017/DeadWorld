package biz;

import java.util.List;

import po.Cell;
import po.Land;
import po.People;
import po.Position;
import po.Tool;

public interface MapManageBiz {
	public void initMapList(int row, int col, List<Cell> clist);
	//初始化空地图
	public void initLand(int row, int col, List<Land> llist,List<Cell> clist);
	
	// 随机生成位置，需要传入row,col作为最大随机数的范围
	public Position initPositionRandom(int row, int col);
	
	public int initLtypeRandom();
	
	//生成道具
	public boolean initTool(int row,int col,List<Tool> tlist,List<Cell> clist);

	public int initTtypeRandom();
	

}
