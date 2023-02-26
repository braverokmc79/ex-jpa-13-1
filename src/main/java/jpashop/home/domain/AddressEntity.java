package jpashop.home.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ADDRESS_HISTORY")
public class AddressEntity {

	
	@Id @GeneratedValue
	private Long id;
	
	private Address address;

	public AddressEntity(String city, String street, String zipcode) {
		this.address=new Address(city, street, zipcode);
	}
	
	
}
