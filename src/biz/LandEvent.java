package biz;

import po.Land;
import po.NormalPeople;

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
	public int findLandByPeople(NormalPeople np);

	//ս��ǰ�ж�,����������¼�������
	public int isShelter(int ltype,NormalPeople np);
	
	//ս��ʱ�ж�
	
	//ս�����ж�

}
