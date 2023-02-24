package jpashop.home.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Movie  extends Item{
	private String director;
	private String actor;
}
