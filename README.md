Thematic
========

1.專題包裝

2.function使用:

  JSON_productID_read() : 回傳JSONArray型態的產品ID陣列
  
  public String[] result_search(String) : 回傳String[]型態的字串陣列，預設大小50(寫死，所以宣告請new String[50])
  
                          注意:由於是寫死，所以沒用到的部分會成為null
  以上都請先宣告 Main_Process 變數名稱 = new Main_Process();    
  
  public String article_recovery(String) : 回傳文章內容，參數請傳入文章ID

  public String produrctName_return(String prod):回傳產品名稱，參數傳入產品ID
  
  public String match(String art,String prod):回傳match到的字，參數傳入文章ID、產品ID
  
