package po;


/*����
 * 
 * ����false��ɥʬ
 * ����true��������
 * 
 */
public class People {
	protected int pid;//�˵ı��
	protected Position ppos;//λ��
	protected boolean ptype;//�˵�����,false��ɥʬ
	protected int trapYear;//���ֶ���ʱ�䣬��ʼΪ0
	protected boolean state;//״̬��falseΪ���ɲ���


	public People() {
		super();
		 
	}


	public People(int pid, Position ppos, boolean ptype) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = ptype;
		this.trapYear = 0;
	}

	
	public boolean isPtype() {
		return ptype;
	}


	public void setPtype(boolean ptype) {
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

 
	public void setTrapYear(int trapYear){
		this.trapYear = trapYear;
	}
	
	
	public int getTrapYear(){
		return trapYear;
	}


	

	@Override
	public String toString() {
		return "People [pid=" + pid + ", ppos=" + 
			ppos + ", ptype=" + ptype + ", state=" + state + "]";
	}


	public boolean isState() {
		return state;
	}


	public void setState(boolean state) {
		this.state = state;
	}
	
	
	
}
