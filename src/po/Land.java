package po;

/*������,��Ŷ�Ӧ�õ�ͼ����͵�������
 * 
 * ����1���ӻ���
 * ����2�������
 * ����3�������
 * ����4����ĸ��
 * ����5����������
 * ����6������	
 * 
 */

//��ĸ��4  ��ʱ�޷�ʵ�֣��ȸ���

public class Land {
	private int ltype;// ��������
	private Position lpos;// ��������

	public Land() {
		super();

	}

	public Land(int ltype, Position lpos) {
		super();
		this.ltype = ltype;
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

	@Override
	public String toString() {
		return "Land [ltype=" + ltype + ", lpos=" + lpos + "]";
	}

}
