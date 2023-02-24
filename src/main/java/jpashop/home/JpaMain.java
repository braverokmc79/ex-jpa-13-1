package jpashop.home;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Movie;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
//			Team team =new Team();
//			team.setName("TeamA");
//			em.persist(team);
//			
//			Member member =new Member();
//			member.setUsername("member1");
//			member.setTeam(team);
//			
//			em.persist(member);
//			
//			em.flush();
//			em.clear();
//			
//			Team findTeam=member.getTeam();
//			System.out.println(" findTeam  : "  + findTeam.getName());
//			
//			Member findMember =em.find(Member.class, member.getId());
//			List<Member> members =findMember.getTeam().getMembers();
//			
//			for(Member m :  members) {
//				System.out.println("m  = " +m.getUsername());
//			}
			
			
			Movie movie =new Movie();
			movie.setDirector("aaa");
			movie.setActor("bbb");
			movie.setName("바람과함께 사라지다");
			movie.setPrice(10000);
			
			
			em.persist(movie);
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
	
		
		emf.close();	
	}
	
	
}
