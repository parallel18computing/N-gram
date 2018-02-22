import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;

public class DocumentList {
    private ArrayList<BufferedReader> documents;
    private BlockingQueue<String> texts;

    private ConcurrentHashMap<String, Integer> total_lb;
    private ConcurrentHashMap<String, Integer> total_lt;
    private ConcurrentHashMap<String, Integer> total_wb;
    private ConcurrentHashMap<String, Integer> total_wt;

    public DocumentList() {

        documents = new ArrayList();
        texts = new ArrayBlockingQueue(100000);
        total_lb = new ConcurrentHashMap<>();
        total_lt = new ConcurrentHashMap<>();
        total_wb = new ConcurrentHashMap<>();
        total_wt = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Integer> getTotal_lb() {
        return total_lb;
    }

    public ConcurrentHashMap<String, Integer> getTotal_lt() {
        return total_lt;
    }

    public ConcurrentHashMap<String, Integer> getTotal_wb() {
        return total_wb;
    }

    public ConcurrentHashMap<String, Integer> getTotal_wt() {
        return total_wt;
    }

    public void addDocument(String filename) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            this.documents.add(br);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void buildAllDocumentsNgram() throws Exception {

        createTexts();

        while(!texts.isEmpty()) {
            String text = texts.take();

            HashMap<String, Integer> letterBigrams = Ngram.letterBigrams(text);
            HashMap<String, Integer> letterTrigrams = Ngram.letterTrigrams(text);
            HashMap<String, Integer> wordBigrams = Ngram.wordBigrams(text);
            HashMap<String, Integer> wordTrigrams = Ngram.wordTrigrams(text);

            for (String key : letterBigrams.keySet()) {
                if (!total_lb.containsKey(key)) {
                    total_lb.put(key, letterBigrams.get(key));
                } else {
                    total_lb.put(key, total_lb.get(key) + letterBigrams.get(key));
                }
            }

            for (String key : letterTrigrams.keySet()) {
                if (!total_lt.containsKey(key)) {
                    total_lt.put(key, letterTrigrams.get(key));
                } else {
                    total_lt.put(key, total_lt.get(key) + letterTrigrams.get(key));
                }
            }

            for (String key : wordBigrams.keySet()) {
                if (!total_wb.containsKey(key)) {
                    total_wb.put(key, wordBigrams.get(key));
                } else {
                    total_wb.put(key, total_wb.get(key) + wordBigrams.get(key));
                }
            }

            for (String key : wordTrigrams.keySet()) {
                if (!total_wt.containsKey(key)) {
                    total_wt.put(key, wordTrigrams.get(key));
                } else {
                    total_wt.put(key, total_wt.get(key) + wordTrigrams.get(key));
                }
            }


        }




    }

    private BufferedReader getDocument(int i) throws Exception{

        BufferedReader br = null;

        try {
            br = this.documents.get(i);

        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return br;

    }


    private void createTexts() {
        for(int doc = 0; doc < documents.size(); doc++) {
            try {
                BufferedReader br = getDocument(doc);
                br.mark(0);

                String line = br.readLine();

                while(line != null) {

                    if(texts.remainingCapacity() == 0) {
                        BlockingQueue<String> newTexts = new ArrayBlockingQueue(texts.size() + 500);
                        texts.drainTo(newTexts);
                        texts = newTexts;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(line);
                    texts.put(sb.toString());

                    line = br.readLine();

                }

            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

}

