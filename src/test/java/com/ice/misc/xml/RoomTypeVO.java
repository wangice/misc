package com.ice.misc.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ice
 * @Date 2018/10/11 11:23
 */
@XmlType(propOrder = {"typeName", "price"})
public class RoomTypeVO {

  private int typeId;
  private String typeName;
  private String price;

  @XmlAttribute(name = "typeId")
  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  @XmlElement(name = "typeName")
  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @XmlElement(name = "price")
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
