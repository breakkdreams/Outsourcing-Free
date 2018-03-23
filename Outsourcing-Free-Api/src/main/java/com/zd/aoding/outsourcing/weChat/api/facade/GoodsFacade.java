package com.zd.aoding.outsourcing.weChat.api.facade;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;

public interface GoodsFacade {
	/**
	 * @Title: getPageGoodsVo
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<GoodsVo>
	 */
	PageResult<GoodsBO> getPageGoodsVo(PageEntity pageEntity);
	
	PageResult<GoodsBO> getPageGoodsVoByParam(PageEntity pageEntity);
	/**
	 * @Title: getGoodsVoByPK
	 * @Description: id查询
	 * @param goodsId
	 * @return
	 * @return: GoodsVo
	 */
	GoodsBO getGoodsVoByPK(String goodsId);
	GoodsDO getGoodsPoByPK(Integer goodsId);

	/**
	 * @Title: changeGoodsDoStatus
	 * @Description: TODO
	 * @param goodsId
	 * @param status
	 * @return
	 * @return: int
	 */
	int changeGoodsPoStatus(Integer goodsId, Integer status);
	/**
	 * @Title: insertGoodsDo
	 * @Description: 添加产品
	 * @param goodsPo
	 * @return
	 * @return: int
	 */
	int insertGoodsPo(GoodsDO goodsPo);
	
	/**
	 * @Title: updateGoodsDo 
	 * @Description: 编辑产品
	 * @param goodsPo
	 * @return
	 * @return: int
	 */
	int updateGoodsPo(GoodsDO goodsPo);
	/**
	 * @Title: agreeGoods 
	 * @Description: 产品审核通过
	 * @param goodsId
	 * @return
	 * @return: int
	 */
	//int agreeGoods(Integer goodsId);
	/**
	 * @Title: refuseGoods 
	 * @Description: 产品审核拒绝
	 * @param goodsId
	 * @return
	 * @return: int
	 */
	//int refuseGoods(Integer goodsId, String rejectreason);
	
	/**
	 * @Title: getGoodsList 
	 * @Description: 获取全部的商品
	 * @return
	 * @return: List<GoodsDo>
	 */
	List<GoodsDO> getGoodsList(Map<String, Object> param);
	
	/**
	 * @Title: countGoods 
	 * @Description: 获取商品数
	 * @return
	 * @return: List<GoodsDo>
	 */
	int countGoods(Map<String, Object> param);
	
	/**
	 * @Title: countModelGoods 
	 * @Description: 统计模块中产品数量
	 * @param modelId
	 * @return
	 * @return: int
	 */
	int countModelGoods(Integer modelId);
	
	/**
	 * @Title: 导入excel
	 * @param file	文件
	 * @return
	 */
	public List<Map<String, Object>> saveExcel(File file);
	
	int changeGoodsDoStatus(Integer goodsId, Integer status);
}
