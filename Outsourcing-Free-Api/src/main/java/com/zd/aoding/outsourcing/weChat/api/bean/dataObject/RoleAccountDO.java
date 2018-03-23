package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.zd.aoding.base.DO.base.DOBase;

public class RoleAccountDO extends DOBase {
	private Integer id;

	// 账户id
	private Integer accountId;

	// 角色id
	private Integer roleId;

	public RoleAccountDO(Integer accountId, Integer roleId) {
		this.accountId = accountId;
		this.roleId = roleId;
	}

	public RoleAccountDO() {
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取账户id
	 *
	 * @return account_id - 账户id
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 设置账户id
	 *
	 * @param accountId
	 *            账户id
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取角色id
	 *
	 * @return role_id - 角色id
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色id
	 *
	 * @param roleId
	 *            角色id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "RoleAccountDO{" + "id=" + id + ", accountId=" + accountId + ", roleId=" + roleId + '}';
	}
}