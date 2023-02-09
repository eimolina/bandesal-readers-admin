package sv.gob.bandesal.blog.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogReaderId implements Serializable {

	@Column(name = "R_ID")
	private Long rid;

	@Column(name = "B_ID")
	private Long bid;
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BlogReaderId that = (BlogReaderId) o;
        return Objects.equals(rid, that.getRid()) && Objects.equals(bid, that.getBid());
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(rid, bid);
    }
}
