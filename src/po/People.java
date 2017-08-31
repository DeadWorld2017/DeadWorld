package po;

/*人类
 * 
 * 类型false：丧尸
 * 类型true：正常人
 * 
 */
public class People {
	protected int pid;// 人的编号
	protected Position ppos;// 位置
	protected boolean ptype;// 人的类型,false是丧尸
	protected int trapYear;// 物种逗留时间，初始为0
	protected boolean state;// 状态，false为不可操作

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

	public void setTrapYear(int trapYear) {
		this.trapYear = trapYear;
	}

	public int getTrapYear() {
		return trapYear;
	}

	@Override
	public String toString() {
		return "People [pid=" + pid + ", ppos=" + ppos + ", ptype=" + ptype + ", state=" + state + "]";
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
