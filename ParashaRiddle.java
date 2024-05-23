import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParashaRiddle {

    public static void main(String[] args) throws IOException {
        int[] lengths = {3,3,3};
        System.out.println("מתחיל");
        //String path = "https://www.toratemetfreeware.com/online/f_01684_part_7.html";
        //String path = "file:///home/mickey/Mickey/Temp/Behar.txt";
        String path = "https://beneyisrael.com/Tikun-Qorim/cp/cp0309.htm";
        URL url = new URL(path);
        //StringBuilder out = new StringBuilder();
        //Scanner scanner = new Scanner(url.openStream());
        //while (scanner.hasNext()) {
        //    out.append(scanner.nextLine());
        //}
        Document doc = Jsoup.connect(path).get();
        String textContents = doc.body().text();

        //System.out.println(out);
        String[] words1 = textContents.split("[^א-ת]");
        String[] words = new String[words1.length];
        int i = 0;
        for (String s : words1) {
            if (!s.isEmpty()) {
                words[i++] = s;
            }
        }
        System.out.println(words.length);
        for (i = 0; i < words.length - lengths.length; i++) {

            for (int j = 0; j < lengths.length; j++) {
                if (words[i+j] == null) {
                    System.out.println(i + " " + j);
                    System.exit(1);
                }
                if (words[i + j].length() !=lengths[j]) {
                    break;
                }
                if (j == lengths.length - 1) {
                    for (int k = 0; k < lengths.length; k++) {
                        System.out.print(words[i+k] + " ");
                    }
                    System.out.println();
                }
            }


        }
    }
}
