package po;

public class DeadPeople extends People{
	private int level;//等级1、2、3
	private int baseDamage;//基本攻击力，狂暴值
	private int killNumber;//杀人数量，用于升级
	
	public DeadPeople() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeadPeople(int level, int baseDamage, int killNumber) {
		super();
		this.level = level;
		this.baseDamage = baseDamage;
		this.killNumber = killNumber;
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
