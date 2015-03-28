package com.smartcontroller.util;

import java.util.StringTokenizer;

public class StringUtil {

	public static boolean isa2z(String s) {
		if(s.equals("")||s.equals(" ")){
			return false;
		}
		if (s != null) {
			StringTokenizer st = new StringTokenizer(s, " ");
			while (st.hasMoreTokens()) {
				String str = st.nextToken();
				System.out.println(str);
				if (!str.matches("-?\\d+\\.\\d+|-?\\d+")) {
					return false;
				}
			}
			return true;
		}
		return false;

	}


}
