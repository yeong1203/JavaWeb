package com.kosta.sample.craw;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class crawTest  {
	
	public ArrayList<NewsVO> getNaverNews(){
		String url = "https://news.naver.com/section/101";
		Document doc = null;
		try {
			// pravite로 되어 있고 static 되어 있어서 Jsoup. 이렇게 쓰면 됨.
			// Connection은 url을 받아서 자동으로 connect 해주는 것.
		    doc = Jsoup.connect(url).get();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		// elements : 요소 = 태그
		Elements liList = doc.select("#newsct > div.section_latest > div > div.section_latest_article._CONTENT_LIST._PERSIST_META > div:nth-child(1) > ul > li");

		ArrayList<NewsVO> list = new ArrayList<NewsVO>();
		for(Element li : liList) {

		    String href = li.select("div > div > div.sa_thumb._LAZY_LOADING_ERROR_HIDE > div > a").attr("href"); // 해당 정보 중 href를 가져와랑
		    System.out.println(href);
		    String img = li.select("div > div > div.sa_thumb._LAZY_LOADING_ERROR_HIDE > div > a > img").attr("data-src");
		    System.out.println(img);
		    String title = li.select("div > div > div.sa_text > a > strong").text(); // <>안에 없는 text를 가져와라!
		    System.out.println(title);
		    String ref = li.select("div > div > div.sa_text > div.sa_text_info > div.sa_text_info_left > div.sa_text_press").text();
		    System.out.println(ref);

		    NewsVO nvo = new NewsVO();
		    nvo.setHref(href);
		    nvo.setImg(img);
		    nvo.setTitle(title);
		    nvo.setRef(ref);
		    list.add(nvo);
		}		
		// 아래 선언문 LombokCallTest 로 이동시킴.
//		crawTest ct = new crawTest();
//		ArrayList<NewsVO> list = ct.getNaverNews();
//		System.out.println(list.size());
//		for(NewsVO nvo : list) {
//			System.err.println(nvo.getTitle());
//		}
		
		return list;
	}
	
	public static void main(String[] args) {

	    // data-src인 이유?
//		String url = "https://news.naver.com/section/101";
//		Document doc = null;
//		try {
//		    doc = Jsoup.connect(url).get();
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//		ArrayList<NewsVO> list = new ArrayList<NewsVO>();
//		Elements li = doc.select("#newsct > div.section_latest > div > div.section_latest_article._CONTENT_LIST._PERSIST_META > div:nth-child(1) > ul > li");
//	    String href = li.select("div > div > div.sa_thumb._LAZY_LOADING_ERROR_HIDE > div > a").attr("href"); // 해당 정보 중 href를 가져와랑
//	    System.out.println(href);
//	    System.out.println(li.select("div > div > div.sa_thumb._LAZY_LOADING_ERROR_HIDE > div > a > img"));
//	   
		
	}

}
