import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DocumentList {
    private ArrayList<BufferedReader> documents;
    private HashMap<Integer, String> texts;
    private int size;


    public DocumentList() {

        documents = new ArrayList<>();
        texts = new HashMap<>();
        size = 0;
    }

    public void addDocument(String filename) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            this.documents.add(br);
            size++;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getSize() {
        return size;
    }

    public BufferedReader getDocument(int i) throws Exception{

        BufferedReader br = null;

        try {
            br = this.documents.get(i);

        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return br;

    }

}

