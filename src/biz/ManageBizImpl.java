package biz;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import po.Cell;
import po.People;
import po.Position;
import po.Tool;

public class ManageBizImpl implements ManageBiz {


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


	// �жϷ���1-8��8��������0Ϊ������˳ʱ����ת��0Ϊ�̶�����
	public int direction() {
		Random rd = new Random();
		int direction = rd.nextInt(9);
		return direction;
	}

	
	//
	public Position moveByDirection(int row, int col, int direction, People p, List<Cell> clist) {
		Position pposNew = new Position(p.getPpos().getX(),p.getPpos().getY());// ��¼�µ�λ��
		switch (direction) {
		case 0:
			return p.getPpos();
		case 1:
			if (pposNew.getX() > 0) 
				pposNew.setX(pposNew.getX() - 1); // �����ƶ�
			break;
		case 2:
			if (pposNew.getX() > 0)
				pposNew.setX(pposNew.getX() - 1);
			if (pposNew.getY() < row - 1)
				pposNew.setY(pposNew.getY() + 1);// �����Ϸ��ƶ�
			break;
		case 3:
			if (pposNew.getY() < row - 1)
				pposNew.setY(pposNew.getY() + 1);// ���Ҳ��ƶ�
			break;
		case 4:
			if (pposNew.getX() < col - 1)
				pposNew.setX(pposNew.getX() + 1);
			if (pposNew.getY() < row - 1)
				pposNew.setY(pposNew.getY() + 1);// �����·��ƶ�
			break;
		case 5:
			if (pposNew.getX() < col - 1)
				pposNew.setX(pposNew.getX() + 1);// ���·��ƶ�
			break;
		case 6:
			if (pposNew.getX() < col - 1)
				pposNew.setX(pposNew.getX() + 1);
			if (pposNew.getY() > 0)
				pposNew.setY(pposNew.getY() - 1);// �����·��ƶ�
			break;
		case 7:
			if (pposNew.getY() > 0)
				pposNew.setY(pposNew.getY() - 1);// ������ƶ�
			break;
		case 8:
			if (pposNew.getX() > 0)
				pposNew.setX(pposNew.getX() - 1);
			if (pposNew.getY() > 0)
				pposNew.setY(pposNew.getY() - 1);// �����Ϸ��ƶ�
			break;
		}
		
		if (hasPeople(col, pposNew, clist)) // ����������
			return p.getPpos();// ����
		else {
			// ����cell��pid�� ptype
			int indexOri = p.getPpos().getY() * col + p.getPpos().getX();
			int indexTemp = pposNew.getY() * col + pposNew.getX();
			Cell cOri = clist.get(indexOri);
			Cell cNew = clist.get(indexTemp);
			cNew.setPid(cOri.getPid());// ����������
			cNew.setPtype(cOri.getPtype());// ����������
			cOri.setPid(-1);// ���ߵ��ˣ���û��
			cOri.setPtype(-1);// ���ߵ��ˣ���û��
			return pposNew;// û��
		}
	}


	public boolean hasPeople(int col, Position pos, List<Cell> clist) {
		int index = pos.getY() * col + pos.getX();// Y���Ը�+X���õ�����
		Cell c = clist.get(index);// Ҫ�ƶ��ĸ���
		if (c.getPid() == -1)
			return false;// û��
		else
			return true;// ����
	}



}
