package po;

/*道具类
 * 
 * 类型1：刀
 * 类型2：枪械
 * 类型3：火箭筒
 * 类型4：自杀弹
 * 类型5：逃跑鞋
 * 类型6：加速鞋
 * 类型7：兴奋剂
 * 
 */

public class Tool {
	private int tid;// 道具编号
	private int ttype;// 道具类型
	private Position tpos;// 道具坐标
	private boolean usage;// 使用情况，0表示在地图上，1表示在人物身上

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
