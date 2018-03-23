package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.util.List;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiModel;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RoleDO;

@ApiModel(value = "", description = "权限验证类")
public class RoleBO {
	private int roleId;
	/**
	 * 功能操作名
	 */
     private String roleName;
     /**
      * 权限标识1.有权限 0.没有权限
      */
     private String roleFalg;
     /**
      * 没有权限返回Url
      */
     private String error;

     //平台类型
     private String type;


	public RoleBO(RoleDO rolePo) {
		this.roleId = rolePo.getRoleId();
		this.roleName = rolePo.getRoleName();
		if(rolePo.getType().equals(RoleDO.TYPE_MANAGE)){
			type = "总后台";
		} else if(rolePo.getType().equals(RoleDO.TYPE_USER)){
			type = "客户平台";
		} else if(rolePo.getType().equals(RoleDO.TYPE_SUPPLIER)){
			type = "供应商平台";
		}
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleFalg() {
		return roleFalg;
	}

	public void setRoleFalg(String roleFalg) {
		this.roleFalg = roleFalg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RoleBO(List<Map<String, Object>> Listmap , String bs){
   	 for (Map<String, Object> map : Listmap) {
   		 if(map.get("pName").equals(bs)){
   			 this.roleName = (String) map.get("pName");
   			 this.roleFalg ="1";
   			 return;
   		 }else{
   			 this.roleFalg ="0";
   			 this.error="/admin/error";
   		 }
		}
    }
     
}
