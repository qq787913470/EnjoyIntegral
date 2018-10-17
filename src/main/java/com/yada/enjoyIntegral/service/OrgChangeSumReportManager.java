/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import com.yada.enjoyIntegral.dao.OrgChangeSumReportDaoImpl;
import com.yada.enjoyIntegral.model.OrgChangeSumReport;
import com.yada.enjoyIntegral.query.OrgChangeSumReportQuery;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class OrgChangeSumReportManager extends BaseService<OrgChangeSumReport, String>{
	@Autowired
	private OrgChangeSumReportDaoImpl orgChangeSumReportDao;


	@Override
	protected BaseDao<OrgChangeSumReport, String> getBaseDao() {
		return orgChangeSumReportDao;
	}

	public List<OrgChangeSumReport>  fineAll(OrgChangeSumReportQuery query){
		return orgChangeSumReportDao.fineAll(query);
	}

}
