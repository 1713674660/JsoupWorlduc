package com.zc.jsoup.test;

import com.zc.jsoup.JsoupFromWorlduc;

/**
 * ������Ŀ���������
 * @author zc
 * 2018��9��12��14:41:28
 */
public class JsoupTest {

	public static void main(String[] args) {
		
		String folder = "G:\\java\\worlduc\\";
		int uid = 2331920;
		JsoupFromWorlduc jfw = JsoupFromWorlduc.getJsoup();
		jfw.jsoupInfo(folder, uid);
	}

}
