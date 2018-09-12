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
 * ��ȡworld.com���û���Ϣ
 * @author zc
 * 2018��9��11��23:21:54
 */
public class JsoupFromWorlduc {

	//����ģʽ
	private JsoupFromWorlduc() {
		
	}
	private static JsoupFromWorlduc jw = new JsoupFromWorlduc();
	public static JsoupFromWorlduc getJsoup() {
		return jw;
	}
	
	//�����ѧ�Ǿ���ռ���ַ
	private static final String url="http://worlduc.com";
	private static final String url2="/SpaceShow/Index.aspx?uid=";
	
	//�̳߳�
	private ExecutorService pool = Executors.newCachedThreadPool();
	
	/**
	 * ��ȡ��Ϣ
	 * @param �����ļ���
	 * @param �û����
	 */
	public void jsoupInfo(String folder,int uid) {
		
		Document doc = null;
		User user = new User();
		
		try {
			doc = Jsoup.connect(url+url2+uid).timeout(12000).get();
			//���˻�����Ϣ
			System.out.printf("%s��������ȡ��%d�Ļ�����Ϣ",Thread.currentThread().getName(),uid);
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
			System.out.printf("%s����ȡ��%d�Ļ�����Ϣ��ɣ�",Thread.currentThread().getName(),uid);
			
			//��ȡ������Ϣ
			Elements eles = doc.select(".peo_list.cb.mt10.clearfix li a");
			System.out.println("��ʼ��ȡ"+name+"��"+eles.size()+"λ���ѵ���Ϣ");
			for(Element e:eles) {
				String path = e.attr("href");
				String id = path.substring(path.lastIndexOf("=")+1);
				int uid2 = Integer.parseInt(id);
				pool.submit(new DownRunnable(folder,uid2));
				
			}
			
			//��Ƭ
			String path = doc.selectFirst(".tc.mt5 img").attr("src");
			String pathName = doc.selectFirst(".tc.mt5 img").attr("alt");
			Thread thread = new Thread(new DownImg(folder,path,pathName));
			thread.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
