package com.cxd.permissionManager.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxd
 * @since 2018-01-13
 */
@TableName("userRole")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("userNo")
	private String userNo;
	@TableField("roleNo")
	private String roleNo;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	@Override
	public String toString() {
		return "UserRole{" +
			", id=" + id +
			", userNo=" + userNo +
			", roleNo=" + roleNo +
			"}";
	}
}
