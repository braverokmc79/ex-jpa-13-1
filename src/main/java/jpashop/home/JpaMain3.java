package jpashop.home;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Address;
import jpashop.home.domain.Member;
import jpashop.home.domain.Period;

public class JpaMain3 {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
			Address address =new Address("city", "street", "10000");
			
			Member member =new Member();
			member.setUsername("member1");
			member.setHomeAddress(address);
			em.persist(member);
			
			Member member2 =new Member();
			member2.setUsername("member1");
			member2.setHomeAddress(address);
			em.persist(member2);
			
			//다음코드 적용시 member2도 newCity 변경 처리된다.
			member.getHomeAddress().setCity("newCity");
			
			//따라서 불변성 유지를 위해 새롭게 Address 객체를 생성하고,
			//Address address2 =new Address("city", "street", "10000");
			//setter 를 사용 하지 못하도록 한다.
			
			
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
