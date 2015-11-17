import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * refactored version with List storage
 *
 */
public class IndexerV2 implements Indexer{
    private final static String SPLIT_REGEXP = "\\|";

    private List<String[]> list = new ArrayList<>();

    @Override
    public void parse(String filename) throws IOException {
        list.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(SPLIT_REGEXP));
            }
        }
    }

    /***
     * Returns true if the list contains the given key.
     */
    @Override
    public boolean containsEntry(String key) {
        boolean found = false;
        for (String[] entry : list) {
            if (entry.length>0 && entry[0]!=null && entry[0].equals(key)) {
                found = true;
            }
        }
        return found;
    }

    /***
     * Returns the value from the list if key is in list.
     */
    @Override
    public String getValue(String key) {
        if (!containsEntry(key)) {
            return null;
        }
        String result = "";
        for (String[] entry : list) {
            if (entry.length>1 && key.equals(entry[0])) {
                result = entry[1];
            }
        }
        return result;
    }

}