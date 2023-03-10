package jpashop.home;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jpashop.home.domain.Member;

public class JpaMain7Criter {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
			//Criteria 사용 준비
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Member> query=cb.createQuery(Member.class);
			
			Root<Member> m=query.from(Member.class);
			
			CriteriaQuery<Member> cq=query.select(m).where(cb.equal(m.get("username"), "kim"));
			
			String username="aaa";
			if(username!=null) {
				cq=cq.where(cb.equal(m.get("username"), "kim"));
			}
			
			
			List<Member> resultList =em.createQuery(cq).getResultList();
			
			
			
			
			
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
