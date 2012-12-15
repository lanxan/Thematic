import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

public class Main_Process {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*result 用*/
		FileReader fr = null;
        BufferedReader br = null;
        File file = new File("result_.csv");
		FileWriter fw;
		String csv_article_ID = null,pre_article_ID = null;
        String product_temp = null;
		String[] csv_product_ID = new String[50];
        String str_readLine = "";
		/*json用*/
		FileReader frj = null;
		BufferedReader brj = null;
		StringBuilder readStr = new StringBuilder("");
		String[] json_product_ID_content = new String[50];
		int flag;
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
        //System.out.println(readStr);
        
		try {
			j_ob = new JSONObject(readStr.toString());
			//Object jsonOb = js.getJSONObject("Product").getJSONArray("DpcYJuxfm88").get(0);
			j_a = j_ob.getJSONObject("Product").names();
			//System.out.println(jsonOb);
			//System.out.println(js.getJSONObject("Product").names().length());
			
			//for(int i=0;i<j_a.toString().length();i++)	{
			for(int i=0;i<15;i++)	{
				//json_product_ID_content[i] = j_a.toString().split(" ")[i];
				//System.out.println(json_product_ID_content[i]);
				j_product_ID = j_a.get(i);
				//System.out.println(json_product_ID_content.length);
				j_product_name = j_ob.getJSONObject("Product").getJSONArray(j_product_ID.toString()).get(0);
				for(int j=0;j<j_product_name.toString().split(" ").length;j++)
					json_product_ID_content[j] = j_product_name.toString().split(" ")[j];
				System.out.println(j_product_name.toString().split(" ").length);
				for(int j=0;j<j_product_name.toString().split(" ").length;j++) {
					flag = json_product_ID_content[j].toLowerCase().compareTo("RAMD600".toLowerCase());
				
					if(flag == 0)
						System.out.println("a"+i);
					else
						System.out.println("b"+j);
				}
				
					
			}
		} catch (JSONException e1) {
			// TODO 自動產生的 catch 區塊
			e1.printStackTrace();
		}
        
		
		
		
		
		
		
		
		
		
		
		
		if(file.exists())								
			file.delete();
        
        try {
			fr = new FileReader("result.csv");
			br = new BufferedReader(fr);
			fw = new FileWriter(file,true);
	
			while (br.ready()) {
				str_readLine = br.readLine();
				csv_article_ID = str_readLine.split(":")[0];
				if(pre_article_ID == csv_article_ID)
				{
					product_temp = str_readLine.split(",")[1];

					for(int j = 0;j<product_temp.split(" ").length;j++) {
						csv_product_ID[j] = product_temp.split(" ")[j];
						fw.write(csv_product_ID[j]+" ");				
					}
				}
				else
				{
					if(pre_article_ID != null && pre_article_ID != csv_article_ID)
						fw.write("\n");
					fw.write(csv_article_ID+",");
					product_temp = str_readLine.split(",")[1];

					for(int j = 0;j<product_temp.split(" ").length;j++) {
						csv_product_ID[j] = product_temp.split(" ")[j];
						fw.write(csv_product_ID[j]+" ");				
					}
					pre_article_ID = csv_article_ID;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
	}
	
}
