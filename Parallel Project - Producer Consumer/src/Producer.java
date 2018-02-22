import java.io.BufferedReader;
import java.util.concurrent.BlockingQueue;


public class Producer implements Runnable {
    private DocumentList dl;
    private BlockingQueue<String> texts;
    private int numConsumer;


    public Producer(DocumentList dl, BlockingQueue<String> texts, int numConsumer) {
        this.dl = dl;
        this.texts = texts;
        this.numConsumer = numConsumer;
    }

    @Override
    public void run() {

        try {

            for (int doc = 0; doc < dl.getSize(); doc++) {

                BufferedReader br = dl.getDocument(doc);
                br.mark(0);

                String line = br.readLine();

                while (line != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(line);
                    texts.put(sb.toString());
                    line = br.readLine();

                }

            }

            for(int i = 0; i < numConsumer; i++) {
                texts.put("BlockingQueueEnd");
            }




        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}
