package baseball.model;

import baseball.model.player.Examiner;
import baseball.model.player.Resolver;
import org.junit.jupiter.api.Test;

public class BaseBallTest {

    @Test
    void 게임_시작() {
        BaseBall baseBall = BaseBall.of(Resolver.of(), Examiner.of());
        baseBall.start();
    }

    @Test
    void 게임_종료() {

    }


}
