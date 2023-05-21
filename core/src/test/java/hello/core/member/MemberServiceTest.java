package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    // 예전 코드
//    MemberService memberService = new MemberServiceImpl();

    // 새로운 코드
    MemberService memberService;

    // 각 테스트를 실행하기 이전에 무조건 실행해줌
    // 그래서 테스트를 실행하기 전에 AppConfig를 만들고 MemberService에 AppConfig를 할당해줌.
    // 그리고 난 다음에 @Test부분이 돌아감
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given - 어떤 환경이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when - 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then - 이렇게 된다
        // member와 findMember가 똑같은지 확인
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
