package biz;

import java.util.List;

import po.Cell;
import po.NormalPeople;
import po.People;


//�����¼��ӿ���
public interface NormalEventBiz {
	
	//�������¼�
	//ͳ�������ӵģ�����һ����ѭ��
	public void PregnantManage(List<People> plist, List<Cell> clist, int row, int col);
	
	//�ж���Χ���������ߣ�����ƥ��Ķ���
	public NormalPeople PregnantMatch(People p, List<People> plist, List<Cell> clist, int row, int col);
	
	//�ж���Χ���ӣ�������������list
	public void JudgeCell(int x, int y, List<People> markPList, List<People> plist);
	
	//��ƥ��õĶ���ʵ�ֽ��䣬�����²����ĺ���ʵ��
	public People CreateChild(NormalPeople p1, NormalPeople p2);
	
	//�����¼�
	//ս���������������������ǵ����������
	public void DeadEvent(People p, List<People> plist);
	
	//�Ƿ���ڿ���
	public int isAntibody(NormalPeople p);
	
	//��������
	//������������������������
	public void AgeEvent(List<People> p);
	

}
