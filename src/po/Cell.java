package po;


/*������
 * 
 */
public class Cell {
	private Position cpos;//���ӵ�λ��
	private int pid;//������
	private int ptype;//�˵�����
	private int ttype;//��������
	private int ltype;//��������
	
	
	

	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Cell(Position cpos, int ttype, int ltype, int pid, int ptype) {
		super();
		this.cpos = cpos;
		this.ttype = ttype;
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
	public int getttype() {
		return ttype;
	}
	public void setttype(int ttype) {
		this.ttype = ttype;
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
