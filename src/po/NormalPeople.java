package po;

import java.util.ArrayList;
import java.util.List;

/*��������
 * �̳�People��
 * ���ͱ��Ϊ1���������ڵ���
 * ���ͱ��Ϊ2����ת��Ϊɥʬ��������������
 */
public class NormalPeople extends People {

	private boolean gender;// �Ա�,trueΪ���ԣ�falseΪŮ��
	private int age;// ����
	private double survivability;// �������ֵ
	private boolean antibody;// ����,trueΪ�п��壬falseΪ�޿���
	private List<Tool> tlist;// ����������list
	private boolean pregnancyFlag;// ���б��

	public NormalPeople() {
		super();

	}

	public NormalPeople(int pid, Position ppos, boolean gender,
			int age, double survivability, boolean antibody,
			boolean pregnancyFlag) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = 1;// ����������Ϊ1
		this.gender = gender;
		this.age = age;
		this.survivability = survivability;
		this.antibody = antibody;
		this.tlist = new ArrayList<Tool>();
		this.pregnancyFlag = pregnancyFlag;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSurvivability() {
		return survivability;
	}

	public void setSurvivability(double survivability) {
		this.survivability = survivability;
	}

	public boolean isAntibody() {
		return antibody;
	}

	public void setAntibody(boolean antibody) {
		this.antibody = antibody;
	}

	public List<Tool> getTlist() {
		return tlist;
	}

	public void setTlist(List<Tool> tlist) {
		this.tlist = tlist;
	}

	public boolean getPregnancyFlag() {
		return pregnancyFlag;
	}

	public void setPregnancyFlag(boolean pregnancyFlag) {
		this.pregnancyFlag = pregnancyFlag;
	}

	@Override
	public String toString() {
		return "NormalPeople [gender=" + gender + ", age=" + age + ", survivability=" + survivability + ", antibody="
				+ antibody + ", tlist=" + tlist + ", pregnancyFlag=" + pregnancyFlag + "]";
	}

	 
	 

}
