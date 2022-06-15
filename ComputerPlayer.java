package tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String token) {
        super(token);
    }

    @Override
    int[] provideCoordinates(GameTable gameTable) {
        List<int[]> freeCellCoordinates = gameTable.getFreeCellCoordinates();
        if (App.gameLevel == App.GAME_LEVEL.EASY) {
            System.out.println("Making move level \"easy\"");

            int[] randomCoordinates = getRandomCoordinatesFromFreeCells(freeCellCoordinates);
            return randomCoordinates;
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
