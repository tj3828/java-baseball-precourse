package baseball.config;

public class BaseBallConfig {

    public static int BASEBALL_ANSWER_LENGTH = 3;
    public static int BASEBALL_ANSWER_DIGITS_MIN_VALUE = 1;
    public static int BASEBALL_ANSWER_DIGITS_MAX_VALUE = 9;

    public static void validate() {
        if(BASEBALL_ANSWER_LENGTH >= BASEBALL_ANSWER_DIGITS_MAX_VALUE - BASEBALL_ANSWER_DIGITS_MIN_VALUE + 1) {
            throw new IllegalStateException();
        }
    }

}
