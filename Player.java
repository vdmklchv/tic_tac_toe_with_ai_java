package tictactoe;

public interface Player {
    String getToken();
    int[] provideCoordinates(GameTable gameTable);
    void move(GameTable gameTable, Screen screen);

}
