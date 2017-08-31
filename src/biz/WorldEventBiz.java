package biz;

import po.People;

//世界事件接口
public interface WorldEventBiz {
	public void isRain();
	// 判断是否下雨，暂定10%概率下雨，只在地图刷新时调用一次

	public void isUpMorale();
	// 只在地图刷新时，在事件标志清空前调用一次，判断是否鼓舞士气，若返回true则使全局UpMoraleFlag置为true

	public void isDownMorale();
	// 只在地图刷新时，在事件标志清空前调用一次，判断是否降低士气，若返回true则使全局DownMoraleFlag置为true

	public void finishBattle(People p);
	// 每次战斗结束后调用进行判断，传入胜利方的类实例
	// 可能需要放进attack事件里面，暂时放在这

	public double attackByWorldEvent(double attackValue);
	// 计算攻击力时调用攻击力事件的通式，攻击力计算调用该方法，内部包含 attackByRain等
	// 只有计算人的攻击力的时候才需要调用，丧尸不用

	public double attackByRain(double attackValue);
	// 下雨天的攻击影响事件，只需要在计算攻击力的时候调用即可，不需要在外部代码中调用

	public double attackByUpMorable(double attackValue);
	// 被鼓的攻击力影响事件

	public double attackByDownMorable(double attackValue);
	// 沮丧的攻击力影响事件

	public void clear();
	// 事件标志清空，在每次地图刷新时调用

}
