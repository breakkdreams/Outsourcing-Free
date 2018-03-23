//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import com.wordnik.swagger.annotations.ApiModelProperty;
//import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
//import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
//
///**   
// * Copyright © 2017 嘉兴市奥丁网络科技有限公司. All rights reserved.
// * 
// * @Title: Test.java 
// * @Prject: Outsourcing-WeChat-Web
// * @Package:  
// * @Description: TODO
// * @author: HCD   
// * @date: 2017年12月29日 下午2:30:02 
// * @version: V1.0   
// */
///**
// * @ClassName: Test
// * @Description: TODO
// * @author: HCD
// * @date: 2017年12月29日 下午2:30:02
// */
//public class Test {
//	public static void main(String[] args) {
//		
//		//总人数
//		int userAllNum = 1000;
//		//奖项总人数
//		int prizeAllNum = 20;
//		//客户获奖人数
//		int customerPrizeNum = 10;
//		//员工获奖数
//		int staffPrizeNum = 10;
//		
//		List<UserDO> userListCUS = new ArrayList<UserDO>();
//		List<UserDO> userListSTA = new ArrayList<UserDO>();
//		UserDO userDO = new UserDO();
//		
//		BigDecimal y = BigDecimal.valueOf(50.00/1000);
//		
//		int userId = 0;
//		int x = 10;
//		int z = 0;
//		//添加参与抽奖用户
//		for (int i = 0; i < userAllNum; i++) {
//			userDO = new UserDO();
//			userDO.setUserId(userId++);
//			userDO.setNickName("admin"+i);
//			if(i <= 230){
//				userDO.setUserType(UserDO.USER_STAFF_TYPE);
//				userDO.setProbability(new BigDecimal(0.003));
//				userListSTA.add(userDO);
//			}else{
//				z++;
//				if(z <= 10){
//					userDO.setProbability(new BigDecimal(0.003));
//				}else{
//					userDO.setProbability(new BigDecimal(0.003));
//				}
//				userDO.setUserType(UserDO.USER_VIP_TYPE);
//				userListCUS.add(userDO);
//			}
//			//System.out.println("名称："+userDO.getNickName()+"-----类型："+userDO.getUserType()+"----概率："+userDO.getProbability());
//			
//		}
//		//员工和贵宾总人数
//		int stsAllNum = userListSTA.size();
//		int cusAllNum = userListCUS.size();
//		
//		//抽取员工中奖
//		Random rand = new Random();
//		
//        Set<UserDO>  set =new HashSet<>();
//        int pp = 0;
//        do {
//        	int rInt = rand.nextInt(stsAllNum);
//        	set.add(userListSTA.get(rInt));
//        	//System.out.println("set.size()---:"+set.size());
//        	//System.out.println("用户---:"+userListSTA.get(rInt).toString());
//        	pp = set.size();
//        	pp++;
//		} while (pp <= staffPrizeNum);
//		int xu = 0;
//        for( Iterator   it = set.iterator();  it.hasNext(); ) { 
//        	UserDO u = (UserDO)it.next();
//        	xu++;
//            System.out.println("序号："+xu+"--中奖用户名："+u.getNickName());              
//        }
//        
//		
//	}
//}
