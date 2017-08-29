package biz;

import java.util.List;
import po.Land;
import po.People;
import po.Cell;

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
//�����¼��ӿ�
public interface LandEvent {
	
	//ͨ����������ʵ����ͨ������Ѱ�Ҷ�Ӧ�ĵ��Σ����ض�Ӧ��land�����ͱ�ţ�1-6������ltype
	public int findLandByPeople(People np, List<Cell> listC);
		
	//ս��ǰ�ж�
	//����� 1���ӻ���  ��������������������������޸�����������ֵ���������κβ���
	public void isShelter(People np);
	//�����5�� ��������  �������ֵ����ﶼ������,���������List��ɾȥ
	public void isDeathtrap(People p);
		
	//ս��ʱ�ж�
	//�����2�� �����  �������˹�����������ɥʬ��������ǿ,���ص�����Ӧ�ı仯ֵ 
	public int isRadient();
	//�����3�� �����  ����������Ĺ���������(0)��ɥʬ�Ĺ�������ǿ
	public int isSwampland();
		
	//ս�����ж�
	//�����4�� ��ĸ��  �������࣬����������˵Ľ��伸�ʣ�����ֵ��һ������
	public int isDMRiver();
	//�����6�� ����  �����ڴ˴���ͣ��3�� �޸����ֵı�ǩ���иı䣨3�ݼ���0������ˢ�µ�ͼʱ�����ж����ǩ�Ƿ�Ϊ0
	public void isTrappedLand(People p);

}
