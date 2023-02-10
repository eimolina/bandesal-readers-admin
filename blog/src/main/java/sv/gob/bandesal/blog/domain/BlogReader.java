package sv.gob.bandesal.blog.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
	@JoinColumn(name = "R_ID")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bid")
    @JoinColumn(name = "B_ID")
    private Blog blog;
    
    @SuppressWarnings("serial")
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class BlogReaderId implements Serializable {

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
	
}
