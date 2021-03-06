package biz;

import java.util.List;
import po.Land;
import po.People;
import po.Tool;
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
public interface LandEventBiz {

	// 通过传进物种实例，通过坐标寻找对应的地形，返回对应的land的类型编号（1-6），即ltype
	public int findLandByPeople(People p, List<Cell> clist);

	// 战斗前判断
	// 如果是 1、庇护所 若传进来的是正常人类对象，则修改人类存活能力值，否则不做任何操作
	public int isShelter(People p);

	// 如果是5、 死亡陷阱 所有物种到这里都会死亡,即将对象从List中删去
	public int isDeathtrap();

	// 战斗时判断
	// 如果是2、 辐射地 则正常人攻击力减弱，丧尸攻击力增强,返回的是相应的变化值
	public int isRadient(People p);

	// 如果是3、 沼泽地 则正常人类的攻击力不变(0)，丧尸的攻击力增强
	public int isSwampland(People p);

	// 战斗后判断
	// 如果是4、 子母河 若是人类，就增加这个人的交配几率，返回值是一个几率
	public int isDMRiver(People p);

	// 如果是6、 困阵 物种在此处会停留3年 修改物种的标签进行改变（3递减至0），即刷新地图时候先判断这标签是否为0
	public int isTrappedLand(People p);
		
	//用来管理Land事件的方法
	public int ManageLandEvent(List<People> plist, List<Cell> clist);
	
	//战斗前调用
	public void beforeAttackEvent(int col, List<People> plist, List<Cell> clist,List<Tool> tlist);
	
	//战斗后调用
	public void afterAttackEvent(int col, List<People> plist,List<Cell> clist);

}
