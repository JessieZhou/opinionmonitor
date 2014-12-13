package chen.bupt.httpclient;

import chen.bupt.httpclient.handler.DefaultHttpRequestRetryHandler;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.log4j.Logger;

//import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-13
 * Time: 12:56
 */
public class NormalHttpClient {
  private static final Logger LOG = Logger.getLogger(NormalHttpClient.class);
  private DefaultHttpClient httpClient;
  private BasicHttpContext httpContext;
  private String ip;
  private int port;

  public NormalHttpClient() {
    httpClient = new DefaultHttpClient();
    clientInit();
    httpContext = new BasicHttpContext();
  }

  /**
   * 设置线程池
   *
   * @param maxConnection 最大连接数
   * @param maxPerRoute   每个route的最大连接数
   */
  public NormalHttpClient(int maxConnection, int maxPerRoute) {
    SchemeRegistry schemeRegistry = new SchemeRegistry();
    schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
    PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
    cm.setMaxTotal(maxConnection);
    cm.setDefaultMaxPerRoute(maxPerRoute);
    httpClient = new DefaultHttpClient(cm);
    clientInit();
    httpContext = new BasicHttpContext();
  }

  private void clientInit() {
    httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler());
    CookieSpecFactory csf = getCookieSpec();
    httpClient.getCookieSpecs().register("easy", csf);
    httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
  }

  /**
   * 设置连接代理
   *
   * @param ip
   * @param port
   */
  public void setProxy(String ip, int port) {
    this.ip = ip;
    this.port = port;
    HttpHost httpHost = new HttpHost(this.ip, this.port);
    httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, httpHost);
  }

  public void setAutoRedirection(boolean autoRedirection) {
    httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, autoRedirection);
  }

  /**
   * 用户需要进行其他“高级”设置时刻使用
   *
   * @return
   */
  public DefaultHttpClient getHttpClient() {
    return httpClient;
  }

  /**
   * @param request
   * @param pageCompressed    网页是否压缩，一般设为false，而在后期进行处理
   * @param releaseConnection 是否释放request资源，如果设为true，之后不能在使用request发起请求
   * @return
   * @throws IOException
   */
  public HttpResponse sendRequest(HttpRequestBase request, boolean pageCompressed, boolean releaseConnection) throws IOException {
    HttpClient client;
    if (pageCompressed) {
      client = new DecompressingHttpClient(this.httpClient);
    } else {
      client = this.httpClient;
    }
    HttpResponse httpResponse = client.execute(request,httpContext);
    if (releaseConnection) {
      request.releaseConnection();
    }
    return httpResponse;
  }

  public BasicHttpContext getHttpContext(){
    return this.httpContext;
  }


  /**
   * @param request
   * @param releaseConnection 是否释放request资源，如果设为true，之后不能在使用request发起请求
   * @return
   * @throws IOException
   */
  public HttpResponse sendRequest(HttpRequestBase request, boolean releaseConnection) throws IOException {
    return sendRequest(request, false, releaseConnection);
  }

  public HttpResponse sendRequest(HttpRequestBase request) throws IOException {
    return sendRequest(request, false, false);
  }

  public void setHttpRequestRetryHandler(HttpRequestRetryHandler retryHandler) {
    this.httpClient.setHttpRequestRetryHandler(retryHandler);
  }

  public void setParam(String key, Object val){
    this.httpClient.getParams().setParameter(key,val);
  }

  public void releaseConnection() {
    this.httpClient.getConnectionManager().shutdown();
  }

  public String getCookie() {
    StringBuilder sb = new StringBuilder();
    List<Cookie> cookies = this.httpClient.getCookieStore().getCookies();
    for (Cookie cookie : cookies) {
      sb.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    LOG.warn("test");
  }

  private CookieSpecFactory getCookieSpec() {
    CookieSpecFactory csf = new CookieSpecFactory() {
      public CookieSpec newInstance(HttpParams params) {
        return new BrowserCompatSpec() {
          @Override
          public void validate(Cookie cookie, CookieOrigin origin)
              throws MalformedCookieException {
            // Oh, I am easy
          }
        };
      }
    };
    return csf;
  }
}
