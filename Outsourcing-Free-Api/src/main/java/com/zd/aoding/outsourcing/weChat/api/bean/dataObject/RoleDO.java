package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "角色表")
public class RoleDO extends DOBase  {
	/** 总平台 */
	public final static int TYPE_MANAGE = 1;
	/** 客户平台 */
	public final static int TYPE_USER = 2;
	/** 供应商平台 */
	public final static int TYPE_SUPPLIER = 3;
	/** 商户平台 */
	public final static int TYPE_DEALER = 4;
	/** 经销商平台 */
	public final static int TYPE_DISTRIBUTOR = 5;

	@ApiModelProperty("角色表主键id")
	private Integer roleId;
	@ApiModelProperty("平台类别(1.总后台,2.客户平台,3.供应商平台)")
	private Integer type;
	@ApiModelProperty("角色名")
	private String roleName;
	@ApiModelProperty("按钮ID，以，分割开")
	private String menuList;
	@ApiModelProperty("权限ID，以，分割开。这里面的说明都是可以操作的")
	private String managerList;
	@ApiModelProperty("type为1时 == 0  type为2时 == 客户id type为3时 == 供货商id")
	private Integer ownerId;


	public RoleDO() {
		super();
	}

	public RoleDO(Integer type, String roleName, String menuList, Integer ownerId) {
		super();
		this.type = type;
		this.roleName = roleName;
		this.menuList = menuList;
		this.ownerId = ownerId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMenuList() {
		return menuList;
	}

	public void setMenuList(String menuList) {
		this.menuList = menuList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getManagerList() {
        return managerList;
    }

    public void setManagerList(String managerList) {
        this.managerList = managerList;
    }

    @Override
	public String toString() {
		return "RoleDO [roleId=" + roleId + ", type=" + type + ", roleName="
				+ roleName + ", menuList=" + menuList + ", ownerId=" + ownerId
				+ ", toString()=" + super.toString() + "]";
	}

}
