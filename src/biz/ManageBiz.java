package biz;

import java.util.List;

import po.People;
import po.Position;

public interface ManageBiz {
	
	public void randomMove(List<People> plist);
	
	public int direction();
	//�жϷ���1-8��8��������0Ϊ������˳ʱ����ת��0Ϊ�̶�����
	
	public Position moveByDirection(int direction,People p);
	//�������ƶ�
}
