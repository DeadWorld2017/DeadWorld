package po;

/*ɥʬ��
 * �̳�People��
 * ptype=0��ɥʬ
 */
public class DeadPeople extends People {
	private int level;// �ȼ�1��2��3
	private int baseDamage;// ��������������ֵ
	private int killNumber;// ɱ����������������

	public DeadPeople() {
		super();

	}

	public DeadPeople(int pid, Position ppos) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = 0;// ɥʬ����Ϊ0
		this.level = 1;
		this.baseDamage = 10;// ��ʱĬ��Ϊ10
		this.killNumber = 0;
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
