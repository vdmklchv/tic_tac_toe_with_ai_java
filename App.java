package tictactoe;

public class App {

    GAME_STATE gameState = GAME_STATE.NOT_FINISHED;
    static GAME_LEVEL gameLevel = GAME_LEVEL.EASY;
    ACTIVE_PLAYER activePlayer = ACTIVE_PLAYER.PLAYER;

    enum GAME_STATE {
        NOT_FINISHED, DRAW, XWIN, OWIN
    }

    enum GAME_LEVEL {
        EASY, MEDIUM, HARD
    }

    enum ACTIVE_PLAYER {
        PLAYER, COMPUTER
    }

    void startGame() {
        // PREPARE GAME
        GameTable gameTable = new GameTable();
        Player player = new Player("X");
        ComputerPlayer computerPlayer = new ComputerPlayer("O");
        // START GAME
        gameTable.printField();
        while (gameState == GAME_STATE.NOT_FINISHED) {
            move(gameTable, player, computerPlayer);
        }
    }

    private void move(GameTable gameTable, Player player, ComputerPlayer computerPlayer) {

        int[] coordinates;
        if (activePlayer == ACTIVE_PLAYER.PLAYER) {
            coordinates = player.provideCoordinates(gameTable);
            gameTable.placeToken(coordinates, player.getToken());
        } else {
            coordinates = computerPlayer.provideCoordinates(gameTable);
            gameTable.placeToken(coordinates, computerPlayer.getToken());
        }

        gameTable.printField();
        setGameState(gameTable);
        if (gameState != GAME_STATE.NOT_FINISHED) {
            printGameState(gameState);
        }
        switchPlayers();
    }

    private void setGameState(GameTable gameTable) {
        // Check if game won
        if (gameTable.isWinningCombination("X")) {
            gameState = GAME_STATE.XWIN;
        } else if (gameTable.isWinningCombination("O")) {
            gameState = GAME_STATE.OWIN;
        } else if (gameTable.getNumberOfElements() == 0 && !gameTable.isWinningCombination("X")
                && !gameTable.isWinningCombination("O")) {
            gameState = GAME_STATE.DRAW;
        } else if (gameTable.getNumberOfElements() > 0 && !gameTable.isWinningCombination("X")
                && !gameTable.isWinningCombination("O")) {
            gameState = GAME_STATE.NOT_FINISHED;
        }
    }

    private void printGameState(GAME_STATE state) {
        switch (state) {
            case DRAW:
                System.out.println("Draw");
                break;
            case XWIN:
                System.out.println("X wins");
                break;
            case OWIN:
                System.out.println("O wins");
                break;
            default:
                System.out.println("Unknown state");
                break;
        }
    }

    private void switchPlayers() {
        activePlayer = activePlayer == ACTIVE_PLAYER.PLAYER ? ACTIVE_PLAYER.COMPUTER : ACTIVE_PLAYER.PLAYER;
    }
}
