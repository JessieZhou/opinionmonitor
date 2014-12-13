package chen.bupt.httpclient.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: chenlingpeng
 * Date: 14-5-13
 * Time: 16:38
 */
public final class PasswordEncrypt {
  public static String md5Hex(String plainText){
    return DigestUtils.md5Hex(plainText);
  }

  public static String shaHex(String plainText){
    return DigestUtils.shaHex(plainText);
  }

  public static String sha256Hex(String plainText){
    return DigestUtils.sha256Hex(plainText);
  }

  public static String sha384Hex(String plainText){
    return DigestUtils.sha384Hex(plainText);
  }

  public static String sha512Hex(String plainText){
    return DigestUtils.sha512Hex(plainText);
  }

}
