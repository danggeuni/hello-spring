package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
//        given
        Member member = new Member();
        member.setName("danggeuni");

//        when
        repository.save(member);

//        then
//        repository에 넣어놓은 member 객체를 꺼내오고,
//        위에서 생성한 member와 동일한지 비교한다.
//        실제 그 객체를 가르키는지 확인하기 위해 isSameAs를 사용한다.
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isSameAs(result);
    }

    @Test
    public void findByName(){
//        given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        when
        Member result = repository.findByName("spring1").get();

//        then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
//        given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        when
        List<Member> result = repository.findAll();

//        then
        assertThat(result.size()).isEqualTo(2);
    }
}
