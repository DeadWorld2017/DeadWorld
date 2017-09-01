package biz;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.People;
import po.Position;

public class ManageBizImpl implements ManageBiz {

	@Override
	public void randomMove(int row, int col, List<People> plist, List<Cell> clist) {
		Iterator<People> it = plist.iterator();
		while (it.hasNext()) {
			People p = it.next();
			if (p.getPtype() != 2) {
				int direction = direction();
				Position newpos = moveByDirection(row, col, direction, p, clist);
				p.setPpos(newpos);
			}
		}
	}

	@Override
	// 判断方向，1-8共8个方向，以0为正方向顺时针旋转，0为固定不动
	public int direction() {
		Random rd = new Random();
		int direction = rd.nextInt(9);
		return direction;
	}

	@Override
	//
	public Position moveByDirection(int row, int col, int direction, People p, List<Cell> clist) {
		Position pposNew = new Position(p.getPpos().getX(),p.getPpos().getY());// 记录新的位置
		switch (direction) {
		case 0:
			return p.getPpos();
		case 1:
			if (pposNew.getX() > 0) 
				pposNew.setX(pposNew.getX() - 1); // 向上移动
			break;
		case 2:
			if (pposNew.getX() > 0)
				pposNew.setX(pposNew.getX() - 1);
			if (pposNew.getY() < row - 1)
				pposNew.setY(pposNew.getY() + 1);// 向右上方移动
			break;
		case 3:
			if (pposNew.getY() < row - 1)
				pposNew.setY(pposNew.getY() + 1);// 向右侧移动
			break;
		case 4:
			if (pposNew.getX() < col - 1)
				pposNew.setX(pposNew.getX() + 1);
			if (pposNew.getY() < row - 1)
				pposNew.setY(pposNew.getY() + 1);// 向右下方移动
			break;
		case 5:
			if (pposNew.getX() < col - 1)
				pposNew.setX(pposNew.getX() + 1);// 向下方移动
			break;
		case 6:
			if (pposNew.getX() < col - 1)
				pposNew.setX(pposNew.getX() + 1);
			if (pposNew.getY() > 0)
				pposNew.setY(pposNew.getY() - 1);// 向左下方移动
			break;
		case 7:
			if (pposNew.getY() > 0)
				pposNew.setY(pposNew.getY() - 1);// 向左侧移动
			break;
		case 8:
			if (pposNew.getX() > 0)
				pposNew.setX(pposNew.getX() - 1);
			if (pposNew.getY() > 0)
				pposNew.setY(pposNew.getY() - 1);// 向左上方移动
			break;
		}
		
		if (hasPeople(col, pposNew, clist)) // 传入新坐标
			return p.getPpos();// 有人
		else {
			// 更新cell的pid和 ptype
			int indexOri = p.getPpos().getY() * col + p.getPpos().getX();
			int indexTemp = pposNew.getY() * col + pposNew.getX();
			Cell cOri = clist.get(indexOri);
			Cell cNew = clist.get(indexTemp);
			cNew.setPid(cOri.getPid());// 更新新坐标
			cNew.setPtype(cOri.getPtype());// 更新新坐标
			cOri.setPid(-1);// 人走掉了，就没了
			cOri.setPtype(-1);// 人走掉了，就没了
			return pposNew;// 没人
		}
	}

	@Override
	public boolean hasPeople(int col, Position pos, List<Cell> clist) {
		int index = pos.getY() * col + pos.getX();// Y乘以高+X，得到坐标
		Cell c = clist.get(index);// 要移动的格子
		if (c.getPid() == -1)
			return false;// 没人
		else
			return true;// 有人
	}

}
