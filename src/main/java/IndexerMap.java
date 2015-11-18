import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/***
 * Indexer with Map storage
 *
 */
public class IndexerMap implements Indexer{
    private static final Logger log = Logger.getLogger(IndexerMap.class.toString());

    private Map<String, String> map = new HashMap<>();

    /**
     * only one key/value pair will be stored
     * if file has more than one key/value pair then it will be logged warning
     * @param filename
     * @throws IOException
     */
    @Override
    public void parse(String filename) throws IOException {
        map.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                //skip empty lines
                if(line.isEmpty()) continue;
                //only split into 2 elements so a|b|c will be a = b|c
                String[] splittedVal = line.split(SPLIT_REGEXP, 2);
                if(splittedVal.length>0){
                    if(map.containsKey(splittedVal[0])){
                        log.warning("entry "+ splittedVal[0]+"|"+map.get(splittedVal[0])+
                                " will be replaced with "+Arrays.toString(splittedVal) );
                    }
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