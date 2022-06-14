package tictactoe;

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

            // GET RANDOM COORDINATE FROM FREE CELL LIST
            int listSize = freeCellCoordinates.size();
            Random random = new Random();
            int chosenInt = random.nextInt(listSize);

            // REPEAT IF IT IS A WINNING COMBINATION
            // Check if combination is winning
            int[] coordinates = freeCellCoordinates.get(chosenInt);
            gameTable.placeToken(coordinates, this.getToken());

            if (!gameTable.isWinningCombination(this.getToken())) {
                return freeCellCoordinates.get(chosenInt);
            } else {
                gameTable.placeToken(coordinates, "_");
                provideCoordinates(gameTable);
            }
        }
        return new int[] {-1, -1};
    }
}
