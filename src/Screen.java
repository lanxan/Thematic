
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
	
	private static JTextArea board;		//��static�~��b�O���a��ϥ�Screen.function()
	private JTextField txa;
	private	JScrollPane spText;
	private ButtonGroup butType;
	
	private static int width = 400;
	private static int height = 500;
	
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
/*        bis = new JRadioButton("Business");
        bis.setBackground(Color.white);        
        fav = new JRadioButton("Favorite");
        fav.setBackground(Color.white);
        prod = new JRadioButton("Products");
        prod.setBackground(Color.white);
        other = new JRadioButton("Other");
        other.setBackground(Color.white);*/
        
        //��W�z���|�ӿﶵ�X���@�Ӹs��
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
        
        
        
        //�ŧisearch���s�A��google��search�ܹ�
        but = new JButton("Search");
        but.setBounds(260, 40, 76, 20);
        JButton cho = new JButton("Choise");
        //cho.setBounds(260, 40, 76, 20);
        menu.add(cho);
        
        //�ŧi��J�ϰ�
        txa = new JTextField();
        txa.setBounds(55, 40, width-200, 22);
        
        //�ŧi�T�����i��
        board = new JTextArea("Tasyt!");
        board.setEnabled(false);
        board.setBackground(new Color(250, 240, 230));
        board.setBounds(10, 260, width-25, 100);
        board.setFont(new Font("Times New Roman", Font.BOLD, 20));
        board.setBorder(new BevelBorder(BevelBorder.LOWERED));//�]�w�ؽu
        
        spText = new JScrollPane(board);
        
/*        menu.add(bis);
        menu.add(fav);
        menu.add(prod);
        menu.add(other);*/
        
        con.setLayout(null);//��ƦC�����������v���X��
        con.add(txa);
        con.add(but);
        con.add(menu);
        con.add(board);
        con.add(pane);
        
        demo.setResizable(false);//�]�w�ϥΪ̤��ఱ������j�p
        demo.setVisible(true);
        
        //�H�U�����ƥ�
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
	
	//���osearch bar�̭����r��
	public String getSearchKeyWord(){
		return txa.getText();
	}
	//���o�ߦn�s�թҿ�ܪ�����
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
	//���Ѽg�Jboard���禡
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
