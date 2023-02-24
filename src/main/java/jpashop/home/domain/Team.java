package jpashop.home.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {


	@Id @GeneratedValue
	@Column(name="TEAM_ID")
	private Long id;
	private String name;
	
	/**★★★외래키가 있는 곳을 주인으로 정해라!
	 * 객체와 테이블이 관계를 맺는 차이
	 * 객체 연관관계 =2개
	 * mappedBy 는 Many 테이블의 외래키값을 넣으면 된다.
	 * 여기서는 Member 테이블의 team 이 외래키이다.
	 * 
	 * 조회만 가능하고 외래키의 값의 업데이트는 member 의 team 에서 업데이트 가능하다.
	 */
	@OneToMany(mappedBy = "team")
	private List<Member> members=new ArrayList<Member>();
	
	

}
