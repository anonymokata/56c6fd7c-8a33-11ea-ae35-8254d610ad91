package bensmith;

import java.io.FileNotFoundException;

/**
 * Application for outputting the solution to a word search puzzle.
 * 
 * <P>One argument required, the name of the puzzle file.
 *
 * @author Benjamin Smith	bensmith@iastate.edu
 * @see <a href="http://orcid.org/0000-0003-2607-9338">My ORCID is: 0000-0003-2607-9338</a>
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		if (args.length != 1) {
			System.out.println("No word search file argument given.");
			System.exit(0);
		}
		String filename = args[0];
		WordSearch wordSearch = null;
		try {
			wordSearch = WordSearch.getInstanceFromFile(filename);
		} catch (FileNotFoundException e) {
			System.out.println("File " + filename + " not found.");
			System.exit(1);
		}
		String output = wordSearch.allWordsFoundOutput();
		System.out.println(output);
    }
}
