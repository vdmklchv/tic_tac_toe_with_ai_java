package tictactoe;

import java.util.List;
import tictactoe.Enums.*;

public class ComputerPlayer extends HumanPlayer implements Player {

    GAME_LEVEL gameLevel;
    public ComputerPlayer(String token, GAME_LEVEL gameLevel) {
        super(token);
        this.gameLevel = gameLevel;
    }

    @Override
    public int[] provideCoordinates(GameTable gameTable) {
        List<int[]> freeCellCoordinates = gameTable.getFreeCellCoordinates();
        if (this.gameLevel == GAME_LEVEL.EASY) {
            System.out.println("Making move level \"easy\"");

            return gameTable.getRandomCoordinatesFromFreeCells(freeCellCoordinates);
        } else if (this.gameLevel == GAME_LEVEL.MEDIUM) {
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

            return gameTable.getRandomCoordinatesFromFreeCells(freeCellCoordinates);
        }
        return new int[] {-1, -1};
    }

}
