package biz;

import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//���߹���ӿ�
public interface ToolManageBiz {

	
	public boolean isWeopon(int ttype);
	// �ж��Ƿ������������������򷵻�true,��û�������򷵻�false

	public int addWeoponDamage(int attackValue);
	// �����������˺�,����ֵΪint��

	public boolean isBomb(NormalPeople np);
	// ��������ս�ܵ�ʱ��ѭ���ж��Ƿ���ը�� �������򷵻�true
	

	public void bombFunction(List<People> plist, NormalPeople np, DeadPeople dp);
	// ���뷢��ս����˫����ʵ��,����ʵ��������list��ɾ��

	public void destoryBomb(NormalPeople np, List<Tool> tlist, int tid);
	// �����������tid����ȫ��tlist������Я��tlist������

	public boolean isShoes(int ttype);
	// �ж��Ƿ���Ь��,���򲻱䷵��true��û���򷵻�false

	public boolean escapeShoes();
	// ����Ь����

	
	/*δ��ɵ��˷ܼ�
	 public int isDoping(NormalPeople np);
	// �ж��Ƿ����˷ܼ�,��û�з���-1�������򷵻�tid
	
	public double attackByDoping(double attackValue);
	//�˷ܼ��Ĺ��ܣ�����50%�Ĺ�����
	
	public void dopingDuration(NormalPeople np, List<Tool> tlist);
	//�ж��˷ܼ�ʱ�䣬����ʱ������
	 */
}
