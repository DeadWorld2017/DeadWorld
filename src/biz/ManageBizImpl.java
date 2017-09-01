package biz;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.People;
import po.Position;

public class ManageBizImpl implements ManageBiz{

	@Override
	public void randomMove(List<People> plist) {
		Iterator<People> it = plist.iterator();
		while (it.hasNext()){ 
			People p = it.next();
			
		}
		
	}

	@Override
	//判断方向，1-8共8个方向，以0为正方向顺时针旋转，0为固定不动
	public int direction() {
		Random rd = new Random();
		int direction = rd.nextInt(9);
		return direction;
	}

	@Override
	public Position moveByDirection(int direction, People p) {
		Position temppos = p.getPpos();
		switch(direction){
		case 0:
			break;//不变
		case 1:
			temppos.setX(temppos.getX()-1);//向上移动
			break;
		case 2:
			temppos.setX(temppos.getX()-1);
			temppos.setY(temppos.getY()+1);//向右上方移动
			break;
		case 3:
			temppos.setY(temppos.getY()+1);//向右侧移动
			break;
		case 4:
			temppos.setX(temppos.getX()+1);
			temppos.setY(temppos.getY()+1);//向右下方移动
			break;
		case 5:
			temppos.setX(temppos.getX()+1);//向下方移动
			break;
		case 6:
			temppos.setX(temppos.getX()+1);
			temppos.setY(temppos.getY()-1);//向左下方移动
			break;
		case 7:
			temppos.setY(temppos.getY()-1);//向左侧移动
			break;
		case 8:
			temppos.setX(temppos.getX()-1);
			temppos.setY(temppos.getY()-1);//向左上方移动
			break;
		}		
		return temppos;
	}
}
