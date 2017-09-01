package po;

import java.util.List;

/*正常人类
 * 继承People类
 * 类型编号为1，正常存在的人
 * 类型编号为2，被转化为丧尸被保存下来的人
 */
public class NormalPeople extends People {

	private boolean gender;// 性别,true为男性，false为女性
	private int age;// 年龄
	private double survivability;// 存活能力值
	private boolean antibody;// 抗体,true为有抗体，false为无抗体
	private List<Tool> tlist;// 存放武器编号list
	private boolean pregnancyFlag;// 怀孕标记

	public NormalPeople() {
		super();

	}

	public NormalPeople(int pid, Position ppos, boolean gender,
			int age, double survivability, boolean antibody,
			boolean pregnancyFlag) {
		super();
		this.pid = pid;
		this.ppos = ppos;
		this.ptype = 1;// 正常人类型为1
		this.gender = gender;
		this.age = age;
		this.survivability = survivability;
		this.antibody = antibody;
		this.tlist = null;
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
		return "NormalPeople [pid=" + pid + ", ppos=" + ppos + ", ptype=" + ptype + "]";
	}

	 

}
