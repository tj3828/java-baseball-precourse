package baseball.config;

public class BaseBallConfig {

    public static int BASEBALL_ANSWER_LENGTH = 3;
    public static int BASEBALL_ANSWER_DIGITS_MIN_VALUE = 1;
    public static int BASEBALL_ANSWER_DIGITS_MAX_VALUE = 9;

    public static void validate() {
        if (BASEBALL_ANSWER_LENGTH
            > BASEBALL_ANSWER_DIGITS_MAX_VALUE - BASEBALL_ANSWER_DIGITS_MIN_VALUE + 1) {
            throw new IllegalStateException();
        }

        if (BASEBALL_ANSWER_LENGTH < 2) {
            throw new IllegalStateException();
        }

        if (!String.valueOf(BASEBALL_ANSWER_DIGITS_MIN_VALUE).matches("[0-9]+")) {
            throw new IllegalStateException();
        }

        if (!String.valueOf(BASEBALL_ANSWER_DIGITS_MAX_VALUE).matches("[0-9]+")) {
            throw new IllegalStateException();
        }

        if (BASEBALL_ANSWER_DIGITS_MAX_VALUE <= BASEBALL_ANSWER_DIGITS_MIN_VALUE) {
            throw new IllegalStateException();
        }
    }

    public static boolean isBetweenAnswerDigitsRange(int value) {
        return BASEBALL_ANSWER_DIGITS_MIN_VALUE <= value && value <= BASEBALL_ANSWER_DIGITS_MAX_VALUE;
    }

}
