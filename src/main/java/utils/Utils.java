package utils;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class Utils {

	private static Configuration conf = new Configuration();

	public static HttpHeaders setHeaders(MediaType mediaType, boolean noCache) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Connection", "Close");
		if (mediaType != null) {
			headers.setContentType(mediaType);
			headers.setAccept(Arrays.asList(mediaType));
		}
		if (noCache) {
			headers.setCacheControl("no-cache");
		}
		headers.add("Authorization", conf.getAuth());
		return headers;
	}
}
