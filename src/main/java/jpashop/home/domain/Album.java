package jpashop.home.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue("A") //타입명 축약
public class Album extends Item{
	private String artist;
}
