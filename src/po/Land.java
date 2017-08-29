package po;

//地形类,存放对应得地图坐标和地形类型
public class Land {
	private int ltype;//地形种类
	private String lname;//地形名称
	private Position lpos;//地形坐标
	
	public Land() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Land(int ltype, String lname, Position lpos) {
		super();
		this.ltype = ltype;
		this.lname = lname;
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


	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
