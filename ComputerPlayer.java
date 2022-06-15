package tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    App.GAME_LEVEL gameLevel;
    public ComputerPlayer(String token, App.GAME_LEVEL gameLevel) {
        super(token);
        this.gameLevel = gameLevel;
    }

    @Override
    int[] provideCoordinates(GameTable gameTable) {
        List<int[]> freeCellCoordinates = gameTable.getFreeCellCoordinates();
        if (this.gameLevel == App.GAME_LEVEL.EASY) {
            System.out.println("Making move level \"easy\"");

            int[] randomCoordinates = getRandomCoordinatesFromFreeCells(freeCellCoordinates);
            return randomCoordinates;
        } else if (this.gameLevel == App.GAME_LEVEL.MEDIUM) {
            // MEDIUM GAME LEVEL
            System.out.println("Making move level \"medium\"");

            int[] coordinates = gameTable.getWinningCoordinateFor(getToken());
            if (coordinates != null) {
                return coordinates;
            }

            String opponentToken = "X".equals(getToken()) ? "O" : "X";
            coordinates = gameTable.getWinningCoordinateFor(opponentToken);
            if (coordinates != null) {
                return coordinates;
            }

            return getRandomCoordinatesFromFreeCells(freeCellCoordinates);
        }
        return new int[] {-1, -1};
    }

    private int[] getRandomCoordinatesFromFreeCells(List<int[]> freeCells) {
        // GET RANDOM COORDINATE FROM FREE CELL LIST
        int listSize = freeCells.size();
        Random random = new Random();
        int chosenInt = random.nextInt(listSize);

        int[] coordinates = freeCells.get(chosenInt);
        return coordinates;
    }

}
