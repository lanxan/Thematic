import java.io.IOException;

import org.json.*;//JSON格式需要

public class Thematic {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub
		Screen screen = new Screen();
		Screen.printTextInBoard("No");
		//新加測試
		Main_Process mp = new Main_Process();
		try {
			JSONArray ja = mp.JSON_productID_read();
			System.out.println(ja);
			System.out.println(ja.get(0));
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
		//新加測試結束，JSONArray型態的ja為一陣列格式。若要取得第一筆資料可用get(0)來獲取
		//EX:ja.get(0) (型態為object而非string)
	}
}
