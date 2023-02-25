package jpashop.home.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseEntity{

	@Id @GeneratedValue
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
	
	
}








