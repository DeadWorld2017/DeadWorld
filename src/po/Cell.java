package po;


//������
public class Cell {
	private Position cpos;//���ӵ�λ��
	private int tid;//�������
	private int lid;//���α��
	
	
	
	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cell(Position cpos, int tid, int lid) {
		super();
		this.cpos = cpos;
		this.tid = tid;
		this.lid = lid;
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
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
	
}
