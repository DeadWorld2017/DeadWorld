package po;

/*����
 * 
 * ����0��ɥʬ
 * ����1��������
 * ����2����ת��Ϊɥʬ��������
 * 
 */
public class People {
	protected int pid;// �˵ı��
	protected Position ppos;// λ��
	protected int ptype;// �˵�����
	protected int trapYear;// ���ֶ���ʱ�䣬��ʼΪ0
	//protected boolean state;// ״̬��falseΪ���ɲ���

	public People() {
		super();

	}

	public People(int pid, Position ppos, int ptype) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = ptype;
		this.trapYear = 0;
	}

	public int getPtype() {
		return ptype;
	}

	public void setPtype(int ptype) {
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

	public void setTrapYear(int trapYear) {
		this.trapYear = trapYear;
	}

	public int getTrapYear() {
		return trapYear;
	}

	 
}
