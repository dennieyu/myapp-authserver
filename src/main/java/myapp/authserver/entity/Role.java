package myapp.authserver.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_oauth_role", schema = "myapp")
public class Role extends BaseIdEntity {

	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_oauth_permission_role",
			schema = "myapp",
			joinColumns = {
				@JoinColumn(name = "role_id", referencedColumnName = "id")
			},
			inverseJoinColumns = {
				@JoinColumn(name = "permission_id", referencedColumnName = "id")
			})
	private List<Permission> permissions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
