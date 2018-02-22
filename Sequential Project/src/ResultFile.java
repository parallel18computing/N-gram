import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ResultFile {
    private TreeMap<String, Integer> letterBigrams;
    private TreeMap<String, Integer> letterTrigrams;
    private TreeMap<String, Integer> wordBigrams;
    private TreeMap<String, Integer> wordTrigrams;


    public ResultFile() {
        letterBigrams = new TreeMap<>();
        letterTrigrams = new TreeMap<>();
        wordBigrams = new TreeMap<>();
        wordTrigrams = new TreeMap<>();
    }


    private TreeMap<String, Integer> sortMapByValue(ConcurrentHashMap<String, Integer> hm) {
        ValueComparator comparator = new ValueComparator(hm);
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>(comparator);

        tm.putAll(hm);

        return tm;

    }

    public void setLetterBigrams(ConcurrentHashMap<String,Integer> map) {
        letterBigrams = sortMapByValue(map);
    }

    public void setLetterTrigrams(ConcurrentHashMap<String,Integer> map) {
        letterTrigrams = sortMapByValue(map);
    }

    public void setWordBigrams(ConcurrentHashMap<String,Integer> map) {
        wordBigrams = sortMapByValue(map);
    }

    public void setWordTrigrams(ConcurrentHashMap<String,Integer> map) {
        wordTrigrams = sortMapByValue(map);
    }


    public void getResult(String location) throws Exception {
        File file = new File(location);

        FileWriter writer = new FileWriter(file);

        writer.write("Letter Bigrams:" + "\n\n");

        writer.write(letterBigrams.toString());

        writer.write("\n\n" + "Letter Trigrams:" + "\n\n");

        writer.write(letterTrigrams.toString());

        writer.write("\n\n" + "Word Bigrams:" + "\n\n");

        writer.write(wordBigrams.toString());

        writer.write("\n\n" + "Word Trigrams:" + "\n\n");

        writer.write(wordTrigrams.toString());


        writer.close();



    }




}
