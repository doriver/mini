# 창고형 식자재 마트
## 소프트웨어 아키텍처
<img width="600" height="300" alt="image" src="https://github.com/user-attachments/assets/0e813608-2dd3-42fc-beaa-56d819522692" />

## Rest API 응답 설계
'HTTP 상태코드' 에 따른 응답
* 2xx은 @controller에서
* 4xx, 5xx 은 @ExceptionHandler 쪽에서
  * 커스텀한 Expected4xxException, Expected5xxException를 api로직에서 throw함
* 응답형식은 ApiResponse클래스로 일괄 처리
  * 정적 팩토리 메서드(Static Factory Method)패턴을 사용

### 주문 기능 : [OrderService.java](https://github.com/doriver/mini/blob/master/src/main/java/com/ex/mini/shop/application/OrderService.java)
