package baseball.model;

import baseball.config.BaseBallConfig;
import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Set;

public class Resolver extends Player {
    public static Resolver of() {
        return new Resolver();
    }

    public String readAnswer() {
        String inputAnswer = Console.readLine();
        validateInputAnswer(inputAnswer);

        return inputAnswer;
    }

    private void validateInputAnswer(String inputAnswer) {

        if(!validateInputAnswerIfNumber(inputAnswer)) {
            throw new IllegalArgumentException("Please input value only " + BaseBallConfig.BASEBALL_ANSWER_DIGITS_MIN_VALUE + " to " + BaseBallConfig.BASEBALL_ANSWER_DIGITS_MAX_VALUE + " for each digit");
        }

        if(inputAnswer.length() != BaseBallConfig.BASEBALL_ANSWER_LENGTH) {
            throw new IllegalArgumentException("Please input value only " + BaseBallConfig.BASEBALL_ANSWER_LENGTH + " digits");
        }

        if(!validateInputAnswerDuplicateNumber(inputAnswer)) {
            throw new IllegalArgumentException("Please input value non-duplicate digits");
        }

    }

    private boolean validateInputAnswerIfNumber(String inputAnswer) {
        final String regex = "[" + BaseBallConfig.BASEBALL_ANSWER_DIGITS_MIN_VALUE + "-" + BaseBallConfig.BASEBALL_ANSWER_DIGITS_MAX_VALUE + "]+";
        return inputAnswer.matches(regex);
    }

    private boolean validateInputAnswerDuplicateNumber(String inputAnswer) {
        Set<Character> inputAnswerSet = new HashSet<>();
        char[] inputAnswerCharArr = inputAnswer.toCharArray();
        for(char inputAnswerChar: inputAnswerCharArr) {
            inputAnswerSet.add(inputAnswerChar);
        }

        return inputAnswerCharArr.length == inputAnswerSet.size();
    }

}
