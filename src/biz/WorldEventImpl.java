package biz;

import java.util.Random;

import po.People;

//�����¼��ӿ�ʵ��
public class WorldEventImpl implements WorldEvent {

	private int victoryOfDeadPeopleNumber = 0;// ɥʬʤ��������
	private int victoryOfNormalPeopleNumber = 0;// ������ʤ��������

	private int paramRainProbability = 10;// ������ʵĲ������ݶ�10%�������꣬���ڿɸ���

	@Override
	public boolean isRain() {
		Random rd = new Random();
		int rainProbability = rd.nextInt(100);// �������
		if (rainProbability < paramRainProbability) // 10%��������
			return true;
		else
			return false;
	}

	@Override
	public void isUpMorale() {

	}

	@Override
	public void isDownMorale() {

	}

	@Override
	// ÿ��ս����������ý����жϣ�����ʤ��������ʵ��
	public void finishBattle(People p) {
		if (p.isPtype() == true) // ɥʬʤ��
			victoryOfDeadPeopleNumber++;
		else// ������ʤ��
			victoryOfNormalPeopleNumber++;

	}

	@Override
	// �¼���־��գ���ÿ�ε�ͼˢ��ʱ����
	public void clear() {
		victoryOfDeadPeopleNumber = 0;
		victoryOfNormalPeopleNumber = 0;

	}

	public int getKillDeadPeopleNumber() {
		return victoryOfDeadPeopleNumber;
	}

	public void setKillDeadPeopleNumber(int victoryOfDeadPeopleNumber) {
		this.victoryOfDeadPeopleNumber = victoryOfDeadPeopleNumber;
	}

	public int getKillNormalPeopleNumber() {
		return victoryOfNormalPeopleNumber;
	}

	public void setKillNormalPeopleNumber(int victoryOfNormalPeopleNumber) {
		this.victoryOfNormalPeopleNumber = victoryOfNormalPeopleNumber;
	}

}
