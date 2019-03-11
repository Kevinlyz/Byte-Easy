package com.mbyte.easy.config;


import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 定义前端常量信息
 * @author Administrator
 *
 */
public abstract class FrontConfig {
	//定义前端用户session访问的key
	public static final String FONT_SESSION = "font_session";
	//存储用户第一次访问系统时访问的url的sessionKey
	public static final String FONT_FRIST_URL = "font_frist_url";

	public static final Map<String, Object> SITE_VAR = new HashMap<>();

}
