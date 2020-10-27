
import java.io.InputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleSudoku {

	/**
	 * Returns a list of length 9 containing the digits from the specified row in
	 * the specified puzzle.
	 * 
	 * @param n      the row index
	 * @param puzzle a Sudoku puzzle
	 * @return a list of length 9 containing the digits from the specified row in
	 *         the specified puzzle
	 */
	public static List<Integer> getRow(int n, Puzzle puzzle) {
		List<Integer> row = new ArrayList<>();
		for (int i = 0; i < Puzzle.SIZE; i++) {
			row.add(puzzle.get(n, i));
		}
		return row;
	}

	/**
	 * 
	 * @param c      the column index.
	 * @param puzzle
	 * @return a list of length 9 containing all digits from the specified column
	 */
	public static List<Integer> getColumn(int c, Puzzle puzzle) {
		List<Integer> column = new ArrayList<>();
		for (int i = 0; i < Puzzle.SIZE; i++) {
			column.add(puzzle.get(i, c));
		}
		return column;
	}

	/**
	 * 
	 * @param b      the block number 0-8
	 * @param puzzle
	 * @return a list of length 9 containing all digits from the specified block.
	 */
	public static List<Integer> getBlock(int b, Puzzle puzzle) {
		List<Integer> block = new ArrayList<>();

		if (b == 0) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 1) {
			for (int i = 0; i < 3; i++) {
				for (int j = 3; j < 6; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 2) {
			for (int i = 0; i < 3; i++) {
				for (int j = 6; j < Puzzle.SIZE; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 3) {
			for (int i = 3; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 4) {
			for (int i = 3; i < 6; i++) {
				for (int j = 3; j < 6; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 5) {
			for (int i = 3; i < 6; i++) {
				for (int j = 6; j < Puzzle.SIZE; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 6) {
			for (int i = 6; i < Puzzle.SIZE; i++) {
				for (int j = 0; j < 3; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 7) {
			for (int i = 6; i < Puzzle.SIZE; i++) {
				for (int j = 3; j < 6; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}
		if (b == 8) {
			for (int i = 6; i < Puzzle.SIZE; i++) {
				for (int j = 6; j < Puzzle.SIZE; j++) {
					block.add(puzzle.get(i, j));
				}
			}
		}

		return block;
	}

	/**
	 * Reads a puzzle from the file with specified file name.
	 * 
	 * @param fileName the puzzle file name
	 * @return a Puzzle object reference containing the digits of the puzzle
	 */
	public static Puzzle readPuzzle(String fileName) {
		Puzzle p = new Puzzle();
		InputStream stream = SimpleSudoku.class.getResourceAsStream(fileName);

		/* make a Scanner object here and read in the puzzle one int at a time */
		Scanner scan = new Scanner(stream);
		while (scan.hasNextInt()) {
			int digit = scan.nextInt();
			p.addValue(digit);
		}
		scan.close();
		return p;
	}

	/**
	 * Prints out the puzzle following the given format.
	 * 
	 * @param p
	 */

	public static void print(Puzzle p) {
		for (int i = 0; i < Puzzle.SIZE; i++) {
			if (i == 3 || i == 6) {
				System.out.println("----------------------");
			}
			for (int j = 0; j < Puzzle.SIZE; j++) {
				if (j == 3 || j == 6) {
					System.out.print(" |");
				}
				System.out.print(" " + p.get(i, j));
				if (j == 8) {
					System.out.println();
				}
			}
		}
	}

	/**
	 * Checks if a list has contains all 1 through 9.
	 * 
	 * @param list
	 * @return a list contains any missing numbers or empty list if there are none.
	 */
	public static List<Integer> testList(List<Integer> list) {
		List<Integer> checkList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		for (int i = 0; i < list.size(); i++) {
			if (checkList.contains(list.get(i))) {
				checkList.remove(Integer.valueOf(list.get(i)));
			}
		}
		return checkList;
	}

	/**
	 * Test if any rows in the puzzle miss any numbers 1-9
	 * 
	 * @param p
	 * @return a list contains indexes of rows that miss any numbers from 1-9 if
	 *         applicable.
	 */
	public static List<Integer> testRow(Puzzle p) {
		List<Integer> missingIndex = new ArrayList<Integer>();
		for (int i = 0; i < Puzzle.SIZE; i++) {
			if (!testList(getRow(i, p)).isEmpty()) {
				missingIndex.add(i);
			}
		}
		return missingIndex;
	}

	/**
	 * Test if any columns in the puzzle miss any numbers 1-9
	 * 
	 * @param p
	 * @return a list contains indexes of columns that miss any numbers from 1-9 if
	 *         applicable.
	 */
	public static List<Integer> testColumn(Puzzle p) {
		List<Integer> missingIndex = new ArrayList<Integer>();
		for (int i = 0; i < Puzzle.SIZE; i++) {
			if (!testList(getColumn(i, p)).isEmpty()) {
				missingIndex.add(i);
			}
		}
		return missingIndex;
	}

	/**
	 * Test if any blocks in the puzzle miss any numbers 1-9
	 * 
	 * @param p
	 * @return a list contains indexes of blocks that miss any numbers from 1-9 if
	 *         applicable.
	 */
	public static List<Integer> testBlock(Puzzle p) {
		List<Integer> missingIndex = new ArrayList<Integer>();
		for (int i = 0; i < Puzzle.SIZE; i++) {
			if (!testList(getBlock(i, p)).isEmpty()) {
				missingIndex.add(i);
			}
		}
		return missingIndex;
	}

	/**
	 * Fix 1 missing value by first finding out which row is missing a value, next
	 * find out which value is missing in that row and then set the missing value.
	 * 
	 * @param p
	 */
	public static void fixMiss(Puzzle p) {
		if (!testRow(p).isEmpty()) {
			int value;
			for (int i = 0; i < Puzzle.SIZE; i++) {
				if (!testList(getRow(i, p)).isEmpty()) {
					value = (int) testList(getRow(i, p)).get(0);
					p.setMissingTo(value);
				}
			}
		}
	}

	public static void main(String[] args) {
		Puzzle puzzle;
		if (args.length > 0) {
			puzzle = readPuzzle(args[0]);
		} else {
			puzzle = readPuzzle("error03.txt"); // change the filename here to read different puzzles
		}

		/* the rest of the main method should start below this line */

		if (puzzle.hasMissingValue()) {
			fixMiss(puzzle);
			System.out.println("Complete puzzle is:");
			print(puzzle);
		} else if (!puzzle.hasMissingValue()) {
			if (testRow(puzzle).isEmpty() && testColumn(puzzle).isEmpty() && testBlock(puzzle).isEmpty()) {
				System.out.println("The puzzle is a valid solution");
			}
			if (!testRow(puzzle).isEmpty()) {
				System.out.println("There are errors in rows: " + testRow(puzzle));
			}
			if (!testColumn(puzzle).isEmpty()) {
				System.out.println("There are errors in columns: " + testColumn(puzzle));
			}
			if (!testBlock(puzzle).isEmpty()) {
				System.out.println("There are errors in blocks: " + testBlock(puzzle));
			}
			print(puzzle);
		}
	}
}
