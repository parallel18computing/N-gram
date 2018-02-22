import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer implements Runnable {
    private ConcurrentHashMap<String, Integer> total_lb;
    private ConcurrentHashMap<String, Integer> total_lt;
    private ConcurrentHashMap<String, Integer> total_wb;
    private ConcurrentHashMap<String, Integer> total_wt;

    private BlockingQueue<String> texts;


    public Consumer(BlockingQueue<String> texts, ConcurrentHashMap<String, Integer> tlb, ConcurrentHashMap<String, Integer> tlt, ConcurrentHashMap<String, Integer> twb, ConcurrentHashMap<String, Integer> twt) {
        this.texts = texts;
        this.total_lb = tlb;
        this.total_lt = tlt;
        this.total_wb = twb;
        this.total_wt = twt;
    }


    @Override
    public void run() {

        try {

            HashMap<String, Integer> letterBigrams = new HashMap<>();
            HashMap<String, Integer> letterTrigrams = new HashMap<>();
            HashMap<String, Integer> wordBigrams = new HashMap<>();
            HashMap<String, Integer> wordTrigrams = new HashMap<>();

            while(true) {

                String text = texts.take();

                if(text.equals("BlockingQueueEnd")) {
                    updateTotal(letterBigrams, letterTrigrams, wordBigrams, wordTrigrams);
                    break;
                }

                if(letterBigrams.size() == 0) {
                    letterBigrams = Ngram.letterBigrams(text);
                    letterTrigrams = Ngram.letterTrigrams(text);
                    wordBigrams = Ngram.wordBigrams(text);
                    wordTrigrams = Ngram.wordTrigrams(text);
                }
                else {

                    HashMap<String, Integer> lb;
                    HashMap<String, Integer> lt;
                    HashMap<String, Integer> wb;
                    HashMap<String, Integer> wt;


                    lb = Ngram.letterBigrams(text);
                    lt = Ngram.letterTrigrams(text);
                    wb = Ngram.wordBigrams(text);
                    wt = Ngram.wordTrigrams(text);


                    for (String key : lb.keySet()) {
                        if (!letterBigrams.containsKey(key)) {
                            letterBigrams.put(key, lb.get(key));
                        } else {
                            letterBigrams.put(key, letterBigrams.get(key) + lb.get(key));
                        }
                    }

                    for (String key : lt.keySet()) {
                        if (!letterTrigrams.containsKey(key)) {
                            letterTrigrams.put(key, lt.get(key));
                        } else {
                            letterTrigrams.put(key, letterTrigrams.get(key) + lt.get(key));
                        }
                    }

                    for (String key : wb.keySet()) {
                        if (!wordBigrams.containsKey(key)) {
                            wordBigrams.put(key, wb.get(key));
                        } else {
                            wordBigrams.put(key, wordBigrams.get(key) + wb.get(key));
                        }
                    }

                    for (String key : wt.keySet()) {
                        if (!wordTrigrams.containsKey(key)) {
                            wordTrigrams.put(key, wt.get(key));
                        } else {
                            wordTrigrams.put(key, wordTrigrams.get(key) + wt.get(key));
                        }
                    }
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateTotal(HashMap<String, Integer> lb, HashMap<String, Integer> lt, HashMap<String, Integer> wb, HashMap<String, Integer> wt) {
        for (String key : lb.keySet()) {
            if (!total_lb.containsKey(key)) {
                total_lb.put(key, lb.get(key));
            } else {
                total_lb.put(key, total_lb.get(key) + lb.get(key));
            }
        }

        for (String key : lt.keySet()) {
            if (!total_lt.containsKey(key)) {
                total_lt.put(key, lt.get(key));
            } else {
                total_lt.put(key, total_lt.get(key) + lt.get(key));
            }
        }

        for (String key : wb.keySet()) {
            if (!total_wb.containsKey(key)) {
                total_wb.put(key, wb.get(key));
            } else {
                total_wb.put(key, total_wb.get(key) + wb.get(key));
            }
        }

        for (String key : wt.keySet()) {
            if (!total_wt.containsKey(key)) {
                total_wt.put(key, wt.get(key));
            } else {
                total_wt.put(key, total_wt.get(key) + wt.get(key));
            }
        }


    }
}
