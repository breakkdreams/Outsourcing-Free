package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsOptionBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;

public interface GoodsOptionFacade {
	/**
	 * @Title: getOptionPoBySpecIds
	 * @Description: 根据id查询产品配置祝组合信息
	 * @param specItemIds
	 * @param goodsId
	 * @return
	 * @return: GoodsOptionPo
	 */
	GoodsOptionBO getOptionVoBySpecIds(String specItemIds, String goodsId);
	List<GoodsOptionDO> getOptionPoByGoodsId(Integer goodsId);
	List<GoodsOptionBO> getOptionBOByGoodsId(Integer goodsId);
	/**
	 * @Title: getOptionVoByPK
	 * @Description: 根据id查询option
	 * @param optionId
	 * @return
	 * @return: GoodsOptionVo
	 */
	GoodsOptionBO getOptionVoByPK(String optionId);
	GoodsOptionBO getOptionVoByPK(Integer optionId);
	GoodsOptionDO getOptionPoByPK(Integer optionId);
	int insertOptionPo(GoodsOptionDO goodsOptionPo);
	/**
	 * @Title: deletedAllOptionByGoodsId
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	PageResult<GoodsOptionBO> getPageOption(PageEntity pageEntity);
	int updateAllOptionByGoodsId(String goodsId, Double price, Double purchasePrice, Integer stock, Integer showStock);
	int updateOptionByPk(GoodsOptionDO goodsOptionPo);
	
	GoodsOptionDO findGoodsOptionByBarCode(String barCode);
	
	 int deletedAllOptionByGoodsId(String goodsId);
	 
	 int updateGoodsOptionPo(GoodsOptionDO optionPo);
	 
	 List<GoodsOptionDO> getGoodsOptionByPatam(Map<String, Object> param);
}
