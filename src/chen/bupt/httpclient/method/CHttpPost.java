package chen.bupt.httpclient.method;

import chen.bupt.httpclient.commons.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-13
 * Time: 14:29
 */
public class CHttpPost {
  private static final String defaultUrlEncoder = "utf-8";
  private HttpPost httpPost;
  private List<NameValuePair> formParams;
  private String urlEncoder = defaultUrlEncoder;

  public CHttpPost(String url) {
    httpPost = new HttpPost(url);
    httpPost.setHeader("User-Agent", Constants.defaultUserAgent);
    httpPost.setHeader("Accept", Constants.defaultAccept);
    httpPost.setHeader("Accept-Encoding", Constants.defaultAcceptEncoding);
    httpPost.setHeader("Accept-Language", Constants.defaultAcceptLanguage);
    httpPost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
    formParams = new ArrayList<NameValuePair>();
  }

  public void setHeaders(Map<String, String> headers) {
    Set<Map.Entry<String, String>> headerEntrys = headers.entrySet();
    for (Map.Entry<String, String> headerEntry : headerEntrys) {
      httpPost.setHeader(headerEntry.getKey(), headerEntry.getValue());
    }
  }

  public void setHeaders(String[] headers) {
    for (String header : headers) {
      String[] KV = header.split(":");
      if (KV.length == 2) {
        httpPost.setHeader(KV[0].trim(), KV[1].trim());
      }
    }
  }

  public void setUrlEncoder(String encoder){
    this.urlEncoder = encoder;
  }

  public void addHeader(String key, String value) {
    httpPost.setHeader(key, value);
  }

  public HttpPost getHttpPost() {
    if (formParams.size() > 0) {
      try {
        this.httpPost.setEntity(new UrlEncodedFormEntity(formParams, this.urlEncoder));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return this.httpPost;
  }

  public void addFormParam(String key, String value) {
    formParams.add(new BasicNameValuePair(key, value));
  }

  public void addFormParam(NameValuePair pair) {
    this.formParams.add(pair);
  }

}
