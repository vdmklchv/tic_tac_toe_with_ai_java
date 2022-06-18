package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    int getNumberOfFreeElements() {
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

    private String getCellContents(Coordinate coordinate) {
        return field[coordinate.getRow()][coordinate.getColumn()];
    }

    void setCellContents(Coordinate coordinate, String token) {
        field[coordinate.getRow()][coordinate.getColumn()] = token;
    }
    
    String[][] getField() {
        return this.field;
    }

    // CHECK IF BOARD HAS WINNING COMBINATION
    boolean isWinningCombination(String token) {
        // horizontal check
        if (token.equals(getCellContents(new Coordinate(0, 0))) && token.equals(getCellContents(new Coordinate(0, 1)))
                && token.equals(getCellContents(new Coordinate(0, 2)))) {
            return true;
        }
        if (token.equals(getCellContents(new Coordinate(1, 0)))
                && token.equals(getCellContents(new Coordinate(1, 1)))
                && token.equals(getCellContents(new Coordinate(1, 2)))) {
            return true;
        }
        if (token.equals(getCellContents(new Coordinate(2, 0)))
                && token.equals(getCellContents(new Coordinate(2, 1)))
                && token.equals(getCellContents(new Coordinate(2, 2)))) {
            return true;
        }
        if (token.equals(getCellContents(new Coordinate(0, 0)))
                && token.equals(getCellContents(new Coordinate(1, 0)))
                && token.equals(getCellContents(new Coordinate(2, 0)))) {
            return true;
        }
        if (token.equals(getCellContents(new Coordinate(0, 1)))
                && token.equals(getCellContents(new Coordinate(1, 1)))
                && token.equals(getCellContents(new Coordinate(2, 1)))) {
            return true;
        }
        if (token.equals(getCellContents(new Coordinate(0, 2)))
                && token.equals(getCellContents(new Coordinate(1, 2)))
                && token.equals(getCellContents(new Coordinate(2, 2)))) {
            return true;
        }
        if (token.equals(getCellContents(new Coordinate(0, 0)))
                && token.equals(getCellContents(new Coordinate(1, 1)))
                && token.equals(getCellContents(new Coordinate(2, 2)))) {
            return true;
        }
        return token.equals(getCellContents(new Coordinate(2, 0)))
                && token.equals(getCellContents(new Coordinate(1, 1)))
                && token.equals(getCellContents(new Coordinate(0, 2)));
    }

    // CHECK IF CELL OCCUPIED
    boolean isCellOccupied(Coordinate coordinates) {
        String cellContent = field[coordinates.getRow()][coordinates.getColumn()];
        return "O".equals(cellContent) || "X".equals(cellContent);
    }

    // METHOD TO PLACE TOKEN
    void placeToken(Coordinate coordinate, String token) {
        field[coordinate.getRow()][coordinate.getColumn()] = token;
    }

    // GET FREE CELLS COORDINATES
    List<Coordinate> getFreeCellCoordinates() {
        List<Coordinate> freeCellCoordinates = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if ("_".equals(field[i][j])) {
                    freeCellCoordinates.add(new Coordinate(i, j));
                }
            }
        }
        return freeCellCoordinates;
    }

    // METHOD TO CHECK POTENTIALLY WINNING COMBINATION FOR X OR O
    Coordinate getWinningCoordinateFor(String token) {
        for (int i = 0; i < field.length; i++) {
            int count = 0;
            Coordinate lastEmptyCoordinate = null;

            for (int j = 0; j < field.length; j++) {

                if (token.equals(field[i][j])) {
                    count++;
                } else {
                    lastEmptyCoordinate = new Coordinate(i, j);
                }
            }

            if (isCoordinateWinning(count, lastEmptyCoordinate)) {
                return lastEmptyCoordinate;
            }
        }

        for (int j = 0; j < field.length; j++) {
            int count = 0;
            Coordinate lastMissedCoordinate = null;
            for (int i = 0; i < field.length; i++) {
                if (token.equals(field[i][j])) {
                    count++;
                } else {
                    lastMissedCoordinate = new Coordinate(i, j);
                }
            }

            if (isCoordinateWinning(count, lastMissedCoordinate)) {
                return lastMissedCoordinate;
            }
        }

        int count = 0;
        Coordinate lastMissedCoordinate = null;

        for (int i = 0, j = 0; i < field.length; i++, j++) {
                if (token.equals(field[i][j])) {
                    count++;
                } else {
                    lastMissedCoordinate = new Coordinate(i, j);
                }


            if (isCoordinateWinning(count, lastMissedCoordinate)) {
                return lastMissedCoordinate;
            }
        }

        count = 0;
        lastMissedCoordinate = null;

        for (int i = 0, j = 2; j >= 0; i++, j--) {

                if (token.equals(field[i][j])) {
                    count++;
                } else {
                    lastMissedCoordinate = new Coordinate(i, j);
                }

            if (isCoordinateWinning(count, lastMissedCoordinate)) {
                return lastMissedCoordinate;
            }
        }
        return null;
    }

    private boolean isCoordinateWinning(int count, Coordinate coordinate) {
        return count == 2 && coordinate != null
                && !"O".equals(field[coordinate.getRow()][coordinate.getColumn()])
                && !"X".equals(field[coordinate.getRow()][coordinate.getColumn()]);
    }

    Coordinate getRandomCoordinatesFromFreeCells(List<Coordinate> freeCells) {
        // GET RANDOM COORDINATE FROM FREE CELL LIST
        int listSize = freeCells.size();
        Random random = new Random();
        int chosenInt = random.nextInt(listSize);

        return freeCells.get(chosenInt);
    }
}
