package po;


/*����
 * 
 * ����0��ɥʬ
 * ����1��������
 * 
 */
public class People {
	public int pid;//�˵ı��
	public Position ppos;//λ��
	public int ptype;//�˵�����
	public int trapYear;//���ֶ���ʱ�䣬��ʼΪ0
	
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
