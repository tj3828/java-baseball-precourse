package baseball.model.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import baseball.config.BaseBallConfig;
import baseball.config.BaseBallResultType;
import java.lang.reflect.Field;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(Lifecycle.PER_CLASS)
public class ResultTest {

    Result result;

    @BeforeEach
    void result_초기화() {
        result = Result.of();
    }


    @DisplayName("베이스볼_자리수마다의_결과")
    @Test
    void 베이스볼_자리수마다의_결과() {
        result.addByBaseBallResultType(BaseBallResultType.STRIKE);
        result.addByBaseBallResultType(BaseBallResultType.BALL);
        result.addByBaseBallResultType(BaseBallResultType.NOTHING);

        assertEquals(result.getStrikeCount(), 1);
        assertEquals(result.getBallCount(), 1);
    }

    @DisplayName("게임이_끝났는지_확인")
    @Test
    void 게임이_끝났는지_확인() {
        for (int i = 0; i < BaseBallConfig.BASEBALL_ANSWER_LENGTH; i++) {
            result.addByBaseBallResultType(BaseBallResultType.STRIKE);
        }

        assertTrue(result.isCorrect());
    }

    @DisplayName("결과_텍스트_출력_확인")
    @ParameterizedTest
    @MethodSource("providerArgumentsForResultToString")
    void 결과_텍스트_출력_확인(int strikeCount, int ballCount, String resultName) throws Exception {
        Field strikeCountField = Result.class.getDeclaredField("strikeCount");
        Field ballCountField = Result.class.getDeclaredField("ballCount");
        strikeCountField.setAccessible(true);
        ballCountField.setAccessible(true);

        strikeCountField.set(result, strikeCount);
        ballCountField.set(result, ballCount);

        assertThat(result.toString()).contains(resultName);
    }

    private static Stream<Arguments> providerArgumentsForResultToString() {
        return Stream.of(
            Arguments.of(0, 0, BaseBallResultType.NOTHING.getName()),
            Arguments.of(1, 0, 1 + BaseBallResultType.STRIKE.getName()),
            Arguments.of(0, 1, 1 + BaseBallResultType.BALL.getName()),
            Arguments.of(1, 1, 1 + BaseBallResultType.BALL.getName() + " 1" + BaseBallResultType.STRIKE.getName())
        );
    }
}
