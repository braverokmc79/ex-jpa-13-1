package jpashop.home;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Child;
import jpashop.home.domain.Parent;

public class JpaMain2 {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
				
			Child child1=new Child();
			Child child2=new Child();
			
			Parent parent =new Parent();
			parent.addChild(child1);
			parent.addChild(child2);
				
			em.persist(parent);
			//em.persist(child1);
			//em.persist(child2);

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
