package po;

import java.util.List;

public class NormalPeople extends People{
	private boolean gender;//�Ա�
	private int age;//����
	private int Survivability;//�������ֵ
	private boolean antibody;//����
	private List<Integer> tlist;//����������list
	private int pregnancyFlag;//���б��
	
	public NormalPeople() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NormalPeople(boolean gender, int age, int survivability, boolean antibody, List<Integer> tlist,
			int pregnancyFlag) {
		super();
		this.gender = gender;
		this.age = age;
		Survivability = survivability;
		this.antibody = antibody;
		this.tlist = tlist;
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

	public int getSurvivability() {
		return Survivability;
	}

	public void setSurvivability(int survivability) {
		Survivability = survivability;
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
	
	
	
	
}
