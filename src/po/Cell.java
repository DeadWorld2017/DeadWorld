package po;


//������
public class Cell {
	private Position cpos;//���ӵ�λ��
	private int tid;//���߱��
	private int ltype;//���α��
	private int pid;//������
	private int ptype;//������
	
	

	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Cell(Position cpos, int tid, int ltype, int pid, int ptype) {
		super();
		this.cpos = cpos;
		this.tid = tid;
		this.ltype = ltype;
		this.pid = pid;
		this.ptype = ptype;
	}



	public Position getCpos() {
		return cpos;
	}
	public void setCpos(Position cpos) {
		this.cpos = cpos;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getltype() {
		return ltype;
	}
	public void setltype(int ltype) {
		this.ltype = ltype;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPtype() {
		return ptype;
	}

	public void setPtype(int ptype) {
		this.ptype = ptype;
	}
	
	
}
