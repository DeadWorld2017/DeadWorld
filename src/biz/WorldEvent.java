package biz;

import po.People;

//�����¼��ӿ�
public interface WorldEvent {
	public boolean isRain();
	// �ж��Ƿ����꣬�ݶ�10%��������
	
	public boolean isUpMorale();
	// ��ͼˢ��ʱ�����¼���־���ǰ���ã��ж��Ƿ����ʿ����������true��ʹȫ��UpMoraleFlag��Ϊtrue
	
	public boolean isDownMorale();
	// ��ͼˢ��ʱ�����¼���־���ǰ���ã��ж��Ƿ񽵵�ʿ����������true��ʹȫ��DownMoraleFlag��Ϊtrue
	
	public int attackByRain(int attackValue);
	// ������Ҫ�Ž�attack�¼����棬��ʱ������
	
	
	/*��̫�ԣ�û���˲�����
	public int attackByUpMorable(Boolean UpMoraleFlag,int attackValue);
	// �����Ƿ���
	
	public int attackByDownMorable(Boolean DownMoraleFlag,int attackValue);
	*/
	
	public void finishBattle(People p);
	//ÿ��ս����������ý����жϣ�����ʤ��������ʵ��
	
	public void clear();
	//�¼���־��գ���ÿ�ε�ͼˢ��ʱ����
	
}
