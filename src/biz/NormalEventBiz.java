package biz;

import java.util.List;

import po.Cell;
import po.People;


//�����¼��ӿ���
public interface NormalEventBiz {
	
	//�������¼�
	//ͳ�������ӵģ�����һ����ѭ��
	public void PregnantManage(List<People> plist, List<Cell> clist, int row, int col);
	
	//�ж���Χ���������ߣ�����ƥ��Ķ���
	public People PregnantMatch(People p, List<People> plist, List<Cell> clist, int row, int col);
	
	//��ƥ��õĶ���ʵ�ֽ��䣬�����²����ĺ���ʵ��
	public People CreateChild(People p1, People p2);
	
	//�����¼�
	//ս���������������������ǵ����������
	public void DeadEvent(People p, List<People> plist);
	
	//�Ƿ���ڿ���
	public int isAntibody(People p);
	
	//��������
	//������������������������
	public void AgeEvent(List<People> p);
}
