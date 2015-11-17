# Task 
Given Java code here  https://gist.github.com/03f45bfcbacca576d283 explain the code. How and why you would change it?

## Tools used
 * Idea v. 15
 * Jdk 1.8
 * maven

## Input
 Input data is text file test.txt 
    foo|bar
    foo2|bar2
    gg
    
There should be 3 methods in class that reads file: 

    void parse(String filename) throws IOException;
    boolean containsEntry(String key);
    String getValue(String key);

*****

## Refactoring

### Part 1 - Write tests
 Before start to change code I will write simple tests for original version. 
 IndexerTest.java has some simple tests cases what I will use to test refactored code.
 I don't want to change original version of code, that is why there is Indexer interface and original version in class IndexerV1.
 
 Failed tests with IndexerV1 implementation 
 + shouldReadEmptyValue
 + testNullKey
 + shouldThrowExceptionOnWrongFile
 
### Part 2 - Improve code
 IndexerV2 is refactored version of Indexer.
 Some improvements and errors that is not covered by tests 
 * Close FileReader in parse method by using JDK 8 AutoCloseable try-catch syntax
 * parse method should throw IOException and don't catch it inside method
 * make 'list' property  private and not static 
 * check for Null all possible places and valid array index before access it
 * clear 'list' before parsing file. This is in case we will parse different files with the same instance of Indexer.
 
 Make green all JUnit tests.
 
### Part 3 - Optimization
 Original version does parsing of the lines every time it checks and return key and value. 
 This is not optimal and it will be better to do it in parse method once and store result in 'list' property.
 
 I don't know why originally was used List to store key-value pars, so I kept implementation with List and added one more with HashMap implementation.
 Make sure that all tests green in IndexerMap as well.



