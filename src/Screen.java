
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen {

	private static final ActionListener ActionListener = null;

	/**
	 * @param args
	 */
	private Container con;
	
	private Panel text;
	private Panel menu;
	
	private JButton but;
	private JRadioButton bis, fav, prod, other;
	private static JTextArea board;		//用static才能在別的地方使用Screen.function()
	private JTextField txa;
	private	JScrollPane spText;
	private ButtonGroup butType;
	
	private static int width = 400;
	private static int height = 300;
	
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
        bis = new JRadioButton("Business");
        bis.setBackground(Color.white);        
        fav = new JRadioButton("Favorite");
        fav.setBackground(Color.white);
        prod = new JRadioButton("Products");
        prod.setBackground(Color.white);
        other = new JRadioButton("Other");
        other.setBackground(Color.white);
        
        //把上述的四個選項合成一個群組
        butType = new ButtonGroup();
        butType.add(bis);
        butType.add(fav);
        butType.add(prod);
        butType.add(other);
        
        //宣告search按鈕，跟google的search很像
        but = new JButton("Search");
        but.setBounds(260, 40, 76, 20);
        
        //宣告輸入區域
        txa = new JTextField();
        txa.setBounds(55, 40, width-200, 22);
        
        //宣告訊息布告欄為
        board = new JTextArea("Tasyt!");
        board.setEnabled(false);
        board.setBackground(new Color(250, 240, 230));
        board.setBounds(10, 150, width-25, 100);
        board.setFont(new Font("Times New Roman", Font.BOLD, 20));
        board.setBorder(new BevelBorder(BevelBorder.LOWERED));//設定框線
        
        spText = new JScrollPane(board);
        
        menu.add(bis);
        menu.add(fav);
        menu.add(prod);
        menu.add(other);
        
        con.setLayout(null);//把排列版面的控制權拿出來
        con.add(txa);
        con.add(but);
        con.add(menu);
        con.add(board);
        
        demo.setResizable(false);//設定使用者不能停整視窗大小
        demo.setVisible(true);
        
        //以下均為事件
        but.addActionListener(new searchListener());
	}
	
	//取得search bar裡面的字串
	public String getSearchKeyWord(){
		return txa.getText();
	}
	//取得喜好群組所選擇的項目
	public String getInterestedType(){
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
	}
	//提供寫入board的函式
	public static void printTextInBoard(String msg){
		board.setText(msg);
	}
	//當search被按下時發生的事情
	class searchListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			board.setText(getInterestedType());
			//do analysis..., call some function
		}
	}

}
