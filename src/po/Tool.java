package po;

/*������
 * 
 * ����1����
 * ����2��ǹе
 * ����3�����Ͳ
 * ����4����ɱ��
 * ����5������Ь
 * ����6������Ь
 * ����7���˷ܼ�
 * 
 */

public class Tool {
	private int tid;//���߱��
	private	int ttype;//��������
	private Position tpos;//��������
	private boolean usage;//ʹ�������0��ʾ�ڵ�ͼ�ϣ�1��ʾ����������
	
	public Tool() {
		super();
		 
	}

	
	public Tool(int tid, int ttype, Position tpos, boolean usage) {
		super();
		this.tid = tid;
		this.ttype = ttype;
		this.tpos = tpos;
		this.usage = usage;
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
	public int getTtype() {
		return ttype;
	}
	public void setTtype(int ttype) {
		this.ttype = ttype;
	}


	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}
	
	
	
}
