package jpashop.home.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Parent {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "parent",cascade = CascadeType.ALL )
	private List<Child> childList=new ArrayList<>();
	
	public void addChild(Child child) {
		childList.add(child);
		child.setParent(this);
	}
}
