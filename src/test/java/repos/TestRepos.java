package repos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestRepos {
    private Log logger=LogFactory.getLog(this.getClass().getName());
	public List<String> readLine(InputStream in) {
		if (in == null) {
			return null;
		}
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(reader);
		List<String> results = new ArrayList<String>();
		try {
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				if(!"".equals(str)&&!"//".equals(str)){
					results.add(str);
				}
			}
		} catch (IOException e) {
			logger.error("IOParser readLine error" + e.getMessage());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.error("IOParser IO close error" + e.getMessage());
			}
		}
		logger.info("readLine result size " + results.size());
		return results;
	}
	protected InputStream getInputStream(String filename) {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		return resourceAsStream;
	}
}
