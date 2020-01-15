import java.io.*;
import java.util.*;
public class WordStatCount {

    public static final String UTF_8 = "utf8";
    public static final int SIZE = 1024;

    public static void main(String[] args) {
        try {
            MyScannerFast scan = new MyScannerFast(new File(args[0]));
            Map<String, Integer> wordcur = new LinkedHashMap<>();
            while(scan.hasNextLine()) {
                while (scan.hasNextinLine()) {
                    String num = scan.readNextWord().toLowerCase();
                    if (num.length() > 0) {
                        System.out.println(num);
                        wordcur.put(num, wordcur.getOrDefault(num, 0) + 1);
                    }
                }
            }
            try {
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(new File(args[1])),
                                        UTF_8
                                ),
                                SIZE
                        )
                );
                try {
                    List<Map.Entry<String, Integer>> sortbycur = new ArrayList<>(wordcur.entrySet());
                    Collections.sort(sortbycur, Comparator.comparing(Map.Entry::getValue));
                    for (int i = 0; i < sortbycur.size(); i++) {
                        out.println(sortbycur.get(i).getKey() + " " + sortbycur.get(i).getValue());
                    }
                } finally {
                    out.close();
                }
            } finally {
                scan.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

    }
}