package com.yada.enjoyIntegral.dao;

import com.yada.enjoyIntegral.model.OrgChangeMxReport;
import com.yada.enjoyIntegral.model.OrgChangeSumReport;
import com.yada.enjoyIntegral.query.OrgChangeMxReportQuery;
import com.yada.enjoyIntegral.query.OrgChangeSumReportQuery;
import com.yada.security.base.BaseDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrgChangeMxReportDaoImpl extends BaseDaoImpl<OrgChangeMxReport, String>{
	public List<OrgChangeMxReport> fineAll(OrgChangeMxReportQuery query){
		return this.getSqlSessionTemplate().selectList("OrgChangeMxReport_findListByWhere",query);
	}
}
