package po;

//������
public class Tool {
	private int tid;//���߱��
	private String tname;//��������
	private Position tpos;//��������
	private boolean usage;//ʹ�������0��ʾ�ڵ�ͼ�ϣ�1��ʾ����������
	
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
