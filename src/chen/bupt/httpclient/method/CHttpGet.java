package chen.bupt.httpclient.method;

import chen.bupt.httpclient.commons.Constants;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-13
 * Time: 14:14
 */
public class CHttpGet {
  private HttpGet httpGet;

  public CHttpGet(String url) {
    httpGet = new HttpGet(url);
    httpGet.setHeader("User-Agent", Constants.defaultUserAgent);
    httpGet.setHeader("Accept", Constants.defaultAccept);
    httpGet.setHeader("Accept-Encoding", Constants.defaultAcceptEncoding);
    httpGet.setHeader("Accept-Language", Constants.defaultAcceptLanguage);
    httpGet.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
  }

  public void setCookiePolicy(String cookiePolicy) {
    httpGet.getParams().setParameter(ClientPNames.COOKIE_POLICY, cookiePolicy);
  }

  public void setHeaders(Map<String, String> headers) {
    Set<Map.Entry<String, String>> headerEntrys = headers.entrySet();
    for (Map.Entry<String, String> headerEntry : headerEntrys) {
      httpGet.setHeader(headerEntry.getKey(), headerEntry.getValue());
    }
  }

  public void setHeaders(String[] headers) {
    for (String header : headers) {
      String[] KV = header.split(":");
      if (KV.length == 2) {
        httpGet.setHeader(KV[0].trim(), KV[1].trim());
      }
    }
  }

  public void addHeader(String key, String value) {
    httpGet.setHeader(key, value);
  }

  public HttpGet getHttpGet() {
    return this.httpGet;
  }

  public static void main(String[] args) {
    CHttpGet g = new CHttpGet("http://www.baidu.com");
    System.out.println(g.httpGet.getAllHeaders().length);
  }
}
