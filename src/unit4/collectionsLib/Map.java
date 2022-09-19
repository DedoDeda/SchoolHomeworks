package unit4.collectionsLib;

/** 
 * <h4 dir="rtl" style="color:red; font-family: Helvetica, Arial, sans-serif">
 * ������ ������ ���� ����� ����� ��� ������ ������. ������� ���� ������ ������ ���� ������� ������
 * </h4>
 * 
 * @author ���� ���� �����, ����� ������ ������, ����������� ������, �������
 * @version 26.11.2007
 * @param <V> ����� ������ ������� �������
 */
public class Map<V>
{
	private BinTreeNode<Pair<String,V>> root;
	private int numOfKeys;
	
    /**
	 * <dt dir="rtl"/>
     * <b>
	 * ������ ���� ��� ���� 
     * </b>
     */
	public Map()
	{
		this.root = null;
		this.numOfKeys = 0;
	}
	
    /**
	 * <dt dir="rtl"/>
     * <b>
     * ������ ������ �� ���� ����� ����� key;
     * ������ ������ null �� ����� ���� ���� ����
     * <br/>
     * </b>
	 * 
	 * @param key ����
	 * @return ��� �����
     */
	public V getValue(String key)
	{
		BinTreeNode<Pair<String,V>> pos = searchKey(key);
		if(pos==null)
			return null;
		return pos.getInfo().getValue();
	}
	
    /**
	 * <dt dir="rtl"/>
     * <b>
     * ������ ������ ���� ������� ���� key ��� ���� value ����� ����;
     * �� ����� key ���� ����, ������ ������ �� ���� ����� ���� ���� value ������  
     * </b>
	 * 
	 * @param key ���� ������
	 * @param value ��� �����
     */
	public void insert(String key, V value)
	{
		BinTreeNode<Pair<String,V>> pos = searchKey(key);
		
		if(pos != null)
			pos.getInfo().setValue(value);
		else
		{
			this.numOfKeys++;
			pos = this.root;
			boolean inTree = false;
			BinTreeNode<Pair<String,V>> node = new BinTreeNode<Pair<String,V>>(new Pair<String,V>(key,value));
			
			if(this.root == null)
			{
				this.root = node;
				inTree = true;
			}
			while(!inTree)
			{
				if(key.compareTo(pos.getInfo().getKey()) < 0)
				{
					if(pos.getLeft() == null)
					{
						pos.setLeft(node);
						inTree = true;
					}
					else
						pos = pos.getLeft();
				}
				else
				{
					if(pos.getRight() == null)
					{
						pos.setRight(node);
						inTree = true;
					}
					else
						pos = pos.getRight();
				}
			}
		}
	}
	
    /**
	 * <dt dir="rtl"/>
     * <b>
     * ������ ������ ����� ������� �� ����� key ��� ���� ����� ����;
     * ������ ������ �� ���� ����� ����� ����� �����;
     * <br/>�� ����� ���� ���� ���� ��� ������ null
     * </b>
	 * 
	 * @param key ���� ������
	 * @return ��� ����� �����
     */
	public V remove(String key)
	{
		BinTreeNode<Pair<String,V>> pos = searchKey(key);
		
		if(pos == null)
			return null;
		
		this.numOfKeys--;
		
		BinTreeNode<Pair<String,V>> next = null;
		BinTreeNode<Pair<String,V>> parent = getParent(pos);
		
		if((pos.getLeft() == null) || (pos.getRight() == null))
		{
			if(pos.getLeft() != null)
			{
				next = pos.getLeft();
				pos.setLeft(null);
			}
			else
			{
				next = pos.getRight();
				pos.setRight(null);
			}
			if(parent == null)
				this.root = next;
			else
				if(parent.getLeft() == pos)
					parent.setLeft(next);	
				else
					parent.setRight(next);
		}
		else
		{
			next = pos.getRight();
			while(next.getLeft() != null)
				next = next.getLeft();
			pos.setInfo(next.getInfo());
			if(pos.getRight() != next)
			{
				parent = getParent(next);
				parent.setLeft(next.getRight());
			}
			else
				pos.setRight(next.getRight());
			next.setRight(null);
		}
		
		return pos.getInfo().getValue();
	}
	
    /**
	 * <dt dir="rtl"/>
     * <b>
     * ������ ������ �� ���� ������� ������� ���� ������� ����� ���� ������� ����.
     * �� ���� ����, ����� ���� ����� ���	
     * </b>
	 * 
	 * @return ���� �� ������ ������� ���� ����� ���� ������� ����
     */
	public String[] getAllKeys()
	{
		int i = 0;
		BinTreeNode<Pair<String,V>> tmp = this.root;
		String[] keys = new String[this.numOfKeys];
    	Stack<BinTreeNode<Pair<String,V>>> stk = new Stack<BinTreeNode<Pair<String,V>>>();
    			
		do
		{
			while(tmp != null)
			{
				stk.push(tmp);
				tmp = tmp.getLeft();
			}
			if(!stk.isEmpty())
			{
				tmp = stk.pop();
				keys[i] = tmp.getInfo().getKey();
				i++;
				tmp = tmp.getRight();
			}
		} while(!stk.isEmpty() || tmp != null);
		return(keys);
	}
	
    /**
	 * <dt dir="rtl"/>
     * <b>
     * ������ ������ ������ ������ �� ����
     * </b>
	 * 
	 * @return [key1:value1, key2:value2, key3:value3, ...] :������ ������ �� ���� ����� ���
     */ 
	public String toString()
	{
		int i;
		String str = "[";
		String[] keys = this.getAllKeys();
				
		for(i=0; i<keys.length-1; i++)
			str = str + keys[i] + ":" + this.getValue(keys[i]).toString() + ", ";
		if(keys.length>0)
			str = str + keys[i] + ":" + this.getValue(keys[i]).toString();
		str = str + "]";
		return(str);
	}
	
	
	// ------------------ private methods for help -----------------
	private BinTreeNode<Pair<String,V>> searchKey(String key)
	{
		BinTreeNode<Pair<String,V>> p = this.root;
		
		while(p != null)
		{
			if(key.compareTo(p.getInfo().getKey()) == 0)
				return(p);
			if(key.compareTo(p.getInfo().getKey()) < 0)
				p = p.getLeft();
			else
				p = p.getRight();
		}
		return null;
	}
	
	private BinTreeNode<Pair<String,V>> getParent(BinTreeNode<Pair<String,V>> node)
	{
		BinTreeNode<Pair<String,V>> p = this.root;
		
		while(p != null)
		{
			if((node == p.getLeft()) || (node == p.getRight()))
				return(p);
			if(node.getInfo().getKey().compareTo(p.getInfo().getKey()) >= 0)
				p = p.getRight();
			else
				p = p.getLeft();
		}
		return(null);
	}
}
