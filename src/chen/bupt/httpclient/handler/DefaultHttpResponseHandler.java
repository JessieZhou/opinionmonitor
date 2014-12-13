package chen.bupt.httpclient.handler;

import chen.bupt.httpclient.utils.InputStreamUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-16
 * Time: 09:41
 */
public class DefaultHttpResponseHandler implements ResponseHandler<String> {
  @Override
  public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
    return InputStreamUtils.entity2String(httpResponse.getEntity());
  }
}
