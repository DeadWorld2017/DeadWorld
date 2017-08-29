package biz;

import po.Land;
import po.NormalPeople;

/*地形类,存放对应得地图坐标和地形类型
 * 
 * 类型1：庇护所
 * 类型2：辐射地
 * 类型3：沼泽地
 * 类型4：子母河
 * 类型5：死亡陷阱
 * 类型6：困阵	
 * 
 */
//地形事件接口
public interface LandEvent {
	
	//通过传进人类实例，通过坐标寻找对应的地形，返回对应的land的类型编号（1-6），即ltype
	public int findLandByPeople(NormalPeople np);

	//战斗前判断,若是则根据事件返回相
	public int isShelter(int ltype,NormalPeople np);
	
	//战斗时判断
	
	//战斗后判断

}
