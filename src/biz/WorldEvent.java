package biz;

import po.People;

//世界事件接口
public interface WorldEvent {
	public boolean isRain();
	// 判断是否下雨，暂定10%概率下雨
	
	public boolean isUpMorale();
	// 地图刷新时，在事件标志清空前调用，判断是否鼓舞士气，若返回true则使全局UpMoraleFlag置为true
	
	public boolean isDownMorale();
	// 地图刷新时，在事件标志清空前调用，判断是否降低士气，若返回true则使全局DownMoraleFlag置为true
	
	public int attackByRain(int attackValue);
	// 可能需要放进attack事件里面，暂时放在这
	
	
	/*不太对，没电了不改了
	public int attackByUpMorable(Boolean UpMoraleFlag,int attackValue);
	// 传入是否有
	
	public int attackByDownMorable(Boolean DownMoraleFlag,int attackValue);
	*/
	
	public void finishBattle(People p);
	//每次战斗结束后调用进行判断，传入胜利方的类实例
	
	public void clear();
	//事件标志清空，在每次地图刷新时调用
	
}
