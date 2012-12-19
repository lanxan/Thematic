
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Screen{

	/**
	 * @param args
	 */
	
	private Container con;
	private Panel text;
	private Panel menu;
	
	private JButton but;
	//private JRadioButton bis, fav, prod, other;
	
	private JComboBox<String> tokenid;
	private JList<String> list;
	private DefaultListModel<String> model;
	
	private static JTextArea board;		//用static才能在別的地方使用Screen.function()
	private JTextField txa;
	private	JScrollPane spText;
	private ButtonGroup butType;
	
	private static int width = 400;
	private static int height = 500;
	
	public Screen(){
		JFrame demo = new JFrame("Demo");
        demo.setSize(width, height);//設定視窗大小
        demo.setLocationRelativeTo(null);//設定視窗置中
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//設定右上角叉號刪除功能
        //當setEnable設置false時，用這段改變默認的灰色字體
        UIManager.put("TextArea.inactiveForeground", new Color(105, 105, 105));
        
        con = demo.getContentPane();
        con.setBackground(Color.white);
        
        text = new Panel();
        text.setBackground(Color.blue);
        menu = new Panel();
        menu.setBounds(0, 70, width, 60);
        
        //四個選項
/*        bis = new JRadioButton("Business");
        bis.setBackground(Color.white);        
        fav = new JRadioButton("Favorite");
        fav.setBackground(Color.white);
        prod = new JRadioButton("Products");
        prod.setBackground(Color.white);
        other = new JRadioButton("Other");
        other.setBackground(Color.white);*/
        
        //把上述的四個選項合成一個群組
/*        butType = new ButtonGroup();
        butType.add(bis);
        butType.add(fav);
        butType.add(prod);
        butType.add(other);*/
        
        //create a menu list 
        
        tokenid = new JComboBox<String>();
        menu.add(tokenid);
   
        model = new DefaultListModel<String>();
        list = new JList<String>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane  pane= new JScrollPane(list);
        pane.setBackground(Color.pink);
        pane.setBounds(10, 150, width-25, 100);
        
        
        
        //宣告search按鈕，跟google的search很像
        but = new JButton("Search");
        but.setBounds(260, 40, 76, 20);
        JButton cho = new JButton("Choise");
        //cho.setBounds(260, 40, 76, 20);
        menu.add(cho);
        
        //宣告輸入區域
        txa = new JTextField();
        txa.setBounds(55, 40, width-200, 22);
        
        //宣告訊息布告欄
        board = new JTextArea("Tasyt!");
        board.setEnabled(false);
        board.setBackground(new Color(250, 240, 230));
        board.setBounds(10, 260, width-25, 100);
        board.setFont(new Font("Times New Roman", Font.BOLD, 20));
        board.setBorder(new BevelBorder(BevelBorder.LOWERED));//設定框線
        
        spText = new JScrollPane(board);
        
/*        menu.add(bis);
        menu.add(fav);
        menu.add(prod);
        menu.add(other);*/
        
        con.setLayout(null);//把排列版面的控制權拿出來
        con.add(txa);
        con.add(but);
        con.add(menu);
        con.add(board);
        con.add(pane);
        
        demo.setResizable(false);//設定使用者不能停整視窗大小
        demo.setVisible(true);
        
        //以下均為事件
        but.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	
            	board.setText(list.getSelectedValue().toString());
            }
        });
        /* button choice listener:
         * print the selected product id
         * list which text has this product
        */
        cho.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		model.clear();
            	int i = 0;
            	String str = tokenid.getSelectedItem().toString();
            	//board.setText(str);
            	
            	String[] textid = null;
            	textid = new String[50];
            	Main_Process temp = new Main_Process();
				try {
					textid = temp.result_search(str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
	            	while(textid[i] != null){
	            		System.out.println(str+":"+textid[i]);
	        			sendStr2List(textid[i]);
	        			i++;
	        		}
				}
            }
        });
        
	}
	
	//取得search bar裡面的字串
	public String getSearchKeyWord(){
		return txa.getText();
	}
	//取得喜好群組所選擇的項目
/*	public String getInterestedType(){
		if(fav.isSelected()){
			return fav.getText();
		}else if(bis.isSelected()){
			return bis.getText();
		}else if(prod.isSelected()){
			return prod.getText();
		}else if(other.isSelected()){
			return other.getText();
		}else{
			return null;
		}
	}*/
	//private function
	private void item2JCB(JComboBox<String> jmb, String str){
		jmb.addItem(str);
	}
	private void item2ListModel(DefaultListModel<String> md, String str){
		md.addElement(str);
	}
	//provide public function
	//提供寫入board的函式
	public static void printTextInBoard(String msg){
		board.setText(msg);
	}
	public void sendStr2JCB(String str){
		item2JCB(tokenid, str);
	}
	public void sendStr2List(String str){
		item2ListModel(model, str);
	}

}
