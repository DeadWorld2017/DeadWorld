package biz;

import java.util.Random;

import po.People;

//世界事件接口实现
public class WorldEventBizImpl implements WorldEventBiz {

	private int victoryOfDeadPeopleNumber = 0;// 丧尸胜利的数量
	private int victoryOfNormalPeopleNumber = 0;// 正常人胜利的数量

	private boolean isRainFlag = false;// 下雨标志
	private boolean isUpMoraleFlag = false;// 鼓舞标志
	private boolean isDownMoraleFlag = false;// 沮丧标志

	private int paramRainProbability = 10;// 下雨概率的参数，暂定10%概率下雨，后期可更改
	private double paramAttackByRain = 0.8;// 下雨影响攻击力的参数，暂定0.8
	private double paramAttackByUpMorale = 1.1;// 鼓舞影响攻击力的参数
	private double paramAttackByDownMorale = 0.9;// 沮丧影响攻击力的参数
	private int paramVictoryConditionNumber = 10;// 诱发鼓舞和沮丧的概率条件参数

	
	// 判断是否下雨，暂定10%概率下雨，只在地图刷新时调用一次
	public void isRain() {
		Random rd = new Random();
		int rainProbability = rd.nextInt(100);// 下雨概率
		if (rainProbability < paramRainProbability) // 10%概率下雨
			setRainFlag(true);
		else
			setRainFlag(false);
	}


	// 只在地图刷新时，在事件标志清空前调用一次，判断是否鼓舞士气，若返回true则使全局UpMoraleFlag置为true
	public void isUpMorale() {
		if (victoryOfDeadPeopleNumber > paramVictoryConditionNumber)
			setUpMoraleFlag(true);
		else
			setUpMoraleFlag(false);
	}

	
	// 只在地图刷新时，在事件标志清空前调用一次，判断是否降低士气，若返回true则使全局DownMoraleFlag置为true
	public void isDownMorale() {
		if (victoryOfNormalPeopleNumber > paramVictoryConditionNumber)
			setUpMoraleFlag(true);
		else
			setUpMoraleFlag(false);
	}

	
	// 每次战斗结束后调用进行判断，传入胜利方的类实例
	public void finishBattle(People p) {
		if (p.getPtype() == 0) // 丧尸胜利
			victoryOfDeadPeopleNumber++;
		else// 正常人胜利
			victoryOfNormalPeopleNumber++;

	}

	
	public double attackByWorldEvent(double attackValue) {
		if(isRainFlag())//下雨了调用计算
			attackValue = attackByRain(attackValue);
		if(isUpMoraleFlag())//鼓舞了调用计算
			attackValue = attackByUpMorable(attackValue);
		if(isDownMoraleFlag())//沮丧了调用计算
			attackValue = attackByDownMorable(attackValue);
		return attackValue;
	}


	// 下雨天的攻击影响事件，只需要在计算攻击力的时候调用即可，不需要在外部代码中调用
	public double attackByRain(double attackValue) {
		attackValue *= paramAttackByRain;// 暂定0.8
		return attackValue;
	}


	// 被鼓的攻击力影响事件
	public double attackByUpMorable(double attackValue) {
		attackValue *= paramAttackByUpMorale;
		return attackValue;
	}

	
	// 沮丧的攻击力影响事件
	public double attackByDownMorable(double attackValue) {
		attackValue *= paramAttackByDownMorale;
		return 0;
	}

	
	// 事件标志清空，在每次地图刷新时调用
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
