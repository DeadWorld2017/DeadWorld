package po;

//������,��Ŷ�Ӧ�õ�ͼ����͵�������
public class Land {
	private int ltype;//��������
	private String lname;//��������
	private Position lpos;//��������
	
	public Land() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Land(int ltype, String lname, Position lpos) {
		super();
		this.ltype = ltype;
		this.lname = lname;
		this.lpos = lpos;
	}



	public int getLtype() {
		return ltype;
	}


	public void setLtype(int ltype) {
		this.ltype = ltype;
	}


	public Position getLpos() {
		return lpos;
	}


	public void setLpos(Position lpos) {
		this.lpos = lpos;
	}


	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
