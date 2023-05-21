package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfig {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 3. 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        /**
         * 출력값 - 생성할 때마다 다른 객체( @c540f5a, @770c2e6b )가 생성됨 -> JVM 메모리에 계속 객체가 생성해서 올라가게 됨 -> 효율적X
         * memberService1 = hello.core.member.MemberServiceImpl@c540f5a
         * memberService2 = hello.core.member.MemberServiceImpl@770c2e6b
         */

        // memberService1 != memberService2 인 경우 성공
        assertThat(memberService1).isNotSameAs(memberService2);
    }
}
