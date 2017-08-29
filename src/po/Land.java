package po;

//地形类,存放对应得地图坐标和地形类型
public class Land {
	private int lid;//地形编号
	private String lname;//道具名称
	
	public Land() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Land(int lid, String lname) {
		super();
		this.lid = lid;
		this.lname = lname;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
