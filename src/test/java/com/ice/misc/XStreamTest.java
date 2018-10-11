package com.ice.misc;

import com.ice.misc.xml.Person;
import com.ice.misc.xml.XmlUtf8Xstream;
import com.thoughtworks.xstream.XStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.junit.Test;
import org.xml.sax.InputSource;

/**
 * @author ice
 * @Date 2018/10/11 14:22
 */
public class XStreamTest {

  @Test
  public void toXml() {
    Person person = new Person();
    person.setId(1);
    person.setName("ice");
    person.setAge(25);
    person.setAddress("address");
//    XStream xStream = new XStream(new StaxDriver());//使用StaxDriver驱动处理xml
    XStream xStream = new XmlUtf8Xstream();
    xStream.autodetectAnnotations(true);//处理注解
    xStream.alias("person", Person.class);
    String xml = xStream.toXML(person);
    System.out.println(xml);
    xml = "<?xml version=\"1.0\" ?><person id__a=\"1\"><name>ice</name><age>25</age><address>address</address></person>";
    Person person1 = (Person) xStream.fromXML(xml);
    System.out.println(Misc.obj2json(person1));
  }

  public static String formatXml(String xml) {
    try {
      Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
      serializer.setOutputProperty(OutputKeys.INDENT, "yes");
      serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
      StreamResult res = new StreamResult(new ByteArrayOutputStream());
      serializer.transform(xmlSource, res);
      return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
    } catch (Exception e) {
      return xml;
    }
  }
}
