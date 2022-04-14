package baseball.model;

import baseball.config.BaseBallConfig;
import baseball.config.BaseBallStatusType;
import baseball.model.player.Examiner;
import baseball.model.player.Resolver;
import baseball.model.result.Result;
import baseball.view.BaseBallSystemOutput;

public class BaseBall {

    private final Resolver resolver;
    private final Examiner examiner;

    private BaseBallStatusType status;

    private BaseBall(Resolver resolver, Examiner examiner) {
        this.resolver = resolver;
        this.examiner = examiner;
        this.setStatus(BaseBallStatusType.READY);
    }

    public static BaseBall of(Resolver resolver, Examiner examiner) {
        return new BaseBall(resolver, examiner);
    }

    public void start() {
        while (!isExit()) {
            this.init();
            this.setStatus(BaseBallStatusType.RUNNING);
            this.startBaseBall();
            this.checkRestartByResolver();
        }
    }

    private void startBaseBall() {
        while (!isFinish()) {
            BaseBallSystemOutput.writeToGetNumber();
            String answerByResolver = this.resolver.readAnswer();
            Result result = this.examiner.checkAnswer(answerByResolver);
            BaseBallSystemOutput.writeAnswerResult(result);
            checkGameFinish(result);
        }
    }

    private void checkGameFinish(Result result) {
        if (result.isCorrect()) {
            BaseBallSystemOutput.writeCorrect();
            this.setStatus(BaseBallStatusType.FINISH);
        }
    }

    private void checkRestartByResolver() {
        BaseBallSystemOutput.writeToGetIfRestart();
        boolean isRestart = this.resolver.isRestart();
        if (!isRestart) {
            this.setStatus(BaseBallStatusType.EXIT);
        }
    }

    private void init() {
        this.setStatus(BaseBallStatusType.READY);
        BaseBallConfig.validate();
        this.examiner.generateAnswer();
    }

    private boolean isFinish() {
        return status == BaseBallStatusType.FINISH;
    }

    private boolean isExit() {
        return status == BaseBallStatusType.EXIT;
    }

    private void setStatus(BaseBallStatusType status) {
        this.status = status;
    }

}
