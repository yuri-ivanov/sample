import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *  input file format:
 *
 *  foo|bar
 *  foo2|bar2
 *  .
 *  .
 */
public interface Indexer {

    void parse(String filename) throws IOException;

    /***
     * Returns true if the list contains the given key.
     */
    boolean containsEntry(String key);

    /***
     * Returns the value from the list if key is in list.
     */
    String getValue(String key);
}
