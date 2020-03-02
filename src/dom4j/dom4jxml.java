package dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class dom4jxml {
    private static Document doc;


    public static void main(String[] args) {
        //test2();
        //add();
        //remove();
        updata("4555");
    }
    public static Document getDoc(String filePath) {
        try {
            SAXReader reader = new SAXReader();
            doc= reader.read(dom4jxml.class.getResourceAsStream(filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }


    public static void savaXML(String savePath) {
        XMLWriter writer=null;
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();

            format.setEncoding("UTF-8");

            FileOutputStream fos=null;
            fos = new FileOutputStream(savePath);

            writer = new XMLWriter(fos, format);
            writer.write(doc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void test2() {
        doc=getDoc("/book.xml");
        //获得根元素
        Element root = doc.getRootElement();
        //获得所有子元素
        List<Element> books = root.elements("book");
        for (Element ele : books) {
            System.out.println(ele.attributeValue("id"));
            System.out.println(ele.elementText("title"));
            System.out.println(ele.elementText("author"));
            System.out.println(ele.elementText("price"));
        }

    }


    public static void add() {
        doc=getDoc("/book.xml");
        //获得根元素
        Element root = doc.getRootElement();
        //创建新节点
        Element newbook = root.addElement("book");

        newbook.addAttribute("id","1003");
        //创建子节点
        Element title = newbook.addElement("title");
        title.addText("html");

        Element author = newbook.addElement("author");
        author.addText("王五");


        Element price = newbook.addElement("price");
        price.addText("78");

        //
        savaXML("D:\\xml解析\\src\\book.xml");

        System.out.println("新增成功！");
    }

    public static void remove() {
        doc=getDoc("/book.xml");
        Element root = doc.getRootElement();
        List<Element> books = root.elements("book");
        System.out.println(books.size());
        for (Element ele : books) {
            if(ele.attributeValue("id").equals("1003")){
                ele.getParent().remove(ele);
                break;
            }
        }

        savaXML("D:\\xml解析\\src\\book.xml");

        System.out.println("删除成功！");
    }

    public static void updata(String newPrice) {
        doc=getDoc("/book.xml");
        Element root = doc.getRootElement();
        List<Element> books = root.elements("book");
        System.out.println(books.size());
        for (Element ele : books) {
            if(ele.attributeValue("id").equals("1003")){
                List<Element> elements = ele.elements();
                for (Element ele2 : elements) {
                    if (ele2.getName().equals("price")){
                        ele2.setText(newPrice);
                        break;
                    }
                }
            }
        }

        savaXML("D:\\xml解析\\src\\book.xml");

        System.out.println("修改成功！");

        System.out.println("新增内容");

    }
}
