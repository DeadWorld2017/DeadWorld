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
	public People CreateChild(NormalPeople p1, NormalPeople p2, int num, List<Cell> clist, int row, int col);
	
	//�����¼�
	//ս���������������������ǵ����������
	public void DeadEvent(People p, List<People> plist,List<People> deadList);
	
	//���ڿ���ǰ���£�������������¼�
	public int AntibodyRandomEvent();
	
	
	public void DestroyCell(int col, List<Cell> clist, People p);
	
	/*
	//����ȫ���˵�ͣ��ʱ��
	public void AdjustTrapYear(List<People> plist);
	
	//����ȫ���˵Ļ��б�ǩ
	public void AdjustPregnancy(List<People> plist);
	
	//����ȫ���˵�����
	public void AdjustAge(List<People> plist);
	*/
	
	//����ȫ���˵�ͣ��ʱ�䡢���б�ǩ�����䣬��һ������������Խ�ʡ������ѭ��
	public void AdjustNormalPeopleAttr(List<People> plist);
	
	//�����¼�����People�����list���Ƴ�ͬʱ����clist�ж�Ӧ��ֵ
	//public void DeadEvent(People p, List<People> plist, List<Cell> clist);
	

}
