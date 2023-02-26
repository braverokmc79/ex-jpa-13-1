package jpashop.home;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpashop.home.domain.Address;
import jpashop.home.domain.AddressEntity;
import jpashop.home.domain.Member;

public class JpaMain5 {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			
			Member member =new Member();
			member.setUsername("member1");
			member.setHomeAddress(new Address("city1",  "street", "10000"));
			
			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("족발");
			member.getFavoriteFoods().add("피자");
			
//			member.getAddressHistory().add(new Address("old1",  "street", "10000"));
//			member.getAddressHistory().add(new Address("old2",  "street", "10000"));
//			===>
			
			//member.getAddressHistory().add(new AddressEntity("old1",  "street", "10000"));
			//member.getAddressHistory().add(new AddressEntity("old2",  "street", "10000"));
			
			
			em.persist(member);
			
			em.flush();
			em.clear();
			
			System.out.println("================START==========");
			/** 값타입 컬렉션은 지연로딩 처리 된다. **/
			Member findMember=em.find(Member.class, member.getId());
//			
//			List<Address> addressHistory =findMember.getAddressHistory();
//			for(Address address: addressHistory) {
//				System.out.println("address = " + address.getCity());
//			}
//			
//			Set<String> favoriteFoods =findMember.getFavoriteFoods();
//			for(String favoriteFood :favoriteFoods) {
//				System.out.println("favoriteFoods = "+ favoriteFood);
//			}
			
			/**
			 * 값타입은 추가시 새롭게 객체 생성 
			 */
			Address a=findMember.getHomeAddress();
			findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
			
			
			/** 값타입 String 은 기존것 제거후 추가 */
			findMember.getFavoriteFoods().remove("치킨");
			findMember.getFavoriteFoods().add("한식");
			
			
			/** 값타입 기존것 전부 제거후 새롭게 추가 */
//			findMember.getAddressHistory().remove(new Address("old1", a.getStreet(), a.getZipcode()));
//			findMember.getAddressHistory().add(new Address("newCity1", a.getStreet(), a.getZipcode() ));
			
			//=>
			
			findMember.getAddressHistory().remove(new AddressEntity("old1", a.getStreet(), a.getZipcode()));
			findMember.getAddressHistory().add(new AddressEntity("newCity1", a.getStreet(), a.getZipcode() ));
		
			
			/**
			 * ID 값을 만들어서 사용해라
			 */
			//==> AddressEntity  엔티티 객체를 만들어 사용할 경우 다음과 같이 id 컬럼을 생성 된다. 
//		    create table ADDRESS_HISTORY (
//		    	       id bigint not null,
//		    	        city varchar(255),
//		    	        street varchar(255),
//		    	        ZIPCODE varchar(255),
//		    	        MEMBER_ID bigint,
//		    	        primary key (id)
//		    	    )
			
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
