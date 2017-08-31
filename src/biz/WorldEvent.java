package biz;

import po.People;

//世界事件接口
public interface WorldEvent {
	public boolean isRain();
	// 判断是否下雨，暂定10%概率下雨
	
	public void isUpMorale();
	// 判断是否鼓舞士气
	
	public void isDownMorale();
	// 判断是否降低士气
	
	public void finishBattle(People p);
	//每次战斗结束后调用进行判断，传入胜利方的类实例
	
	public void clear();
	//事件标志清空，在每次地图刷新时调用
	
}
