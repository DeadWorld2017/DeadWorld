package po;

import java.util.List;

/*正常人类
 * 继承People类
 * 类型编号为1
 * state 为true为还能行动的人
 * 		 为false为已经被转化为丧尸的人
 */
public class NormalPeople extends People{

	private boolean gender;//性别,true为男性，false为女性
	private int age;//年龄
	private double survivability;//存活能力值
	private boolean antibody;//抗体,true为有抗体，false为无抗体
	private List<Integer> tlist;//存放武器编号list
	private int pregnancyFlag;//怀孕标记
	
	
		 
	public NormalPeople() {
		super();
		 
	}

	public NormalPeople(int pid,Position ppos, boolean gender, int age, 
			double survivability, boolean antibody, int pregnancyFlag) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = true;//正常人类型为1
		this.gender = gender;
		this.age = age;
		this.survivability = survivability;
		this.antibody = antibody;
		this.tlist = null;
		this.pregnancyFlag = pregnancyFlag;
		this.setState(true);
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

	


	
	
	
	
}
