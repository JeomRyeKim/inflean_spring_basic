package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createorder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 정보 조회
        Member member = memberRepository.findById(memberId);

        // 2. 할인 정책에 회원 정보 넘겨서 최종으로 할인된 금액을 받음
        // -> 등급만 넘길지, 아니면 회원 정보를 넘길지는 선택의 문제. 미래 확장성을 위해 회원 정보 전체를 넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // OrderService 입장 : 할인같은거 난 모르겠고 discountPolicy 니가 알아서 해서 결과만 나한테 던져줘 -> 단일 책임의 원칙(SRP)
        // 만약 할인에 대한 변경이 있다면 할인쪽만 고치면 됨. 주문쪽까지 같이 고칠 필요가 없음

        // 3. 최종 생성된 주문을 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
