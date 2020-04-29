package bensmith;

import java.util.ArrayList;
import java.util.Objects;

/**
 * PositionPair is a class that stores a pair of integers, with helper methods
 * to output in a given format (x,y).  Also returns a list of these pairs in four
 * directions.
 *
 * <P>The constructor creates the pair, and these objects are treated as immutable.
 * Equals and hashCode are implemented for algorithm improvements in the future.
 *
 * @author Benjamin Smith	bensmith@iastate.edu
 * @see <a href="http://orcid.org/0000-0003-2607-9338">My ORCID is: 0000-0003-2607-9338</a>
 *
 */
public class PositionPair {
    private final int myX;
    private final int myY;

    public PositionPair(int x, int y) {
        myX = x;
        myY = y;
    }
	
	private ArrayList<PositionPair> getWholeList(int length, int xMult, int yMult) {
		ArrayList<PositionPair> result = new ArrayList<>();
        for (int pos = 0; pos < length; pos++) {
            result.add(new PositionPair(myX + (pos * xMult), myY + (pos * yMult)));
        }
        return result;
	}

	public ArrayList<PositionPair> getWholeListHorizontal(int length) {
        return getWholeList(length, 1, 0);
    }

    public ArrayList<PositionPair> getWholeListVertical(int length) {
        return getWholeList(length, 0, 1);
    }

    public ArrayList<PositionPair> getWholeListDiagonalDescending(int length) {
        return getWholeList(length, 1, 1);
    }

    public ArrayList<PositionPair> getWholeListDiagonalAscending(int length) {
        return getWholeList(length, 1, -1);
	}

    @Override
    public String toString() {
        return "(" + myX + "," + myY + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionPair that = (PositionPair) o;
        return myX == that.myX &&
                myY == that.myY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myX, myY);
    }
}
