package com.sc.util;
/**
 * 替换由地址栏传参过程中空格的问题
 * @author zhuke
 *
 */
public class UrlParmUtil {
	
	
	public static void replaceBlank(String s){
		s.replace("%20", " ");
	}

}
