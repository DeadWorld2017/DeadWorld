package biz;

import java.util.List;
import java.util.Random;

import po.Cell;
import po.Land;
import po.Position;
import po.Tool;

public class MapManageBizImpl implements MapManageBiz {

	int countShelter = 100;// 1,�ӻ���
	int countRadient = 100;// 2,�����
	int coutSwampland = 100;// 3,�����
	int coutDMRiver = 100;// 4,��ĸ��
	int coutDeathtrap = 100;// 5����������
	int coutTrappedLand = 100;// 6������
	int countLand = 600;// �ܵ�����
	
	
	int countKnife = 200;
	int countGun = 100;
	int countBazooka = 60;
	int countBomb = 20;
	int countEscapeShoes = 20;
	int countTool= 400;
	

	// ��ʼ���յ�ͼ
	public void initMapList(int row, int col, List<Cell> clist) {
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				Position p = new Position(i, j);
				Cell c = new Cell(p);
				clist.add(c);
			}
	}

	//���ɵ���
	public void initLand(int row, int col, List<Land> llist,List<Cell> clist) {
		Position lpos = new Position();
		int index;// �±�
		Cell c;
		for (int i = 0; i < countLand; i++) {
			int ltype = initLtypeRandom();// ��������
			do {
				lpos = initPositionRandom(row, col);
				index = lpos.getY() * col + lpos.getX();// Y���Գ�+X���õ�����
				c = clist.get(index);
			} while (c.getltype() != -1);// �����ϲ����ڵ��Σ���������

			c.setltype(ltype);
			Land l = new Land(ltype, lpos);
			llist.add(l);
		}
	}

	// �������λ�ã���Ҫ����row,col��Ϊ���������ķ�Χ
	public Position initPositionRandom(int row, int col) {
		Random rd = new Random();// �����
		int x = Math.abs(rd.nextInt(col));
		int y = Math.abs(rd.nextInt(row));
		Position pos = new Position(x, y);// �������λ��
		return pos;
	}


	public int initLtypeRandom() {
		Random rd = new Random();
		int ltype = rd.nextInt(6) + 1;// ��1~6
		//if (ltype==5)
		//		return 1;
		return 0;
	}

	//���ɵ���
	
	public boolean initTool(int row, int col, List<Tool> tlist, List<Cell> clist) {
		Position tpos = new Position();
		int index;// �±�
		Cell c;
		for (int i = 0; i < countTool; i++) {
			int tid=i;
			int ttype = initTtypeRandom();// ��������
			do {
				tpos = initPositionRandom(row, col);
				index = tpos.getY() * col + tpos.getX();// Y���Գ�+X���õ�����
				c = clist.get(index);
			} while (c.getttype() != -1);// �����ϲ����ڵ��ߣ���������

			
			c.setttype(ttype);
			c.setTid(tid);
			
			Tool t = new Tool(tid,ttype,tpos);
			tlist.add(t);
		}
		return false;
	}

	
	public int initTtypeRandom() {
		Random rd = new Random();
		int ttype = rd.nextInt(400);
		if(ttype<200)
			return 1;//��
		else if(ttype<300)
			return 2;//ǹ
		else if(ttype<360)
			return 3;//���Ͳ
		else if(ttype<380)
			return 4;//��ɱ��
		else if(ttype<400)
			return 5;//����Ь
		else 
			return -1;//����
	}
	
	
	

}
