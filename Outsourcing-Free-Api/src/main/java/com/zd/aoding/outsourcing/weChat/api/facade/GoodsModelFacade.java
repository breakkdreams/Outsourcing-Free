package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsModelBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;


public interface GoodsModelFacade {

	/**
	 * 添加
	 * @param goodsModelPo
	 * @return
	 */
	int insertGoodsModelPo(GoodsModelDO goodsModelPo);

	/**
	 * 根据主键查询
	 * @param goodModelId
	 * @return
	 */
	GoodsModelDO getGoodsModelPoByPK(String goodModelId);
	GoodsModelBO getGoodsModelVoByPK(Integer goodModelId);

	/**
	 * 修改
	 * @param goodsModelPo
	 * @return
	 */
	int updateGoodsModelPo(GoodsModelDO goodsModelPo);

	/**
	 * 分页查询
	 * @param pageEntity
	 * @return
	 */
	PageResult<GoodsModelBO> getPageGoodsModelVo(PageEntity pageEntity);

	/**
	 * 统计
	 * @param param
	 * @return
	 */
	int countGoodsModel(Map<String, Object> param);

	/**
	 * 查询全部
	 * @param param
	 * @return
	 */
	List<GoodsModelDO> getGoodsModelPoList(Map<String, Object> param);
	
	List<GoodsModelBO> getGoodsModelList(Map<String, Object> param);
}
