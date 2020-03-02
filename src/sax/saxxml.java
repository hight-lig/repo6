package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

public class saxxml {
    public static void main(String[] args) throws Exception{
        BookHandler bookHadler = new BookHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(saxxml.class.getResourceAsStream("/book.xml"),bookHadler);
        List<Book> list = bookHadler.getList();
        for (Book book : list) {
            System.out.println(book);
        }
    }
}
