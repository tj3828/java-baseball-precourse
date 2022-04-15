package baseball.config;

import static baseball.config.BaseBallConfig.BASEBALL_ANSWER_DIGITS_MAX_VALUE;
import static baseball.config.BaseBallConfig.BASEBALL_ANSWER_DIGITS_MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class BaseBallConfigTest {

    @AfterEach
    void 설정값_초기화() {
        BaseBallConfig.BASEBALL_ANSWER_LENGTH = 3;
        BaseBallConfig.BASEBALL_ANSWER_DIGITS_MIN_VALUE = 1;
        BaseBallConfig.BASEBALL_ANSWER_DIGITS_MAX_VALUE = 9;
    }

    @DisplayName("설정값_유효성_검사")
    @ParameterizedTest
    @CsvSource(value = {"4:1:3", "1:1:1", "1:-1:1", "1:1:-1", "3:3:3", "3:3:2"}, delimiter = ':')
    void 설정값_유효성_검사(int length, int min, int max) {
        BaseBallConfig.BASEBALL_ANSWER_LENGTH = length;
        BaseBallConfig.BASEBALL_ANSWER_DIGITS_MIN_VALUE = min;
        BaseBallConfig.BASEBALL_ANSWER_DIGITS_MAX_VALUE = max;

        assertThatIllegalStateException().isThrownBy(BaseBallConfig::validate);
    }

    @DisplayName("최소값과_최대값_범위_체크")
    @ParameterizedTest
    @MethodSource("providerArgumentsForConfigRangeCheck")
    void 최소값과_최대값_범위_체크(int input, int standardValue, boolean result) {
        assertEquals(BaseBallConfig.isBetweenAnswerDigitsRange(input + standardValue), result);
    }

    private static Stream<Arguments> providerArgumentsForConfigRangeCheck() {
        return Stream.of(
            Arguments.of(-1, BASEBALL_ANSWER_DIGITS_MIN_VALUE, false),
            Arguments.of(1, BASEBALL_ANSWER_DIGITS_MAX_VALUE, false),
            Arguments.of(BASEBALL_ANSWER_DIGITS_MAX_VALUE - BASEBALL_ANSWER_DIGITS_MIN_VALUE,
                BASEBALL_ANSWER_DIGITS_MIN_VALUE, true)
        );
    }

}
