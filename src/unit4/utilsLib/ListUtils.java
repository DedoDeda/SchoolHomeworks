package unit4.utilsLib;

import unit4.collectionsLib.List;
import unit4.collectionsLib.Node;


/**
 * <h3 dir="rtl" style="color:red; font-family: Helvetica, Arial, sans-serif">
 * ����� ����� ������ ���� ������ ������ �������� ������ �� ����� 
 * </h3>
 * 
 * @author ���� ���� �����, ����� ������ ������, ����������� ������, �������
 * @version 26.11.2007
 */
public final class ListUtils
{
	private ListUtils(){}
	
	/**
	 * <dt dir="rtl" >
     * <b>
	 * ������ ������ �� ���� ������� ������
     * </b>
	 * 
	 * @param lst ����� �� ������ �����
	 * @return ���� ������� ������
	 */
	public static int getLength(List<Integer> lst)
	{
		int len = 0;
		Node<Integer> pos = lst.getFirst();
		
		while(pos != null)
		{
			len++;
			pos = pos.getNext();
		}
		return(len);
	}
	
	/**
	 * <dt dir="rtl" >
     * <b>
	 * ������ ������ �� ����� ������ ������ �� ���� ���� ������
     * </b>
	 * 
	 * @param lst ����� �� ������ �����
	 * @param x ���� ��� ������
	 * @return null ������ �� ���� ���� ������. ����� ��� ���� ����� 
	 */
	public static Node<Integer> getPosition(List<Integer> lst, int x)
	{
		Node<Integer> pos = lst.getFirst();
		
		while(pos != null)     
		{
			if(pos.getInfo() == x)
				return(pos);
			else
				pos = pos.getNext();
		}
		return(null);    	
	} 
	
	/**
	 * <dt dir="rtl" >
     * <b>
	 * ����� ������� ������� ������� �� �� ������� ����� �����
     * </b>
	 * 
	 * @param lst ����� �� ������ �����
	 * @param x ���� ���
	 */
	public static void removeAllOccurances(List<Integer> lst, int x)
	{
		Node<Integer> pos = lst.getFirst();
		
		while (pos != null)
		{
			if (pos.getInfo().compareTo(x) == 0) 
				pos = lst.remove(pos);
			else 
				pos = pos.getNext();
		}
	}
	
	/**
	 * <dt dir="rtl" >
     * <b>
	 * ������ ����� �� ��� ���� ������
     * </b>
	 * 
	 * @param lst ����� �� ������ ����� ���� �� �����
	 */
	public static void reverse(List<Integer> lst)
	{		
		Node<Integer> pos = lst.getFirst();			
		
		while(pos != null)
		{		
			lst.insert(null,pos.getInfo());	
			pos = lst.remove(pos);
		}
	}
	
	/**
	 * <dt dir="rtl" >
     * <b>
	 *  ����� ������ ��� ������ ������� ����� �� ������ ��������
     * </b>
	 * 
	 *  @param lst1 ����� �� ������ �����
	 *  @param lst2 ����� �� ������ �����
	 *  @return ����� ������ �� �� ������ �������� ���� ������� ��������
	 */
	public static List<Integer> common(List<Integer> lst1, List<Integer> lst2)
	{
		int x;
		List<Integer> lst3 = new List<Integer>();
		Node<Integer> pos1 = lst1.getFirst();
		Node<Integer> pos3 = null;
		
		while(pos1 != null)
		{
			x = pos1.getInfo();
			if(ListUtils.getPosition(lst2,x) != null)
				if(ListUtils.getPosition(lst3,x) == null)
					pos3 = lst3.insert(pos3,x);
			pos1 = pos1.getNext();
		}
		return(lst3);
	}
	
	/**
	 * <dt dir="rtl" >
     * <b>
	 * ����� ������ ����� �� ������ ����� ����� ������ ����� ������
     * </b>
	 * 
	 * @param numOfItems ���� ������ �������� ������
	 * @param low ���� ������ ���� �����
	 * @param high ���� ������ ����� �����
	 * @return ����� �� ����� �������  
	 */
	public static List<Integer> createRandomList(int numOfItems, int low, int high)
	{
		int x;
		List<Integer> lst = new List<Integer>();
		
		for(int i=1; i<=numOfItems; i++)
		{
			x = (int)(Math.random()*(high-low+1)+low);
			lst.insert(null,x);
		}
		return(lst);
	}
	
	
	
	//*****************************************************************//
	//                              ������                             //
	//*****************************************************************//
	/**
	 * <dt dir="rtl" >
	 * <b>
	 * ������ ����� (����� ���� �����) ����� ������ ���� ����;
	 * ������ �� ���� �� ������ �������, ���, ������ ����� ���� ������;
	 * ������ ����� ������ insertIntoSortedList
	 * </b>
	 *  
	 * @param lst ����� ������ �����
	 * @return ����� ������ ����� ������ ���� ����
	 */
	public static List<Integer> insertionSort(List<Integer> lst)
	{
		List<Integer> tmpList = new List<Integer>();
		Node<Integer> pos = lst.getFirst();
		
		while(pos != null)
		{
			ListUtils.insertIntoSortedList(tmpList,pos.getInfo());
			pos = pos.getNext();
		}
		return(tmpList);
	}
	
	/**
	 * <dt dir="rtl" >
	 * <b>
	 * ������ ������ ��� ������ ������ ������
	 * </b>
	 * 
	 * @param lst ����� ������ ����� ������
	 * @param x ��� (���� ���) ������ 
	 */
	public static void insertIntoSortedList(List<Integer> lst, int x)
 	{
		boolean inList = false;
		Node<Integer> prev = null;
		Node<Integer> pos = lst.getFirst();
		
		while(pos!=null && !inList)
		{
			if(x < pos.getInfo())
				inList = true;
			else
			{
				prev = pos;
				pos = pos.getNext();
			}
		}
		lst.insert(prev,x);
 	}
	
	// ----------------------------------------------------------------//	
	/**
	 * <dt dir="rtl" >
	 * <b>
	 * ;������ ������ (����� ���� �����) ���� ���� ����� ������ 
	 * ������ �� ������ ����� ������, ���, ���� �� ������ �������
	 * </b>
	 * 
	 * @param lst ����� ������ �����
	 */
//	private static void selectionSort(List<Integer> lst)
//    {
//    	int temp;
//		Node<Integer> pos1,pos2,minPos;
//				
//		pos1 = lst.getFirst();
//		while(pos1 != null)
//		{
//			minPos = pos1; 
//			pos2 = minPos.getNext();
//			while(pos2 != null)
//			{
//				if(pos2.getInfo() < minPos.getInfo())
//					minPos = pos2;
//				pos2 = pos2.getNext();
//			}
//			temp = pos1.getInfo();
//			pos1.setInfo(minPos.getInfo());
//			minPos.setInfo(temp);
//			pos1 = pos1.getNext();
//		}		
// 	}
 	
	//*****************************************************************//
	//                               �����                             //
	//*****************************************************************//
 	/**
	 * <dt dir="rtl" >
	 * <b>
 	 * ������ ����� ��� ������
	 * </b>
 	 * 
 	 * @param lst1 ����� ������ �������
 	 * @param lst2 ����� ������ �������
 	 * @return ����� ������ ������� ���� ����� �� ��� ������� �������
 	 */
	public static List<Integer> mergeLists(List<Integer> lst1, List<Integer> lst2)
    {
		Node<Integer> pos1, pos2, pos3=null;
		List<Integer> lst3 = new List<Integer>();
				
		pos1 = lst1.getFirst();
		pos2 = lst2.getFirst();
		while(pos1 != null && pos2 != null)
		{
			if(pos1.getInfo() > pos2.getInfo())
			{
				pos3 = lst3.insert(pos3,pos2.getInfo());
				pos2 = pos2.getNext();
			}
			else
			{
				pos3 = lst3.insert(pos3,pos1.getInfo());
				pos1 = pos1.getNext();
			}
		}	
		
		while(pos1 != null)
		{
			pos3 = lst3.insert(pos3,pos1.getInfo());
			pos1 = pos1.getNext();
		}		
		
		while(pos2 != null)
		{
			pos3 = lst3.insert(pos3,pos2.getInfo());
			pos2 = pos2.getNext();
		}	
		return(lst3);
 	}
}
