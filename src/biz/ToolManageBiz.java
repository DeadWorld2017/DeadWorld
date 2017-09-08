package biz;

import java.util.List;

import po.Cell;
import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//���߹���ӿ�
public interface ToolManageBiz {

	public void pickTool(List<People> plist,List<Cell> clist, List<Tool> tlist,int col);
	
	public void destoryTool(List<Tool> tlist, int tid);
	// ������ߵ�tid����ȫ��tlist������
	public boolean compareTool(NormalPeople np,int ttype);
	//�Ƿ�������
	public boolean isTool();
	
	public int isWeopon(NormalPeople np);
	// �ж��Ƿ������������������򷵻�tid
	
	public int isBomb(NormalPeople np);
	// ��������ս�ܵ�ʱ��ѭ���ж��Ƿ���ը�� ����û�з���-1�������򷵻�tid

	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp,List<Tool> tlist);
	// ���뷢��ս����˫����ʵ��,����ʵ��������list��ɾ��

	
	public int isEscapeShoes(NormalPeople np);
	// �ж��Ƿ�������Ь��,���򲻱䷵��tid��û���򷵻�-1

	public boolean escapeShoeFunction(List<People> plist, NormalPeople np,List<Tool> tlist,int tid);
	// ����Ь����������ж��Ƿ����ܣ��������true��ʲô�����������������false�����ִ�в���

 
	
	/*δ��ɵ��˷ܼ�
	 public int isDoping(NormalPeople np);
	// �ж��Ƿ����˷ܼ�,��û�з���-1�������򷵻�tid
	
	public double attackByDoping(double attackValue);
	//�˷ܼ��Ĺ��ܣ�����50%�Ĺ�����
	
	public void dopingDuration(NormalPeople np, List<Tool> tlist);
	//�ж��˷ܼ�ʱ�䣬����ʱ������
	 */
}
