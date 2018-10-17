package com.yada.enjoyIntegral.dao;

import com.yada.enjoyIntegral.model.ConvertibleServiceReport;
import com.yada.enjoyIntegral.model.OrgChangeSumReport;
import com.yada.enjoyIntegral.query.ConvertibleServiceReportQuery;
import com.yada.enjoyIntegral.query.OrgChangeSumReportQuery;
import com.yada.security.base.BaseDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrgChangeSumReportDaoImpl extends BaseDaoImpl<OrgChangeSumReport, String>{
	public List<OrgChangeSumReport> fineAll(OrgChangeSumReportQuery query){
		return this.getSqlSessionTemplate().selectList("OrgChangeSumReport_findListByWhere",query);
	}
}
