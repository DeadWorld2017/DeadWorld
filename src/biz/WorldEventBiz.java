package biz;

import po.People;

//�����¼��ӿ�
public interface WorldEventBiz {
	public void isRain();
	// �ж��Ƿ����꣬�ݶ�10%�������ֻ꣬�ڵ�ͼˢ��ʱ����һ��

	public void isUpMorale();
	// ֻ�ڵ�ͼˢ��ʱ�����¼���־���ǰ����һ�Σ��ж��Ƿ����ʿ����������true��ʹȫ��UpMoraleFlag��Ϊtrue

	public void isDownMorale();
	// ֻ�ڵ�ͼˢ��ʱ�����¼���־���ǰ����һ�Σ��ж��Ƿ񽵵�ʿ����������true��ʹȫ��DownMoraleFlag��Ϊtrue

	public void finishBattle(People p);
	// ÿ��ս����������ý����жϣ�����ʤ��������ʵ��
	// ������Ҫ�Ž�attack�¼����棬��ʱ������

	public double attackByWorldEvent(double attackValue);
	// ���㹥����ʱ���ù������¼���ͨʽ��������������ø÷������ڲ����� attackByRain��
	// ֻ�м����˵Ĺ�������ʱ�����Ҫ���ã�ɥʬ����

	public double attackByRain(double attackValue);
	// ������Ĺ���Ӱ���¼���ֻ��Ҫ�ڼ��㹥������ʱ����ü��ɣ�����Ҫ���ⲿ�����е���

	public double attackByUpMorable(double attackValue);
	// ���ĵĹ�����Ӱ���¼�

	public double attackByDownMorable(double attackValue);
	// ��ɥ�Ĺ�����Ӱ���¼�

	public void clear();
	// �¼���־��գ���ÿ�ε�ͼˢ��ʱ����

}
