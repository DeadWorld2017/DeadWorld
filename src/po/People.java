package po;


/*����
 * 
 * ����false��ɥʬ
 * ����true��������
 * 
 */
public class People {
	public int pid;//�˵ı��
	public Position ppos;//λ��
	public boolean ptype;//�˵�����,false��ɥʬ
	public int trapYear;//���ֶ���ʱ�䣬��ʼΪ0
	


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
		return "People [pid=" + pid + ", ppos=" + ppos + 
				", ptype=" + ptype + ", trapYear=" + trapYear + "]";
	}
	
	
	
}
