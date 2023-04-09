package bookreadingandindexing;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

public class BookIndexer {
    private Book book;
    private Index index;

    public BookIndexer() {
        book = new Book();
        index = new Index();
    }

    public void readPages(String... fileNames) {
        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                book.addPage(sb.toString(), i + 1);
            } catch (Exception e) {
                System.err.format("Exception occurred while reading file %s: %s%n", fileName, e);
            }
        }
    }

    public void readExcludeWords(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();

            while (line != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    index.addWord(word, 0);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.err.format("Exception occurred while reading exclude words file %s: %s%n", fileName, e);
        }
        
    }

    public void generateIndex(String indexFileName) {
        for (BookPage page : book.getPages()) {
            String[] words = page.getPageContent().split("\\s+");
            for (String word : words) {
                index.addWord(word, page.getPageNum());
            }
        }

        try (PrintWriter writer = new PrintWriter(new File(indexFileName))) {
            for (String word : index.getWords()) {
                List<Integer> pages = index.getPagesForWord(word);
                String pageStr = String.join(",", pages.stream().map(Object::toString).toArray(String[]::new));
                writer.println(word + " : " + pageStr);
            }
        } catch (Exception e) {
            System.err.format("Exception occurred while writing index file %s: %s%n", indexFileName, e);
        }
    }
    public static void main(String[] args) throws IOException {
        BookIndexer indexer = new BookIndexer();
        indexer.readExcludeWords("C:\\Users\\HP\\Desktop\\task\\bookreadingandindexing\\exclude-words.txt");
        indexer.readPages("C:\\Users\\HP\\Desktop\\task\\bookreadingandindexing\\Page1.txt", "C:\\Users\\HP\\Desktop\\task\\bookreadingandindexing\\Page2.txt", "C:\\Users\\HP\\Desktop\\task\\bookreadingandindexing\\Page3.txt");

        // indexer.readPages("Page1.txt", "Page2.txt", "Page3.txt");
        indexer.generateIndex("C:\\Users\\HP\\Desktop\\task\\bookreadingandindexing\\index.txt");
    }
}



