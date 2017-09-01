package biz;

import java.util.List;
import java.util.Random;

import po.Cell;
import po.Position;

public class MapManageBizImpl implements MapManageBiz{

	@Override
	//³õÊ¼»¯¿ÕµØÍ¼
	public void initMapList(int row, int col, List<Cell> clist) {
		for(int i = 0;i<row;i++)
			for(int j=0;j<col;j++){
				Position p = new Position(i,j);
				Cell c = new Cell(p); 
				clist.add(c);
			}
	}

	 
	 
	 
	
}
