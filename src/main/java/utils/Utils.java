package utils;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class Utils {

	public static HttpHeaders setHeaders(MediaType mediaType, boolean noCache, String login) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Connection", "Close");
		if (mediaType != null) {
			headers.setContentType(mediaType);
			headers.setAccept(Arrays.asList(mediaType));
		}
		if (noCache) {
			headers.setCacheControl("no-cache");
		}
		headers.add("Authorization", login);
		return headers;
	}
}
