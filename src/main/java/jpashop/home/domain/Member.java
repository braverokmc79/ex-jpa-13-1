package jpashop.home.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseEntity{

	@Id @GeneratedValue
	@Column(name="MEMBER_ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;
	
	//기간 Period
	@Embedded
	private Period workPeriod;
	
	//주소 Address
	@Embedded
	private Address homeAddress;
	
	@ElementCollection
	@CollectionTable(name="FAVORITE_FOOD", joinColumns = 
			@JoinColumn(name="MEMBER_ID")
		)
	@Column(name="FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();
	
//	@ElementCollection
//	@CollectionTable(name="ADDRESS_HISTORY", joinColumns = @JoinColumn(name="MEMBER_ID"))
//	private List<Address> addressHistory=new ArrayList<>();
//	
	
	//부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제 하는 기능을 고아 객체
	//다음과 같이 변경=======>
	
	@OneToMany(cascade =CascadeType.ALL, orphanRemoval =true )
	@JoinColumn(name="MEMBER_ID")
	private List<AddressEntity> addressHistory=new ArrayList<>();
	
	
}





//work Address
//@Embedded
//@AttributeOverrides({
//	@AttributeOverride(name="city",column =@Column(name="WORK_CITY")),
//	@AttributeOverride(name="street",column=@Column(name="WORK_STREET") ),
//	@AttributeOverride(name="zipcode",column=@Column(name="WORK_ZIPCODE") )
//	
//})
//private Address workAddress;
//
//


