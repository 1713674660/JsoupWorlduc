package com.zc.jsoup.runnable;

import com.zc.jsoup.JsoupFromWorlduc;
/**
 * ���߳�
 * @author zc
 * 2018��9��12��16:56:51
 */
public class DownRunnable implements Runnable {

	String folder = null;
	private int uid=0;
	public DownRunnable(String folder,int uid) {
		this.folder = folder;
		this.uid = uid;
	}
	
	@Override
	public void run() {
		
		JsoupFromWorlduc jfw = JsoupFromWorlduc.getJsoup();
		jfw.jsoupInfo(folder,uid);
	}

}
