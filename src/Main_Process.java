import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
	private FileReader frj = null;
	private BufferedReader brj = null;
	private StringBuilder readStr = new StringBuilder("");
	private Boolean judge = false;
	private JSONObject j_ob = null;
	private JSONObject j_ob_1 = null;
	private JSONObject j_ob_2 = null;
    JSONArray j_a = null;
	/*result 用*/
	private FileReader fr = null;
	private BufferedReader br = null;
	private String article_ID = null,product_temp = null;
	private String csv_product_ID;
	private String match_position = null;
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
		} catch (JSONException e1) {
			// TODO 自動產生的 catch 區塊
			e1.printStackTrace();
		}
		//frj.close();
		readStr.delete(0, readStr.length());
		
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
        count = 0;
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
				else if(j_ob.getJSONObject("TextItem").getJSONArray(ID).get(i).toString().compareTo("<P>") == 0)
					str_readLine = str_readLine.concat("   ");
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
	public void test() throws IOException, JSONException {
		
		Reader zx = new FileReader("result.csv");
		BufferedReader qw = new BufferedReader(zx);
		Reader zxc = new FileReader("myProduct.json");
        BufferedReader qwe = new BufferedReader(zxc);
        String[] fuck = new String[50];
        int cc = 0;
        while(qwe.ready()) {
        	str_readLine = qwe.readLine();
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
        j_ob_1 = new JSONObject(readStr.toString());
        
        readStr.delete(0, readStr.length());
        qwe.close();zxc.close();qw.close();zx.close();
        
        File file = new File("check.txt");
		FileWriter fw = new FileWriter(file);
		
		try {
			JSONArray T_T = null;
			T_T = JSON_productID_read();
			for(int i=0;i<T_T.length();i++) {
				fuck = result_search(T_T.get(i).toString());
				if(fuck[0] != null) {
					
					fw.write(T_T.get(i).toString()+" : "+j_ob_1.getJSONObject("Product").getJSONArray(T_T.get(i).toString()).get(0)+"\r\n");
					
					while(fuck[cc] != null) {
						
						fw.write(fuck[cc]+" : "+match(fuck[cc],T_T.get(i).toString())+"\r\n");
						
						fuck[cc] = null;
						cc++;
					}
				}
				cc = 0;
				fuck = null;
				fw.write("********************************************************************************************************\r\n");
			}
			
		} catch (JSONException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
	}
	public String match(String art,String prod) throws IOException, JSONException {
		Reader f_csv = new FileReader("result.csv");
		BufferedReader br_csv = new BufferedReader(f_csv);
		Reader f_js = new FileReader("mytext.json");
        BufferedReader br_js = new BufferedReader(f_js);
		String ttt = "";
		
		while(br_js.ready()) {
        	str_readLine = br_js.readLine();
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
		j_ob_2 = new JSONObject(readStr.toString());
		
		while (br_csv.ready()) {
			str_readLine = br_csv.readLine();
			article_ID = str_readLine.split(":")[0];
			product_temp = str_readLine.split(",")[1];
			
			if(art.compareTo(article_ID) == 0) {
				for(int i = 0;i<product_temp.split(" ").length;i++) {
					if(prod.compareTo(product_temp.split(" ")[i]) == 0) {
						product_temp = str_readLine.split(":")[1];
						match_position = product_temp.split("-")[0];
						ttt = ttt.concat(" "+j_ob_2.getJSONObject("TextItem").getJSONArray(art).get(Integer.valueOf(match_position)-1).toString());
					}
				}
			}
		}
		readStr.delete(0, readStr.length());
		return ttt;
	}
	public static void main(String[] args) throws JSONException {
		Main_Process mp = new Main_Process();
		
		try {
			mp.test();	
			//System.out.println(mp.article_recovery("5af9854e5f7d69f0828eea8a08567f5d"));
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
	}
}
