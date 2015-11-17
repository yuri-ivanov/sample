import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * input file format:
 *
 *  foo|bar
 *  foo2|bar2
 *  .
 *  .
 */
public class IndexerMap implements Indexer{
    private final static String SPLIT_REGEXP = "\\|";
    private Map<String, String> map = new HashMap<>();

    public void parse(String filename) throws IOException {
        map.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] splittedVal = line.split(SPLIT_REGEXP);
                if(splittedVal!=null && splittedVal.length>0){
                    map.put(splittedVal[0], (splittedVal.length>1)?splittedVal[1]:"");
                }
            }
        }
    }

    /***
     * Returns true if the map contains the given key.
     */
    public boolean containsEntry(String key) {
        return map.containsKey(key);
    }

    /***
     * Returns the value from the map if key is in map.
     */
    public String getValue(String key) {
        return map.get(key);
    }

}