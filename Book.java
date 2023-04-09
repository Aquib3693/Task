package bookreadingandindexing;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private List<BookPage> pages;
    
    public Book() {
        pages = new ArrayList<>();
    }
    
    public void addPage(BookPage page) {
        pages.add(page);
    }
    
    public List<BookPage> getPages() {
        return pages;
    }

    public void addPage(String pageContent, int pageNum) {
        BookPage page = new BookPage(pageNum, pageContent);
        addPage(page);
    }
}
