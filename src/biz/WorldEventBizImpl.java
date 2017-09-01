package biz;

import java.util.Random;

import po.People;

//�����¼��ӿ�ʵ��
public class WorldEventBizImpl implements WorldEventBiz {

	private int victoryOfDeadPeopleNumber = 0;// ɥʬʤ��������
	private int victoryOfNormalPeopleNumber = 0;// ������ʤ��������

	private boolean isRainFlag = false;// �����־
	private boolean isUpMoraleFlag = false;// �����־
	private boolean isDownMoraleFlag = false;// ��ɥ��־

	private int paramRainProbability = 10;// ������ʵĲ������ݶ�10%�������꣬���ڿɸ���
	private double paramAttackByRain = 0.8;// ����Ӱ�칥�����Ĳ������ݶ�0.8
	private double paramAttackByUpMorale = 1.1;// ����Ӱ�칥�����Ĳ���
	private double paramAttackByDownMorale = 0.9;// ��ɥӰ�칥�����Ĳ���
	private int paramVictoryConditionNumber = 10;// �շ�����;�ɥ�ĸ�����������

	@Override
	// �ж��Ƿ����꣬�ݶ�10%�������ֻ꣬�ڵ�ͼˢ��ʱ����һ��
	public void isRain() {
		Random rd = new Random();
		int rainProbability = rd.nextInt(100);// �������
		if (rainProbability < paramRainProbability) // 10%��������
			setRainFlag(true);
		else
			setRainFlag(false);
	}

	@Override
	// ֻ�ڵ�ͼˢ��ʱ�����¼���־���ǰ����һ�Σ��ж��Ƿ����ʿ����������true��ʹȫ��UpMoraleFlag��Ϊtrue
	public void isUpMorale() {
		if (victoryOfDeadPeopleNumber > paramVictoryConditionNumber)
			setUpMoraleFlag(true);
		else
			setUpMoraleFlag(false);
	}

	@Override
	// ֻ�ڵ�ͼˢ��ʱ�����¼���־���ǰ����һ�Σ��ж��Ƿ񽵵�ʿ����������true��ʹȫ��DownMoraleFlag��Ϊtrue
	public void isDownMorale() {
		if (victoryOfNormalPeopleNumber > paramVictoryConditionNumber)
			setUpMoraleFlag(true);
		else
			setUpMoraleFlag(false);
	}

	@Override
	// ÿ��ս����������ý����жϣ�����ʤ��������ʵ��
	public void finishBattle(People p) {
		if (p.getPtype() == 0) // ɥʬʤ��
			victoryOfDeadPeopleNumber++;
		else// ������ʤ��
			victoryOfNormalPeopleNumber++;

	}

	@Override
	public double attackByWorldEvent(double attackValue) {
		if(isRainFlag())//�����˵��ü���
			attackValue = attackByRain(attackValue);
		if(isUpMoraleFlag())//�����˵��ü���
			attackValue = attackByUpMorable(attackValue);
		if(isDownMoraleFlag())//��ɥ�˵��ü���
			attackValue = attackByDownMorable(attackValue);
		return attackValue;
	}

	@Override
	// ������Ĺ���Ӱ���¼���ֻ��Ҫ�ڼ��㹥������ʱ����ü��ɣ�����Ҫ���ⲿ�����е���
	public double attackByRain(double attackValue) {
		attackValue *= paramAttackByRain;// �ݶ�0.8
		return attackValue;
	}

	@Override
	// ���ĵĹ�����Ӱ���¼�
	public double attackByUpMorable(double attackValue) {
		attackValue *= paramAttackByUpMorale;
		return attackValue;
	}

	@Override
	// ��ɥ�Ĺ�����Ӱ���¼�
	public double attackByDownMorable(double attackValue) {
		attackValue *= paramAttackByDownMorale;
		return 0;
	}

	@Override
	// �¼���־��գ���ÿ�ε�ͼˢ��ʱ����
	public void clear() {
		victoryOfDeadPeopleNumber = 0;
		victoryOfNormalPeopleNumber = 0;

	}

	public int getVictoryOfDeadPeopleNumber() {
		return victoryOfDeadPeopleNumber;
	}

	public void setVictoryOfDeadPeopleNumber(int victoryOfDeadPeopleNumber) {
		this.victoryOfDeadPeopleNumber = victoryOfDeadPeopleNumber;
	}

	public int getVictoryOfNormalPeopleNumber() {
		return victoryOfNormalPeopleNumber;
	}

	public void setVictoryOfNormalPeopleNumber(int victoryOfNormalPeopleNumber) {
		this.victoryOfNormalPeopleNumber = victoryOfNormalPeopleNumber;
	}

	public boolean isRainFlag() {
		return isRainFlag;
	}

	public void setRainFlag(boolean isRainFlag) {
		this.isRainFlag = isRainFlag;
	}

	public boolean isUpMoraleFlag() {
		return isUpMoraleFlag;
	}

	public void setUpMoraleFlag(boolean isUpMoraleFlag) {
		this.isUpMoraleFlag = isUpMoraleFlag;
	}

	public boolean isDownMoraleFlag() {
		return isDownMoraleFlag;
	}

	public void setDownMoraleFlag(boolean isDownMoraleFlag) {
		this.isDownMoraleFlag = isDownMoraleFlag;
	}

	public int getParamRainProbability() {
		return paramRainProbability;
	}

	public void setParamRainProbability(int paramRainProbability) {
		this.paramRainProbability = paramRainProbability;
	}

	public double getParamAttackByRain() {
		return paramAttackByRain;
	}

	public void setParamAttackByRain(double paramAttackByRain) {
		this.paramAttackByRain = paramAttackByRain;
	}

}
