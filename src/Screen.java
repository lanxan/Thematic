
import javax.swing.*;

import java.awt.*;

public class Screen {

	/**
	 * @param args
	 */
	private static int width = 400;
	private static int height = 300;
	
	public Screen(){
		JFrame demo = new JFrame("Demo");
        demo.setSize(width, height);//�]�w�����j�p
        demo.setLocationRelativeTo(null);//�]�w�����m��
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�]�w�k�W���e���R���\��
        
        Container con = demo.getContentPane();
        
        JCheckBox checkbox = new JCheckBox("JCheckBox");
        JRadioButton radiobutton = new JRadioButton("JRadiobutton");
        JButton button = new JButton("JButton");
        button.setBounds(60, 60, 100,30);
        JLabel label = new JLabel("JLabel");
        JTextArea textarea = new JTextArea("JTextArea");
        textarea.setBounds(20, 20, 200, 30);
        //con.add(BorderLayout.EAST, checkbox);
        //con.add(BorderLayout.WEST, radiobutton);
        //con.add(BorderLayout.SOUTH, button);
		con.add(BorderLayout.NORTH , label);
        con.add(textarea);
        //con.add(button);
        

         
        demo.setVisible(true);
		
	}
}
