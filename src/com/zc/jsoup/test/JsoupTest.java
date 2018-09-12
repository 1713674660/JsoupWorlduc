package com.zc.jsoup.test;

import com.zc.jsoup.JsoupFromWorlduc;

/**
 * 测试项目的运行情况
 * @author zc
 * 2018年9月12日14:41:28
 */
public class JsoupTest {

	public static void main(String[] args) {
		
		String folder = "G:\\java\\worlduc\\";
		int uid = 2331920;
		JsoupFromWorlduc jfw = JsoupFromWorlduc.getJsoup();
		jfw.jsoupInfo(folder, uid);
	}

}
