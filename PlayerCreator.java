package tictactoe;

class PlayerCreator {
     Player make(String option, String token) {
        if ("user".equals(option)) {
            return new HumanPlayer(token);
        }
        if ("easy".equals(option)) {
            return new ComputerPlayer(token, Enums.GAME_LEVEL.EASY);
        }
        if ("medium".equals(option)) {
            return new ComputerPlayer(token, Enums.GAME_LEVEL.MEDIUM);
        } else {
            return new ComputerPlayer(token, Enums.GAME_LEVEL.HARD);
        }
    }
}
