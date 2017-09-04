package biz;

import java.util.List;
import java.util.Random;

import po.Cell;
import po.Land;
import po.Position;

public class MapManageBizImpl implements MapManageBiz {

	int countShelter = 100;// 1,庇护所
	int countRadient = 100;// 2,辐射地
	int coutSwampland = 100;// 3,沼泽地
	int coutDMRiver = 100;// 4,子母河
	int coutDeathtrap = 100;// 5，死亡陷阱
	int coutTrappedLand = 100;// 6，困阵
	int countLand = 600;// 总的数量

	// 初始化空地图
	public void initMapList(int row, int col, List<Cell> clist) {
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				Position p = new Position(i, j);
				Cell c = new Cell(p);
				clist.add(c);
			}
	}

	@Override
	public void initLand(int row, int col, List<Land> llist,List<Cell> clist) {
		Position lpos = new Position();
		int index;// 下标
		Cell c;
		for (int i = 0; i < countLand; i++) {
			int ltype = initLtypeRandom();// 地形种类
			do {
				lpos = initPositionRandom(row, col);
				index = lpos.getY() * col + lpos.getX();// Y乘以长+X，得到坐标
				c = clist.get(index);
			} while (c.getltype() != -1);// 格子上不存在地形，重新生成

			c.setltype(ltype);
			Land l = new Land(ltype, lpos);
			llist.add(l);
		}
	}

	// 随机生成位置，需要传入row,col作为最大随机数的范围
	public Position initPositionRandom(int row, int col) {
		Random rd = new Random();// 随机数
		int x = Math.abs(rd.nextInt(col));
		int y = Math.abs(rd.nextInt(row));
		Position pos = new Position(x, y);// 随机生成位置
		return pos;
	}

	@Override
	public int initLtypeRandom() {
		Random rd = new Random();
		int ltype = rd.nextInt(6) + 1;// 从1~6
		return ltype;
	}

}
