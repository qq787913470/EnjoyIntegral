package com.yada.enjoyIntegral.dao;

import com.yada.enjoyIntegral.model.ConvertibleServiceReport;
import com.yada.enjoyIntegral.query.ConvertibleServiceReportQuery;
import com.yada.security.base.BaseDaoImpl;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ConvertibleServiceReportDaoImpl extends BaseDaoImpl<ConvertibleServiceReport, String>{
	public List<ConvertibleServiceReport> fineAll(ConvertibleServiceReportQuery query){
		return this.getSqlSessionTemplate().selectList("ConvertibleServiceReport_findListByWhere",query);
	}
}
