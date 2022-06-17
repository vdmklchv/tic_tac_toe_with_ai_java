package tictactoe;

public class Options {
    private final String option_1;
    private final String option_2;
    private final String option_3;

    public Options(String option_1, String option_2, String option_3) {
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
    }

    public String getOption_1() {
        return option_1;
    }

    public String getOption_2() {
        return option_2;
    }

    public String getOption_3() {
        return option_3;
    }
}
