
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

import org.json.JSONException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Screen{

	/**
	 * @param args
	 */
	
	protected static JTextArea prodName = null;
	protected static JTextArea matchWord = null;
	
	private Container con;
	private Panel product;
	private Panel textAtt;
	private Panel boardPanel;
	
	private JComboBox<String> tokenid;
	private JList<String> list;
	private DefaultListModel<String> model;
	
	private static JTextArea board;		//用static才能在別的地方使用Screen.function()
	
	private static int width = 1280;
	private static int height = 720;
	
	public Screen(){
		JFrame demo = new JFrame("Article keyword aware product recommendation system");
        demo.setSize(width, height);//設定視窗大小
        demo.setLocationRelativeTo(null);//設定視窗置中
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//設定右上角叉號刪除功能
      //當setEnable設置false時，用這段改變默認的灰色字體
        UIManager.put("TextArea.inactiveForeground", new Color(105, 105, 105));
        
      //JMenuBar
        JMenuBar bar = new JMenuBar();
        
        JMenu fun = new JMenu("功能(F)");
        fun.setMnemonic('F');
        bar.add(fun);
        
        JMenuItem clear = new JMenu("清空(c)");
        clear.setMnemonic('c');
        fun.add(clear);
        clear.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
            	board.setText("");
            }
        });
        JMenu help = new JMenu("Help(H)");
        help.setMnemonic('h');
        bar.add(help);
        
        demo.setJMenuBar(bar);
         
        
        
        con = demo.getContentPane();
        con.setBackground(Color.white);
        
        product = new Panel();
        product.setBounds(0, 0, width, 100);
        product.setBackground(new Color(254, 255, 175));
        product.setLayout(null);
        
        textAtt = new Panel();
        textAtt.setBounds(0, 100, width, 140);
        textAtt.setBackground(new Color(254, 255, 175));
        textAtt.setLayout(null);
        
      //宣告訊息布告欄   
        boardPanel = new Panel();
        boardPanel.setBounds(0, 240, width, height-(100+140));
        boardPanel.setBackground(new Color(230, 255, 175));
        boardPanel.setLayout(null);
        
        JLabel content = new JLabel("Content:");
        content.setFont(new Font("標楷體", Font.BOLD, 20));
        content.setBounds(10, 10, 100, 20);
        boardPanel.add(content);
        
        
        board = new JTextArea();
        board.setEnabled(false);
       // board.setLineWrap(true);
        board.setBackground(new Color(250, 240, 230));
        board.setFont(new Font("Times New Roman", Font.BOLD, 20));
        board.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(74, 146, 75), new Color(74, 146, 75)));//設定框線
        
        JScrollPane spText = new JScrollPane(board);
        spText.setBounds(10, 40, width-28, height-(100+150)-90);
        
        boardPanel.add(spText);
        
      //create a product list 
        // show product id
        tokenid = new JComboBox<String>();
        tokenid.setFont(new Font("標楷體", Font.BOLD, 20));
        tokenid.setBounds(10, 10, 200, 30);
        product.add(tokenid);
        
      //宣告choice按鈕
        JButton cho = new JButton("Choice");
        cho.setFont(new Font("標楷體", Font.BOLD, 20));
        cho.setBounds(205, 10, 100, 30);
        product.add(cho);
        
      //show product name
        prodName = new JTextArea();
        prodName.setEnabled(false);
        prodName.setBounds(10, 50, 700, 32);
        prodName.setBorder(new BevelBorder(BevelBorder.LOWERED));
        prodName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        product.add(prodName);
        
      //文章
      //show the match text id
        model = new DefaultListModel<String>();
        list = new JList<String>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("標楷體", Font.BOLD, 20));
        
        JScrollPane pane= new JScrollPane(list);
        pane.setBackground(Color.pink);
        pane.setBounds(10, 10, width-150, 80);
        textAtt.add(pane);
      //create a button to show the selected text
        JButton txc = new JButton("Read");
        txc.setBounds((width-150+10+10), 59, 70, 30);
        textAtt.add(txc);
      //match word label
        JLabel mat = new JLabel("Match:");
        mat.setFont(new Font("標楷體", Font.BOLD, 20));
        mat.setBounds(12, 100, 70, 30);
        mat.setBackground(Color.green);
        textAtt.add(mat);
      //show the match word in the text
        matchWord = new JTextArea();
        matchWord.setEnabled(false);
        matchWord.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
        JScrollPane spMat = new JScrollPane(matchWord);
        spMat.setBounds(80, 100, 500, 30);
        spMat.setBorder(new BevelBorder(BevelBorder.LOWERED));
         
        textAtt.add(spMat);
        
        
      //把排列版面的控制權拿出來
        con.setLayout(null);
        
        con.add(product);
        con.add(textAtt);
        con.add(boardPanel);
        
        demo.setResizable(false);//設定使用者不能停整視窗大小
        demo.setVisible(true);
        
      //以下均為事件
      /* button choice listener:
       * print the selected product id
       * list which text has this product
       */
        cho.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		model.clear();
            	int i = 0;
            	Main_Process temp = new Main_Process();
            	
            	String str = tokenid.getSelectedItem().toString();
            	try {
					prodName.setText(temp.produrctName_return(str));
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (JSONException e2) {
					e2.printStackTrace();
				}
            	String[] textid = null;
            	textid = new String[50];    	
            	
				try {
					textid = temp.result_search(str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
	            	while(textid[i] != null){
	        			sendStr2List(textid[i]);
	        			i++;
	        		}
				}
            }
        });
        txc.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(list.getSelectedIndex() == -1)
        			list.setSelectedIndex(0);
        		
        		Main_Process temp = new Main_Process();
        		
        		String art = list.getSelectedValue().toString();
        		System.out.println(art);
        		
        		String prod = tokenid.getSelectedItem().toString();
        		
        		try {
					matchWord.setText(temp.match(art, prod));
					board.setText(temp.article_recovery(art));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
	}


	//private function
	private void item2JCB(JComboBox<String> jmb, String str){
		jmb.addItem(str);
	}
	private void item2ListModel(DefaultListModel<String> md, String str){
		md.addElement(str);
	}
  //provide public function
	public void sendStr2JCB(String str){
		item2JCB(tokenid, str);
	}
	public void sendStr2List(String str){
		item2ListModel(model, str);
	}

}
