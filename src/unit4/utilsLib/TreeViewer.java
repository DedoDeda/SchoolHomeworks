package unit4.utilsLib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import unit4.collectionsLib.Queue;


class TreeViewer
{
  private JFrame f;
  private Object tree;
  private TreePanelB panel;
  private JComboBox cbFonts;
  private JComboBox cbSize;
  private JComboBox cbOrder;
  private JTextField labelOrder;
  
  private TreeViewer(JFrame f, TreePanelB p,Object t) throws Exception
  {
   this.f = f;
   this.panel = p;
   this.tree = clone(t);
   
//Class<?> c = t.getClass(); 
//Constructor[] constructors = c.getDeclaredConstructors();
//for(int i = 0; i < constructors.length; i++)   // Display constructors.
//    System.err.println(constructors[i]);
  }
  
  /**
   * ����� ������ ����� ����� �� �����
   * @param tree 
   */
  public static void show(Object tree, String title)
  {  
   JFrame f = new JFrame(title + " - ���� �� �����");
   f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch(Exception exception1)
            {
             try
             {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                }
             catch(Exception exception2)
             {
              System.err.println("Error loading L&F: " + exception2);
             }
            }
     f.setSize(650,450);
     f.setLocationRelativeTo(null);
     try
     {
      TreePanelB panel = new TreePanelB(tree,20);
//System.err.println("1");
     TreeViewer td = new TreeViewer(f,panel,tree);
//System.err.println("2");
      td.createGUI();
//System.err.println("3");
     } catch (Exception e) {System.err.println(e); }
     }
  
  private void createGUI() throws Exception
  {
     JPanel orderPanel = new JPanel();
     orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.X_AXIS));
     
//System.err.println("4");
     labelOrder = new JTextField(" " + preOrderTraversal(tree));
//System.err.println("4.1");
         labelOrder.setEditable(false);
         labelOrder.setEnabled(true);
         labelOrder.setFont(new Font("Arial",0,12));
         
         String[] orders = (new String[] {"PreOrder - ����� ���� �����","InOrder - ����� ���� ����","PostOrder - ����� ���� ����","LevelOrder - ����� ��� ����"});
         cbOrder = new JComboBox(orders);
         cbOrder.setSelectedIndex(0);
         cbOrder.setEditable(false);
         cbOrder.setFocusable(false);
         cbOrder.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         ActionListener actionlistener = new ActionListener() 
         {
             public void actionPerformed(ActionEvent actionevent) 
             {
//System.err.println("5");
              try
              {
                  switch(cbOrder.getSelectedIndex())
                  {
                   case 0: labelOrder.setText(" " + preOrderTraversal(tree)); break;
                   case 1: labelOrder.setText(" " + inOrderTraversal(tree)); break;
                   case 2: labelOrder.setText(" " + postOrderTraversal(tree)); break;
                   case 3: labelOrder.setText(" " + levelOrderTraversal(tree)); break;
                  }
              } catch (Exception e) {System.err.println(e); }
                 f.repaint();
             }
         };
         cbOrder.addActionListener(actionlistener);
         orderPanel.add(cbOrder);
         orderPanel.add(labelOrder);
     
         
         f.getContentPane().add(panel,BorderLayout.CENTER);
     f.getContentPane().add(orderPanel,BorderLayout.SOUTH);
     
     JPanel p1 = new JPanel();
     p1.add(new JLabel("      "));
     p1.setBackground(Color.WHITE);
     JPanel p2 = new JPanel(new GridLayout(3,1));
     p2.add(new JLabel(" "));
     p2.add(new JLabel(" "));
     p2.setBackground(Color.WHITE);
     f.getContentPane().add(p1,BorderLayout.WEST);
     f.getContentPane().add(p2,BorderLayout.NORTH);
     
     f.setJMenuBar(createMenu());
     f.setResizable(true);
     //f.setIconImage(Utils.getHujiImage());
      f.setVisible(true);
  }
  
  public static JPanel getTreeShowAsPanel(Object t, int nodeSize) throws Exception
  {
     TreePanelB panel = new TreePanelB(t,nodeSize);
     return(panel);
  }
  
  private JMenuBar createMenu()
  { 
   //Create the menu bar.
   JMenuBar menuBar = new JMenuBar();
   menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
          
   JLabel lbl = new JLabel(" ���� ����: ");
         menuBar.add(lbl);
     
         String[] fontSizes = (new String[] {
             "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", 
             "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", 
             "28", "29", "30", "34", "38", "42", "48", "52", "60", "70"
             });
         cbSize = new JComboBox(fontSizes);
         cbSize.setMaximumSize(new Dimension(50, 21));
         cbSize.setEditable(true);
         cbSize.setSelectedItem("20");
         cbSize.setEditable(false);
         cbSize.setFocusable(false);
         cbSize.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         //cbSize.setBackground(Color.YELLOW);
         ActionListener actionlistener = new ActionListener() 
         {
             public void actionPerformed(ActionEvent actionevent)
             {
                 int fontSize = 0;
                 try
                 {
                  fontSize = Integer.parseInt(cbSize.getSelectedItem().toString());
                 }
                 catch(NumberFormatException numberformatexception)
                 {
                     return;
                 }
                 panel.RADIUS = fontSize;
                 panel.font = new Font(cbFonts.getSelectedItem().toString(),Font.PLAIN,panel.RADIUS-2);
                 f.repaint();
             }

         };
         cbSize.addActionListener(actionlistener);
         menuBar.add(cbSize); 
   
         // Build the Fonts toolBar
         lbl = new JLabel("   ����: ");
         menuBar.add(lbl);
         
         GraphicsEnvironment graphicsenvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
         String[] fontNames = graphicsenvironment.getAvailableFontFamilyNames();
         cbFonts = new JComboBox(fontNames);
         cbFonts.setMaximumSize(new Dimension(200, 21));
         cbFonts.setEditable(true);
         cbFonts.setSelectedItem("Arial");
         cbFonts.setEditable(false);
         cbFonts.setFocusable(false);
         cbFonts.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         //cbFonts.setBackground(Color.YELLOW);
         actionlistener = new ActionListener() {

             public void actionPerformed(ActionEvent actionevent)
             {
              panel.font = new Font(cbFonts.getSelectedItem().toString(),Font.PLAIN,panel.RADIUS-2);
              f.repaint();
             }

         };
         cbFonts.addActionListener(actionlistener);
         menuBar.add(cbFonts);
         
   //Build the HELP menu.
         menuBar.add(Box.createHorizontalGlue());
         JMenu menu = new JMenu("�����");
   menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   menu.setFont(new Font(menu.getFont().getFamily(), Font.BOLD, menu.getFont().getSize()-1));
   
   JMenuItem menuItem = new JMenuItem("����");
   menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   menuItem.setFont(new Font(menuItem.getFont().getFamily(), Font.PLAIN, menuItem.getFont().getSize()-1));
   menuItem.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent actionevent)
             {
           String[] msgs = {"����","\n","����� ���� �� ����� ����� �����."};
              showHebrewMessageDialog(f,msgs, "���� �� �����" ,JOptionPane.INFORMATION_MESSAGE);  
             }
         });
         menu.add(menuItem);
         menuItem = new JMenuItem("������ ������");
   menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   menuItem.setFont(new Font(menuItem.getFont().getFamily(), Font.PLAIN, menuItem.getFont().getSize()-1));
   menuItem.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent actionevent)
             {
           String[] msgs = {"������ ������","\n",CENTER,TEAM,"\n","�����: ���� ���."};
              showHebrewMessageDialog(f ,msgs, "���� �� �����" ,JOptionPane.INFORMATION_MESSAGE);  
             }
         });
         menu.add(menuItem);
         menuBar.add(menu,Component.LEFT_ALIGNMENT);
         
   return menuBar;
  }
  
  private static final String CENTER = "����� ������ ������ �� ����������� ������ �������� 2007.";
  private static final String TEAM = "���� ������: ����' ������ ����, ���� �����, ��� ��������, ���� ���, ��� ��������, ��� ���� ����� ����.";

  private static  void showHebrewMessageDialog(Component cmp, String[] msg, String title, int msgType) 
  {
   JPanel area = new JPanel(new GridLayout(msg.length,1));
   JLabel[] labels = new JLabel[msg.length];
   
   for(int i=0; i<msg.length; i++)
   {
    labels[i] = new JLabel(msg[i]);
    area.add(labels[i]);
    labels[i].setHorizontalAlignment(JTextField.RIGHT);
    if(i==0)
     labels[i].setFont(new Font(labels[i].getFont().getFamily(), Font.BOLD, labels[i].getFont().getSize()));
    else
     labels[i].setFont(new Font(labels[i].getFont().getFamily(), Font.PLAIN,  labels[i].getFont().getSize()));   
   }
      
      JOptionPane.showMessageDialog(cmp,area,title,msgType);
  }
  
  private static String preOrderTraversal(Object tree) throws Exception
  {
   String str = new String();

   str = str + (tree.getClass().getMethod("getInfo",(Class[])null).invoke(tree, (Object[])null)).toString() + " , ";    
   Object left = tree.getClass().getMethod("getLeft",(Class[])null).invoke(tree, (Object[])null);
   Object right = tree.getClass().getMethod("getRight",(Class[])null).invoke(tree, (Object[])null);
   if (left != null)
    str = str + preOrderTraversal(left);
   
   if (right != null)
    str = str + preOrderTraversal(right);
   
   return(str);
  }
  
  private static String inOrderTraversal(Object tree) throws Exception
  {
   String str = new String();
   
   Object left = tree.getClass().getMethod("getLeft",(Class[])null).invoke(tree, (Object[])null);
   Object right = tree.getClass().getMethod("getRight",(Class[])null).invoke(tree, (Object[])null);
   
   if (left != null)
    str = str + inOrderTraversal(left);
   
   str = str + tree.getClass().getMethod("getInfo",(Class[])null).invoke(tree,(Object[])null).toString() + " , ";
   
   if (right != null)
    str = str + inOrderTraversal(right);
   
   return(str);
  }
  
  private static String postOrderTraversal(Object tree) throws Exception
  {
   String str = new String();
   
   Object left = tree.getClass().getMethod("getLeft",(Class[])null).invoke(tree, (Object[])null);
   Object right = tree.getClass().getMethod("getRight",(Class[])null).invoke(tree, (Object[])null);
   
   if (left != null)
    str = str + postOrderTraversal(left);
   
   if (right != null)
    str = str + postOrderTraversal(right);
   
   str = str + tree.getClass().getMethod("getInfo",(Class[])null).invoke(tree, (Object[])null).toString() + " , ";
   
   return(str);
  }
  
     private static  String levelOrderTraversal(Object tree) throws Exception
  {
   Object t;
   String str = new String();
   Queue<Object> q = new Queue<Object>();
   
   q.insert(tree);
   while(!q.isEmpty())
   {
    t = q.remove();
    str = str + t.getClass().getMethod("getInfo",(Class[])null).invoke(t,(Object[])null).toString() + " , ";
    Object left = t.getClass().getMethod("getLeft",(Class[])null).invoke(t, (Object[])null);
    Object right = t.getClass().getMethod("getRight",(Class[])null).invoke(t, (Object[])null);
    if(left != null)
     q.insert(left);
    if(right != null)
     q.insert(right);
   }
    
   return(str);
  }
     
     private static Object clone(Object tree) throws Exception
  {
   Object left = null;
   Object right = null;
   
   Object l = tree.getClass().getMethod("getLeft",(Class[])null).invoke(tree, (Object[])null);
   Object r = tree.getClass().getMethod("getRight",(Class[])null).invoke(tree, (Object[])null);
   
   if(l != null)
    left = clone(l);
   if(r != null)
    right = clone(r);
   
   Object info = tree.getClass().getMethod("getInfo",(Class[])null).invoke(tree, (Object[])null);
   Class[] param = {tree.getClass(),Object.class,tree.getClass()};
   return(tree.getClass().getConstructor(param).newInstance(left,info,right));
   //return new BinTree<T>(tree.getInfo(), left, right);
   
  }
   
}

@SuppressWarnings("serial") 
class TreePanelB extends JPanel 
{
  private  int V_SKIP;
  private  int H_SKIP;
  public  int RADIUS;
  public  Font font; 
  private  final int MAXNODES = 200;

  private TVNodeB[] nodes = new TVNodeB[MAXNODES];
  private Link[] links = new Link[MAXNODES-1];
  private int numNodes = 0;
  private int numLinks = 0;
  

   public TreePanelB(Object t, int nodeSize) throws Exception
   {
    super();
    
    RADIUS = nodeSize;
    font = new Font("Arial",Font.PLAIN,RADIUS-2); 
  if (t != null)
     buildTree(t, 0);
   }
   

 public int buildTree(Object t, int depth) throws Exception
   {
    try
    {
     // the next 3 lines can be remove. they only for check!
     //t.setInfo(t.getInfo());
     //t.setLeft(t.getLeft());
     //t.setRight(t.getRight());
     // end of check
   Object info = t.getClass().getMethod("getInfo",(Class[])null).invoke(t, (Object[])null);
   
     Object left = t.getClass().getMethod("getLeft",(Class[])null).invoke(t, (Object[])null);
   Object right = t.getClass().getMethod("getRight",(Class[])null).invoke(t, (Object[])null);
   
     if (left != null) 
     {
      int u = buildTree(left, depth+1);
      links[numLinks] = new Link( u, numNodes );
      numLinks++;
     }
    
     int index = numNodes;
     nodes[numNodes] = new TVNodeB(info.toString(), numNodes, depth );
     numNodes++;
     
     if (right != null) 
     {
      int u = buildTree(right, depth+1);
      links[numLinks] = new Link( u, index );
      numLinks++;
     }
     return index;
    }
    catch(java.lang.NoSuchMethodError e)
    {
     System.err.print("Error in BinTree.java >>> ");
     String m = e.toString().substring(e.toString().indexOf(":"),e.toString().indexOf("("));
     m = m.substring(m.indexOf(".")+1,m.length());
     System.err.println(m + "() not found !!!");
     System.exit(1);
    }
    return(0);
  }

  public void paint(Graphics graphics) 
  {
  super.paint(graphics);
  Graphics2D g = (Graphics2D) graphics;
       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       g.setColor(Color.white);
       g.fillRect(0,0,getWidth(),getHeight());
       int maxx=0,maxy=0;
       for( int i = 0; i < numNodes; i++ )
       {
        if (maxx<nodes[i].x)
         maxx = nodes[i].x;
        if (maxy<nodes[i].y)
         maxy = nodes[i].y;
       }
    
       // resize                
       V_SKIP = (this.getHeight() / (maxy+1)) - RADIUS;
       H_SKIP = (this.getWidth() / (maxx+1)) - (int)(RADIUS/4.5);
       
       // draw the lines
       for( int i = 0; i < numLinks; i++ )
       {
        g.setColor(Color.black);
        g.drawLine(2*RADIUS + nodes[ links[i].u ].x * H_SKIP, 2*RADIUS + nodes[ links[i].u ].y * V_SKIP,
                       2*RADIUS + nodes[ links[i].v ].x * H_SKIP, 2*RADIUS + nodes[ links[i].v ].y * V_SKIP);
       }
       
       // draw the nodes
       g.setColor(Color.white);
       for( int i = 0; i < numNodes; i++ ) 
       {
        g.setColor(Color.yellow);
        g.fillOval(RADIUS + nodes[i].x * H_SKIP, RADIUS + nodes[i].y * V_SKIP, 2*RADIUS, 2*RADIUS);
        g.setColor(Color.black);
        g.drawOval(RADIUS + nodes[i].x * H_SKIP, RADIUS + nodes[i].y * V_SKIP, 2*RADIUS, 2*RADIUS);
        Rectangle r = new Rectangle(RADIUS + nodes[i].x * H_SKIP, RADIUS + nodes[i].y * V_SKIP, 2*RADIUS, 2*RADIUS);
        
        
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);
        String str = nodes[i].key;
        int w = fm.charsWidth(str.toCharArray(),0,str.length());
        int h = fm.getHeight();     
        g.drawString(str, r.x + (r.width - w)/2, r.y + ((r.height)/2 + h/4 + 1));
       }
  }
}

class TVNodeB
{
  protected String key;
  protected int x;
  protected int y;

  public TVNodeB( String keyVal, int xVal, int yVal )
  {
    key = keyVal;
    x = xVal;
    y = yVal;
  }
}

class Link
{
  protected int u;
  protected int v;

  public Link( int uVal, int vVal )
  {
    u = uVal;
    v = vVal;
  }
}