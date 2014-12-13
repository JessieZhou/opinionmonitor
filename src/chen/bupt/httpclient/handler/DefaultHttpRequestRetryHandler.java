package chen.bupt.httpclient.handler;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-13
 * Time: 13:47
 * </p>
 * 重试策略类
 */
public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
  private static final int defaultRetryNum = 3;
  private final int retryNum;

  /**
   *
   * @param retryNum 重试次数
   */
  public DefaultHttpRequestRetryHandler(final int retryNum) {
    this.retryNum = retryNum;
  }

  public DefaultHttpRequestRetryHandler() {
    this(defaultRetryNum);
  }

  @Override
  public boolean retryRequest(IOException e, int retryCount, HttpContext httpContext) {
//    System.out.println("will retry "+retryCount);
    if (retryCount >= retryNum) {
      return false;
    }
    if (e instanceof InterruptedIOException) {
      return false;
    }
    if (e instanceof UnknownHostException) {
      return false;
    }
    if (e instanceof ConnectException) {
      return false;
    }
    if (e instanceof SSLException) {
      return false;
    }
    HttpRequest request = (HttpRequest) httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
    return !(request instanceof HttpEntityEnclosingRequest);
  }
}
