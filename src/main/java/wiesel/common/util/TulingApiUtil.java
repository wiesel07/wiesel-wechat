package wiesel.common.util;

import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.net.URLEncoder;  
  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONException;  
import org.json.JSONObject;  
/** 
*
* @ClassName   类名：TulingApiUtil 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2017年3月21日
* @author      创建人：Wiesel
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2017年3月21日   wujian   创建该类功能。
*
***********************************************************************
*</p>
*/


public class TulingApiUtil {
	/** 
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果 
     * @param content 
     * @return 
     */  
      
    private static final String KEY="d19ab1f3e4ea4bf0840c1bf6174d64b2";  
      
    public static String getTulingResult(String content){  
          
        /** 此处为图灵api接口，参数key需要自己去注册申请，先以11111111代替 */  
         String apiUrl = "http://www.tuling123.com/openapi/api?key="+KEY+"&info=";  
        String param = "";  
   
        try {  
            param = apiUrl+URLEncoder.encode(content,"utf-8");  
        } catch (UnsupportedEncodingException e1) {  
            System.out.println("UnsupportedEncodingException");  
            e1.printStackTrace();  
        } //将参数转为url编码  
    
        /** 发送httpget请求 */  
        HttpGet request = new HttpGet(param);  
        String result = "";  
        try {  
               
            HttpResponse response = HttpClients.createDefault().execute(request);  
        /** 
         * 特别注意  这一步一定要加commons-logging 这个jar包  否则会没反应，调试了好久！！ 
         * 似乎这个jar包是打印信息的     
         */  
            int code =response.getStatusLine().getStatusCode();  
            if(code==200){  
                result = EntityUtils.toString(response.getEntity());  
            }  
            else {  
            //  System.out.println("code="+code);  
            }  
        } catch (ClientProtocolException e) {  
            System.out.println("ClientProtocolException");  
            e.printStackTrace();  
        } catch (IOException e) {  
            System.out.println("IOException");  
            e.printStackTrace();  
        }  
          
          
        /** 请求失败处理 */  
        if(null==result){  
        //  System.out.println("null==result");  
            return "对不起，你说的话真是太高深了……";  
        }  
       
        try {  
            StringBuffer bf=new StringBuffer();  
            String s="";  
            JSONObject json = new JSONObject(result);  
            //以code=100000为例，参考图灵机器人api文档  
            /** 
             *   code   说明 
                100000  文本类 
                200000  链接类 
                302000  新闻类 
                308000  菜谱类 
             */  
            if(100000==json.getInt("code")){  
                s = json.getString("text");  
                bf.append(s);  
            }  
            else if(200000==json.getInt("code")){  
                s = json.getString("text");  
                bf.append(s);  
                bf.append("\n");  
                s = json.getString("url");  
                bf.append(s);  
            }  
            else if(302000==json.getInt("code")){  
                //s = json.getString("text");  
                s="待开发有点麻烦！\n";  
                bf.append(s);  
            }  
            else if(308000==json.getInt("code")){  
                //s = json.getString("text");  
                s="待开发有点麻烦！\n";  
                bf.append(s);  
            }  
            result=bf.toString();  
        } catch (JSONException e) {   
            System.out.println("JSONException");  
            e.printStackTrace();  
        }  
        //System.out.println("机器人回复->"+result);  
        return result;  
    }  
}