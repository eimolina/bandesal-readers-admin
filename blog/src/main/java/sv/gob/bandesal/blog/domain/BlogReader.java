package sv.gob.bandesal.blog.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BLOGS_READERS")
@Data
@NoArgsConstructor
public class BlogReader {

	@EmbeddedId
	private BlogReaderId id;
}
