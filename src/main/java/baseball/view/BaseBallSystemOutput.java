package baseball.view;

import baseball.config.BaseBallConfig;
import baseball.config.BaseBallStatusType;
import baseball.model.result.Result;

public class BaseBallSystemOutput {

    public static void writeToGetNumber() {
        System.out.print("숫자를 입력해주세요 : ");
    }

    public static void writeCorrect() {
        System.out.println(BaseBallConfig.BASEBALL_ANSWER_LENGTH + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    public static void writeToGetIfRestart() {
        System.out.println(
            "게임을 새로 시작하려면 " + BaseBallStatusType.RESTART.getCode() +
                ", 종료하려면 " + BaseBallStatusType.EXIT.getCode()
                + "를 입력하세요.");
    }

    public static void writeAnswerResult(Result result) {
        System.out.println(result.toString());
    }

}
