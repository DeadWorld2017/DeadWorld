package biz;

import po.People;

//�����¼��ӿ�
public interface WorldEvent {
	public boolean isRain();
	// �ж��Ƿ����꣬�ݶ�10%��������
	
	public void isUpMorale();
	// �ж��Ƿ����ʿ��
	
	public void isDownMorale();
	// �ж��Ƿ񽵵�ʿ��
	
	public void finishBattle(People p);
	//ÿ��ս����������ý����жϣ�����ʤ��������ʵ��
	
	public void clear();
	//�¼���־��գ���ÿ�ε�ͼˢ��ʱ����
	
}
