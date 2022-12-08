public class SudokuSolver {
  // Define a two-dimensional array to represent the Sudoku puzzle
  private int[][] puzzle = new int[9][9];

  // Define a method to set the initial puzzle
  public void setPuzzle(int[][] p) {
    puzzle = p;
  }

  // Define a method to solve the puzzle
  public void solve() {
    // Loop through each row and column
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        // If the current cell is empty (0), try to solve it
        if (puzzle[row][col] == 0) {
          // Try each possible value (1-9) for the current cell
          for (int value = 1; value <= 9; value++) {
            // Check if the value is valid for the current cell
            if (isValid(row, col, value)) {
              // If the value is valid, set the cell to that value
              puzzle[row][col] = value;

              // Try to solve the rest of the puzzle
              solve();

              // If the puzzle cannot be solved, backtrack and try the next value
              puzzle[row][col] = 0;
            }
          }

          // If no value is valid for the current cell, return to the previous cell
          return;
        }
      }
    }

    // If the puzzle is solved, print it
    printPuzzle();
  }

  // Define a method to check if a value is valid for a given cell
  private boolean isValid(int row, int col, int value) {
    // Check if the value is already present in the row, column, or 3x3 block
    return !isInRow(row, value) && !isInCol(col, value) && !isInBlock(row, col, value);
  }

  // Define a method to check if a value is already present in a given row
  private boolean isInRow(int row, int value) {
    // Loop through each cell in the row
    for (int col = 0; col < 9; col++) {
      // If the value is found, return true
      if (puzzle[row][col] == value) {
        return true;
      }
    }

    // If the value is not found, return false
    return false;
  }

  // Define a method to check if a value is already present in a given column
  private boolean isInCol(int col, int value) {
    // Loop through each cell in the column
    for (int row = 0; row < 9; row++) {
      // If the value is found, return true
      if (puzzle[row][col] == value) {
        return true;
      }
    }

    // If the value is not found, return false
    return false;
  }

  // Define a method to check if a value is already present in the 3x3 block containing a given cell
  private boolean isInBlock(int row, int col, int value) {
    // Calculate the starting row and column of the 3x3 block
    int startRow = row - row % 3;
