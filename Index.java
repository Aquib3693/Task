package bookreadingandindexing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {
    private Map<String, List<Integer>> index;

    public Index() {
        index = new HashMap<>();
    }

    public void addWord(String word, int pageNum) {
        if (!index.containsKey(word)) {
            index.put(word, new ArrayList<>());
        }

        List<Integer> pages = index.get(word);
        if (!pages.contains(pageNum)) {
            pages.add(pageNum);
        }
    }

    public List<Integer> getPagesForWord(String word) {
        return index.getOrDefault(word, Collections.emptyList());
    }

    public List<String> getWords() {
        List<String> words = new ArrayList<>(index.keySet());
        Collections.sort(words);
        return words;
    }
}

