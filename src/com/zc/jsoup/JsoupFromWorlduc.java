package com.zc.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zc.jsoup.downImg.DownImg;
import com.zc.jsoup.model.User;
import com.zc.jsoup.runnable.DownRunnable;
import com.zc.jsoup.write.JsoupWrite;

/**
 * 爬取world.com的用户信息
 * @author zc
 * 2018年9月11日23:21:54
 */
public class JsoupFromWorlduc {

	//单例模式
	private JsoupFromWorlduc() {
		
	}
	private static JsoupFromWorlduc jw = new JsoupFromWorlduc();
	public static JsoupFromWorlduc getJsoup() {
		return jw;
	}
	
	//世界大学城居民空间网址
	private static final String url="http://worlduc.com";
	private static final String url2="/SpaceShow/Index.aspx?uid=";
	
	//线程池
	private ExecutorService pool = Executors.newCachedThreadPool();
	
	/**
	 * 爬取信息
	 * @param 本地文件夹
	 * @param 用户编号
	 */
	public void jsoupInfo(String folder,int uid) {
		
		Document doc = null;
		User user = new User();
		
		try {
			doc = Jsoup.connect(url+url2+uid).timeout(12000).get();
			//个人基本信息
			System.out.printf("%s，正在爬取：%d的基本信息",Thread.currentThread().getName(),uid);
			String proName = doc.select(".tc.mt10.cb").text();
			String name = proName.substring(0, proName.indexOf("("));
			String profession = proName.substring(proName.indexOf("("));
			
			String message = doc.select(".mt5 li").text();
			String content = doc.select("#Introduction_Content code").text();
			user.setName(name);
			user.setProfession(profession);
			user.setMessage(message);
			user.setContent(content);
			JsoupWrite.wirteFile(folder, user);
			System.out.printf("%s，爬取：%d的基本信息完成！",Thread.currentThread().getName(),uid);
			
			//爬取好友信息
			Elements eles = doc.select(".peo_list.cb.mt10.clearfix li a");
			System.out.println("开始爬取"+name+"的"+eles.size()+"位好友的信息");
			for(Element e:eles) {
				String path = e.attr("href");
				String id = path.substring(path.lastIndexOf("=")+1);
				int uid2 = Integer.parseInt(id);
				pool.submit(new DownRunnable(folder,uid2));
				
			}
			
			//相片
			String path = doc.selectFirst(".tc.mt5 img").attr("src");
			String pathName = doc.selectFirst(".tc.mt5 img").attr("alt");
			Thread thread = new Thread(new DownImg(folder,path,pathName));
			thread.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
