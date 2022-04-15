package baseball.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

import baseball.model.result.Result;
import camp.nextstep.edu.missionutils.Randoms;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

@TestInstance(Lifecycle.PER_CLASS)
public class ExaminerTest {

    Examiner examiner;
    Method addPickNumberInAnswerListMethod;

    @BeforeAll
    void Examiner_초기화() throws Exception {
        examiner = Examiner.of();
        addPickNumberInAnswerListMethod = Examiner.class.getDeclaredMethod("addPickNumberInAnswerList",
            int.class);
        addPickNumberInAnswerListMethod.setAccessible(true);
    }

    @DisplayName("정답숫자리스트에 중복숫자 없이 추가")
    @ParameterizedTest
    @CsvSource(value = {"4:true:1", "4:true:1", "44:false:1", "5:true:2", "6:true:3"}, delimiter = ':')
    void 정답숫자리스트에_숫자넣기_예외(int input, boolean isInput, int answerListCount) throws Exception {
        addPickNumberInAnswerListMethod.invoke(examiner, input);

        assertEquals(examiner.getAnswerList().contains(input), isInput);
        assertEquals(examiner.getAnswerList().size(), answerListCount);
    }

    @DisplayName("스크라이크, 볼 개수 체크")
    @ParameterizedTest
    @CsvSource(value = {"135:3:0", "136:2:0", "178:1:0", "789:0:0", "153:1:2", "359:0:2", "513:0:3"}, delimiter = ':')
    void 결과체크(String answerByResolver, int strikeCount, int ballCount) {

        try (final MockedStatic<Randoms> mock = mockStatic(Randoms.class)) {
            mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt())).thenReturn(1, 3, 5);
            examiner.generateAnswer();
        }

        Result actualResult = examiner.checkAnswer(answerByResolver);

        assertEquals(actualResult.getStrikeCount(), strikeCount);
        assertEquals(actualResult.getBallCount(), ballCount);
    }

}
