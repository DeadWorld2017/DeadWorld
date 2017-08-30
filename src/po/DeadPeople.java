package po;

/*丧尸类
 * 继承People类
 * 类型编号为0
 */
public class DeadPeople extends People{
	private int level;//等级1、2、3
	private int baseDamage;//基本攻击力，狂暴值
	private int killNumber;//杀人数量，用于升级
	
	
	public DeadPeople() {
		super();
	 
	}

	
	
	public DeadPeople(int pid,Position ppos) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = false;//丧尸类型为0
		this.level = 1;
		this.baseDamage = 10;//暂时默认为10
		this.killNumber = 0;
		this.setState(true);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public int getKillNumber() {
		return killNumber;
	}

	public void setKillNumber(int killNumber) {
		this.killNumber = killNumber;
	}







	
	
	
	
}
