package po;

public class People {
	private int pid;//���ֱ��
	private Position ppos;//λ��

	public People() {
		super();
		// TODO Auto-generated constructor stub
	}



	public People(int pid, Position ppos) {
		super();
		this.pid = pid;
		this.ppos = ppos;
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
	
	
	
}
