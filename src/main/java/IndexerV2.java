import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/***
 * refactored version with List storage
 *
 */
public class IndexerV2 implements Indexer{
    private static final Logger log = Logger.getLogger(IndexerV2.class.toString());

    private List<String[]> list = new ArrayList<>();

    /**
     * only one key/value pair will be stored
     * if file has more than one key/value pair then it will be logged warning
     * @param filename
     * @throws IOException
     */
    @Override
    public void parse(String filename) throws IOException {
        //clear list before adding new values
        list.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] entry = line.split(SPLIT_REGEXP);
                if(entry.length==0) continue;
                int existEntryIndx = findEntry(entry[0]);
                if(existEntryIndx<0){
                    list.add(entry);
                } else {
                    log.warning("entry " + Arrays.toString(list.get(existEntryIndx)) +
                            " will be replaced with " + Arrays.toString(entry) );
                    list.set(existEntryIndx, entry);
                }
            }
        }
    }

    /***
     * Returns true if the list contains the given key.
     */
    @Override
    public boolean containsEntry(String key) {
        return findEntry(key)>=0;
    }

    /***
     * Returns the value from the list if key is in list.
     */
    @Override
    public String getValue(String key) {
        int indx = findEntry(key);
        if(indx < 0 ) return null;
        return (list.get(indx).length>1)?list.get(indx)[1]:"";
    }

    private int findEntry(String key){
        int indx = -1;
        for (int i=0 ; i < list.size(); i++) {
            if (checkLineByKey(list.get(i), key)) {
                indx = i;
                break;
            }
        }
        return indx;
    }

    private boolean checkLineByKey(String[] line, String key) {
        return line.length>0 && line[0]!=null && line[0].equals(key);
    }

}