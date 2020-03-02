package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

class BookHandler extends DefaultHandler {
        private Book book;
        private List<Book> list;
        private String currenTag;


    public List<Book> getList() {
        return list;
    }


    public void setList(List<Book> list) {
        this.list = list;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("==开始加载xml文件==");
        list= new ArrayList<Book>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currenTag=qName;
        if (qName.equals("book")){
            book = new Book();
            if (attributes!=null){
                String id = attributes.getValue("id");
                if ((id!=null)){
                    book.setId(id);
                }
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(currenTag.equals("title")){
            book.setTitle(new String(ch, start, length));
        }else  if(currenTag.equals("author")){
            book.setAuthor(new String(ch, start, length));
        }else  if(currenTag.equals("price")){
            book.setPrice(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        currenTag="";
        if (qName.equals("book")){
            list.add(book);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
