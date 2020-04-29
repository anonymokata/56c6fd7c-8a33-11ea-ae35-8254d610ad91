package bensmith;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for the development of the word search kata.
 *
 * <P>First creates a few testing files, then uses them to test the searching operations.
 * Some of the WordSearch methods are no longer used, as some refactoring found cleaner
 * alternatives.  More refactoring will reduce this even further, but this is a good
 * record of most of the development process.
 *
 * @author Benjamin Smith	bensmith@iastate.edu
 * @see <a href="http://orcid.org/0000-0003-2607-9338">My ORCID is: 0000-0003-2607-9338</a>
 *
 */
public class WordSearchTest {

    WordSearch blankWordSearch;
    WordSearch notBlankWordSearch;
    WordSearch multiWordSearch;
    WordSearch gridWordSearch;
    WordSearch exampleWordSearch;
    WordSearch exampleNotFoundWordSearch;

    @Before
    public void setUp() {
        File file = new File("TESTFILE.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            blankWordSearch = WordSearch.getInstanceFromFile("TESTFILE.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File file2 = new File("WORDFILE.txt");
        FileWriter fr2 = null;
        try {
            fr2 = new FileWriter(file2);
            fr2.write("WORD");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file3 = new File("OTHERWORDFILE.txt");
        FileWriter fr3 = null;
        try {
            fr3 = new FileWriter(file3);
            fr3.write("WORD,OTHER");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file4 = new File("GRIDWORDFILE.txt");
        FileWriter fr4 = null;
        try {
            fr4 = new FileWriter(file4);
            fr4.write("WORD,OTHER\n");
            fr4.write("A");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr4.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file5 = new File("EXAMPLEFILE.txt");
        FileWriter fr5 = null;
        try {
            fr5 = new FileWriter(file5);
            fr5.write("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA\n");
            fr5.write("U,M,K,H,U,L,K,I,N,V,J,O,C,W,E\n" +
                    "L,L,S,H,K,Z,Z,W,Z,C,G,J,U,Y,G\n" +
                    "H,S,U,P,J,P,R,J,D,H,S,B,X,T,G\n" +
                    "B,R,J,S,O,E,Q,E,T,I,K,K,G,L,E\n" +
                    "A,Y,O,A,G,C,I,R,D,Q,H,R,T,C,D\n" +
                    "S,C,O,T,T,Y,K,Z,R,E,P,P,X,P,F\n" +
                    "B,L,Q,S,L,N,E,E,E,V,U,L,F,M,Z\n" +
                    "O,K,R,I,K,A,M,M,R,M,F,B,A,P,P\n" +
                    "N,U,I,I,Y,H,Q,M,E,M,Q,R,Y,F,S\n" +
                    "E,Y,Z,Y,G,K,Q,J,P,C,Q,W,Y,A,K\n" +
                    "S,J,F,Z,M,Q,I,B,D,B,E,M,K,W,D\n" +
                    "T,G,L,B,H,C,B,E,C,H,T,O,Y,I,K\n" +
                    "O,J,Y,E,U,L,N,C,C,L,Y,B,Z,U,H\n" +
                    "W,Z,M,I,S,U,K,U,R,B,I,D,U,X,S\n" +
                    "K,Y,L,B,Q,Q,P,M,D,F,C,K,E,A,B");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr5.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file6 = new File("EXAMPLENOTFOUNDFILE.txt");
        FileWriter fr6 = null;
        try {
            fr6 = new FileWriter(file6);
            fr6.write("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA,MISSING\n");
            fr6.write("U,M,K,H,U,L,K,I,N,V,J,O,C,W,E\n" +
                    "L,L,S,H,K,Z,Z,W,Z,C,G,J,U,Y,G\n" +
                    "H,S,U,P,J,P,R,J,D,H,S,B,X,T,G\n" +
                    "B,R,J,S,O,E,Q,E,T,I,K,K,G,L,E\n" +
                    "A,Y,O,A,G,C,I,R,D,Q,H,R,T,C,D\n" +
                    "S,C,O,T,T,Y,K,Z,R,E,P,P,X,P,F\n" +
                    "B,L,Q,S,L,N,E,E,E,V,U,L,F,M,Z\n" +
                    "O,K,R,I,K,A,M,M,R,M,F,B,A,P,P\n" +
                    "N,U,I,I,Y,H,Q,M,E,M,Q,R,Y,F,S\n" +
                    "E,Y,Z,Y,G,K,Q,J,P,C,Q,W,Y,A,K\n" +
                    "S,J,F,Z,M,Q,I,B,D,B,E,M,K,W,D\n" +
                    "T,G,L,B,H,C,B,E,C,H,T,O,Y,I,K\n" +
                    "O,J,Y,E,U,L,N,C,C,L,Y,B,Z,U,H\n" +
                    "W,Z,M,I,S,U,K,U,R,B,I,D,U,X,S\n" +
                    "K,Y,L,B,Q,Q,P,M,D,F,C,K,E,A,B");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr6.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            notBlankWordSearch = WordSearch.getInstanceFromFile("WORDFILE.txt");
            multiWordSearch = WordSearch.getInstanceFromFile("OTHERWORDFILE.txt");
            gridWordSearch = WordSearch.getInstanceFromFile("GRIDWORDFILE.txt");
            exampleWordSearch = WordSearch.getInstanceFromFile("EXAMPLEFILE.txt");
            exampleNotFoundWordSearch = WordSearch.getInstanceFromFile("EXAMPLENOTFOUNDFILE.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenWordSearchIsPassedAFileNameNotFoundItThrowsFileNotFoundException() {
        try {
            WordSearch wordSearch = WordSearch.getInstanceFromFile("");
            fail();
        } catch (FileNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void whenWordSearchIsPassedARealFileNameItDoesNotThrowAnException() {
        try {
            WordSearch wordSearch = WordSearch.getInstanceFromFile("TESTFILE.txt");
        } catch (FileNotFoundException e) {
            fail();
        }
        assertTrue(true);
    }

    @Test
    public void whenWordSearchIsPassedATooShortFileANonNullIsCreated() {
        WordSearch emptySearch = null;
        try {
            emptySearch = WordSearch.getInstanceFromFile("TESTFILE.txt");
        } catch (FileNotFoundException e) {
            fail();
        }
        assertTrue(emptySearch != null);
    }

    @Test
    public void whenWordSearchIsCreatedWithoutAListOfWordsItHasAnEmptyWordList() {
        WordSearch noListElements = blankWordSearch;
        ArrayList<String> wordList = noListElements.getWordList();
        assertTrue(wordList.isEmpty());
    }

    @Test
    public void whenWordSearchIsCreatedWithAListOfWordsItHasANonEmptyWordList() {
        WordSearch hasListElements = notBlankWordSearch;

        ArrayList<String> wordList = hasListElements.getWordList();
        assertTrue(!wordList.isEmpty());
    }

    @Test
    public void whenWordSearchIsCreatedWithSpecificWordItIsInWordList() {
        WordSearch hasListElements = notBlankWordSearch;

        ArrayList<String> wordList = hasListElements.getWordList();
        assertTrue(wordList.contains("WORD"));
    }

    @Test
    public void whenWordSearchIsCreatedWithMultipleWordsTheyAreAllInWordList() {
        WordSearch hasListElements = multiWordSearch;

        ArrayList<String> wordList = hasListElements.getWordList();
        assertTrue(wordList.contains("WORD"));
        assertTrue(wordList.contains("OTHER"));
    }

    @Test
    public void whenWordSearchFileHasNoGridItWillReturnEmptyGrid() {
        WordSearch hasNoGrid = multiWordSearch;
        String [][] grid = hasNoGrid.getGrid();
        assertTrue(grid.length == 0);
    }

    @Test
    public void whenFileHasAGridItReturnsNonEmptyGrid() {
        WordSearch hasGrid = gridWordSearch;
        String [][] grid = hasGrid.getGrid();
        assertTrue(grid.length != 0);
    }

    @Test
    public void whenFileHasANonEmptyGridItIsSquare() {
        WordSearch hasGrid = gridWordSearch;
        String [][] grid = hasGrid.getGrid();
        int lengthA = grid.length;
        int lengthB = grid[0].length;
        assertTrue(lengthA == lengthB);
    }

    @Test
    public void whenFileHasANonEmptyGridItHasAnElementAt0x0() {
        WordSearch hasGrid = gridWordSearch;
        String element = hasGrid.getGridElement(0,0);
        assertEquals(element, "A");
    }


    @Test
    public void whenExampleFileIsGivenItCreatesAValidWordSearch() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(theExample != null);
        assertTrue(theExample.getWordList().contains("SPOCK"));
        assertTrue(theExample.getGrid().length == theExample.getGrid()[0].length);
    }

    @Test
    public void whenNonDiagonalCharacterRequestedItIsCorrectByConvention() {
        WordSearch theExample = exampleWordSearch;
        assertEquals(theExample.getGridElement(0,1), "L");
        assertEquals(theExample.getGridElement(2,3), "J");
    }

    @Test
    public void whenSearchingHorizontalFindScotty() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(theExample.findHorizontal("SCOTTY"));
    }

    @Test
    public void whenSearchingARowHorizontalFindScotty() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(theExample.findHorizontalInRow(5, "SCOTTY"));
    }

    @Test
    public void whenSearchingARowHorizontalDontFindScotty() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(!theExample.findHorizontalInRow(4, "SCOTTY"));
    }

    @Test
    public void dimensionGivesCorrectValue() {
        assertEquals(15, exampleWordSearch.getDimension());
        assertEquals(1, gridWordSearch.getDimension());
    }

    @Test
    public void whenSearchingMakeSureWordCanFit() {
        assertTrue(exampleWordSearch.wordCanFit("SCOTTY"));
        assertTrue(!gridWordSearch.wordCanFit("SCOTTY"));
    }

    @Test
    public void whenSearchingFindScottyOnlyInRow5() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(!theExample.findHorizontalInRow(0, "SCOTTY"));
        assertTrue(!theExample.findHorizontalInRow(1, "SCOTTY"));
        assertTrue(!theExample.findHorizontalInRow(2, "SCOTTY"));
        assertTrue(!theExample.findHorizontalInRow(3, "SCOTTY"));
        assertTrue(!theExample.findHorizontalInRow(4, "SCOTTY"));
        assertTrue(theExample.findHorizontalInRow(5, "SCOTTY"));
        assertTrue(!theExample.findHorizontalInRow(6, "SCOTTY"));
    }

    @Test
    public void whenComparingLettersFindCorrectMatches() {
        assertEquals(WordSearch.getLetter(0, "SCOTTY"), "S");
        assertEquals(WordSearch.getLetter(1, "SCOTTY"), "C");
    }

    @Test
    public void whenSearchingForALongWordFindIt() {
        assertTrue(exampleWordSearch.findHorizontal("KYLBQQPMDFCKEAB"));
    }

    @Test
    public void obtainCorrectReverseOfWords() {
        assertEquals("SCOTTY", WordSearch.reverseWord("YTTOCS"));
        assertEquals("RETTEB", WordSearch.reverseWord("BETTER"));
    }

    @Test
    public void whenHorizontalFindInReverse() {
        assertTrue(exampleWordSearch.findHorizontalReverse("KIRK"));
    }

    @Test
    public void whenSearchingAColumnFindBones() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(theExample.findVerticalInCol(0, "BONES"));
    }

    @Test
    public void whenSearchingVerticalFindBones() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(theExample.findVertical("BONES"));
        assertTrue(!theExample.findVertical("BOOONES"));
    }

    @Test
    public void whenSearchingDiagonalDescendingFindSpock() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(theExample.findDiagonalDescending("SPOCK"));
        assertTrue(!theExample.findDiagonalDescending("SPOOK"));
    }

    @Test
    public void whenSearchingDiagonalAscendingFindWord() {
        WordSearch theExample = exampleWordSearch;
        assertTrue(theExample.findDiagonalAscending("ARUHU"));
        assertTrue(!theExample.findDiagonalAscending("AROOHA"));
    }

    @Test
    public void whenSearchingFindAllWords() {
        assertTrue(exampleWordSearch.foundAllWords());
    }

    @Test
    public void reportStartingPositionPair() {
        PositionPair pp = new PositionPair(0,0);
        assertEquals(pp.toString(), "(0,0)");
    }

    @Test
    public void whenEqualPositionPairsComparedTheyAreEqual() {
        PositionPair pp1 = new PositionPair(123, 456);
        PositionPair pp2 = new PositionPair(123, 456);
        assertEquals(pp1, pp2);
    }

    @Test
    public void whenNonEqualPositionPairsComparedTheyAreNotEqual() {
        PositionPair pp1 = new PositionPair(123, 456);
        PositionPair pp2 = new PositionPair(456, 123);
        assertNotEquals(pp1, pp2);
    }

    @Test
    public void whenSearchingHorizontalReturnPositionPairStart() {
        PositionPair pp1 = new PositionPair(0, 0);
        PositionPair pp2 = new PositionPair(10, 14);
        assertEquals(exampleWordSearch.reportHorizontal("UMKHU"), pp1);
        assertEquals(exampleWordSearch.reportHorizontal("CKEAB"), pp2);
    }


    @Test
    public void whenSearchingVerticalReturnPositionPairStart() {
        PositionPair pp1 = new PositionPair(0, 0);
        PositionPair pp2 = new PositionPair(10, 0);
        assertEquals(exampleWordSearch.reportVertical("ULHBAS"), pp1);
        assertEquals(exampleWordSearch.reportVertical("JGSKHPUF"), pp2);
        PositionPair ppEx = new PositionPair(0,6);
        assertEquals(exampleWordSearch.reportVertical("BONES"), ppEx);
    }

    @Test
    public void whenSearchingDiagonalDescendingReturnPositionPairStart() {
        PositionPair pp1 = new PositionPair(0, 0);
        PositionPair pp2 = new PositionPair(10, 0);
        assertEquals(exampleWordSearch.reportDiagonalDescending("ULUSGYE"), pp1);
        assertEquals(exampleWordSearch.reportDiagonalDescending("JJXLD"), pp2);
    }

    @Test
    public void whenSearchingDiagonalAscendingReturnPositionPairStart() {
        PositionPair pp1 = new PositionPair(0, 10);
        PositionPair pp2 = new PositionPair(3, 14);
        assertEquals(exampleWordSearch.reportDiagonalAscending("SYIILY"), pp1);
        assertEquals(exampleWordSearch.reportDiagonalAscending("BSLBBP"), pp2);
    }

    @Test
    public void whenSearchingAWordReportStartString() {
        assertEquals("BONES: (0,6)", exampleWordSearch.outputStart("BONES"));
        assertEquals("SCOTTY: (0,5)", exampleWordSearch.outputStart("SCOTTY"));
        assertEquals("SPOCK: (2,1)", exampleWordSearch.outputStart("SPOCK"));
    }

    @Test
    public void whenSearchingAWordReportStartStringReverse() {
        assertEquals("KHAN: (5,6)", exampleWordSearch.outputStart("KHAN"));
        assertEquals("KIRK: (1,7)", exampleWordSearch.outputStart("KIRK"));
        assertEquals("UHURA: (0,4)", exampleWordSearch.outputStart("UHURA"));
    }

    @Test
    public void whenWordFoundOutputEntireLocation() {
        assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", exampleWordSearch.outputWholeLocation("SCOTTY"));
        assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)", exampleWordSearch.outputWholeLocation("BONES"));
        assertEquals("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)", exampleWordSearch.outputWholeLocation("SPOCK"));
        assertEquals("KZYBM: (0,14),(1,13),(2,12),(3,11),(4,10)", exampleWordSearch.outputWholeLocation("KZYBM"));
    }

    @Test
    public void whenWordFoundOutputEntireLocationReverseCases() {
        assertEquals("KHAN: (5,9),(5,8),(5,7),(5,6)", exampleWordSearch.outputWholeLocation("KHAN"));
        assertEquals("KIRK: (4,7),(3,7),(2,7),(1,7)", exampleWordSearch.outputWholeLocation("KIRK"));
        assertEquals("SULU: (3,3),(2,2),(1,1),(0,0)", exampleWordSearch.outputWholeLocation("SULU"));
        assertEquals("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)", exampleWordSearch.outputWholeLocation("UHURA"));
    }

    @Test
    public void whenAllWordsFoundCompleteOutput() {
        String expected = "BONES: (0,6),(0,7),(0,8),(0,9),(0,10)\n" +
                "KHAN: (5,9),(5,8),(5,7),(5,6)\n" +
                "KIRK: (4,7),(3,7),(2,7),(1,7)\n" +
                "SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)\n" +
                "SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)\n" +
                "SULU: (3,3),(2,2),(1,1),(0,0)\n" +
                "UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)";
        assertEquals(expected, exampleWordSearch.allWordsFoundOutput());
    }

    @Test
    public void whenAWordIsInTheListButNotTheGrid() {
        String expected = "BONES: (0,6),(0,7),(0,8),(0,9),(0,10)\n" +
                "KHAN: (5,9),(5,8),(5,7),(5,6)\n" +
                "KIRK: (4,7),(3,7),(2,7),(1,7)\n" +
                "SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)\n" +
                "SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)\n" +
                "SULU: (3,3),(2,2),(1,1),(0,0)\n" +
                "UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)\n" +
                "MISSING: NOT FOUND";
        assertEquals(expected, exampleNotFoundWordSearch.allWordsFoundOutput());
    }

    @Test
    public void outputFromBigFileStates() {
        WordSearch statesWordSearch = null;
        try {
            statesWordSearch = WordSearch.getInstanceFromFile("word-search-test-file-1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //String statesOutput = statesWordSearch.allWordsFoundOutput();
        //System.out.println(statesOutput);
    }

    @Test
    public void whenPositionPairsAreEqualTheyHaveSameHashcode() {
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                PositionPair a = new PositionPair(x,y);
                PositionPair b = new PositionPair(x,y);
                assertEquals(a.hashCode(), b.hashCode());
            }
        }
    }
}
