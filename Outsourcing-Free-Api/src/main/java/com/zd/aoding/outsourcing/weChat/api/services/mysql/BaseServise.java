package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BasePo;

public interface BaseServise<T extends BasePo> {
	/**
	 * @Title: insertMapper
	 * @Description: 插入数据
	 * @param Entity
	 * @return设定文件
	 * @return int 返回类型
	 */
	 abstract int insert(T entity);

	/**
	 * @Title: update
	 * @Description: 根据主键更新mapper
	 * @param mapper
	 * @return设定文件
	 * @return int 返回类型
	 */
	 abstract int update(T entity);

	/**
	 * @Title: countMapper
	 * @Description: 统计数据总条数
	 * @return设定文件
	 * @return int 返回类型
	 */

	 abstract int count();

	/**
	 * @Title: countMapper
	 * @Description: 根据条件，统计数据总条数
	 * @param param
	 * @return设定文件
	 * @return int 返回类型
	 */
	 abstract int count(Map<String, Object> params);

	/**
	 * @Title: getMapperByPK
	 * @Description: 主键查询Mapper
	 * @param primaryKey
	 * @return设定文件
	 * @return T 返回类型
	 */
	 abstract T get(Integer primaryKey);

	/**
	 * @Title: getMapperList
	 * @Description: 根据条件查询
	 * @param param
	 * @return设定文件
	 * @return List<T> 返回类型
	 */
	 abstract List<T> getList(Map<String, Object> param);

	/**
	 * @Title: getMapperPage
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return设定文件
	 * @return List<T> 返回类型
	 */
	 abstract List<T> getPagination(PageEntity pageEntity);

}
