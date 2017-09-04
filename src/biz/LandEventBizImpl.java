package biz;

import java.util.Iterator;
import java.util.List;

import po.Cell;
import po.NormalPeople;
import po.People;

//土地事件接口实现
public class LandEventBizImpl implements LandEventBiz {

	public int findLandByPeople(People p, List<Cell> clist) {
		// 通过传入的People类型，找到这个人所在的位置，地形类型
		Iterator<Cell> it = clist.iterator();
		Cell cell;
		int ltype;
		int x = p.getPpos().getX();
		int y = p.getPpos().getY();
		while (it.hasNext()) {
			cell = it.next();
			if (cell.getCpos().getX() == x && cell.getCpos().getY() == y) {
				ltype = cell.getltype();
				return ltype;
			}
		}
		return -1; // 若没有找到就返回-1表示此处没有相关地形
	}

	public int isShelter(People p) {
		// 当所处位置为庇护所的时候，类型1
		double survivability;
		if (p.getPtype() == 1) {
			// 当判断为可操作正常人的时候(存疑)
			NormalPeople np = (NormalPeople) p;
			survivability = np.getSurvivability();
			survivability += 0.2; // 先设定统一增加0.2的存活值
			np.setSurvivability(survivability);
		}
		return 0;
	}

	public int isDeathtrap() {
		// 当所处位置为死亡陷阱的时候，类型5
		return 1;
	}
	
	public int isRadient(People p) {
		// 当所处位置为辐射地的时候，类型2
		// 正常人类攻击力减弱
		if (p.getPtype() == 1) {
			return 2;
		}
		// 丧尸攻击力增强
		else if (p.getPtype() == 0) {
			return -2;
		}
		// 不可操作者
		else
			return 0;
	}

	public int isSwampland(People p) {
		// 当所处位置为沼泽地的时候，类型3
		// 当为丧尸的时候
		if (p.getPtype() == 0) {
			return 2;
		}
		// 不可操作者
		else
			return 0;
	}

	public int isDMRiver(People p) {
		// 当所处位置为子母河的时候,类型4
		// 若为可操作可生孩子的正常人类
		if (p.getPtype() == 1) {
			return 50; // 百分之50怀孕几率
		} else
			return 0;
	}

	public int isTrappedLand(People p) {
		// 当所处位置为困阵的时候，类型6
		//将物种的停留时间改为3
		if(p.getPtype() == 0 || p.getPtype() == 1){
			p.setTrapYear(3);
		}
		return 0;
	}

	public int ManageLandEvent(People p, List<Cell> clist) {
		// 通过获取的land类型来调用相应的事件函数
		// 返回标签
		int tag = 0;
		// 获取land的类型
		int ltype = findLandByPeople(p, clist);
		switch (ltype) {
		case 1:
			tag = isShelter(p);
			break;
		case 2:
			tag = isRadient(p);
			break;
		case 3:
			tag = isSwampland(p);
			break;
		case 4:
			tag = isDMRiver(p);
			break;
		case 5:
			tag = isDeathtrap();
			break;
		case 6:
			tag = isTrappedLand(p);
			break;
		default:
			break;
		}
		return tag;
	}

}
