package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class App {

    GAME_STATE gameState;
    static GAME_LEVEL gameLevel = GAME_LEVEL.EASY;
    ACTIVE_PLAYER activePlayer;

    APP_STATE appState;

    enum GAME_STATE {
        NOT_FINISHED, DRAW, XWIN, OWIN
    }

    enum APP_STATE {
        ON, OFF
    }

    enum GAME_LEVEL {
        EASY, MEDIUM, HARD
    }

    enum ACTIVE_PLAYER {
        PLAYER1, PLAYER2
    }

    enum PLAYER_TYPE {
        HUMAN, COMPUTER
    }



    void startGame() {
        setAppState(APP_STATE.ON);

        while (appState == APP_STATE.ON) {
            String[] command = null;
            while (command == null) {
                showMenu();
                command = getCommandArray();
            }
            if ("exit".equals(command[0])) {
                setAppState(APP_STATE.OFF);
            } else {
                setGameState(GAME_STATE.NOT_FINISHED);
                GameTable gameTable = new GameTable();
                Player player1;
                Player player2;
                String player_1 = command[1];
                String player_2 = command[2];
                activePlayer = ACTIVE_PLAYER.PLAYER1;

                if ("easy".equals(player_1) && "easy".equals(player_2)) {
                    player1 = new ComputerPlayer("X");
                    player2 = new ComputerPlayer("O");
                } else if ("user".equals(player_1)) {
                    player1 = new Player("X");
                    player2 = new ComputerPlayer("O");
                } else {
                    player2 = new Player("O");
                    player1 = new ComputerPlayer("X");
                }

                while (gameState == GAME_STATE.NOT_FINISHED) {
                    playGame(gameTable, player1, player2);
                }
            }
        }
    }

    private void showMenu() {
        System.out.print("Input command: ");
    }

    private String[] getCommandArray() {
        Scanner sc = new Scanner(System.in);
        String commandString = sc.nextLine();
        String[] commandArray = commandString.split(" ");

        if (!isCommandValid(commandArray)) {
            System.out.println("Bad parameters!");
            return null;
        }
        return commandArray;
    }

    private boolean isCommandValid(String[] command) {
        if ("exit".equals(command[0])) {
            return true;
        }
        if (!"start".equals(command[0])) {
            return false;
        } else {
            if (command.length != 3) {
                return false;
            }
            if (!"easy".equals(command[1]) && !"user".equals(command[1])) {
                return false;
            }
            if (!"easy".equals(command[2]) && !"user".equals(command[2])) {
                return false;
            }
            return true;
        }
    }

    void playGame(GameTable gameTable, Player player1, Player player2) {
        gameTable.printField();
        while (gameState == GAME_STATE.NOT_FINISHED) {
            Player currentActivePlayer = getActivePlayer(player1, player2);
            move(gameTable, currentActivePlayer);
        }
    }

    private void move(GameTable gameTable, Player player) {
        int[] coordinates;
        coordinates = player.provideCoordinates(gameTable);
        gameTable.placeToken(coordinates, player.getToken());

        gameTable.printField();
        setFieldBasedGameState(gameTable);
        if (gameState != GAME_STATE.NOT_FINISHED) {
            printGameState(gameState);
        }
        switchPlayers();
    }

    private void setFieldBasedGameState(GameTable gameTable) {
        // Update game state according to situation on the game field
        if (gameTable.isWinningCombination("X")) {
            setGameState(GAME_STATE.XWIN);
        } else if (gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.OWIN);
        } else if (gameTable.getNumberOfElements() == 0 && !gameTable.isWinningCombination("X")
                && !gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.DRAW);
        } else if (gameTable.getNumberOfElements() > 0 && !gameTable.isWinningCombination("X")
                && !gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.NOT_FINISHED);
        }
    }

    private void setGameState(GAME_STATE newGameState) {
        gameState = newGameState;
    }

    private void setAppState(APP_STATE newAppState) {
        appState = newAppState;
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
        activePlayer = activePlayer == ACTIVE_PLAYER.PLAYER1 ? ACTIVE_PLAYER.PLAYER2 : ACTIVE_PLAYER.PLAYER1;
    }

    private Player getActivePlayer(Player player1, Player player2) {
        if (activePlayer == ACTIVE_PLAYER.PLAYER1) {
            return player1;
        } else {
            return player2;
        }
    }
}
