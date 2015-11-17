import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * Indexer with Map storage
 *
 */
public class IndexerMap implements Indexer{

    private Map<String, String> map = new HashMap<>();

    @Override
    public void parse(String filename) throws IOException {
        map.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] splittedVal = line.split(SPLIT_REGEXP);
                if(splittedVal.length>0){
                    map.put(splittedVal[0], (splittedVal.length>1)?splittedVal[1]:"");
                }
            }
        }
    }

    /***
     * Returns true if the map contains the given key.
     */
    @Override
    public boolean containsEntry(String key) {
        return map.containsKey(key);
    }

    /***
     * Returns the value from the map if key is in map.
     */
    @Override
    public String getValue(String key) {
        return map.get(key);
    }

}