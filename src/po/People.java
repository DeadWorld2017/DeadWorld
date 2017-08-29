package po;

public class People {
	public int pid;//物种编号
	public Position ppos;//位置
	public int ptype;//人的种类，0为丧尸，1为正常人

	public People() {
		super();
		// TODO Auto-generated constructor stub
	}


	public People(int pid, Position ppos, int ptype) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = ptype;
	}


	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}


	public Position getPpos() {
		return ppos;
	}


	public void setPpos(Position ppos) {
		this.ppos = ppos;
	}



	public int getPtype() {
		return ptype;
	}



	public void setPtype(int ptype) {
		this.ptype = ptype;
	}
	
	
	
}
