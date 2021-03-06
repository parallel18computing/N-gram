import java.util.concurrent.ConcurrentHashMap;


public class Main {
    public static void main(String[] args) throws Exception{


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
        dl_eng.addDocument("/home/giulia/IdeaProjects/Documents/English Documents/205-0.txt");
        
        long inizio = System.currentTimeMillis();

        dl_ita.buildAllDocumentsNgram();
        dl_eng.buildAllDocumentsNgram();

        long fine = System.currentTimeMillis();

        ConcurrentHashMap<String, Integer> italianLetterBigrams = dl_ita.getTotal_lb();
        ConcurrentHashMap<String, Integer> italianLetterTrigrams = dl_ita.getTotal_lt();
        ConcurrentHashMap<String, Integer> italianWordBigrams = dl_ita.getTotal_wb();
        ConcurrentHashMap<String, Integer> italianWordTrigrams = dl_ita.getTotal_wt();

        ConcurrentHashMap<String, Integer> englishLetterBigrams = dl_eng.getTotal_lb();
        ConcurrentHashMap<String, Integer> englishLetterTrigrams = dl_eng.getTotal_lt();
        ConcurrentHashMap<String, Integer> englishWordBigrams = dl_eng.getTotal_wb();
        ConcurrentHashMap<String, Integer> englishWordTrigrams = dl_eng.getTotal_wt();


        ResultFile result_ita = new ResultFile();
        result_ita.setLetterBigrams(italianLetterBigrams);
        result_ita.setLetterTrigrams(italianLetterTrigrams);
        result_ita.setWordBigrams(italianWordBigrams);
        result_ita.setWordTrigrams(italianWordTrigrams);


        result_ita.getResult("/home/giulia/IdeaProjects/Documents/Italian Documents/Sequential Italian Results.txt");

        ResultFile result_eng = new ResultFile();
        result_eng.setLetterBigrams(englishLetterBigrams);
        result_eng.setLetterTrigrams(englishLetterTrigrams);
        result_eng.setWordBigrams(englishWordBigrams);
        result_eng.setWordTrigrams(englishWordTrigrams);


        result_eng.getResult("/home/giulia/IdeaProjects/Documents/English Documents/Sequential English Results.txt");

        System.out.println(fine - inizio);

    }
}
