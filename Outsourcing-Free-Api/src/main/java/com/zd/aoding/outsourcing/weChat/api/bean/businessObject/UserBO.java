package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;

@ApiModel(value = "", description = "账号展示类")
public class UserBO {
	/** 分页数据 */
	public static final String TO_PAGING = "paging";
	/** 全部信息 */
	public static final String TO_ALL = "all";
	@ApiModelProperty("用户id")
	private Integer userId;
	@ApiModelProperty("帐号id")
	private Integer accountId;
	@ApiModelProperty("用户名")
	private String username;
	@ApiModelProperty("实名认证（1是0否）")
	private String realNameCheck;
	@ApiModelProperty("推荐code")
	private String refreeCode;
	@ApiModelProperty("昵称")
	private String nickName;
	@ApiModelProperty("生日")
	private String birthday;
	@ApiModelProperty("等级")
	private Integer gradeSort;// 1、2、数字等级
	@ApiModelProperty("积分")
	private Integer vipScore;
	@ApiModelProperty("电话")
	private String phone;
	@ApiModelProperty("电话")
	private String phoneStr;
	@ApiModelProperty("邮箱")
	private Integer email;
	@ApiModelProperty("地址")
	private String address;
	@ApiModelProperty("是否能交易")
	private String canTrade;
	@ApiModelProperty(value = "是否能交易", hidden = true)
	private String toWhat;
	@ApiModelProperty(value = "用户类")
	private UserDO userPo;
	@ApiModelProperty(value = "注册时间")
	private String createTimeStr;
	@ApiModelProperty(value = "注册平台")
	private String createApp;
	@ApiModelProperty(value = "性别")
	private Integer sex;
	@ApiModelProperty("积分")
	private Double score;
	@ApiModelProperty("积分")
	private Double bonus;
	@ApiModelProperty("推荐人")
	private String refereeUsername;
	@ApiModelProperty("时间")
	private Date createTime;
	@ApiModelProperty("头像")
	private String portrait;
	

	public UserBO(UserDO userPo, String toWhat) {
		super();
		this.toWhat = toWhat;
		switch (toWhat) {
			case TO_PAGING:
				toPaging(userPo);
				break;
			case TO_ALL:
				toAll(userPo);
				break;
			default:
				toPaging(userPo);
		}
	}
	private void toAll(UserDO userPo) {
		this.userId = userPo.getId();
		this.accountId = userPo.getAccountId();
		this.refreeCode = userPo.getRefreeCode();
		this.nickName = userPo.getNickName();
		this.birthday = DateUtil.Format(DateUtil.CN_FORMAT_NYR, userPo.getBirthday());
		this.gradeSort = userPo.getGradeSort();
		this.phone = userPo.getPhone();
		String phone = userPo.getPhone();
		if (phone != null && phone.length() == 11) {
			this.phoneStr = phone.substring(0, 3) + "****" + phone.substring(7, 11);
		} else {
			this.phoneStr = "未完善";
		}
		this.sex = userPo.getSex();
		this.createTime = userPo.getCreateTime();
		String address = userPo.getAddress();
		if(address!=null && address!=""){
			this.address = address;
		}else{
			this.address = "";
		}
		String portrait = userPo.getPortrait();
		if(portrait!=null && portrait!=""){
			this.portrait = Config.IMG_SERVER+portrait;
		}else{
			this.portrait = "";
		}
	}
	private void toPaging(UserDO userPo) {
		this.userId = userPo.getId();
		this.accountId = userPo.getAccountId();
		this.refreeCode = userPo.getRefreeCode();
		this.nickName = userPo.getNickName();
		this.birthday = DateUtil.Format(DateUtil.CN_FORMAT_NYR, userPo.getBirthday());
		this.gradeSort = userPo.getGradeSort();
		this.vipScore = userPo.getVipScore();
		this.phone = userPo.getPhone();
		String phone = userPo.getPhone();
		if (phone != null && phone.length() == 11) {
			this.phoneStr = phone.substring(0, 3) + "****" + phone.substring(7, 11);
		} else {
			this.phoneStr = "未完善";
		}
		this.email = userPo.getEmail();
		String address = userPo.getAddress();
		if(address!=null && address!=""){
			this.address = address;
		}else{
			this.address = "";
		}
		this.userPo = userPo;
		this.sex = userPo.getSex();
		this.createTime = userPo.getCreateTime();
		this.portrait = userPo.getPortrait();
		String portrait = userPo.getPortrait();
		if(portrait!=null && portrait!=""){
			this.portrait = Config.IMG_SERVER+portrait;
		}else{
			this.portrait = "";
		}
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getRealNameCheck() {
		return realNameCheck;
	}
	public void setRealNameCheck(String realNameCheck) {
		this.realNameCheck = realNameCheck;
	}
	public String getRefreeCode() {
		return refreeCode;
	}
	public void setRefreeCode(String refreeCode) {
		this.refreeCode = refreeCode;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getGradeSort() {
		return gradeSort;
	}
	public void setGradeSort(Integer gradeSort) {
		this.gradeSort = gradeSort;
	}
	public Integer getVipScore() {
		return vipScore;
	}
	public void setVipScore(Integer vipScore) {
		this.vipScore = vipScore;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getEmail() {
		return email;
	}
	public void setEmail(Integer email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCanTrade() {
		return canTrade;
	}
	public void setCanTrade(String canTrade) {
		this.canTrade = canTrade;
	}
	public UserDO getUserDO() {
		return userPo;
	}
	public void setUserDO(UserDO userPo) {
		this.userPo = userPo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getCreateApp() {
		return createApp;
	}
	public void setCreateApp(String createApp) {
		this.createApp = createApp;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public String getRefereeUsername() {
		return refereeUsername;
	}
	public void setRefereeUsername(String refereeUsername) {
		this.refereeUsername = refereeUsername;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getPhoneStr() {
		return phoneStr;
	}
	public void setPhoneStr(String phoneStr) {
		this.phoneStr = phoneStr;
	}
}
