import junit.framework.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexerTest {

    @Test
    public void shouldReadExistingFile(){
        Indexer indexer = new Indexer();
        indexer.parse("test.txt");
        assertThat(indexer).isNotNull();
    }

    @Test
    public void shouldReadKeyAndValue(){
        Indexer indexer = new Indexer();
        assertThat(indexer.containsEntry("foo")).isTrue();
        assertThat(indexer.getValue("foo")).contains("bar");
    }

}