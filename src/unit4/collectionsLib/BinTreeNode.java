package unit4.collectionsLib;

/** 
 * <h4 dir="rtl" style="color:red; font-family: Helvetica, Arial, sans-serif">
 * ������ ������ ����� ������ ��� ��� ������ T ���� ������ ������� �������.
 * </h4>
 * 
 * @author Evgeny Kanel+ ���� ���� �����, ����� ������ ������, ����������� ������, �������
 * @version 26.11.2007-25.12.2013
 * @param <T> ����� ��� ������ �������
 */
public class BinTreeNode<T>
{
    private BinTreeNode<T> left;
    private T info;
    private BinTreeNode<T> right;
    
    /**
  * <dt dir="rtl"/>
  * <b>
     * ������ ���� ����� ������; ��� ������ ��� x ���� ��� ������� ��� ��� null
  * </b>
  * 
     * @param x ��� ������
     */
    public BinTreeNode(T x)
    {
     this.left = null;
        this.info = x;
        this.right = null;
    }
    
    /**
  * <dt dir="rtl"/>
  * <b>
  * ������ ���� ����� ������ ����� ���� x;
  * �������� left �-right �� (������ ��) ���� ������ ������ ���;
  * ���� ������� ������ ����� null
  * </b>
  * 
  * @param left ��-�� �����
     * @param x ��� ������
     * @param right ��-�� ����
     */
    public BinTreeNode(BinTreeNode<T> left, T x, BinTreeNode<T> right)
    {
     this.left = left;
        this.info = x;
        this.right = right;
    }
    
    /**
  * <dt dir="rtl"/>
  * <b>
  * ������ ������ �� ���� �� ������
  * </b>
  * 
  * @return ��� ������
     */
    public T getInfo()
    {
        return this.info;
    }
    
      /** KANEL
  * <dt dir="rtl"/>
  * <b>
  * ������ ������ �� ���� �� ������
  * </b>
  * 
  * @return ��� ������
     */
    public T getValue()
    {
        return this.info;
    }
    /**
  * <dt dir="rtl"/>
  * <b>
  * ������ ���� �� ���� ����� ������ �-x
  * </b>
  * 
  * @param x ��� ������ ����
     */
    public void setInfo(T x)
    {
         this.info = x;
    }
    
       /** KANEL
  * <dt dir="rtl"/>
  * <b>
  * ������ ���� �� ���� ����� ������ �-x
  * </b>
  * 
  * @param x ��� ������ ����
     */
    public void setValue(T x)
    {
         this.info = x;
    }
    /**
  * <dt dir="rtl"/>
  * <b>
  * ������ ������ �� ���� ������ �� ������;
  * �� ��� ��� ����� ������ ������ null
  * </b>
  * 
  * @return ���� ������
     */
    public BinTreeNode<T> getLeft()
    {   
        return this.left;
    }    
    
    /**
  * <dt dir="rtl"/>
  * <b>
  * ������ ������ �� ���� ����� �� ������;
  * �� ��� ��� ���� ������ ������ null
  * </b>
  * 
  * @return ���� �����
     */
    public BinTreeNode<T> getRight()
    {   
        return this.right;
    }
    
    /**
  * <dt dir="rtl"/>
  * <b>
  * ������ ������ �� ���� ������ ������ left
  * </b>
  * 
  * @param left ���� ������ ����
     */
    public void setLeft(BinTreeNode<T> left)
    {
       this.left = left;
    }
    
    /**
  * <dt dir="rtl"/>
  * <b>
  * ������ ������ �� ���� ����� ������ right
  * </b>
  * 
  * @param right ���� ����� ����
     */
    public void setRight(BinTreeNode<T> right)
    {
     this.right = right;
    }
    
        /** KANEL
  * <dt dir="rtl" >
  * <b>

* ������ ����� ��� ����� ����� ���� �����
  * </b>
  * 
  * 
  * @return boolean
     */
    public boolean hasLeft()
    {
      return this.left!=null;
    }
    
            /** KANEL
  * <dt dir="rtl" >
  * <b>

* ������ ����� ��� ����� ����� ���� ������
  * </b>
  * 
  * 
  * @return boolean
     */
    public boolean hasRight()
    {
      return this.right!=null;
    }
    /**
  * <dt dir="rtl"/>
     * <b>
     * ������ ������ ������ ������ �� ���� ����� ������
     * </b>
     * 
     * @return ������ ������ �� ���� ����� ������
     */
    public String toString()
    {
     return this.info.toString();
    }   
}
