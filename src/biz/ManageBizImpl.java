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
	//�жϷ���1-8��8��������0Ϊ������˳ʱ����ת��0Ϊ�̶�����
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
			break;//����
		case 1:
			temppos.setX(temppos.getX()-1);//�����ƶ�
			break;
		case 2:
			temppos.setX(temppos.getX()-1);
			temppos.setY(temppos.getY()+1);//�����Ϸ��ƶ�
			break;
		case 3:
			temppos.setY(temppos.getY()+1);//���Ҳ��ƶ�
			break;
		case 4:
			temppos.setX(temppos.getX()+1);
			temppos.setY(temppos.getY()+1);//�����·��ƶ�
			break;
		case 5:
			temppos.setX(temppos.getX()+1);//���·��ƶ�
			break;
		case 6:
			temppos.setX(temppos.getX()+1);
			temppos.setY(temppos.getY()-1);//�����·��ƶ�
			break;
		case 7:
			temppos.setY(temppos.getY()-1);//������ƶ�
			break;
		case 8:
			temppos.setX(temppos.getX()-1);
			temppos.setY(temppos.getY()-1);//�����Ϸ��ƶ�
			break;
		}		
		return temppos;
	}
}
