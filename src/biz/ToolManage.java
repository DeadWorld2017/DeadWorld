package biz;

import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//���߹���ӿ�
public interface ToolManage {
	
	public boolean isWeopon(int ttype);
	//�ж��Ƿ������������������򷵻�true,��û�������򷵻�false
	
	public int addWeoponDamage(int attackValue);
	//�����������˺�,����ֵΪint��
	
	
	public boolean isBomb(int ttype);
	//�ж��Ƿ���ը��������ը���򷵻�true,��û��ը���򷵻�false
	
	public void bombFuction(List<People> plist,
			NormalPeople np,DeadPeople dp);
	//���뷢��ս����˫����ʵ��,����ʵ��������list��ɾ��
	
	public void destoryBomb(NormalPeople np,List<Tool> tlist,
			int tid);
	//�����������tid����ȫ��tlist������Я��tlist������
	
	public boolean isShoes(int ttype);
	//�ж��Ƿ���Ь��,���򲻱䷵��true��û���򷵻�false
	
	public boolean escapeShoes();//����Ь����
	
	public boolean speedShoes();//����Ь����
	
	public boolean isDoping();//�Ƿ����˷ܼ�
	
	public People findPid(int pid,List<People> plist);
	
}
