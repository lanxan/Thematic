import java.io.IOException;
import java.lang.reflect.Array;

import org.json.*;//JSON�榡�ݭn

public class Thematic {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JSONException, IOException {
		// TODO Auto-generated method stub
		int i = 0;
		
		JSONArray ja = null;
		
		//�s�[����
		Main_Process mp = new Main_Process();
		try {
			 ja = mp.JSON_productID_read();
		} catch (IOException e) {
			// TODO �۰ʲ��ͪ� catch �϶�
			e.printStackTrace();
		}
		//System.out.println(ja);
		//�s�[���յ����AJSONArray���A��ja���@�}�C�榡�C�Y�n���o�Ĥ@����ƥi��get(0)�����
		//EX:ja.get(0) (���A��object�ӫDstring)
		Screen screen = new Screen();
		for(i = 0;i < ja.length();i++){
			screen.sendStr2JCB(ja.get(i).toString());
		}
		
	}
}
