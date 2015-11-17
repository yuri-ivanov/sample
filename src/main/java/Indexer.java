import java.io.FileNotFoundException;
import java.io.IOException;

public interface Indexer {

    void parse(String filename) throws IOException;

    boolean containsEntry(String key);

    String getValue(String key);
}
