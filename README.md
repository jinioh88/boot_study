스프링 부트 이것저것
## 웹 애플리케이션 공통처리
1. 유효성 검증
- 스프링 부트는 spring-boot-starter-validation을 이용해 빈 검증과 스프링 검증을 이용한다. 
- 유효성 검증에는 단일 항목 체크와 상호 관련 항목 체크 2가지가 있다. 
- 단일항목 체크
  - @Validated 애너테이션으로 BindingResult에 유효성 검증의 결과가 설정된다. 
    - BindingResult는 @Validated 애너테이션이 부여된 인수 바로 다음에 정의 되야 한다. 
- 상호관련 체크
  - 예를 들어 함호를 두 번 입력하는 A가 입력된 경우, 값 B의 입력이 필수가 되는 식의 유효성 검증이다. 
  - @InitBinder 애너테이션을 부여한 메서드에서 만든 유효성 검증기를 WebDataBinder에 추가한다. 
  ```
  @Controller
  @RequestMapping("/users/users")
  @Slf4j
  @RequiredArgsConstructor
  public class UserController {
      
      private final UserFormValidator userFormValidator;
     
      @InitBinder("userForm")
      public void validatorBinder(WebDataBinder binder) {
          binder.addValidators(userFormValidator);
      }
      
      @PostMapping("/new")
      public String newUser(@Validated @ModelAttribute("userForm")UserForm form, BindingResult br,
                            RedirectAttributes attributes) {
          if(br.hasErrors()) {
              // 오류 흐름 코드
              return "redirect:/users/new";
          }
          return "register";
      }
  }
  ```
  - 위와 같이 하면 @Validated 애너테이션으로 빈 검증을 실행한 후, 추가한 유효성 검증기를 호출할 수 있다.

2. 객체 매핑
- 객체의 값을 다른 객체에 복사하는 방법이다. 
- 애플리케이션 계층 사이에서 데이터를 전달할 때 객체를 복사하는 데 복사할 항목 수가 많으면 코드량이 많아지고 소스 가독성도 나빠진다. 
- 모델매퍼, 도저, 맵스트럭트 등을 이용해 매핑을 편리하게 할 수 있다.   
  - 모델 매퍼는 느슨한 매핑을 해서 필드명이 비슷하면 의도하지 않게 매핑이 잘못 이루어져 주의해야하한다. STRING 모드로 하는 편이 제어하기 쉬울수 있다.
  
3. 로그 출력
- 엔터프라이즈 애플리케이션 개발에서 사용자가 언제 어떤 작업을 실시했는지 알 수 있는 추적 로그를 기록하게 되어있다. 
- spring-bot-starter-logging는 로그백을 상요하기 때문에 Log4j로 변경하려면 spring-boot-starter-log4j2의존을 추가해야 한다. 
- 로그 출력 공통처리는 요청을 접수한 기능의 처리 시작과 종료를 함께 로그 출력하는 경우가 많다. 
  
4. 파일 다운로드
- 파일의 출력 처리를 공통 처리하면 소스코드작성량이 줄고, 동작이 미묘하게 차이나지 않도록 방법을 통일할 수 있다. 
- 스프링5 부터 서류 출력의 대표적 라이브러리인 재스퍼리포트가 제외됐다. 
- org.springframework.Web.servlet.View 인터페이스를 구현하면 범용적인 로직으로 다양한 파일 다운로드에 대응할 수 있다. 
- CSV 파일은 jackson-dataformat-csv를 사용한다. 
  - jackson-dataformat-csv의 CsvMapper를 사용해 엔티티를 CSV 파일에 매핑한 후 출력한다. 
  
---
## 데이터 액세스
1. 스타터
- 보마를 사용하기에 doma-spring-boot-starter가 필요하다. 
2. 도마 사용법
- 엔티티, Dao 인터페이스 SQL 템플릿이 필요하다. 
  
  