package edu.nyu.cs.assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuValidator {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String filepath = getFilepathFromUser();
        int[][] puzzle = getContentsOfFile(filepath);
        printPuzzle(puzzle);

        if (validatePuzzle(puzzle)) {
            System.out.println("Puzzle is valid.");
        } else {
            System.out.println("Puzzle is invalid, exiting.");
            return;
        }

        while (!wonPuzzle(puzzle)) {
            printRemainingMoves(puzzle);
            if (remainingMoves(puzzle).size() == 0) break;

            System.out.println("What is your next move?");
            String line = scn.nextLine();
            if (line.equals("quit")) break;
            else {
                String[] tokens = line.split(" ");
                try {
                    int row = Integer.parseInt(tokens[0]);
                    int col = Integer.parseInt(tokens[1]);
                    int value = Integer.parseInt(tokens[2]);
                    if (!makeMove(puzzle, row, col, value)) {
                        System.out.println("Try again!");
                    }
                } catch (Exception e) {
                    System.out.println("Did not understand command");
                }
            }
            printPuzzle(puzzle);
        }

        if (wonPuzzle(puzzle)) {
            System.out.println("Congratulations!");
        } else {
            System.out.println("Condolences!");
        }
    }

    public static boolean wonPuzzle(int[][] puzzle) {
        return remainingMoves(puzzle).size() == 0 && validatePuzzle(puzzle);
    }

    public static String getFilepathFromUser() {
        System.out.println("What file would you like to open?");
        return scn.nextLine();
    }

    public static void printRemainingMoves(int[][] puzzle) {
        ArrayList<int[]> moves = remainingMoves(puzzle);
        if (moves.isEmpty()) {
            System.out.println("No moves left!");
        } else {
            System.out.println("Remaining moves:");
            for (int[] move : moves) {
                System.out.print("(" + move[0] + "," + move[1] + ") ");
            }
            System.out.println();
        }
    }

    public static ArrayList<int[]> remainingMoves(int[][] puzzle) {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    moves.add(new int[]{row, col});
                }
            }
        }
        return moves;
    }

    public static int[][] getContentsOfFile(String filepath) {
        int[][] puzzle = new int[9][9];
        try (Scanner fileScanner = new Scanner(new File(filepath))) {
            for (int row = 0; row < 9; row++) {
                String[] numbers = fileScanner.nextLine().split(",");
                for (int col = 0; col < 9; col++) {
                    puzzle[row][col] = Integer.parseInt(numbers[col]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oh no... can't find the file!");
        }
        return puzzle;
    }

    public static void printPuzzle(int[][] puzzle) {
        char[][] board = {
            "╔═══════════════════════════════════╗".toCharArray(),
            "║ X │ X │ X ║ X │ X │ X ║ X │ X │ X ║".toCharArray(),
            "║───┼───┼───║───┼───┼───║───┼───┼───║".toCharArray(),
            "║ X │ X │ X ║ X │ X │ X ║ X │ X │ X ║".toCharArray(),
            "║───┼───┼───║───┼───┼───║───┼───┼───║".toCharArray(),
            "║ X │ X │ X ║ X │ X │ X ║ X │ X │ X ║".toCharArray(),
            "║═══════════╬═══════════╬═══════════║".toCharArray(),
            "║ X │ X │ X ║ X │ X │ X ║ X │ X │ X ║".toCharArray(),
            "║───┼───┼───║───┼───┼───║───┼───┼───║".toCharArray(),
            "║ X │ X │ X ║ X │ X │ X ║ X │ X │ X ║".toCharArray(),
            "║───┼───┼───║───┼───┼───║───┼───┼───║".toCharArray(),
            "║ X │ X │ X ║ X │ X │ X ║ X │ X │ X ║".toCharArray(),
            "╚═══════════════════════════════════╝".toCharArray()
        };
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int val = puzzle[row][col];
                if (val != 0) {
                    board[row * 2 + 1][col * 4 + 2] = (char) (val + '0');
                }
            }
        }
        for (char[] row : board) {
            System.out.println(row);
        }
    }

    public static boolean makeMove(int[][] puzzle, int row, int col, int value) {
        // Check for out-of-bounds row, column, or invalid value
        if (row < 0 || row >= puzzle.length || col < 0 || col >= puzzle[0].length || value < 1 || value > 9) {
            return false;  // Added this line to check for out-of-bounds or invalid inputs
        }
    
        // Check if the move is allowed by Sudoku rules
        if (puzzle[row][col] == 0) {
            puzzle[row][col] = value;  // Place the value in the puzzle
            return true;
        }
        
        return false;
    }

    public static boolean validateCountData(int[] counts) {
        for (int count : counts) {
            if (count > 1) return false;
        }
        return true;
    }

    public static boolean validateRow(int[][] puzzle, int row) {
        int[] counts = new int[10];
        for (int col = 0; col < 9; col++) {
            int num = puzzle[row][col];
            if (num != 0) counts[num]++;
        }
        return validateCountData(counts);
    }

    public static boolean validateColumn(int[][] puzzle, int col) {
        int[] counts = new int[10];
        for (int row = 0; row < 9; row++) {
            int num = puzzle[row][col];
            if (num != 0) counts[num]++;
        }
        return validateCountData(counts);
    }

    public static boolean validateBlock(int[][] puzzle, int row, int col) {
        // Ensure the starting row and column are within the valid range for a 3x3 block
        if (row < 0 || row > 6 || col < 0 || col > 6) {
            return false;  // Invalid starting position for a 3x3 block (should be between 0-6 for both row and col)
        }
    
        boolean[] seen = new boolean[9];  // To track which numbers (1-9) have been seen in the block
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                int num = puzzle[i][j];
                if (num != 0) {  // Only validate non-zero numbers
                    if (num < 1 || num > 9 || seen[num - 1]) {
                        return false;  // Invalid number (either out of range or duplicate)
                    }
                    seen[num - 1] = true;  // Mark the number as seen
                }
            }
        }
        return true;  // Block is valid if no duplicates or invalid numbers were found
    }
    

    public static boolean validateRows(int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            if (!validateRow(puzzle, row)) return false;
        }
        return true;
    }

    public static boolean validateColumns(int[][] puzzle) {
        for (int col = 0; col < 9; col++) {
            if (!validateColumn(puzzle, col)) return false;
        }
        return true;
    }

    public static boolean validateBlocks(int[][] puzzle) {
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!validateBlock(puzzle, row, col)) return false;
            }
        }
        return true;
    }

    public static boolean validatePuzzle(int[][] puzzle) {
        return validateRows(puzzle) && validateColumns(puzzle) && validateBlocks(puzzle);
    }
}
