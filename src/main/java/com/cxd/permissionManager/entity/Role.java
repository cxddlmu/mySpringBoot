package com.cxd.permissionManager.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxd
 * @since 2018-01-13
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("roleNo")
	private String roleNo;
	@TableField("roleName")
	private String roleName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role{" +
			", id=" + id +
			", roleNo=" + roleNo +
			", roleName=" + roleName +
			"}";
	}
}
