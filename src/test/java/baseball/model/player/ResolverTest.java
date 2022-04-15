package baseball.model.player;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import baseball.config.BaseBallStatusType;
import camp.nextstep.edu.missionutils.Console;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

@TestInstance(Lifecycle.PER_CLASS)
public class ResolverTest {

    Resolver resolver;

    @BeforeAll
    void Resolver_초기화() {
        resolver = Resolver.of();
    }

    @Test
    @DisplayName("콘솔에서_읽어온_베이스볼_숫자값")
    void 콘솔에서_읽어온_베이스볼_숫자값() {
        String readAnswer;
        String mockReturnValue = "345";

        try (final MockedStatic<Console> mock = mockStatic(Console.class)) {
            mock.when(Console::readLine).thenReturn(mockReturnValue);
            readAnswer = resolver.readAnswer();
        }

        assertEquals(mockReturnValue, readAnswer);
    }

    @Test
    @DisplayName("콘솔에서_읽어온_재시작_여부값")
    void 콘솔에서_읽어온_재시작_여부값() {
        boolean readRestart;
        String mockReturnValue = String.valueOf(BaseBallStatusType.RESTART.getCode());

        try (final MockedStatic<Console> mock = mockStatic(Console.class)) {
            mock.when(Console::readLine).thenReturn(mockReturnValue);
            readRestart = resolver.isRestart();
        }

        assertTrue(readRestart);
    }

    @Test
    @DisplayName("재시작_여부값_유효성_검사")
    void 재시작_여부값_유효성_검사() throws Exception {
        Method validateInputRestartMethod = Resolver.class.getDeclaredMethod("validateInputRestart",
            String.class);
        validateInputRestartMethod.setAccessible(true);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> {
                try{
                    validateInputRestartMethod.invoke(resolver, "3");
                } catch (InvocationTargetException invocationTargetException) {
                    throw invocationTargetException.getCause();
                }
            });
    }

    @DisplayName("숫자_입력깂_유효성_검사")
    @ParameterizedTest
    @ValueSource(strings = {"2134", "114", "ㅁㄱ1", "ㄱ 3"})
    void 숫자_입력깂_유효성_검사(String input) throws Exception {
        Method validateInputAnswerMethod = Resolver.class.getDeclaredMethod("validateInputAnswer",
            String.class);
        validateInputAnswerMethod.setAccessible(true);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> {
                try{
                    validateInputAnswerMethod.invoke(resolver, input);
                } catch (InvocationTargetException invocationTargetException) {
                    throw invocationTargetException.getCause();
                }
            });
    }

}
