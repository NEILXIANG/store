package job;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class HtmlParserJob extends HttpJob {

	private static Map<String, String> tagMap = new HashMap<String, String>();
	private Map<String, String> urlMap = new HashMap<String, String>();

	static {
		tagMap.put("img", "src");
		tagMap.put("a", "href");
	}

	public void getElementUrls(File input, String[] tags,String host) {
		Document doc;
		try {
			doc = Jsoup.parse(input, "UTF-8");
			for (String tag : tags) {
				Elements elements = doc.getElementsByTag(tag);
				for (Element ele : elements) {
					String httpUrl =ele.attr(tagMap.get(tag));;
					if(host!=null){
						httpUrl=host+httpUrl;
					}
					System.out.println("httpUrl:"+httpUrl);
					if (httpUrl.startsWith("http")
							&& (httpUrl.contains(".jpg") || httpUrl.contains(".JPG") || httpUrl.contains(".gif") || httpUrl.contains(".GIF"))) {
							urlMap.put(httpUrl.trim(),httpUrl.trim());
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		File file = new File("F:/html/");
		File[] listFiles = file.listFiles();
		File fileStore = new File("F:/images/");
		for (File f : listFiles) {
			getElementUrls(f, new String[] { "img" },"http://shopping.moonbasa.com");//"http://shopping.moonbasa.com"
		}
		System.out.println(urlMap.size());
		for(String url:urlMap.values()){
			System.out.println(url);
		}
		System.out.println("urlMap.values()============================================size:"+urlMap.values().size());
		downloadHttpResources(new ArrayList<String>(urlMap.values()), fileStore);
	}

	@Test
	public void test2() throws IOException {
//		http://shopping.moonbasa.com/Images/ALIPAYBANKBCOM.gif?id=2
		Document doc = Jsoup.connect("http://item.jd.com/1032925231.html").get();
//		System.out.println(doc.toString());
		Elements elements = doc.getElementsByTag("img");
		for (Element ele : elements) {
			String httpUrl =ele.attr("src");
			if(httpUrl==null||"".equals(httpUrl.trim())){
				httpUrl=ele.attr("data-lazyload");
			}
			if (httpUrl.contains("http")
					&& (httpUrl.contains(".png") ||httpUrl.contains(".jpg") || httpUrl.contains(".JPG") || httpUrl.contains(".gif") || httpUrl.contains(".GIF"))) {
				if(httpUrl.contains("'")){
					httpUrl=httpUrl.substring(httpUrl.indexOf("'")+1, httpUrl.lastIndexOf("'"));
				}
				System.out.println(httpUrl);
				urlMap.put(httpUrl, httpUrl);
			}
		}

		System.out.println(urlMap.size());
		File fileStore = new File("F:/images/");
		downloadHttpResources(new ArrayList<String>(urlMap.values()), fileStore);//为什么只能拿到8kb的大小图片呢？
	}
	
	@Test
	public void testHttp() throws ClientProtocolException, IOException, URISyntaxException{
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		 HttpGet request = new HttpGet("http://119.254.82.208/dms/test.jpg");  
		HttpResponse response = defaultHttpClient.execute(request);
		HttpEntity entity = response.getEntity();
		byte[] byteArray = EntityUtils.toByteArray(entity);
		System.out.println(byteArray.length);
	
	}
}
