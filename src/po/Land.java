package po;

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
public class Land {
	private int ltype;//地形类型
	private Position lpos;//地形坐标
	
	public Land() {
		super();
		 
	}
	
	public Land(int ltype, Position lpos) {
		super();
		this.ltype = ltype;
		this.lpos = lpos;
	}



	public int getLtype() {
		return ltype;
	}


	public void setLtype(int ltype) {
		this.ltype = ltype;
	}


	public Position getLpos() {
		return lpos;
	}


	public void setLpos(Position lpos) {
		this.lpos = lpos;
	}

	
	
}
