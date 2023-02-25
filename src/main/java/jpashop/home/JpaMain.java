package jpashop.home;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Member;
import jpashop.home.domain.Team;

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
			
			
			
//			Movie movie =new Movie();
//			movie.setDirector("aaa");
//			movie.setActor("bbb");
//			movie.setName("바람과함께 사라지다");
//			movie.setPrice(10000);
//			
//			
//			em.persist(movie);
			
			
//			Member member=new Member();
//			member.setUsername("user");
//			member.setCreatedBy("kim");
//			member.setCreatedDate(LocalDateTime.now());
//			
//			em.persist(member);
//			em.flush();
//			em.clear();
			
//			Member findMemberOne=em.getReference(Member.class, member.getId());
//			System.out.println("before findMember = " +findMemberOne.getClass());
//			System.out.println("findMember.username  " +findMemberOne.getUsername());
//			System.out.println("after findMember = " +findMemberOne.getClass());
//			
//			
//			System.out.println("=====================");
//				Member findMember1=em.find(Member.class, member.getId());
//				Member findMember2=em.getReference(Member.class, member.getId());
//				logic(findMember2,findMember2);
//			System.out.println("=====================");
//			
//			
//			System.out.println("findMember.id = " +findMember1.getId());
//			System.out.println("findMember  = " +findMember1.getUsername());

			
			/**
			 * 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환
			 */
//			em.flush();
//			em.clear();
//			Member m1=em.find(Member.class, member.getId());
//			System.out.println("m1 = "+ m1.getClass());
//			
//			Member reference =em.getReference(Member.class, member.getId());
//			System.out.println("reference = " +reference.getClass());
//			System.out.println("a == a: " +(m1==reference) );
//			
//			em.flush();
//			em.clear();
			
			/**
			 * 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화하면문제 발생
				(하이버네이트는 org.hibernate.LazyInitializationException 예외를 터트림)
			 */
//			Member refMember=em.getReference(Member.class, member.getId());
//			System.out.println("refMember = " +refMember.getClass()); //Proxy
//			System.out.println("isLoadded1 = " +emf.getPersistenceUnitUtil().isLoaded(refMember));
//			em.clear();
//			refMember.getUsername();
//			System.out.println("isLoadded2 = " +emf.getPersistenceUnitUtil().isLoaded(refMember));
//			
//			//강제초기화
//			Hibernate.initialize(refMember);
			
			Team team =new Team();
			team.setName("teamA");
			em.persist(team);
			
			
			Member member1=new Member();
			member1.setUsername("user");
			member1.setCreatedBy("kim");
			member1.setCreatedDate(LocalDateTime.now());
			member1.setTeam(team);
			em.persist(member1);
			
		
			
			em.flush();
			em.clear();
			
			Member m =em.find(Member.class, member1.getId());
			System.out.println("m = " +m.getTeam().getClass());
			System.out.println("==============================");
			m.getTeam().getName();
			System.out.println("==============================");
			
			
			
			List<Member> members=em.createQuery("select  m from Member m join fetch m.team ", Member.class).getResultList();
			
			
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	
		
		emf.close();	
	}
	
	
	private static void printMember(Member member) {
		System.out.println("member = " +member.getUsername());
	}
	
	private static void printMemberAndTeam(Member member) {
		String username=member.getUsername();
		System.out.println("useranme = " + username);
		
		Team team=member.getTeam();
		System.out.println("team = " +team.getName());
	}
	
	/**
	 * 프록시 객체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야함 (== 비교 실패, 대신 instance of 사용)
	 */
	private static void logic(Member m1, Member m2) {
		System.out.println("m1 === m2 : " + (m1 instanceof Member));
		System.out.println("m1 === m2 : " + (m2 instanceof Member));
	}
	
}
