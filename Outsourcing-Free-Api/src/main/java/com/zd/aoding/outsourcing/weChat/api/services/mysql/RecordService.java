package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;


public interface RecordService {
	/**
	 * @Title: getPurseRecordMapperList
	 * @Description: 查询钱包操作记录
	 * @param param
	 * @return
	 * @return: List<PurseRecordMapper>
	 */
	public List<RecordsDO> getPageRecord(PageEntity pageEntity);

	/**
	 * @Title: insertMapper
	 * @Description:插入指定类型记录
	 * @param classSimpleName
	 * @param Entity
	 * @return
	 * @return: int
	 */
	public int insert(RecordsDO Entity);

	public RecordsDO get(Integer recordId, Integer lever);

	/**
	 * 
	 * @Title: countRecord
	 * @Description: 统计
	 * @param recordLever
	 *            普通记录还是重要记录
	 * @return
	 * @return: int
	 */
	int countRecord(Integer recordLever);

	/**
	 * 
	 * @Title: countRecord
	 * @Description: 条件统计
	 * @param recordLever
	 *            普通记录还是重要记录
	 * @param param
	 * @return
	 * @return: int
	 */
	int countRecord(Integer recordLever, Map<String, Object> param);
	int countRecordByMap(Map<String, Object> param);

}
