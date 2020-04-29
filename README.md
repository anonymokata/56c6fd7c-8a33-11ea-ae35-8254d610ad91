# Word Search kata 
# This includes all tests and test files for the word search code, and the test can be run using maven.

Usage:

mvn test

# There is also a source file for finding the solution to word searches in the test format specified in the kata description.
# Any file in the correct format should work, but there is one included where the words are U.S. states.

Usage:

mvn package

java -cp .\target\Kata-1.0-SNAPSHOT.jar bensmith.App .\word-search-test-file-1.txt
