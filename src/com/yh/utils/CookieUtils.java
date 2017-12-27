package com.yh.utils;

import javax.servlet.http.Cookie;

/**
 * 编写cookie工具类
 * @author Administrator
 *
 */
public class CookieUtils {
	public static Cookie getCookie(Cookie[] allCookie,String cookieName){
		if(cookieName == null){
			return null;
		}
		if(allCookie != null){
			for(Cookie c:allCookie){
				if(cookieName.equals(c.getName())){
					return c;
				}
			}
		}
		return null;
	}
}
