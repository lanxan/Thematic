import java.io.IOException;

import org.json.*;//JSON�榡�ݭn

public class Thematic {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub
		Screen screen = new Screen();
		Screen.printTextInBoard("No");
		//�s�[����
		Main_Process mp = new Main_Process();
		try {
			JSONArray ja = mp.JSON_productID_read();
			System.out.println(ja);
			System.out.println(ja.get(0));
		} catch (IOException e) {
			// TODO �۰ʲ��ͪ� catch �϶�
			e.printStackTrace();
		}
		//�s�[���յ����AJSONArray���A��ja���@�}�C�榡�C�Y�n���o�Ĥ@����ƥi��get(0)�����
		//EX:ja.get(0) (���A��object�ӫDstring)
	}
}
