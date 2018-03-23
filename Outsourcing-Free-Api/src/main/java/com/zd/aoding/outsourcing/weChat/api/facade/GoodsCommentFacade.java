package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsCommentBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsCommentDO;


public interface GoodsCommentFacade {

	/**
	 * 添加
	 * @param goodsCommentPo
	 * @return
	 */
	int insertGoodsCommentPo(GoodsCommentDO goodsCommentPo);

	/**
	 * 根据主键查询
	 * @param goodModelId
	 * @return
	 */
	GoodsCommentDO getGoodsCommentPoByPK(String goodCommentId);
	GoodsCommentBO getGoodsCommentVoByPK(Integer goodCommentId);

	/**
	 * 修改
	 * @param goodsCommentPo
	 * @return
	 */
	int updateGoodsCommentPo(GoodsCommentDO goodsCommentPo);

	/**
	 * 分页查询
	 * @param pageEntity
	 * @return
	 */
	PageResult<GoodsCommentBO> getPageGoodsCommentVo(PageEntity pageEntity);

	/**
	 * 统计
	 * @param param
	 * @return
	 */
	int countGoodsComment(Map<String, Object> param);

	/**
	 * 查询全部
	 * @param param
	 * @return
	 */
	List<GoodsCommentDO> getGoodsCommentPoList(Map<String, Object> param);
	
	List<GoodsCommentBO> getGoodsCommentList(Map<String, Object> param);
}
