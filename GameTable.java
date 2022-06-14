package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameTable {
    final int TABLE_SIZE = 3;
    private final String[][] field;

    public GameTable() {
        this.field = createEmptyField();
    }

    // CREATE EMPTY FIELD
    private String[][] createEmptyField() {
        String[][] field = new String[TABLE_SIZE][TABLE_SIZE];
        for (String[] row: field) {
            Arrays.fill(row, "_");
        }
        return field;
    }

    // GET NUMBER OF ELEMENTS ON BOARD
    int getNumberOfElements() {
        int counter = 0;
        for (String[] row: field) {
            for (String element: row) {
                if ("_".equals(element)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    // CHECK IF BOARD HAS WINNING COMBINATION
    boolean isWinningCombination(String token) {
        // horizontal check
        if (token.equals(field[0][0]) && token.equals(field[0][1]) && token.equals(field[0][2])) {
            return true;
        }
        if (token.equals(field[1][0]) && token.equals(field[1][1]) && token.equals(field[1][2])) {
            return true;
        }
        if (token.equals(field[2][0]) && token.equals(field[2][1]) && token.equals(field[2][2])) {
            return true;
        }
        if (token.equals(field[0][0]) && token.equals(field[1][0]) && token.equals(field[2][0])) {
            return true;
        }
        if (token.equals(field[0][1]) && token.equals(field[1][1]) && token.equals(field[2][1])) {
            return true;
        }
        if (token.equals(field[0][2]) && token.equals(field[1][2]) && token.equals(field[2][2])) {
            return true;
        }
        if (token.equals(field[0][0]) && token.equals(field[1][1]) && token.equals(field[2][2])) {
            return true;
        }
        return token.equals(field[2][0]) && token.equals(field[1][1]) && token.equals(field[0][2]);
    }

    // CHECK IF CELL OCCUPIED
    boolean isCellOccupied(int[] coordinates) {
        String cellContent = field[coordinates[0]][coordinates[1]];
        return "O".equals(cellContent) || "X".equals(cellContent);
    }

    // METHOD TO PLACE TOKEN
    void placeToken(int[] coordinates, String token) {
        field[coordinates[0]][coordinates[1]] = token;
    }

    // TODO: GET FREE CELLS COORDINATES
    List<int[]> getFreeCellCoordinates() {
        List<int[]> freeCellCoordinates = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if ("_".equals(field[i][j])) {
                    freeCellCoordinates.add(new int[] {i, j});
                }
            }
        }
        return freeCellCoordinates;
    }

    void printField() {
        System.out.println("---------");
        for (String[] row: field) {
            System.out.print("| ");
            System.out.print(String.join(" ", row));
            System.out.print(" |\n");
        }
        System.out.println("---------");
    }
}
