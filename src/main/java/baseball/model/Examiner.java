package baseball.model;

import baseball.config.BaseBallConfig;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Examiner extends Player{

    private final List<Integer> answerList = new ArrayList<>();

    public static Examiner of() {
        return new Examiner();
    }

    private void addPickNumberInAnswerList(int pickNumber) {

        if(!answerList.contains(pickNumber)) {
            answerList.add(pickNumber);
        }

    }

    public void generateAnswer() {

        int pickNumberInRange = Randoms.pickNumberInRange(1, 9);

        while(answerList.size() == BaseBallConfig.BASEBALL_ANSWER_LENGTH) {
            this.addPickNumberInAnswerList(pickNumberInRange);
        }

    }

    public Result checkAnswer(String answerByResolver) {
        return null;
    }
}
