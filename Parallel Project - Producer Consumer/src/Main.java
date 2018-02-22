import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String args[]) throws Exception {

        DocumentList dl_ita = new DocumentList();

        dl_ita.addDocument("/home/giulia/IdeaProjects/Documents/Italian Documents/31002-8.txt");
        dl_ita.addDocument("/home/giulia/IdeaProjects/Documents/Italian Documents/38082-0.txt");
        dl_ita.addDocument("/home/giulia/IdeaProjects/Documents/Italian Documents/48769-0.txt");
        dl_ita.addDocument("/home/giulia/IdeaProjects/Documents/Italian Documents/26171-8.txt");
        dl_ita.addDocument("/home/giulia/IdeaProjects/Documents/Italian Documents/40299-8.txt");
        
        DocumentList dl_eng = new DocumentList();

        dl_eng.addDocument("/home/giulia/IdeaProjects/Documents/English Documents/205-0.txt");
        dl_eng.addDocument("/home/giulia/IdeaProjects/Documents/English Documents/1342-0.txt");
        dl_eng.addDocument("/home/giulia/IdeaProjects/Documents/English Documents/5200.txt");
        dl_eng.addDocument("/home/giulia/IdeaProjects/Documents/English Documents/2701-0.txt");
        
        ArrayBlockingQueue<String> buffer_ita = new ArrayBlockingQueue<>(100000);
        ArrayBlockingQueue<String> buffer_eng = new ArrayBlockingQueue<>(100000);

        Thread pt_ita = new Thread(new Producer(dl_ita, buffer_ita, 4));


        ConcurrentHashMap<String, Integer> total_ita_LB = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> total_ita_LT = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> total_ita_WB = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> total_ita_WT = new ConcurrentHashMap<>();

        ConcurrentHashMap<String, Integer> total_eng_LB = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> total_eng_LT = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> total_eng_WB = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> total_eng_WT = new ConcurrentHashMap<>();

        Thread ct_ita = new Thread(new Consumer(buffer_ita,total_ita_LB, total_ita_LT, total_ita_WB, total_ita_WT));
        Thread ct_ita2 = new Thread(new Consumer(buffer_ita,total_ita_LB, total_ita_LT, total_ita_WB, total_ita_WT));
        Thread ct_ita3 = new Thread(new Consumer(buffer_ita,total_ita_LB, total_ita_LT, total_ita_WB, total_ita_WT));
        Thread ct_ita4 = new Thread(new Consumer(buffer_ita,total_ita_LB, total_ita_LT, total_ita_WB, total_ita_WT));

        Thread pt_eng = new Thread(new Producer(dl_eng, buffer_eng, 4));

        Thread ct_eng = new Thread(new Consumer(buffer_eng, total_eng_LB, total_eng_LT, total_eng_WB, total_eng_WT));
        Thread ct_eng2 = new Thread(new Consumer(buffer_eng, total_eng_LB, total_eng_LT, total_eng_WB, total_eng_WT));
        Thread ct_eng3 = new Thread(new Consumer(buffer_eng, total_eng_LB, total_eng_LT, total_eng_WB, total_eng_WT));
        Thread ct_eng4 = new Thread(new Consumer(buffer_eng, total_eng_LB, total_eng_LT, total_eng_WB, total_eng_WT));


        long inizio = System.currentTimeMillis();


        pt_ita.start();
        ct_ita.start();
        ct_ita2.start();
        ct_ita3.start();
        ct_ita4.start();


        pt_ita.join();
        ct_ita.join();
        ct_ita2.join();
        ct_ita3.join();
        ct_ita4.join();
        //ct_ita5.join();


        pt_eng.start();
        ct_eng.start();
        ct_eng2.start();
        ct_eng3.start();
        ct_eng4.start();
        //ct_eng5.start();

        pt_eng.join();
        ct_eng.join();
        ct_eng2.join();
        ct_eng3.join();
        ct_eng4.join();
        //ct_eng5.join();

        long fine = System.currentTimeMillis();


        ResultFile result_ita = new ResultFile();
        result_ita.setLetterBigrams(total_ita_LB);
        result_ita.setLetterTrigrams(total_ita_LT);
        result_ita.setWordBigrams(total_ita_WB);
        result_ita.setWordTrigrams(total_ita_WT);


        result_ita.getResult("/home/giulia/IdeaProjects/Documents/Italian Documents/Parallel Italian Results.txt");

        ResultFile result_eng = new ResultFile();
        result_eng.setLetterBigrams(total_eng_LB);
        result_eng.setLetterTrigrams(total_eng_LT);
        result_eng.setWordBigrams(total_eng_WB);
        result_eng.setWordTrigrams(total_eng_WT);


        result_eng.getResult("/home/giulia/IdeaProjects/Documents/English Documents/Parallel English Results.txt");


        System.out.println(fine - inizio);


    }
}
