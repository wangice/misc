package com.ice.misc.xml;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * # XmlSeeAlso 指定解析类型，在引用泛型的是必须要加上
 *
 * @author ice
 * @Date 2018/10/11 11:21
 */
@XmlRootElement(name = "hotel")
@XmlSeeAlso({Hotel.class})
public class Hotel {

  private int id;
  private String name;
  private List<RoomTypeVO> roomTypeVOS;

  @XmlAttribute(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElementWrapper(name = "rootTypeVOs")
  @XmlElement(name = "rootTypeVo")
  public List<RoomTypeVO> getRoomTypeVOS() {
    return roomTypeVOS;
  }

  public void setRoomTypeVOS(List<RoomTypeVO> roomTypeVOS) {
    this.roomTypeVOS = roomTypeVOS;
  }
}
