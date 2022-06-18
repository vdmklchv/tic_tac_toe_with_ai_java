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
    public Coordinate provideCoordinates(GameTable gameTable) {
        List<Coordinate> freeCellCoordinates = gameTable.getFreeCellCoordinates();
        if (this.gameLevel == GAME_LEVEL.EASY) {
            System.out.println("Making move level \"easy\"");

            return gameTable.getRandomCoordinatesFromFreeCells(freeCellCoordinates);
        } else if (this.gameLevel == GAME_LEVEL.MEDIUM) {
            // MEDIUM GAME LEVEL
            System.out.println("Making move level \"medium\"");

            Coordinate coordinate = gameTable.getWinningCoordinateFor(getToken());
            if (coordinate != null) {
                return coordinate;
            }

            String opponentToken = "X".equals(getToken()) ? "O" : "X";
            coordinate = gameTable.getWinningCoordinateFor(opponentToken);
            if (coordinate != null) {
                return coordinate;
            }

            return gameTable.getRandomCoordinatesFromFreeCells(freeCellCoordinates);
        }
        return new Coordinate(-1, -1);
    }

}
