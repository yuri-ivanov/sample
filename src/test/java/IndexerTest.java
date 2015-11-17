import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexerTest {
    private Indexer indexer;

    @Before
    public void setUp() throws IOException {
        indexer = new IndexerMap();
        indexer.parse("test.txt");
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionOnWrongFile() throws IOException {
        indexer.parse("somefile");
    }

    @Test
    public void shouldReadExistingFile() throws IOException {
        indexer.parse("test.txt");
        assertThat(indexer).isNotNull();
    }

    @Test
    public void shouldReadKeyAndValue(){
        assertThat(indexer.containsEntry("foo")).isTrue();
        assertThat(indexer.getValue("foo")).contains("bar");
    }

    @Test
    public void shouldReadEmptyValue(){
        assertThat(indexer.containsEntry("gg")).isTrue();
        assertThat(indexer.getValue("gg")).isEmpty();
    }

    @Test
    public void testNotExistingKey(){
        assertThat(indexer.containsEntry("not-here")).isFalse();
        assertThat(indexer.getValue("not-here")).isNull();
    }

    @Test
    public void testNullKey(){
        assertThat(indexer.containsEntry(null)).isFalse();
        assertThat(indexer.getValue(null)).isNull();
    }

}