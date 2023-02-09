package sv.gob.bandesal.blog.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Embeddable
@Data
@NoArgsConstructor
public class BlogReaderId implements Serializable {

	@Column(name = "R_ID")
	private Long rid;

	@Column(name = "B_ID")
	private Long bid;
}
