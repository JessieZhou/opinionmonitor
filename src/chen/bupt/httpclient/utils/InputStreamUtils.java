package chen.bupt.httpclient.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-13
 * Time: 15:38
 * 解析HttpResponse的内容
 */
public class InputStreamUtils {
  private static final Logger log = Logger.getLogger(InputStreamUtils.class);

  private static final String defaultCharSet = "UTF-8";

  public static String inputStream2String(InputStream is, String charSet) {
    if (is != null) {
      StringBuilder sb = new StringBuilder();
      String line;
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charSet));
        while ((line = reader.readLine()) != null) {
          sb.append(line).append("\n");
        }
      } catch (Exception e) {
      } finally {
        try {
          is.close();
          return sb.toString();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  public static String inputStream2String(InputStream is) {
    return inputStream2String(is, defaultCharSet);
  }

  public static void inputStream2File(InputStream is, String fileName, String charSet) {
    try {
      FileOutputStream fos = new FileOutputStream(fileName);
      byte[] stream = new byte[1024];
      while (is.read(stream) != -1) {
        fos.write(stream);
      }
      fos.flush();
      fos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void inputStream2File(InputStream is, String fileName) {
    inputStream2File(is, fileName, defaultCharSet);
  }

  public static HttpEntity getRealEntity(HttpEntity entity){
    Header header = entity.getContentEncoding();
    HttpEntity entity2 = entity;
    if (header != null && header.getValue().toLowerCase().contains("gzip")) {
//      log.info("entity content encoding is " + header.getValue());
      entity2 = new GzipDecompressingEntity(entity);
    } else if (header != null && header.getValue().toLowerCase().contains("deflate")) {
//      log.info("entity content encoding is " + header.getValue());
      entity2 = new DeflateDecompressingEntity(entity);
    }
    return entity2;
  }

  public static String entity2String(HttpEntity entity, String charSet) {
    Header header = entity.getContentEncoding();
    HttpEntity entity2 = entity;
    if (header != null && header.getValue().toLowerCase().contains("gzip")) {
//      log.info("entity content encoding is " + header.getValue());
      entity2 = new GzipDecompressingEntity(entity);
    } else if (header != null && header.getValue().toLowerCase().contains("deflate")) {
//      log.info("entity content encoding is " + header.getValue());
      entity2 = new DeflateDecompressingEntity(entity);
    }

    try {
      return inputStream2String(entity2.getContent(), charSet);
    } catch (IOException e) {
      log.warn(e.getMessage());
      if(e.getMessage().contains("GZIP format")){
        log.info("not a gzip format, try normal");
        try {
          return inputStream2String(entity.getContent(), charSet);
        } catch (IOException e1) {
          log.warn(e1.getMessage());
        }
      }
    }
    return null;
  }

  public static String entity2String(HttpEntity entity) {
    return entity2String(entity, defaultCharSet);
  }

  public static void saveFile(HttpEntity entity, String fileName) throws IOException {
    Header header = entity.getContentEncoding();
    HttpEntity entity2 = entity;
    if (header != null && header.getValue().toLowerCase().contains("gzip")) {
      log.info("entity content encoding is " + header.getValue());
      entity2 = new GzipDecompressingEntity(entity);
    } else if (header != null && header.getValue().toLowerCase().contains("deflate")) {
      log.info("entity content encoding is " + header.getValue());
      entity2 = new DeflateDecompressingEntity(entity);
    }
    inputStream2File(entity2.getContent(), fileName);
  }
}
