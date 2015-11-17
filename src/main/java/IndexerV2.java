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
        for (String[] line : list) {
            if (checkKey(key, line)) {
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
        String result = null;
        for (String[] line : list) {
            if (checkKey(key, line)) {
                result = (line.length>1)?line[1]:"";
            }
        }
        return result;
    }

    private boolean checkKey(String key, String[] line) {
        return line.length>0 && line[0]!=null && line[0].equals(key);
    }

}