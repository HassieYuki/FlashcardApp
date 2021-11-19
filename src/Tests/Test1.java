package Tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test1 {

	public static void main(String[] args) {
		
		String search_word = "阿爸阿爸";

		String url = "https://cjjc.weblio.jp/content/"+search_word;

		try {
			final Document document = Jsoup.connect(url).get();
			Elements elements = document.select("#base #main .kijiWrp .kiji");
			//System.out.println(element);
		
			String word = elements.select(".midashigo").attr("title");
			Elements pinyin = elements.select(".Cgkgj span.pnyn");
			String pynword = pinyin.select(".crosslink").attr("title");
			Elements jpn = elements.select("div.Cgkgj div.level0 p.lvlB");
			String meaning = jpn.text();
			
			if(word.isEmpty()) {
				System.out.println("Empty");
			}
			else if(pynword.isEmpty()) {
				System.out.println("no pinyin");
			}
			else if(meaning.isEmpty()) {
				System.out.println("no meaning");
			}
			else {
			System.out.println(word);
			System.out.println(pynword);
			System.out.println(meaning);
			}	

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}


}
