package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class domxml {

    public static void main(String[] args) throws Exception {
        //        DOM解析XML文件步骤
//        1.创建解析器工厂对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        2.解析器工厂对象创建解析器对象
        DocumentBuilder builder = factory.newDocumentBuilder();
//        3.解析器对象指定XML文件创建Document对象
        Document doc = builder.parse(domxml.class.getResourceAsStream("/book.xml"));
//        4.以Document对象为起点操作DOM树
        NodeList childNodes = doc.getChildNodes();
        //System.out.println(childNodes.getLength());
        //得到根节点的节点名称
        System.out.println(childNodes.item(0).getNodeName());
        NodeList childNodes1 = childNodes.item(0).getChildNodes();
        //System.out.println(childNodes1.getLength());
        for (int i = 0; i < childNodes1.getLength(); i++) {
            Node item = childNodes1.item(i);
            //System.out.println(item.getNodeType());
            if (item.getNodeType()==1){
                //表示的是元素，我们就是要操作这个元素
                //把节点类型变成元素element类型
                Element ele = (Element) item;
                //根据元素里面的属性名得到属性对应的值
                String id = ele.getAttribute("id");
                System.out.println(id);

            }
            //得到book元素下面的子元素的集合
            NodeList childNodes2 = item.getChildNodes();
            for (int j = 0; j < childNodes2.getLength(); j++) {
                Node item1 = childNodes2.item(j);
                if(item1.getNodeType()==1){//确定操作的是元素
                    String txt = item1.getTextContent();
                    System.out.println(txt);
                }
            }
        }


    }
}
