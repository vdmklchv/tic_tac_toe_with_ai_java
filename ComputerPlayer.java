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
        } else if (this.gameLevel == GAME_LEVEL.HARD) {
            // IMPLEMENT HARD AI
            return getBestMoveMinimax(gameTable);
        }
        return new Coordinate(-1, -1);
    }
    Coordinate getBestMoveMinimax(GameTable gameTable) {
        List<Coordinate> freeCells = gameTable.getFreeCellCoordinates();
        int bestScore = Integer.MIN_VALUE;
        Coordinate bestMove = null;
        for (Coordinate cell: freeCells) {
            gameTable.setCellContents(cell, getToken());
            int score = minimax(gameTable, false);
            gameTable.setCellContents(cell, "_");
            if (score > bestScore) {
                bestMove = cell;
                bestScore = score;
            }
        }
        return bestMove;
    }
    int minimax(GameTable gameTable, boolean isMaximizing) {
        List<Coordinate> freeCells = gameTable.getFreeCellCoordinates();
        int bestScore;
        String currentPlayerToken = getToken();
        String anotherPlayerToken = "X".equals(currentPlayerToken) ? "O" : "X";
        if (gameTable.isWinningCombination(currentPlayerToken)) {
            return 1;
        } else if (gameTable.isWinningCombination(anotherPlayerToken)) {
            return -1;
        } else if (!gameTable.isWinningCombination("X") && !gameTable.isWinningCombination("O")
            && gameTable.getFreeCellCoordinates().size() == 0) {
            return 0;
        }
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (Coordinate cell: freeCells) {
                gameTable.setCellContents(cell, currentPlayerToken);
                int score = minimax(gameTable, false);
                gameTable.setCellContents(cell, "_");
                bestScore = Math.max(score, bestScore);
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (Coordinate cell: freeCells) {
                gameTable.setCellContents(cell, anotherPlayerToken);
                int score = minimax(gameTable, true);
                gameTable.setCellContents(cell, "_");
                bestScore = Math.min(score, bestScore);
            }
        }
        return bestScore;
    }

}
