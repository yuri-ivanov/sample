import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/***
 * input file format:
 *
 *  foo|bar
 *  foo2|bar2
 *  .
 *  .
 */
public class IndexerV1 implements Indexer {

    public static ArrayList<String> list = new ArrayList<String>();

    public void parse(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Returns true if the list contains the given key.
     */
    public boolean containsEntry(String key) {
        boolean found = false;
        for (String entry : list) {
            String entryKey = entry.split("\\|")[0];
            if (key.equals(entryKey)) {
                found = true;
            }
        }
        return found;
    }

    /***
     * Returns the value from the list if key is in list.
     */
    public String getValue(String key) {
        if (!containsEntry(key)) {
            return null;
        }
        String result = "";
        for (String entry : list) {
            String entryKey = entry.split("\\|")[0];
            if (key.equals(entryKey)) {
                result = entry.split("\\|")[1];
            }
        }
        return result;
    }

}