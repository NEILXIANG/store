package job;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;


public class RunJob extends HttpJob{

	@Test
	public void download() throws  IOException, InterruptedException {
		FileInputStream inputStream = new FileInputStream("F:\\meitaowang\\css\\style2 - 副本.css");
		System.out.println(inputStream.available());
		List<String> list = readLine(inputStream);
		File file = new File("F:/css/style2.css");
		copyCssFile(list,file);

	}
}
