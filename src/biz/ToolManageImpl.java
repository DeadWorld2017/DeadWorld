package biz;

import java.util.List;

import po.DeadPeople;
import po.NormalPeople;
import po.People;
import po.Tool;

//道具管理接口实现
public class ToolManageImpl implements ToolManage {

	public boolean isWeopon(int ttype) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isBomb(int ttype) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isShoes(int type) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean escapeShoes() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean speedShoes() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isDoping() {
		// TODO Auto-generated method stub
		return false;
	}

	public int addWeoponDamage(int attackValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void bombFuction(List<People> plist, NormalPeople np, DeadPeople dp) {
		// TODO Auto-generated method stub

	}

	public void destoryBomb(NormalPeople np, List<Tool> tlist, int tid) {
		// TODO Auto-generated method stub

	}

	public People findPid(int pid, List<People> plist) {
		// TODO Auto-generated method stub
		return null;
	}

}
