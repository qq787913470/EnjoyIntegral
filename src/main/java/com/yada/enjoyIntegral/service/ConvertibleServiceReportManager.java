/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import com.yada.enjoyIntegral.dao.ActivityDaoImpl;
import com.yada.enjoyIntegral.dao.ConvertibleServiceReportDaoImpl;
import com.yada.enjoyIntegral.dao.CustomerActivityDaoImpl;
import com.yada.enjoyIntegral.dao.CustomerInfoDaoImpl;
import com.yada.enjoyIntegral.model.Activity;
import com.yada.enjoyIntegral.model.ConvertibleServiceReport;
import com.yada.enjoyIntegral.model.CustomerActivity;
import com.yada.enjoyIntegral.model.CustomerInfo;
import com.yada.enjoyIntegral.query.ConvertibleServiceReportQuery;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class ConvertibleServiceReportManager extends BaseService<ConvertibleServiceReport, String>{
	@Autowired
	private ConvertibleServiceReportDaoImpl convertibleServiceReportDao;


	@Override
	protected BaseDao<ConvertibleServiceReport, String> getBaseDao() {
		return convertibleServiceReportDao;
	}

	public List<ConvertibleServiceReport>  fineAll(ConvertibleServiceReportQuery query){
		return convertibleServiceReportDao.fineAll(query);
	}

}
