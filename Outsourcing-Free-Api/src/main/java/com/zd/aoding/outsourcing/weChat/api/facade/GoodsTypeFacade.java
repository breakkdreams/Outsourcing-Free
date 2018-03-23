package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsTypeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsTypeDO;


public interface GoodsTypeFacade {

	/**
	 * 添加
	 * @param goodsTypePo
	 * @return
	 */
	int insertGoodsTypePo(GoodsTypeDO goodsTypePo);

	/**
	 * 根据主键查询
	 * @param goodModelId
	 * @return
	 */
	GoodsTypeDO getGoodsTypePoByPK(String goodModelId);
	GoodsTypeBO getGoodsTypeVoByPK(Integer goodModelId);

	/**
	 * 修改
	 * @param goodsTypePo
	 * @return
	 */
	int updateGoodsTypePo(GoodsTypeDO goodsTypePo);

	/**
	 * 分页查询
	 * @param pageEntity
	 * @return
	 */
	PageResult<GoodsTypeBO> getPageGoodsTypeVo(PageEntity pageEntity);

	/**
	 * 统计
	 * @param param
	 * @return
	 */
	int countGoodsType(Map<String, Object> param);

	/**
	 * 查询全部
	 * @param param
	 * @return
	 */
	List<GoodsTypeDO> getGoodsTypePoList(Map<String, Object> param);
	
	List<GoodsTypeBO> getGoodsTypeList(Map<String, Object> param);
}
