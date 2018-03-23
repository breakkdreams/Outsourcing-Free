package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.onlyCodeNum.OnlyCodeNum;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.*;
import com.zd.aoding.outsourcing.weChat.api.facade.*;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.AccountService;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountFacadeImpl implements AccountFacade {
	@Autowired
	private AccountService accountService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RoleFacade roleFacade;
	@Autowired
	private RegionFacade regionFacade;
	@Autowired
	private ManagerFacade managerFacade;
	@Autowired
	private ManagerPurseFacade managerPurseFacade;
	@Autowired
	private RoleAccountFacade roleAccountFacade;

	public int insertAccountByType(Integer type, AccountDO accountPo) {
		return insertAccountDo(accountPo);
	}
	private int insertAccountDo(AccountDO accountPo) {
		accountPo.insertInit();
		return accountService.insert(accountPo);
	}
	@Override
	public AccountDO getPoByPK(Integer i) {
		return accountService.get(i);
	}
	@Override
	public AccountBO getBoByPK(Integer i) {
		AccountBO accountBO = new AccountBO();
		AccountDO accountPo = accountService.get(i);
		if(accountPo!=null){
			accountBO = view(accountPo);
		}

		return accountBO;
	}
	@Override
	public List<AccountDO> getPoBySQLAdapter(Map<String, Object> param) {
		String obj = param.get("companyAcountId") == null ? "" : param.get("companyAcountId").toString();
		String sql = "SELECT * FROM account WHERE phone =183 AND deleted = 0 ";
		if (param.get("deleted") != null) {
			sql += " AND deleted = " + param.get("deleted");
		}
		SQLAdapter adapter = new SQLAdapter();
		adapter.setSql(sql);
		List<AccountDO> accountPolist = accountService.getBySQLAdapter(adapter);
		return accountPolist;
	}
	@Override
	public List<AccountDO> getList(Map<String, Object> param) {
		return accountService.getList(param);
	}
	@Override
	public AccountDO getUserAccountPoByPhone(String phone) {
		Map<String, Object> param = new HashMap<>();
		param.put("username", phone);
		param.put("deleted", 0);
		param.put("type", AccountDO.TYPE_USER);
		List<AccountDO> listUser = accountService.getList(param);
		if (listUser == null || listUser.size() == 0) {
			return null;
		}
		if (listUser.size() == 1) {
			return listUser.get(0);
		}
		if (listUser.size() > 1) {
			LogUtil.dataError("同名用户账号", this.getClass());
			return listUser.get(0);
		}
		return null;
	}
	@Override
	public AccountDO getManagerAccountPoByPhone(String phone,Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("username", phone);
		param.put("deleted", 0);
		param.put("type", type);
		List<AccountDO> listUser = accountService.getList(param);
		if (listUser == null || listUser.size() == 0) {
			return null;
		}
		if (listUser.size() == 1) {
			return listUser.get(0);
		}
		if (listUser.size() > 1) {
			LogUtil.dataError("同名用户账号", this.getClass());
			return listUser.get(0);
		}
		return null;
	}
	@Override
	public int reSetPassword(String newPassword, Integer id) {
		AccountDO entity = new AccountDO();
		entity.setId(id);
		entity.setPassword(MD5Util.MD5(newPassword));
		// TODO Auto-generated method stub
		return accountService.update(entity);
	}
	@Override
	public int insertAccount(AccountDO accountPo) {
		// TODO Auto-generated method stub
		return accountService.insert(accountPo);
	}
	@Override
	public int deleteAccount(Integer id) {
		AccountDO entity = new AccountDO();
		entity.setId(id);
		entity.setDeleted(1);
		// TODO Auto-generated method stub
		return accountService.update(entity);
	}

	@Override
	public int updateAccountPo(AccountDO accountPo) {
		return accountService.update(accountPo);
	}

	@Override
	public int registerManager(AccountDO accountPo,String name, Integer type, Integer addAccountId) {
		try {
			// 创建账号
			int i = accountService.insert(accountPo);
			// 创建用户
			if (i == 1) {
				ManagerDO managerPo = new ManagerDO(name,accountPo.getUsername(),accountPo.getId());
				managerPo.setType(type);
				managerPo.setOwnerId(addAccountId);
				int u = managerService.insert(managerPo);
				// 创建钱包
				if (u == 1) {
					return 1;
					/*Double defaultValue = new Double("0");
					DealerPursePo dealerPursePo = new DealerPursePo(managerPo.getId(),DealerPursePo.type_admin);
					int p = dealerPurseService.insert(dealerPursePo);
					if (p == 1) {
						return 1;
					} else {
						// 失败删除账号、用户
						managerService.delete(managerPo.getId());
						accountService.delete(accountPo.getId());
					}*/
				} else {
					// 失败删除账号
					accountService.delete(accountPo.getId());
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public int registerDistributor(AccountDO accountPo,String name, Integer type, Integer addAccountId,Integer provinceId) {
		try {
			// 创建账号
			int i = accountService.insert(accountPo);
			// 创建经销商
			if (i == 1) {
				String reCode = OnlyCodeNum.getRefreeCode("", accountPo.getId());
				ManagerDO managerPo = new ManagerDO(name,accountPo.getUsername(),accountPo.getId());
				managerPo.setType(type);
				managerPo.setOwnerId(addAccountId);
				managerPo.setProvinceId(provinceId);
				managerPo.setRefereeCode(reCode);
				int u = managerService.insert(managerPo);

				RoleAccountDO roleAccountDO = new RoleAccountDO(accountPo.getId(), 3);
				roleAccountFacade.insertRoleAccountPo(roleAccountDO);
				// 创建钱包
				if (u == 1) {
//					return 1;
					ManagerPurseDO managerPursePo = new ManagerPurseDO(managerPo.getId(),ManagerDO.TYPE_DISTRIBUTOR);
					int p = managerPurseFacade.insertManagerPurse(managerPursePo);
					if (p == 1) {
						return 1;
					} else {
						// 失败删除账号、用户
						managerService.delete(managerPo.getId());
						accountService.delete(accountPo.getId());
					}
				} else {
					// 失败删除账号
					accountService.delete(accountPo.getId());
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int registerSalesman(AccountDO accountPo,String name, Integer type, Integer addAccountId) {
		try {
			// 创建账号
			int i = accountService.insert(accountPo);
			// 创建经销商
			if (i == 1) {
				String reCode = OnlyCodeNum.getRefreeCode("", accountPo.getId());
				ManagerDO managerPo = new ManagerDO(name,accountPo.getUsername(),accountPo.getId());
				managerPo.setType(type);
				managerPo.setOwnerId(addAccountId);
				managerPo.setRefereeCode(reCode);
				int u = managerService.insert(managerPo);
				// 创建钱包
				if (u == 1) {
					ManagerPurseDO managerPursePo = new ManagerPurseDO(managerPo.getId(),ManagerDO.TYPE_SALESMAN);
					int p = managerPurseFacade.insertManagerPurse(managerPursePo);
					if (p == 1) {
						return 1;
					} else {
						// 失败删除账号、用户
						managerService.delete(managerPo.getId());
						accountService.delete(accountPo.getId());
					}
				} else {
					// 失败删除账号
					accountService.delete(accountPo.getId());
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int registerBusiness(AccountDO accountPo,Map<String, Object> param) {
		try {
			// 创建账号
			int i = accountService.insert(accountPo);
			// 创建商家
			if (i == 1) {
				String reCode = OnlyCodeNum.getRefreeCode("", accountPo.getId());
				ManagerDO managerPo = new ManagerDO(param.get("name")+"",accountPo.getUsername(),accountPo.getId());
				managerPo.setType(ManagerDO.TYPE_BUSINESS);
				managerPo.setOwnerId(Integer.parseInt(param.get("addAccountId")+""));
				managerPo.setRefereeCode(reCode);
				managerPo.setContacts(param.get("contacts")+"");
				managerPo.setBusinessType(Integer.parseInt(param.get("businessType")+""));
				managerPo.setProvinceId(Integer.parseInt(param.get("provinceId")+""));
				managerPo.setLng(new BigDecimal(param.get("lng")+"").doubleValue());
				managerPo.setLat(new BigDecimal(param.get("lat")+"").doubleValue());
				managerPo.setRebate(new BigDecimal(param.get("rebate")+"").doubleValue());
				if(param.get("images") != null){
					managerPo.setImages(param.get("images")+"");
				}
				if(param.get("content") != null){
					managerPo.setContent(param.get("content")+"");
				}

				int u = managerService.insert(managerPo);

				// 创建钱包
				if (u == 1) {
//					return 1;
					ManagerPurseDO managerPursePo = new ManagerPurseDO(managerPo.getId(),ManagerDO.TYPE_BUSINESS);
					int p = managerPurseFacade.insertManagerPurse(managerPursePo);
					if (p == 1) {
						return 1;
					} else {
						// 失败删除账号、用户
						managerService.delete(managerPo.getId());
						accountService.delete(accountPo.getId());
					}
				} else {
					// 失败删除账号
					accountService.delete(accountPo.getId());
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public int registerSupplier(AccountDO accountPo,Map<String, Object> param) {
//		try {
//			// 创建账号
//			int i = accountService.insert(accountPo);
//			// 创建用户
//			if (i == 1) {
//				SupplierPo supplierPo = new SupplierPo(accountPo.getId(), param.get("supplierCode")+"",
//						param.get("name")+"", null, null, null, null, null,
//						null, param.get("contactsName")+"", param.get("contactsPhone")+"", null);
//				int u = supplierService.insert(supplierPo);
//				if (u == 1) {
//					return 1;
//				} else {
//					// 失败删除账号
//					accountService.delete(accountPo.getId());
//				}
//			}
//			return 0;
//		} catch (Exception e) {
//			e.printStackTrace();
			return -1;
//		}
	}
	@Override
	public int registerDealer(AccountDO accountPo,Map<String, Object> param) {
//		try {
//			// 创建账号
//			int i = accountService.insert(accountPo);
//			// 创建用户
//			if (i == 1) {
//				DealerPo dealerPo = new DealerPo(accountPo.getId(), 
//						param.get("name")+"", null, null, null, null, null,
//						param.get("phone")+"", param.get("contactsName")+"", param.get("contactsPhone")+"", null, param.get("dealerQQ")+"");
//				int u = dealerFacade.insertDealerPo(dealerPo);
//				if (u == 1) {
//					DealerPursePo dealerPursePo = new DealerPursePo(dealerPo.getId());
//					int p = dealerPurseFacade.insertDealerPursePo(dealerPursePo);
//					if (p == 1) {
//					    RoleAccountPo roleAccountPo = new RoleAccountPo();
//					    Integer accountId = dealerPo.getAccountId();
//					    roleAccountPo.setAccountId(accountId);
//					    Map<String, Object> map = new HashMap<String, Object>();
//					    map.put("roleName", "商户");
//					    List<RolePo> roleList = roleFacade.getList(map);
//					    RolePo rolePo = roleList.get(0);
//					    roleAccountPo.setRoleId(rolePo.getRoleId());
//					    roleAccountPo.insertInit();
//					    roleAccountFacade.insertRoleAccountPo(roleAccountPo);
//						return 1;
//					} else {
//						// 失败删除账号、用户
//						managerService.delete(dealerPo.getId());
//						accountService.delete(accountPo.getId());
//					}
//				} else {
//					// 失败删除账号
//					accountService.delete(accountPo.getId());
//				}
//			}
//			return 0;
//		} catch (Exception e) {
//			e.printStackTrace();
			return -1;
//		}
	}

	@Override
	public int registerAppCompany(AccountDO accountPo,Map<String, Object> param) {
//		try {
//			// 创建账号
//			int i = accountService.insert(accountPo);
//			// 创建用户
//			if (i == 1) {
//				AppCompanyPo appCompanyPo = new AppCompanyPo(accountPo.getId(),null, param.get("appCode")+"",
//						param.get("name")+"", null, null, null, null, null,
//						null, param.get("contactsName")+"", param.get("contactsPhone")+"", null, 
//						Integer.parseInt(param.get("scoreMall")+""), Integer.parseInt(param.get("bonusMall")+""));
//				int u = appCompanyService.insert(appCompanyPo);
//				// 创建钱包
//				if (u == 1) {
//					CompanyPursePo companyPursePo = new CompanyPursePo(appCompanyPo.getId());
//					int p = companyPurseFacade.insertCompanyPursePo(companyPursePo);
//					if (p == 1) {
//						return 1;
//					} else {
//						// 失败删除账号、用户
//						managerService.delete(appCompanyPo.getId());
//						accountService.delete(accountPo.getId());
//					}
//				} else {
//					// 失败删除账号
//					accountService.delete(accountPo.getId());
//				}
//			}
//			return 0;
//		} catch (Exception e) {
//			e.printStackTrace();
			return -1;
//		}
	}

	@Override
	public PageResult<AccountBO> getPageAccountVo(PageEntity pageEntity) {
		PageResult<AccountBO> pageResult = new PageResult<AccountBO>();
		List<AccountDO> list = accountService.getPagination(pageEntity);
		List<AccountBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (AccountDO accountPo : list) {
				
				listVo.add(view(accountPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(accountService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countAccount(Map<String, Object> param) {
		try {
			return accountService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private AccountBO view(AccountDO accountPo){
		AccountBO accountVo = new AccountBO(accountPo);
		String roleName = "暂无角色";
		String provinceName = "";
		String refereeCode = "";
		String name = "";
		int provinceId = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deleted", 0);
		param.put("accountId", accountVo.getAccountId());
		RoleAccountDO roleAccountPo = roleAccountFacade.getRoleAccountByMap(param);
		if(roleAccountPo != null){
			RoleDO role = roleFacade.getPoByPK(roleAccountPo.getRoleId());
			if(role != null){
				roleName = role.getRoleName();
			}
		}
		accountVo.setRoleName(roleName);
		ManagerDO managerDo = managerFacade.getPoByAccountId(accountVo.getAccountId());
		if(managerDo!=null){
			refereeCode = managerDo.getRefereeCode();
			name = managerDo.getName();
			if(StringUtil.isNumber(managerDo.getProvinceId()+"") && managerDo.getProvinceId()>0){
				provinceId = managerDo.getProvinceId();
				//省
				RegionDO regionDo = regionFacade.getRegionByRegionId(managerDo.getProvinceId());
				if(regionDo!=null){
					provinceName = regionDo.getRegionName();
				}
			}
		}
		accountVo.setRefereeCode(refereeCode);
		accountVo.setName(name);
		accountVo.setProvinceName(provinceName);
		accountVo.setProvinceId(provinceId);
		return accountVo;
	}
}
