package com.ice.misc.xml;

import com.thoughtworks.xstream.XStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 * 增强Xstream
 *
 * @author ice
 * @Date 2018/10/11 14:49
 */
public class XmlUtf8Xstream extends XStream {

  /**
   * xml版本号，默认1.0
   */
  private String version;
  /**
   * xml编码，默认UTF-8
   */
  private String encoding;

  public XmlUtf8Xstream() {
    this("1.0", "UTF-8");
  }

  //XML的声明
  public String getDeclaration() {
    return "<?xml version=\"" + this.version + "\" encoding=\"" + this.encoding + "\"?>\n";
  }

  public XmlUtf8Xstream(String version, String encoding) {
    this.version = version;
    this.encoding = encoding;
  }

  /**
   * 覆盖父类的方法，然后调用父类的，输出的时候先输出这个XML的声明
   */
  @Override
  public void toXML(Object obj, OutputStream output) {
    try {
      String dec = this.getDeclaration();
      byte[] bytesOfDec = dec.getBytes("UTF-8");
      output.write(bytesOfDec);
    } catch (Exception e) {
      throw new RuntimeException("error", e);
    }
    super.toXML(obj, output);
  }

  @Override
  public void toXML(Object obj, Writer writer) {
    try {
      writer.write(getDeclaration());
    } catch (Exception e) {
      throw new RuntimeException("error", e);
    }
    super.toXML(obj, writer);
  }

}
