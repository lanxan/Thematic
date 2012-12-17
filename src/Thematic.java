import java.io.IOException;
import java.lang.reflect.Array;

import org.json.*;//JSON格式需要

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
		
		//新加測試
		Main_Process mp = new Main_Process();
		try {
			 ja = mp.JSON_productID_read();
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
		//System.out.println(ja);
		//新加測試結束，JSONArray型態的ja為一陣列格式。若要取得第一筆資料可用get(0)來獲取
		//EX:ja.get(0) (型態為object而非string)
		Screen screen = new Screen();
		for(i = 0;i < ja.length();i++){
			screen.sendStr2JCB(ja.get(i).toString());
		}
		
	}
}
