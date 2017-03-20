package wiesel.common.util;

import java.util.UUID;

/** 
*
* @ClassName   类名：UUIDTool 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2017年3月13日
* @author      创建人：Wiesel
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2017年3月13日   wujian   创建该类功能。
*
***********************************************************************
*</p>
*/
public class UUIDTool {
	 public UUIDTool() {  
	    }  
	 /**
	  * 
	  * <p>函数名称：        </p>
	  * <p>功能说明：自动生成32位的UUid，对应数据库的主键id进行插入用。  
	  *
	  * </p>
	  *<p>参数说明：</p>
	  * @return
	  *
	  * @date   创建时间：2017年3月13日
	  * @author 作者：wujian
	  */
	    public static String getUUID() {  
	        /*UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	        // 去掉"-"符号  
	        String temp = str.substring(0, 8) + str.substring(9, 13)  
	                + str.substring(14, 18) + str.substring(19, 23)  
	                + str.substring(24);  
	        return temp;*/  
	          
	        return UUID.randomUUID().toString().replace("-", "");  
	    }  
}
