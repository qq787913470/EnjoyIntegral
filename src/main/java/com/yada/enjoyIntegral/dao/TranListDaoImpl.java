package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.enjoyIntegral.model.TranList;

@Component
public class TranListDaoImpl extends BaseDaoImpl<TranList, java.lang.String>{

	public List<TranList> getByTraceNo(String traceNo, String batchNo, String billNo) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("batchNo", batchNo);
		map.put("traceNo", traceNo);
		map.put("billNo", billNo);
		return this.getSqlSessionTemplate().selectList("TranList_getByTraceNo", map);
	}
	/**
	 * @Author      ： zhangQiang
	 * @Date        : 2018/4/9-14:12
	 * @Desc        : 根据流水编号查询多笔流水
	 * @param tranId: 流水编号
	 */
	public List<TranList> getTranListByTranId(String tranId){
		return this.getSqlSessionTemplate().selectList("TranList_findListByTranId",tranId);
	}
}
