package job;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import repos.TestRepos;

public abstract class HttpJob extends TestRepos {

	private Log logger = LogFactory.getLog(this.getClass().getName());
	private Map<String, Image> urlMap = new HashMap<String, Image>();
	private Map<String, Image> alreadyDownloads = new HashMap<String, Image>();

	protected void downloadHttpResources(List<String> httpUrls, File fileStore) {
		try {
			for (String url : httpUrls) {
				System.out.println("============>>>>:"+url);
				if (url.startsWith("http://")) {
					Image image = Image.getImage(url);
					System.out.println("download:"+image.name+","+image.processCount+","+image.buffer.length);
					doDownload(image, fileStore);
					Thread.sleep(1000);
				} else {
					logger.warn(url + " is not start with http");
				}
			}
			
			Set<Entry<String, Image>> entrySet = alreadyDownloads.entrySet();
			System.out.println("===========================alreadyDownloads=========================="+alreadyDownloads.size());
			for (Entry<String, Image> e : entrySet) {
				Image image = e.getValue();
				System.out.println("{" + e.getKey() + ":" + image.processCount + ","+image.url+"}"+image.buffer.length+",length:"+image.length);
			}

		} catch (Exception e) {
		}

	}

	protected void copyCssFile(List<String> cssFileLines, File targetFile) throws IOException {
		List<String> results = new ArrayList<String>();
		for (String cssTxt : cssFileLines) {
			if (cssTxt.contains("url")) {
				String httpUrl = cssTxt.substring(cssTxt.indexOf("(") + 1, cssTxt.lastIndexOf(")")).replaceAll("\"", "");
				if (httpUrl.startsWith("http://")) {
					String fileName = httpUrl.substring(httpUrl.lastIndexOf("/") + 1);
					String start = cssTxt.substring(0, cssTxt.indexOf("(") + 1);
					String middle = "../images/" + fileName;
					String end = cssTxt.substring(cssTxt.lastIndexOf(")"), cssTxt.length());
					cssTxt = start + middle + end;
				}
			}
			results.add(cssTxt);
		}
		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}
		FileWriter fos = new FileWriter(targetFile);
		BufferedWriter bw = new BufferedWriter(fos);
		for (String newCt : results) {
			System.out.println(newCt);
			if (newCt != null && !"".equals(newCt.trim())) {
				bw.newLine();
				bw.append(newCt);
				bw.flush();
			}
		}
		bw.close();
		fos.close();
	}

	private void doDownload(Image image, File fileStore) throws IOException {
		if (image.buffer.length == 0) {
			urlMap.put(image.name, image);
		} else {
			urlMap.remove(image.name);
			alreadyDownloads.put(image.name, image);
		}
		if (!fileStore.isDirectory() && !fileStore.exists()) {
			fileStore.mkdir();
		}
		File file = new File(fileStore, image.name);
		if (!file.exists()) {
			file.createNewFile();
		}else{
			file = new File(fileStore,UUID.randomUUID().toString()+image.name);
		}

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(image.buffer);
		fileOutputStream.close();
	}

	private static class Image {
		private String url;
		private String name;
		private byte[] buffer;
		private int processCount;
        private int length;
		public static Image getImage(String httpUrl) throws Exception {
			String fileName = httpUrl.substring(httpUrl.lastIndexOf("/") + 1);
			Image image = new Image();
			image.name = fileName;
			image.url = httpUrl;
			image.getFromHttp();
			return image;
		}

		public void reGet() throws Exception {
			this.getFromHttp();
		}
		public void getFromHttp() throws IOException, InterruptedException{
			this.processCount++;
//			URL url = new URL(this.url);
//			InputStream inputResource = url.openStream();
//			Thread.sleep(1000);
//			byte[] buffer = new byte[inputResource.available()];
//			this.length=inputResource.available();
//			inputResource.read(buffer);
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
			 HttpGet request = new HttpGet(this.url);  
			HttpResponse response = defaultHttpClient.execute(request);
			HttpEntity entity = response.getEntity();
			this.buffer= EntityUtils.toByteArray(entity);;
		}
	}
}
