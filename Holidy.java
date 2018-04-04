package direct.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Holidy {
	private static final Logger logger = LoggerFactory.getLogger(Holidy.class);
	public static void main(String[] args) {
		String dc = "http://tool.bitefu.net/jiari/?d=";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateFlag = getHoliday(dc+sdf.format(new Date()));
		if ("1".equals(dateFlag) || "2".equals(dateFlag)) {
			logger.info("今天是法定节假日");
		}else {
			logger.info("今天是工作日");
		}
	}
	/**
	 * 
	 * 方法描述：方法描述：获取节假日 访问接口，根据返回值判断当前日期是否为工作日，
	 * 返回结果：检查具体日期是否为节假日，工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2；
	 * 2018年4月3日上午11:26:40
	 */
	public static  String  getHoliday(String  httpUrl) {
		BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
			    sbf.append(strRead);
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			
		}
		return result;
	}

}
