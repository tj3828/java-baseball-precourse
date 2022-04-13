# 숫자 야구 게임

## 구현 절차

### 1. 숫자 야구 게임에서 필요한 모델 객체 정리
   1. Model
      1. Player : 게임에서 문제를 내는 사람과 푸는 사람
         1. Resolver : 문제를 푸는 사람
         2. Examiner : 문제를 내는 사람
      2. Result : 문제마다의 ball, strike 개수
      3. BaseBall : 전체 게임 영역
         1. Player : Questioner, Answerer
         2. Status : 게임중
      4. NumberGenerator
      5. Validation
   2. View
      1. ConsoleFormat
      2. QuestionerInput
      3. AnswererOutput
   3. Config
      1. BaseBallConfig : 숫자 길이 및 범위에 대한 정의


### 2. 정의된 객체가 수행하는 책임과 역할에 대해서 테스트 코드 작성
   1. 랜덤함수 (매번 랜덤인지, 정해진 숫자 범위에서 생성되는지, 자리수가 맞는지)
   2. QuestionerInput validate (정해진 숫자 범위인지, 자리수가 맞는지, 숫자만 입력된건지, 중복 숫자가 입력됐는지)
   3. AnswererOutput validate (generate 숫자에 맞게 strike, ball 카운트가 되는지)
   4. 게임을 멈추고 재시작하고 예외처리에 따라 종료되는지



### 3. 기능 구현



### 4. 리펙토링
