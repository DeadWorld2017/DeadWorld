package po;


//事件类
public class Event {
	private int eid;//事件id
	private String ename;//事件名称
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int eid, String ename) {
		super();
		this.eid = eid;
		this.ename = ename;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	
	
}
