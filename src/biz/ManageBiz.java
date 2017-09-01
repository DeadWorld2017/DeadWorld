package biz;

import java.util.List;

import po.People;
import po.Position;

public interface ManageBiz {
	
	public void randomMove(List<People> plist);
	
	public int direction();
	//判断方向，1-8共8个方向，以0为正方向顺时针旋转，0为固定不动
	
	public Position moveByDirection(int direction,People p);
	//按方向移动
}
