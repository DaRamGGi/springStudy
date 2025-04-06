package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 현재 클래스는 메모리 기반 회원 저장소로,
 * 동시성 문제를 고려하지 않은 구조입니다.
 * 실무에서는 ConcurrentHashMap, AtomicLong 등을 사용하여 동시성 처리를 해야 합니다.
 */
public class MemberRepository {

    // 회원 정보를 저장하는 Map (id → Member)
    // static: 클래스에 단 하나만 존재 → 모든 MemberRepository 객체가 이 Map을 공유함
    private static Map<Long, Member> store = new HashMap<>();

    // 회원 ID 생성을 위한 시퀀스 (1씩 증가)
    // static이므로 모든 객체가 같은 값을 공유
    private static long sequence = 0L;

    // 싱글톤 패턴 적용: 인스턴스를 하나만 생성하고 외부에서 접근할 수 있게 제공
    private static final MemberRepository instance = new MemberRepository();

    // 싱글톤 접근을 위한 public 메서드
    public static MemberRepository getInstance() {
        return instance;
    }

    // 생성자를 private으로 막아 외부에서 new MemberRepository() 못 하도록 함 (싱글톤 보장)
    private MemberRepository() {}

    /**
     * 새로운 회원 저장 메서드
     * - sequence를 1 증가시켜 고유 ID를 생성
     * - 생성된 ID를 member에 설정한 후 Map에 저장
     * @param member 저장할 회원 객체
     * @return 저장된 회원 객체 (id 포함됨)
     */
    public Member save(Member member){
        member.setId(++sequence); // ID 자동 증가
        store.put(member.getId(), member); // Map에 저장
        return member;
    }

    /**
     * ID로 회원 조회
     * @param id 찾을 회원의 ID
     * @return 해당 ID의 회원 정보, 없으면 null
     */
    public Member findById(Long id){
        return store.get(id);
    }

    /**
     * 저장소에 있는 모든 회원 목록을 반환
     * - Map의 values()를 ArrayList로 변환하여 리턴
     * @return 전체 회원 목록
     */
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 저장소 비우기 (테스트용 등으로 주로 사용)
     * - store를 초기화함
     */
    public void clearStore(){
        store.clear();
    }
}
