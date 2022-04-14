package baseball.model.result;

import baseball.config.BaseBallConfig;
import baseball.config.BaseBallResultType;

public class Result {

    private Integer ballCount = 0;
    private Integer strikeCount = 0;

    public static Result of() {
        return new Result();
    }

    public void addByBaseBallResultType(BaseBallResultType baseBallResultType) {
        if (baseBallResultType == BaseBallResultType.STRIKE) {
            this.strikeCount++;
        }

        if (baseBallResultType == BaseBallResultType.BALL) {
            this.ballCount++;
        }
    }

    public boolean isCorrect() {
        return strikeCount == BaseBallConfig.BASEBALL_ANSWER_LENGTH;
    }

    @Override
    public String toString() {
        if (isNothing()) {
            return BaseBallResultType.NOTHING.getName();
        }

        if (ballCount == 0) {
            return toStringOfStrike();
        }

        if (strikeCount == 0) {
            return toStringOfBall();
        }

        return toStringOfBall() + " " + toStringOfStrike();
    }

    private boolean isNothing() {
        return ballCount == 0 && strikeCount == 0;
    }

    private String toStringOfBall() {
        return ballCount + BaseBallResultType.BALL.getName();
    }

    private String toStringOfStrike() {
        return strikeCount + BaseBallResultType.STRIKE.getName();
    }

}
