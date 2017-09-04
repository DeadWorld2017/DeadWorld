package biz;

import java.util.List;
import java.util.Random;

import po.Cell;
import po.Land;
import po.Position;

public class MapManageBizImpl implements MapManageBiz {

	int countShelter = 100;// 1,�ӻ���
	int countRadient = 100;// 2,�����
	int coutSwampland = 100;// 3,�����
	int coutDMRiver = 100;// 4,��ĸ��
	int coutDeathtrap = 100;// 5����������
	int coutTrappedLand = 100;// 6������
	int countLand = 600;// �ܵ�����

	// ��ʼ���յ�ͼ
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

	@Override
	public int initLtypeRandom() {
		Random rd = new Random();
		int ltype = rd.nextInt(6) + 1;// ��1~6
		return ltype;
	}

}
