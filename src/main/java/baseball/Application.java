package baseball;

import baseball.model.BaseBall;
import baseball.model.player.Examiner;
import baseball.model.player.Resolver;

public class Application {

    public static void main(String[] args) {
        BaseBall baseBall = BaseBall.of(Resolver.of(), Examiner.of());
        baseBall.start();
    }

}
