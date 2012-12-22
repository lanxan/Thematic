import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;

public class Main_Process {

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	/*json用*/
	private FileReader frj = null;
	private BufferedReader brj = null;
	private StringBuilder readStr = new StringBuilder("");
	//private String[] json_product_ID_content = new String[50];
	private Boolean judge = false;
	private JSONObject j_ob = null;
	//private Object j_product_ID = null;
	//private Object j_product_name = null;
    JSONArray j_a = null;
	/*result 用*/
	private FileReader fr = null;
	private BufferedReader br = null;
	private String article_ID = null,product_temp = null;
	private String csv_product_ID;
	private String[] csv_article_ID = new String[50];
	int count = 0;
	/*共用*/
	private String str_readLine = "";
	
	public JSONArray JSON_productID_read() throws IOException {
		
		frj = new FileReader("myProduct.json");
        brj = new BufferedReader(frj);
       
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
        
		try {
			j_ob = new JSONObject(readStr.toString());
			//取得產品ID的集合:j_a
			j_a = j_ob.getJSONObject("Product").names();
			//System.out.println(j_a.length());
			//目前可取得產品ID以及做比對
			/*
			for(int i=0;i<j_a.length();i++)	{
				j_product_ID = j_a.get(i);
				j_product_name = j_ob.getJSONObject("Product").getJSONArray(j_product_ID.toString()).get(0);
				
				for(int j=0;j<j_product_name.toString().split(" ").length;j++)
					json_product_ID_content[j] = j_product_name.toString().split(" ")[j];
			}
			*/
		} catch (JSONException e1) {
			// TODO 自動產生的 catch 區塊
			e1.printStackTrace();
		}
		frj.close();
		return j_a;
	}
	public String[] result_search(String search_str) throws IOException {
	
        try {
			fr = new FileReader("result.csv");
			br = new BufferedReader(fr);
	
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
			/*
			for(int j=0;j<csv_article_ID.length;j++) {
				if(csv_article_ID[j] != null)
					System.out.println(csv_article_ID[j]);
			}
			*/
		} catch (FileNotFoundException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
        return csv_article_ID;
	}
	public String article_recovery(String ID) throws IOException {
		
		frj = new FileReader("mytext.json");
        brj = new BufferedReader(frj);
		
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
        
        try {
			j_ob = new JSONObject(readStr.toString());
			str_readLine = "";
			
			for(int i=0;i<j_ob.getJSONObject("TextItem").getJSONArray(ID).length();i++) {
				if(j_ob.getJSONObject("TextItem").getJSONArray(ID).get(i).toString().compareTo("<s>") == 0)
					str_readLine = str_readLine.concat("\n");
				else
					str_readLine = str_readLine.concat(j_ob.getJSONObject("TextItem").getJSONArray(ID).get(i).toString()+" ");
			}
			
			//System.out.println(str_readLine);
		} catch (JSONException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
        return str_readLine;
	}
	public static void main(String[] args) {
		try {
			Main_Process mp = new Main_Process();
			//new Main_Process().article_recovery("ea7aacd36a9fdbf871073d729776db86");
			String st = null;
			st = mp.article_recovery("5af9854e5f7d69f0828eea8a08567f5d");
			System.out.println(st);

		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
	}
}
