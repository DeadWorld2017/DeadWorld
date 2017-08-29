package biz;

import java.util.List;
import po.Land;
import po.People;
import po.Cell;

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
	
	//通过传进物种实例，通过坐标寻找对应的地形，返回对应的land的类型编号（1-6），即ltype
	public int findLandByPeople(People np, List<Cell> listC);
		
	//战斗前判断
	//如果是 1、庇护所  若传进来的是正常人类对象，则修改人类存活能力值，否则不做任何操作
	public void isShelter(People np);
	//如果是5、 死亡陷阱  所有物种到这里都会死亡,即将对象从List中删去
	public void isDeathtrap(People p);
		
	//战斗时判断
	//如果是2、 辐射地  则正常人攻击力减弱，丧尸攻击力增强,返回的是相应的变化值 
	public int isRadient();
	//如果是3、 沼泽地  则正常人类的攻击力不变(0)，丧尸的攻击力增强
	public int isSwampland();
		
	//战斗后判断
	//如果是4、 子母河  若是人类，就增加这个人的交配几率，返回值是一个几率
	public int isDMRiver();
	//如果是6、 困阵  物种在此处会停留3年 修改物种的标签进行改变（3递减至0），即刷新地图时候先判断这标签是否为0
	public void isTrappedLand(People p);

}
