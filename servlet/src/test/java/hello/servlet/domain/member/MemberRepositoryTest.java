package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    // 싱글톤 객체 가져오기 (테스트 대상)
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 각 테스트가 끝난 후 실행됨
    // 저장소 초기화 (테스트 간 영향 없도록)
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    // 회원 저장 테스트
    @Test
    void save() {
        // given: 테스트에 사용할 회원 객체 생성
        Member member = new Member("hello", 20);

        // when: 저장소에 저장
        Member savedMember = memberRepository.save(member);

        // then: 저장된 회원이 다시 조회될 때 동일한지 확인
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember); // 동일해야 성공
    }

    // 전체 회원 조회 테스트
    @Test
    void findAll() {
        // given: 여러 명의 회원 저장
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        Member member3 = new Member("member3", 40);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // when: 전체 회원 조회
        List<Member> result = memberRepository.findAll();

        // then: 총 회원 수와 포함된 회원 확인
        assertThat(result.size()).isEqualTo(3); // 크기 확인
        assertThat(result).contains(member1, member2, member3); // 포함 확인
    }
}

