
/**
 * A very simple class that stores a Sudoku puzzle. The values in the puzzle are
 * added to the puzzle one at a time moving from left to right and top to
 * bottom.
 * 
 * <p>
 * A puzzle is allowed to have up to one missing value. A missing value is
 * indicated by a value of {@code 0}.
 * 
 * <p>
 * A puzzle value can be retrieved using a row and column index. The topmost
 * row has a row index of {@code 0} and the leftmost column has a column
 * index of {@code 0}.
 * 
 * <p>
 * Example usage to fill in the puzzle values:
 * 
 * <pre>{@code 
 * Puzzle p = new Puzzle();
 * for (int row = 0; row < Puzzle.SIZE; row++) {
 *     for (int col = 0; col < Puzzle.SIZE; col++) {
 *         // read in the puzzle value here as val
 *         p.add(val);
 *     }
 * }
 * }</pre>
 *
 */
public class Puzzle {

	private int[] values;
	private int curr;
	private boolean hasMissing;
	private int missingIndex;

	/**
	 * The number of rows in the puzzle. Also equal to the number of columns in the
	 * puzzle.
	 */
	public static final int SIZE = 9;

	/**
	 * The smallest allowable puzzle value. {@code MIN_VALUE} is used to indicate a
	 * missing value. Normal puzzle values start at 1.
	 */
	public static final int MIN_VALUE = 0;

	/**
	 * The largest allowable puzzle value.
	 */
	public static final int MAX_VALUE = 9;

	/**
	 * Initializes an empty and incomplete puzzle (all values equal to
	 * {@code MIN_VALUE}).
	 */
	public Puzzle() {
		this.values = new int[Puzzle.SIZE * Puzzle.SIZE];
		this.curr = 0;
		this.hasMissing = false;
		this.missingIndex = -1;
	}

	/**
	 * Returns {@code true} if {@code Puzzle.SIZE * Puzzle.SIZE} values have been
	 * added to the puzzle.
	 * 
	 * @return true if Puzzle.SIZE * Puzzle.SIZE values have been added to the
	 *         puzzle
	 */
	public boolean isComplete() {
		return this.curr == Puzzle.SIZE * Puzzle.SIZE;
	}

	/**
	 * Returns {@code true} if the puzzle is complete and missing a single value.
	 * 
	 * @return true if the puzzle is complete and missing a single value
	 */
	public boolean hasMissingValue() {
		return this.isComplete() && this.hasMissing;
	}
	
	/**
	 * Throws an exception if the specified value is out of range.
	 * 
	 * @param value a value to check
	 * @throws IllegalArgumentException if value is less than MIN_VALUE or greater
	 *                                  than MAX_VALUE
	 */
	private static void checkValue(int value) {
		if (value < MIN_VALUE) {
			throw new IllegalArgumentException(value + " : is less than " + MIN_VALUE);
		}
		if (value > MAX_VALUE) {
			throw new IllegalArgumentException(value + " : is greater than " + MAX_VALUE);
		}
	}

	/**
	 * Adds a value to the puzzle.
	 * 
	 * @param value the value to add to the puzzle
	 * @throws IllegalArgumentException if value is less than MIN_VALUE or greater
	 *                                  than MAX_VALUE or if the puzzle is already
	 *                                  complete or if more than one missing value
	 *                                  is added to the puzzle
	 */
	public void addValue(int value) {
		Puzzle.checkValue(value);
		if (curr >= this.values.length) {
			throw new IllegalArgumentException("puzzle already has all of its values");
		}
		if (value == 0) {
			if (this.hasMissingValue()) {
				throw new IllegalArgumentException("puzzle can have only one missing value");
			}
			this.hasMissing = true;
			this.missingIndex = this.curr;
		}
		this.values[this.curr] = value;
		this.curr++;
	}

	/**
	 * Returns the puzzle value at the specified row index and the specified column
	 * index.
	 * 
	 * @param row the row index
	 * @param col the column index
	 * @return the puzzle value at the specified row index and the specified column
	 *         index
	 * @throws IndexOutOfBoundsException if either index is less than 0 or greater
	 *                                   than or equal to SIZE
	 */
	public int get(int row, int col) {
		if (row < 0 || row > Puzzle.SIZE - 1) {
			throw new IndexOutOfBoundsException(row + " : row index out of range");
		}
		if (col < 0 || col > Puzzle.SIZE - 1) {
			throw new IndexOutOfBoundsException(col + " : col index out of range");
		}
		return this.values[row * Puzzle.SIZE + col];
	}
	
	/**
	 * Sets the missing puzzle value to the specified value.
	 * 
	 * @param value the missing puzzle value
	 * @throws IllegalArgumentException if value is less than MIN_VALUE or greater
	 *                                  than MAX_VALUE
	 * @throws RuntimeException if the puzzle has no missing value
	 */
	public void setMissingTo(int value) {
		Puzzle.checkValue(value);
		if (!this.hasMissingValue()) {
			throw new RuntimeException("puzzle has no missing value");
		}
		this.values[this.missingIndex] = value;
	}
}
