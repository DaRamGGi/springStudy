package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String username;
    private int age;

    public Member(){
    }

    //생성자
    //Member객체를 만들때 이름과 나이를 바로 넣어서 만들 수 있게 해주는 메서드
    public Member(String username, int age){
        this.username = username;
        this.age = age;
    }
}
