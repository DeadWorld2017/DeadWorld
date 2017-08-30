package po;

import java.util.List;

/*��������
 * �̳�People��
 * ���ͱ��Ϊ1
 * state ΪtrueΪ�����ж�����
 * 		 ΪfalseΪ�Ѿ���ת��Ϊɥʬ����
 */
public class NormalPeople extends People{

	private boolean gender;//�Ա�,trueΪ���ԣ�falseΪŮ��
	private int age;//����
	private double survivability;//�������ֵ
	private boolean antibody;//����,trueΪ�п��壬falseΪ�޿���
	private List<Integer> tlist;//����������list
	private int pregnancyFlag;//���б��
	private boolean state;//״̬��falseΪ�Ѿ���ת��Ϊɥʬ
	
		 
	public NormalPeople() {
		super();
		 
	}

	public NormalPeople(int pid,Position ppos, boolean gender, int age, 
			double survivability, boolean antibody, int pregnancyFlag) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = true;//����������Ϊ1
		this.gender = gender;
		this.age = age;
		this.survivability = survivability;
		this.antibody = antibody;
		this.tlist = null;
		this.pregnancyFlag = pregnancyFlag;
		this.state = true;
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

	public List<Integer> getTlist() {
		return tlist;
	}

	public void setTlist(List<Integer> tlist) {
		this.tlist = tlist;
	}

	public int getPregnancyFlag() {
		return pregnancyFlag;
	}

	public void setPregnancyFlag(int pregnancyFlag) {
		this.pregnancyFlag = pregnancyFlag;
	}

	@Override
	public String toString() {
		return "NormalPeople [pid=" + pid + ", ptype=" + ptype + 
				"gender=" + gender + ", age=" + age + 
				", survivability=" + survivability + 
				", antibody="+ antibody + "]";
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}


	
	
	
	
}
