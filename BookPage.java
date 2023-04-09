package bookreadingandindexing;

public class BookPage {
    private int pageNum;
    private String pageContent;
    
    public BookPage(int pageNum, String pageContent) {
        this.pageNum = pageNum;
        this.pageContent = pageContent;
    }
    
    public int getPageNum() {
        return pageNum;
    }
    
    public String getPageContent() {
        return pageContent;
    }
}
