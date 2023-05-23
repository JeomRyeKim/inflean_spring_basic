package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // HashMap을 사용하면 동시성 이슈가 발생할 수 있어서 실무에서는 ConcurrentHashMap을 사용함.
    // 다만 예제에서는 간단하게 하기 위해 HashMap을 사용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
