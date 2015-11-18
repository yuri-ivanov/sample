import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexerTest {
    private Indexer indexer;

    @Before
    public void setUp() throws IOException {
        indexer = new IndexerV2();
        indexer.parse("test.txt");
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionOnWrongFile() throws IOException {
        indexer.parse("somefile");
    }

    @Test
    public void shouldReadExistingFile() throws IOException {
        indexer.parse("test.txt");
    }

    @Test
    public void shouldReadKeyAndValue(){
        assertThat(indexer.containsEntry("foo")).isTrue();
        assertThat(indexer.getValue("foo")).isEqualTo("bar");
    }

    @Test
    public void onlyLastValueStored(){
        assertThat(indexer.getValue("foo2")).contains("bar3");
    }

    @Test
    public void shouldReadEmptyValue(){
        assertThat(indexer.containsEntry("foo4")).isTrue();
        assertThat(indexer.getValue("foo4")).isEmpty();
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

    @Test
    public void testLineWith2Splitters(){
        assertThat(indexer.containsEntry("foo3")).isTrue();
        assertThat(indexer.getValue("foo3")).isEqualTo("bar3|bar4");
    }

}