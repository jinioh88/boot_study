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