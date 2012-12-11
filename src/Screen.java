
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
	private static JTextArea board;		//��static�~��b�O���a��ϥ�Screen.function()
	private JTextField txa;
	private	JScrollPane spText;
	private ButtonGroup butType;
	
	private static int width = 400;
	private static int height = 300;
	
	public Screen(){
		JFrame demo = new JFrame("Demo");
        demo.setSize(width, height);//�]�w�����j�p
        demo.setLocationRelativeTo(null);//�]�w�����m��
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�]�w�k�W���e���R���\��
        //��setEnable�]�mfalse�ɡA�γo�q�����q�{���Ǧ�r��
        UIManager.put("TextArea.inactiveForeground", new Color(105, 105, 105));
        
        con = demo.getContentPane();
        con.setBackground(Color.white);
        
        text = new Panel();
        text.setBackground(Color.blue);
        menu = new Panel();
        menu.setBounds(0, 70, width, 60);
        
        //�|�ӿﶵ
        bis = new JRadioButton("Business");
        bis.setBackground(Color.white);        
        fav = new JRadioButton("Favorite");
        fav.setBackground(Color.white);
        prod = new JRadioButton("Products");
        prod.setBackground(Color.white);
        other = new JRadioButton("Other");
        other.setBackground(Color.white);
        
        //��W�z���|�ӿﶵ�X���@�Ӹs��
        butType = new ButtonGroup();
        butType.add(bis);
        butType.add(fav);
        butType.add(prod);
        butType.add(other);
        
        //�ŧisearch���s�A��google��search�ܹ�
        but = new JButton("Search");
        but.setBounds(260, 40, 76, 20);
        
        //�ŧi��J�ϰ�
        txa = new JTextField();
        txa.setBounds(55, 40, width-200, 22);
        
        //�ŧi�T�����i�欰
        board = new JTextArea("Tasyt!");
        board.setEnabled(false);
        board.setBackground(new Color(250, 240, 230));
        board.setBounds(10, 150, width-25, 100);
        board.setFont(new Font("Times New Roman", Font.BOLD, 20));
        board.setBorder(new BevelBorder(BevelBorder.LOWERED));//�]�w�ؽu
        
        spText = new JScrollPane(board);
        
        menu.add(bis);
        menu.add(fav);
        menu.add(prod);
        menu.add(other);
        
        con.setLayout(null);//��ƦC�����������v���X��
        con.add(txa);
        con.add(but);
        con.add(menu);
        con.add(board);
        
        demo.setResizable(false);//�]�w�ϥΪ̤��ఱ������j�p
        demo.setVisible(true);
        
        //�H�U�����ƥ�
        but.addActionListener(new searchListener());
	}
	
	//���osearch bar�̭����r��
	public String getSearchKeyWord(){
		return txa.getText();
	}
	//���o�ߦn�s�թҿ�ܪ�����
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
	//���Ѽg�Jboard���禡
	public static void printTextInBoard(String msg){
		board.setText(msg);
	}
	//��search�Q���U�ɵo�ͪ��Ʊ�
	class searchListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			board.setText(getInterestedType());
			//do analysis..., call some function
		}
	}

}
