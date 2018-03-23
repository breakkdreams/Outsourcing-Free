package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "账号")
public class AccountDO extends DOBase {
	/** 管理员长号 */
	public final static int TYPE_MANAGER = 1;
	/** 用户账号 */
	public final static int TYPE_USER = 3;
	/** 经销商账号 */
	public final static int TYPE_DISTRIBUTOR = 2;
	/** 商家账号 */
	public final static int TYPE_BUSINESS = 4;
	/** 营业员号 */
	public final static int TYPE_SALESMAN = 5;
	/** 递推员号 */
	public final static int TYPE_RECURSIVE = 6;
	
	/** 状态 */
	public final static int STATUS_USE = 1;
	/** 状态 */
	public final static int STATUS_UNUSE = 0;
	/** 帐号表id id **/
	@ApiModelProperty("帐号表id")
	private Integer id;
	@ApiModelProperty("账号类型（1管理员，2app账号，3用户账号,5供货商）")
	private Integer type;
	@ApiModelProperty("手机号（登录名）")
	private String username;
	@ApiModelProperty("密码MD5加密")
	private String password;
	@ApiModelProperty("状态(1 启用 0 禁用)")
	private Integer status;

	/*
	 * @ApiModelProperty("角色ID") private RolePo rolePo;
	 */
	public AccountDO() {
		super();
	}

	public AccountDO(Integer type, String username, String password) {
		super();
		this.type = type;
		this.username = username;
		this.password = password;
		this.status = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountDO{" + "id=" + id + ", type=" + type + ", username='" + username + '\'' + ", password='"
				+ password + '\'' + ", status=" + status + '}';
	}
}
