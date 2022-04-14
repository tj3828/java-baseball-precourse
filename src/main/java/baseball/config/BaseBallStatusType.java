package baseball.config;

public enum BaseBallStatusType {


    RESTART(1),
    EXIT(2),
    READY(3),
    RUNNING(4),
    FINISH(5);

    private final Integer code;

    BaseBallStatusType(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
