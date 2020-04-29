package bensmith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The WordSearch class represents a word search puzzle, and contains code to output
 * a solution.
 *
 * <P>Constructors are private, and instances are created through a factory method
 * given a file name.  The reportUniversal method can be used to further refactor
 * and remove the use of reversing the words entirely.
 *
 * @author Benjamin Smith	bensmith@iastate.edu
 * @see <a href="http://orcid.org/0000-0003-2607-9338">My ORCID is: 0000-0003-2607-9338</a>
 *
 */
public class WordSearch {

    private final ArrayList<String> myWordList;
    private final String [][] myGrid;

    private WordSearch() {
        myWordList = new ArrayList<>();
        myGrid = new String[0][0];
    }

    private WordSearch(String [] myWords) {
        myWordList = new ArrayList<>();
        for (String s : myWords) {
            myWordList.add(s);
        }
        myGrid = new String[0][0];
    }

    private WordSearch(String [] myWords, String [][] aGrid) {
        myWordList = new ArrayList<>();
        for (String s : myWords) {
            myWordList.add(s);
        }
        myGrid = aGrid;
    }

    public static WordSearch getInstanceFromFile(String s) throws FileNotFoundException {
        File file = new File(s);
        Scanner sc = new Scanner(file);

		// the first line, if present, should contain a list of comma-separated words
        if (!sc.hasNextLine()) {
            return new WordSearch();
        }

		// the next line starts the puzzle grid, and also indicates the size of the square grid
        String [] wordLine = sc.nextLine().split(",");
        if (!sc.hasNextLine()) {
            return new WordSearch(wordLine);
        }

        String [] gridLine = sc.nextLine().split(",");
        int lineLength = gridLine.length;
        String [][] aGrid = new String [lineLength][lineLength];

		// read the rest of the grid lines
        for (int line = 0; line < lineLength; line++) {
            for (int element = 0; element < lineLength; element++) {
                aGrid[line][element] = gridLine[element];
            }
            if (sc.hasNextLine()) gridLine = sc.nextLine().split(",");
        }

        return new WordSearch(wordLine, aGrid);
	}
	
	public ArrayList<String> getWordList() {
        return myWordList;
	}
	
	public String[][] getGrid() {
        return myGrid;
    }

    public String getGridElement(int x, int y) {
        return myGrid[y][x];
    }

    public static String getLetter(int i, String word) {
        return word.substring(i, i + 1);
    }

    public static String reverseWord(String word) {
        String result = "";
        for (int index = word.length() - 1; index >= 0; index-- ) {
            result += getLetter(index, word);
        }
        return result;
    }
	
	

    public boolean findHorizontal(String word) {
		return reportHorizontal(word) != null;
    }

    public boolean findHorizontalReverse(String word) {
        return findHorizontal(reverseWord(word));
    }

    public boolean findHorizontalInRow(int row, String word) {
        if (!wordCanFit(word)) return false;
        int tooFar = getDimension() - word.length() + 1;
        for (int start = 0; start < tooFar; start++) {
            boolean soFar = true;
            for (int current = 0; current < word.length(); current++) {
                String letter = getLetter(current, word);
                if (!letter.equals(getGridElement(start + current, row))) {
                    // not a match here
                    soFar = false;
                    break;
                }
            }
            if (soFar) return true;
        }

        return false;
    }

    public int getDimension() {
        return myGrid.length;
    }

    public boolean wordCanFit(String word) {
        return (word.length() <= getDimension());
    }

    public boolean findVerticalInCol(int col, String word) {
        if (!wordCanFit(word)) return false;
        int tooFar = getDimension() - word.length() + 1;
        for (int start = 0; start < tooFar; start++) {
            boolean soFar = true;
            for (int current = 0; current < word.length(); current++) {
                String letter = getLetter(current, word);
                if (!letter.equals(getGridElement(col,start + current))) {
                    // not a match here
                    soFar = false;
                    break;
                }
            }
            if (soFar) return true;
        }

        return false;
    }

    public boolean findVertical(String word) {
		return reportVertical(word) != null;
    }


    public boolean findDiagonalDescending(String word) {
		return reportDiagonalDescending(word) != null;
    }

    public boolean findDiagonalAscending(String word) {
		return reportDiagonalAscending(word) != null;
    }

    public boolean foundAllWords() {
        for (String word : getWordList()) {
            if (findHorizontal(word)) continue;
            if (findVertical(word)) continue;
            if (findDiagonalDescending(word)) continue;
            if (findDiagonalAscending(word)) continue;
            String reverse = reverseWord(word);
            if (findHorizontal(reverse)) continue;
            if (findVertical(reverse)) continue;
            if (findDiagonalDescending(reverse)) continue;
            if (findDiagonalAscending(reverse)) continue;

            // word not found (nor its reverse)
            return false;
        }
        return true;
    }

    
	
	private PositionPair reportUniversal(String word, int xIncrement, int yIncrement) {
		for (int startX = 0; startX < getDimension(); startX++) {
            for (int startY = 0; startY < getDimension(); startY++) {
                boolean soFar = true;
                int currentX = startX;
                int currentY = startY;
                int wordCount = 0;
                do {
                    String letter = getLetter(wordCount, word);
                    if (!letter.equals(getGridElement(currentX, currentY))) {
                        soFar = false;
                        break;
                    }
                    wordCount++;
                    currentX += xIncrement;
                    currentY += yIncrement;
                } while (insidePuzzle(currentX, currentY) && (wordCount < word.length()));
                if ((wordCount == word.length()) && soFar) {
                    return new PositionPair(startX, startY);
                }
            }
        }

        return null;
	}

	public PositionPair reportHorizontal(String word) {
		return reportUniversal(word, 1, 0);
    }

    public PositionPair reportVertical(String word) {
		return reportUniversal(word, 0, 1);
	}

    public PositionPair reportDiagonalDescending(String word) {
		return reportDiagonal(word, 1);
    }

    public PositionPair reportDiagonalAscending(String word) {
		return reportDiagonal(word, -1);
	}

	private PositionPair reportDiagonal(String word, int yIncrement) {
		return reportUniversal(word, 1, yIncrement);
	}
	
	private boolean insidePuzzle(int x, int y) {
		return ((x >= 0) && (y >= 0) && (x < getDimension()) && (y < getDimension()));
	}



    public String outputStart(String word) {
        String result = word + ": ";
        if (findHorizontal(word)) return result + reportHorizontal(word).toString();
        if (findVertical(word)) return result + reportVertical(word).toString();
        if (findDiagonalDescending(word)) return result + reportDiagonalDescending(word).toString();
        if (findDiagonalAscending(word)) return result + reportDiagonalAscending(word).toString();

        String wordR = reverseWord(word);

        if (findHorizontal(wordR)) return result + reportHorizontal(wordR).toString();
        if (findVertical(wordR)) return result + reportVertical(wordR).toString();
        if (findDiagonalDescending(wordR)) return result + reportDiagonalDescending(wordR).toString();
        if (findDiagonalAscending(wordR)) return result + reportDiagonalAscending(wordR).toString();

        return result;
    }

    public String outputWholeLocation(String word) {
        String wordR = reverseWord(word);
        String result = word + ": ";
        ArrayList<PositionPair> posList = null;
        if (findHorizontal(word)) {
            PositionPair start = reportHorizontal(word);
            posList = start.getWholeListHorizontal(word.length());
        } else if (findVertical(word)) {
            PositionPair start = reportVertical(word);
            posList = start.getWholeListVertical(word.length());
        } else if (findDiagonalDescending(word)) {
            PositionPair start = reportDiagonalDescending(word);
            posList = start.getWholeListDiagonalDescending(word.length());
        } else if (findDiagonalAscending(word)) {
            PositionPair start = reportDiagonalAscending(word);
            posList = start.getWholeListDiagonalAscending(word.length());
        } else if (findHorizontal(wordR)) {
            PositionPair start = reportHorizontal(wordR);
            posList = start.getWholeListHorizontal(word.length());
            Collections.reverse(posList);
        } else if (findVertical(wordR)) {
            PositionPair start = reportVertical(wordR);
            posList = start.getWholeListVertical(word.length());
            Collections.reverse(posList);
        } else if (findDiagonalDescending(wordR)) {
            PositionPair start = reportDiagonalDescending(wordR);
            posList = start.getWholeListDiagonalDescending(word.length());
            Collections.reverse(posList);
        } else if (findDiagonalAscending(wordR)) {
            PositionPair start = reportDiagonalAscending(wordR);
            posList = start.getWholeListDiagonalAscending(word.length());
            Collections.reverse(posList);
        }

        if (posList != null) {
            String delim = "";
            for (PositionPair pp : posList) {
                result += delim + pp.toString();
                delim = ",";
            }
        } else {
            result += "NOT FOUND";
        }
        return result;
    }

    public String allWordsFoundOutput() {
        String result = "";
        String delim = "";
        for (String word : this.getWordList()) {
            result += delim + outputWholeLocation(word);
            delim = "\n";
        }
        return result;
    }
}
