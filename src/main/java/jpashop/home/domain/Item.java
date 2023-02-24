package jpashop.home.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@DiscriminatorColumn
public class Item {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	private int price;
	
}
