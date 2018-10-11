package com.ice.misc;

import com.ice.misc.xml.Hotel;
import com.ice.misc.xml.JaxbUtil;
import com.ice.misc.xml.RoomTypeVO;
import java.util.Arrays;
import javax.xml.bind.JAXBException;
import org.junit.Test;

/**
 * @author ice
 * @Date 2018/10/11 11:57
 */
public class XmlTest {

  @Test
  public void toXml() throws JAXBException {
    Hotel hotel = new Hotel();
    hotel.setId(1);
    hotel.setName("Hotel");
    RoomTypeVO roomTypeVO = new RoomTypeVO();
    roomTypeVO.setPrice("1.0");
    roomTypeVO.setTypeId(1);
    roomTypeVO.setTypeName("root");
    hotel.setRoomTypeVOS(Arrays.asList(roomTypeVO));
    String xml = JaxbUtil.toXml(hotel, "utf-8");
    System.out.println(xml);
    xml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n"
        + "<hotel id=\"1\">\n"
        + "    <name>Hotel</name>\n"
        + "    <rootTypeVOs>\n"
        + "        <rootTypeVo typeId=\"1\">\n"
        + "            <typeName>root</typeName>\n"
        + "            <price>1.0</price>\n"
        + "        </rootTypeVo>\n"
        + "    </rootTypeVOs>\n"
        + "</hotel>";
    Hotel hotel1 = JaxbUtil.fromXml(Hotel.class, xml);
    System.out.println(Misc.obj2json(hotel1));
  }

  @Test
  public void toListXml() {

  }

}
