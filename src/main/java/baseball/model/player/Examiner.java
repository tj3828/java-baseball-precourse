package baseball.model.player;

import baseball.config.BaseBallConfig;
import baseball.config.BaseBallResultType;
import baseball.model.result.Result;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Examiner extends Player {

    private final List<Integer> answerList = new ArrayList<>();

    public static Examiner of() {
        return new Examiner();
    }

    private void addPickNumberInAnswerList(int pickNumber) {
        if (!answerList.contains(pickNumber)) {
            answerList.add(pickNumber);
        }
    }

    public void generateAnswer() {
        answerList.clear();

        while (answerList.size() != BaseBallConfig.BASEBALL_ANSWER_LENGTH) {
            int pickNumberInRange = Randoms.pickNumberInRange(
                BaseBallConfig.BASEBALL_ANSWER_DIGITS_MIN_VALUE,
                BaseBallConfig.BASEBALL_ANSWER_DIGITS_MAX_VALUE);
            this.addPickNumberInAnswerList(pickNumberInRange);
        }
    }

    public Result checkAnswer(String answerByResolver) {
        char[] charArray = answerByResolver.toCharArray();
        return calculateAnswerResult(charArray);
    }

    private Result calculateAnswerResult(char[] charArray) {
        Result answerResult = Result.of();
        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            char digitChar = charArray[i];
            BaseBallResultType baseBallResultType = calculateAnswerByDigit(i, digitChar);
            answerResult.addByBaseBallResultType(baseBallResultType);
        }
        return answerResult;
    }

    private BaseBallResultType calculateAnswerByDigit(int digitIndex, Character digitChar) {
        int digit = Character.digit(digitChar, 10);

        int answerIndexOfResolverNumber = answerList.indexOf(digit);

        if (answerIndexOfResolverNumber == digitIndex) {
            return BaseBallResultType.STRIKE;
        }

        if (answerIndexOfResolverNumber != -1) {
            return BaseBallResultType.BALL;
        }

        return BaseBallResultType.NOTHING;
    }

}
