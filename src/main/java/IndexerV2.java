import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * input file format:
 *
 *  foo|bar
 *  foo2|bar2
 *  .
 *  .
 */
public class IndexerV2 implements Indexer{
    private final static String SPLIT_REGEXP = "\\|";
    private List<String> list = new ArrayList<>();

    public void parse(String filename) throws IOException {
        list.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
    }

    /***
     * Returns true if the list contains the given key.
     */
    public boolean containsEntry(String key) {
        boolean found = false;
        for (String entry : list) {
            String entryKey = entry.split(SPLIT_REGEXP)[0];
            if (entryKey.equals(key)) {
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
            String[] splittedVal = entry.split(SPLIT_REGEXP);
            String entryKey = splittedVal[0];
            if (splittedVal.length>1 && key.equals(entryKey)) {
                result = splittedVal[1];
            }
        }
        return result;
    }

}