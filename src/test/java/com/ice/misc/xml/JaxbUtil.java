package com.ice.misc.xml;

import com.ice.misc.Misc;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;

/**
 * @author ice
 * @Date 2018/10/11 11:29
 */
public class JaxbUtil {

  public static Marshaller createMarshaller(JAXBContext context, String encoding)
      throws JAXBException {
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    if (!Misc.isNull(encoding)) {
      marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
    }
    return marshaller;
  }

  /**
   * 创建UnMarshaller.
   */
  public static Unmarshaller createUnmarshaller(JAXBContext context) {
    try {
      return context.createUnmarshaller();
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }

  public static String toXml(Object object, String encoding) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(object.getClass());
    StringWriter writer = new StringWriter();
    createMarshaller(context, encoding).marshal(object, writer);
    return writer.toString();
  }

  public static String toXml(Collection collection, String rootName, String encoding)
      throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(collection.getClass());
    CollectionWrapper wrapper = new CollectionWrapper();
    wrapper.collection = collection;

    JAXBElement<CollectionWrapper> collectionWrapper = new JAXBElement<>(
        new QName(rootName), CollectionWrapper.class, wrapper);
    StringWriter writer = new StringWriter();

    createMarshaller(context, encoding).marshal(collectionWrapper, writer);
    return writer.toString();
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class cls, String xml) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(cls);
    StringReader reader = new StringReader(xml);
    return (T) createUnmarshaller(context).unmarshal(reader);
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class cls, String xml, boolean caseSensitive) throws Exception {
    JAXBContext context = JAXBContext.newInstance(cls);
    if (!caseSensitive) {
      xml = xml.toLowerCase();
    }
    StringReader reader = new StringReader(xml);
    return (T) createUnmarshaller(context).unmarshal(reader);
  }


  public static class CollectionWrapper {

    @SuppressWarnings("unchecked")
    @XmlAnyElement
    protected Collection collection;
  }
}
