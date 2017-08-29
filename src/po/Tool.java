package po;

//道具类
public class Tool {
	private int tid;//道具编号
	private String tname;//道具名称
	private Position tpos;//武器坐标
	private boolean usage;//使用情况，0表示在地图上，1表示在人物身上
	
	public Tool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tool(int tid, String tname, Position tpos, boolean usage) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tpos = tpos;
		this.usage = usage;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Position getTpos() {
		return tpos;
	}
	public void setTpos(Position tpos) {
		this.tpos = tpos;
	}
	public boolean isUsage() {
		return usage;
	}
	public void setUsage(boolean usage) {
		this.usage = usage;
	}
	
	
	
}
