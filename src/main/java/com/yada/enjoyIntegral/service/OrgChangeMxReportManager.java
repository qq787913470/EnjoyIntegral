/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import com.yada.enjoyIntegral.dao.OrgChangeMxReportDaoImpl;
import com.yada.enjoyIntegral.model.OrgChangeMxReport;
import com.yada.enjoyIntegral.query.OrgChangeMxReportQuery;
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
public class OrgChangeMxReportManager extends BaseService<OrgChangeMxReport, String>{
	@Autowired
	private OrgChangeMxReportDaoImpl orgChangeMxReportDao;


	@Override
	protected BaseDao<OrgChangeMxReport, String> getBaseDao() {
		return orgChangeMxReportDao;
	}

	public List<OrgChangeMxReport>  fineAll(OrgChangeMxReportQuery query){
		return orgChangeMxReportDao.fineAll(query);
	}

}
