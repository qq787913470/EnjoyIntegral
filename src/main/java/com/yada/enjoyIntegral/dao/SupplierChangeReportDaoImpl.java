package com.yada.enjoyIntegral.dao;

import com.yada.enjoyIntegral.model.OrgChangeSumReport;
import com.yada.enjoyIntegral.model.SupplierChangeReport;
import com.yada.enjoyIntegral.query.OrgChangeSumReportQuery;
import com.yada.enjoyIntegral.query.SupplierChangeReportQuery;
import com.yada.security.base.BaseDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplierChangeReportDaoImpl extends BaseDaoImpl<SupplierChangeReport, String>{
	public List<SupplierChangeReport> fineAll(SupplierChangeReportQuery query){
		return this.getSqlSessionTemplate().selectList("SupplierChangeReport_findListByWhere",query);
	}
}
