package po;

//������,��Ŷ�Ӧ�õ�ͼ����͵�������
public class Land {
	private int lid;//���α��
	private String lname;//��������
	
	public Land() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Land(int lid, String lname) {
		super();
		this.lid = lid;
		this.lname = lname;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
