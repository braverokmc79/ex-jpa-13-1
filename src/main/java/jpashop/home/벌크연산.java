package jpashop.home;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Member;
import jpashop.home.domain.Team;

public class 벌크연산 {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
			Team teamA=new Team();
			teamA.setName("팀A");
			em.persist(teamA);
			
			Team teamB=new Team();
			teamB.setName("팀B");
			em.persist(teamB);
			
			Member member1 =new Member();
			member1.setUsername("회원1");
			member1.setTeam(teamA);
			em.persist(member1);
			
			Member member2 =new Member();
			member2.setUsername("회원2");
			member2.setTeam(teamA);
			em.persist(member2);
			
			Member member3 =new Member();
			member3.setUsername("회원3");
			member3.setTeam(teamB);
			em.persist(member3);
		
		
			
			//createQuery 에서 자동 flush 처리 
			int resultCount =em.createQuery("update Member m set m.age=20").executeUpdate();
			
			/**벌크 연산 수행 후 반드시 영속성 컨텍스트 초기화 **/
			em.clear();
			Member findMember=em.find(Member.class, member1.getId());
			
			System.out.println(" findMember  = " +findMember.getAge());

			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}	
		emf.close();	
	}
	
}
