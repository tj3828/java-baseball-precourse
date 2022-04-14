package baseball.config;

public enum BaseBallResultType {

    NOTHING(1, "낫싱"),
    STRIKE(2, "스트라이크"),
    BALL(3, "볼");

    private final Integer code;
    private final String name;
    BaseBallResultType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
