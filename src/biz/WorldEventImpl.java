package biz;

import java.util.Random;

import po.People;

//世界事件接口实现
public class WorldEventImpl implements WorldEvent {

	private int victoryOfDeadPeopleNumber = 0;// 丧尸胜利的数量
	private int victoryOfNormalPeopleNumber = 0;// 正常人胜利的数量

	private int paramRainProbability = 10;// 下雨概率的参数，暂定10%概率下雨，后期可更改
	private double paramAttackByRain=0.8;//下雨影响攻击力的参数，暂定0.8
	
	@Override
	public boolean isRain() {
		Random rd = new Random();
		int rainProbability = rd.nextInt(100);// 下雨概率
		if (rainProbability < paramRainProbability) // 10%概率下雨
			return true;
		else
			return false;
	}
 

	@Override
	// 每次战斗结束后调用进行判断，传入胜利方的类实例
	public void finishBattle(People p) {
		if (p.isPtype() == true) // 丧尸胜利
			victoryOfDeadPeopleNumber++;
		else// 正常人胜利
			victoryOfNormalPeopleNumber++;

	}

	@Override
	// 事件标志清空，在每次地图刷新时调用
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

	@Override
	
	public int attackByRain(int attackValue) {
		attackValue *= paramAttackByRain;//传入攻击力，乘以百分比
		return 0;
	}


	@Override
	public boolean isUpMorale() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isDownMorale() {
		// TODO Auto-generated method stub
		return false;
	}

}
