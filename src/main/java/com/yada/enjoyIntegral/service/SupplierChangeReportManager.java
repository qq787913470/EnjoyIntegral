/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import com.yada.enjoyIntegral.dao.OrgChangeSumReportDaoImpl;
import com.yada.enjoyIntegral.dao.SupplierChangeReportDaoImpl;
import com.yada.enjoyIntegral.model.OrgChangeSumReport;
import com.yada.enjoyIntegral.model.SupplierChangeReport;
import com.yada.enjoyIntegral.query.OrgChangeSumReportQuery;
import com.yada.enjoyIntegral.query.SupplierChangeReportQuery;
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
public class SupplierChangeReportManager extends BaseService<SupplierChangeReport, String>{
	@Autowired
	private SupplierChangeReportDaoImpl supplierChangeReportDao;


	@Override
	protected BaseDao<SupplierChangeReport, String> getBaseDao() {
		return supplierChangeReportDao;
	}

	public List<SupplierChangeReport>  fineAll(SupplierChangeReportQuery query){
		return supplierChangeReportDao.fineAll(query);
	}

}
