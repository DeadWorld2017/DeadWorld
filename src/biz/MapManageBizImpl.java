package biz;

import java.util.List;
import java.util.Random;

import po.Cell;
import po.Land;
import po.Position;
import po.Tool;

public class MapManageBizImpl implements MapManageBiz {

	int countShelter = 100;// 1,庇护所
	int countRadient = 100;// 2,辐射地
	int coutSwampland = 100;// 3,沼泽地
	int coutDMRiver = 100;// 4,子母河
	int coutDeathtrap = 100;// 5，死亡陷阱
	int coutTrappedLand = 100;// 6，困阵
	int countLand = 2000;// 总的数量
	
	
	int countKnife = 200;
	int countGun = 100;
	int countBazooka = 60;
	int countBomb = 20;
	int countEscapeShoes = 20;
	int countTool= 1000;
	

	// 初始化空地图
	public void initMapList(int row, int col, List<Cell> clist) {
		//貌似对应关系反了，改了过来
		for (int i = 0; i < col; i++)
			for (int j = 0; j < row; j++) {
				Position p = new Position(i, j);
				Cell c = new Cell(p);
				clist.add(c);
			}
	}

	//生成地形
	public void initLand(int row, int col, List<Land> llist,List<Cell> clist) {
		Position lpos = new Position();
		int index;// 下标
		Cell c;
		for (int i = 0; i < countLand; i++) {
			int ltype = initLtypeRandom();// 地形种类
			do {
				lpos = initPositionRandom(row, col);
				index = lpos.getX() * row + lpos.getY();// 改成row（长 即列数）* x + y
				c = clist.get(index);
			} while (c.getltype() != -1);// 格子上存在地形，则重新生成

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


	public int initLtypeRandom() {
		Random rd = new Random();
		int ltype = rd.nextInt(6) + 1;// 从1~6
		return ltype;
	}

	//生成道具
	
	public boolean initTool(int row, int col, List<Tool> tlist, List<Cell> clist) {
		Position tpos = new Position();
		int index;// 下标
		Cell c;
		for (int i = 0; i < countTool; i++) {
			int tid=i;
			int ttype = initTtypeRandom();// 道具种类
			do {
				tpos = initPositionRandom(row, col);
				index = tpos.getY() * col + tpos.getX();// Y乘以长+X，得到坐标
				c = clist.get(index);
			} while (c.getttype() != -1);// 格子上不存在道具，重新生成

			
			c.setttype(ttype);
			c.setTid(tid);
			
			Tool t = new Tool(tid,ttype,tpos);
			tlist.add(t);
		}
		return false;
	}

	
	public int initTtypeRandom() {
		Random rd = new Random();
		int ttype = rd.nextInt(1500);
		if(ttype<300)
			return 1;//刀
		else if(ttype<500)
			return 2;//枪
		else if(ttype<600)
			return 3;//火箭筒
		else if(ttype<800)
			return 4;//自杀弹
		else if(ttype<1000)
			return 5;//逃跑鞋
		else 
			return -1;//出错
	}
	
	
	

}
