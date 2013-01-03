import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.*;

public class Main_Process {

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	/*json用*/
	private StringBuilder readStr = new StringBuilder("");
	private Boolean judge = false;
	private JSONObject j_ob = null;
	private JSONObject j_ob_1 = null;
	private JSONObject j_ob_match = null;
    JSONArray j_a = null;
	/*result 用*/
	private String article_ID = null,product_temp = null;
	private String csv_product_ID;
	private String match_position = null;
	private String[] csv_article_ID = new String[50];
	int count = 0;
	/*共用*/
	private String str_readLine = "";
	public String js_build(String filename) throws IOException {

		Reader frj = new FileReader(filename);
		BufferedReader brj = new BufferedReader(frj);
       
        while(brj.ready()) {
        	str_readLine = brj.readLine();
        	if(judge == true) {
        		readStr.append(str_readLine.trim());
        		judge = false;
        	}
        	else
        		readStr.append(str_readLine.replaceAll(" ", ""));
        	if(str_readLine.endsWith("[")) {
        		judge = true;
        	}
        }
        judge = false;
        return readStr.toString();
	}
	public JSONArray JSON_productID_read() throws IOException, JSONException {

        j_ob = new JSONObject(js_build("myProduct.json"));
		j_a = j_ob.getJSONObject("Product").names();
		readStr.delete(0, readStr.length());

		return j_a;
	}
	public String[] result_search(String search_str) throws IOException {

        try {
        	Reader fr = new FileReader("result.csv");
        	BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				str_readLine = br.readLine();
				article_ID = str_readLine.split(":")[0];
				product_temp = str_readLine.split(",")[1];

				for(int i = 0;i<product_temp.split(" ").length;i++) {
					csv_product_ID = product_temp.split(" ")[i];
					if(search_str.compareTo(csv_product_ID) == 0) {
						if(count != 0) {
							if(csv_article_ID[count-1].compareTo(article_ID) != 0) {
							csv_article_ID[count] = article_ID;
							count++;
							}
						}
						else {
							csv_article_ID[count] = article_ID;
							count++;
						}
					}
				}
			}
			if(csv_article_ID[0] == null)
				csv_article_ID[0] = "NO ARTICLES ARE FOUND!!";
		} catch (FileNotFoundException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
        count = 0;
        return csv_article_ID;
	}
	public String article_recovery(String ID) throws IOException, JSONException {

		j_ob = new JSONObject(js_build("mytext.json"));
		readStr.delete(0, readStr.length());
		str_readLine = "";

		for(int i=0;i<j_ob.getJSONObject("TextItem").getJSONArray(ID).length();i++) {
			if(j_ob.getJSONObject("TextItem").getJSONArray(ID).get(i).toString().compareTo("<s>") == 0)
				str_readLine = str_readLine.concat("\n");
			else if(j_ob.getJSONObject("TextItem").getJSONArray(ID).get(i).toString().compareTo("<P>") == 0)
				str_readLine = str_readLine.concat("   ");
			else
				str_readLine = str_readLine.concat(j_ob.getJSONObject("TextItem").getJSONArray(ID).get(i).toString()+" ");
		}

        return str_readLine;
	}
	public void test() throws IOException, JSONException {

		Reader zx = new FileReader("result.csv");
		BufferedReader qw = new BufferedReader(zx);
        String[] fuck = new String[50];
        int cc = 0;
        qw.close();zx.close();
        
		try {
			JSONArray T_T = null;
			T_T = JSON_productID_read();
			for(int i=0;i<T_T.length();i++) {
				fuck = result_search(T_T.get(i).toString());
				if(fuck[0] != null) {
					//System.out.println(T_T.get(i).toString()+" : "+j_ob_1.getJSONObject("Product").getJSONArray(T_T.get(i).toString()).get(0));
					System.out.println(T_T.get(i).toString()+" : "+produrctName_return(T_T.get(i).toString()));
					while(fuck[cc] != null) {
						//System.out.println(fuck[cc]+" : "+match(fuck[cc],T_T.get(i).toString()));
						fuck[cc] = null;
						cc++;
					}
				}
				cc = 0;
				fuck = null;
				System.out.println("********************************************************************************************************");
			}

		} catch (JSONException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
	}
	public String match(String art,String prod) throws IOException, JSONException {
		Reader f_csv = new FileReader("result.csv");
		BufferedReader br_csv = new BufferedReader(f_csv);
		String ttt = "";

		j_ob_match = new JSONObject(js_build("mytext.json"));
		readStr.delete(0, readStr.length());

		while (br_csv.ready()) {
			str_readLine = br_csv.readLine();
			article_ID = str_readLine.split(":")[0];
			product_temp = str_readLine.split(",")[1];

			if(art.compareTo(article_ID) == 0) {
				for(int i = 0;i<product_temp.split(" ").length;i++) {
					if(prod.compareTo(product_temp.split(" ")[i]) == 0) {
						product_temp = str_readLine.split(":")[1];
						match_position = product_temp.split("-")[0];
						ttt = ttt.concat(" "+j_ob_match.getJSONObject("TextItem").getJSONArray(art).get(Integer.valueOf(match_position)-1).toString());
					}
				}
			}
		}
		return ttt;
	}
	public String produrctName_return(String prod) throws IOException, JSONException {

        j_ob_1 = new JSONObject(js_build("myProduct.json"));
        readStr.delete(0, readStr.length());
 
		return j_ob_1.getJSONObject("Product").getJSONArray(prod).get(0).toString();
	}
	public static void main(String[] args) throws IOException, JSONException {
		Main_Process mp = new Main_Process();

		mp.test();
		//System.out.println(mp.article_recovery("2ed37333ded300102b24d1c9d9a105b0"));
	}
}
