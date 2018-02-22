
import java.util.ArrayList;
import java.util.HashMap;

public class Ngram {
    public Ngram() {
    }

    public static HashMap<String, Integer> letterBigrams(String text) {
        text = text.toLowerCase();
        HashMap<String, Integer> bigrams = new HashMap<>();

        for(int i = 0; i < text.length() - 1; ++i) {
            CharSequence sub = text.subSequence(i, i + 2);
            if (Character.isLetter(sub.charAt(0)) && Character.isLetter(sub.charAt(1))) {
                String keyBigram = (String)sub;
                if (bigrams.containsKey(keyBigram)) {
                    int newValue = ((Integer)bigrams.get(keyBigram)).intValue() + 1;
                    bigrams.put(keyBigram, newValue);
                } else {
                    bigrams.put(keyBigram, Integer.valueOf(1));
                }
            }
        }

        return bigrams;
    }

    public static HashMap<String, Integer> letterTrigrams(String text) {
        text = text.toLowerCase();
        HashMap<String, Integer> trigrams = new HashMap<>();

        for(int i = 0; i < text.length() - 2; ++i) {
            CharSequence sub = text.subSequence(i, i + 3);
            if (Character.isLetter(sub.charAt(0)) && Character.isLetter(sub.charAt(1)) && Character.isLetter(sub.charAt(2))) {
                String keyTrigram = (String)sub;
                if (trigrams.containsKey(keyTrigram)) {
                    int newValue = ((Integer)trigrams.get(keyTrigram)).intValue() + 1;
                    trigrams.put(keyTrigram, newValue);
                } else {
                    trigrams.put(keyTrigram, Integer.valueOf(1));
                }
            }
        }

        return trigrams;
    }

    public static HashMap<String, Integer> wordBigrams(String text) {
        text = text.toLowerCase();
        CharSequence textC = text;

        for(int i = 0; i < textC.length(); ++i) {
            char c = textC.charAt(i);
            if (!Character.isLetter(c)) {
                text = text.replace(c, ' ');
            }
        }

        HashMap<String, Integer> bigrams = new HashMap();
        String[] words = text.split(" ");
        ArrayList<String> temp = new ArrayList();

        int j;
        for(j = 0; j < words.length; ++j) {
            if (words[j].length() > 0) {
                temp.add(words[j]);
            }
        }

        words = (String[])temp.toArray(new String[temp.size()]);

        for(j = 0; j < words.length - 1; ++j) {
            String first = words[j];
            String second = words[j + 1];
            String keyBigram = first + "-" + second;
            if (bigrams.containsKey(keyBigram)) {
                int newValue = ((Integer)bigrams.get(keyBigram)).intValue() + 1;
                bigrams.put(keyBigram, newValue);
            } else {
                bigrams.put(keyBigram, Integer.valueOf(1));
            }
        }

        return bigrams;
    }

    public static HashMap<String, Integer> wordTrigrams(String text) {
        text = text.toLowerCase();
        CharSequence textC = text;

        for(int i = 0; i < textC.length(); ++i) {
            char c = textC.charAt(i);
            if (!Character.isLetter(c)) {
                text = text.replace(c, ' ');
            }
        }

        HashMap<String, Integer> trigrams = new HashMap<>();
        String[] words = text.split(" ");
        ArrayList<String> temp = new ArrayList<>();

        int j;
        for(j = 0; j < words.length; ++j) {
            if (words[j].length() > 0) {
                temp.add(words[j]);
            }
        }

        words = (String[])temp.toArray(new String[temp.size()]);

        for(j = 0; j < words.length - 2; ++j) {
            String first = words[j];
            String second = words[j + 1];
            String third = words[j + 2];
            String keyTrigram = first + "-" + second + "-" + third;
            if (trigrams.containsKey(keyTrigram)) {
                int newValue = ((Integer)trigrams.get(keyTrigram)).intValue() + 1;
                trigrams.put(keyTrigram, newValue);
            } else {
                trigrams.put(keyTrigram, Integer.valueOf(1));
            }
        }

        return trigrams;
    }
}

