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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "USER_ROLE")
public class UserRole {
	
	@EmbeddedId
	private UserRoleId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("uid")
	@JoinColumn(name = "U_ID")
    private ApplicationUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rid")
    @JoinColumn(name = "R_ID")
    private ApplicationRole role;
	
	@SuppressWarnings("serial")
    @Embeddable
    @Data
    public static class UserRoleId implements Serializable {

    	@Column(name = "U_ID")
    	private Long uid;

    	@Column(name = "R_ID")
    	private Long rid;

		@Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            UserRoleId that = (UserRoleId) o;
            return Objects.equals(rid, that.getRid()) && Objects.equals(uid, that.getUid());
        }
    	
    	@Override
        public int hashCode() {
            return Objects.hash(rid, uid);
        }
    }

}
