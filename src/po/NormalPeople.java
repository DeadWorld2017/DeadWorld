package po;

import java.util.List;

public class NormalPeople extends People{
	//int ptype=1;
	private boolean gender;//性别
	private int age;//年龄
	private int Survivability;//存活能力值
	private boolean antibody;//抗体
	private List<Integer> tlist;//存放武器编号list
	private int pregnancyFlag;//怀孕标记
	
		 
	public NormalPeople() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NormalPeople(int pid,Position ppos, boolean gender, 
			int age, int survivability, boolean antibody, 
			List<Integer> tlist,int pregnancyFlag) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		super.ptype = 1;//正常人类型为1
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
