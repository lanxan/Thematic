import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

public class Main_Process {

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	public JSONArray JSON_productID_read() throws IOException {
		/*json用*/
		FileReader frj = null;
		BufferedReader brj = null;
        String str_readLine = "";
		StringBuilder readStr = new StringBuilder("");
		String[] json_product_ID_content = new String[50];
		frj = new FileReader("myProduct.json");
        brj = new BufferedReader(frj);
        
        Boolean judge = false;
        JSONObject j_ob = null;
        Object j_product_ID = null;
        Object j_product_name = null;
        JSONArray j_a = null;
        
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
			/*取得產品ID的集合:j_a*/
			j_a = j_ob.getJSONObject("Product").names();
			//System.out.println(j_a.length());
			/*目前可取得產品ID以及做比對*/
			for(int i=0;i<j_a.length();i++)	{
				j_product_ID = j_a.get(i);
				j_product_name = j_ob.getJSONObject("Product").getJSONArray(j_product_ID.toString()).get(0);
				
				for(int j=0;j<j_product_name.toString().split(" ").length;j++)
					json_product_ID_content[j] = j_product_name.toString().split(" ")[j];
			}
		} catch (JSONException e1) {
			// TODO 自動產生的 catch 區塊
			e1.printStackTrace();
		}
		frj.close();
		return j_a;
	}
	
	public String[] result_search(String search_str) throws IOException {
		/*result 用*/
		FileReader fr = null;
        BufferedReader br = null;
		String article_ID = null,product_temp = null;
		String csv_product_ID;
		String[] csv_article_ID = new String[50];
        String str_readLine = "";
        int count = 0;

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
	
	public static void main(String[] args) {
		try {
			new Main_Process().result_search("8RqztGxb2_8");
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
	}
}
