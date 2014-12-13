package chen.bupt.httpclient.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-15
 * Time: 15:32
 */
public final class ResponseUtils {
  public static int getResponseStatus(HttpResponse httpResponse) {
    return httpResponse.getStatusLine().getStatusCode();
  }

  public static String getResponseContent(HttpResponse httpResponse) {
    return InputStreamUtils.entity2String(httpResponse.getEntity());
  }

  public static String getRedirectionURL(HttpResponse httpResponse){
    String res = null;
    Header[] headers = httpResponse.getHeaders("Location");
    if(headers!=null){
      res = headers[0].getValue();
    }
    return res;
  }
}
