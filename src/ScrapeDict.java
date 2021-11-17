import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScrapeDict {
	
	public String word;
	public String pynword;
	public String meaning;
	
	ScrapeDict(String search_word){
		String url = "https://cjjc.weblio.jp/content/"+search_word;

		try {
			final Document document = Jsoup.connect(url).get();
			Elements elements = document.select("#base #main .kijiWrp .kiji");
			//System.out.println(element);
		
			this.word = elements.select(".midashigo").attr("title");
			Elements pinyin = elements.select(".Cgkgj span.pnyn");
			this.pynword = pinyin.select(".crosslink").attr("title");
			Elements jpn = elements.select("div.Cgkgj div.level0 p.lvlB");
			this.meaning = jpn.text();
			
//			System.out.println(word);
//			System.out.println(pynword);
//			System.out.println(meaning);

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String[] createList(){
		String[] dict = {word,pynword,meaning};
		return dict;
	}

}
