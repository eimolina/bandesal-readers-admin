package sv.gob.bandesal.blog.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "BLOGS_READERS")
public class BlogReader {

	@EmbeddedId
	private BlogReaderId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rid")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bid")
    private Blog blog;
	
}
