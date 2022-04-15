package baseball.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import baseball.config.BaseBallStatusType;
import baseball.model.player.Examiner;
import baseball.model.player.Resolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BaseBallTest {

    @Test
    @DisplayName("생성자_호출시_기본_상태_확인")
    void 생성자_호출시_기본_상태_확인() {
        BaseBall baseBall = BaseBall.of(Resolver.of(), Examiner.of());
        assertEquals(baseBall.getStatus(), BaseBallStatusType.READY);
    }


}
