package com.yada.xmlUtils;

import com.yada.enjoyIntegral.model.ConvertibleServiceReport;
import com.yada.enjoyIntegral.model.OrgChangeMxReport;
import com.yada.enjoyIntegral.model.OrgChangeSumReport;
import com.yada.enjoyIntegral.model.SupplierChangeReport;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xianyong
 */
public class ReportHeader {
    public static Map<String, String> ConvertibleServiceReportHeader = new LinkedHashMap<String, String>();
    public static Map<String, String> OrgChangeMxReportHeader = new LinkedHashMap<String, String>();
    public static Map<String, String> OrgChangeSumReportHeader = new LinkedHashMap<String, String>();
    public static Map<String, String> SupplierChangeReportHeader = new LinkedHashMap<String, String>();


    static {
        ConvertibleServiceReportHeader.put("productName", ConvertibleServiceReport.PRODUCT_NAME);
        ConvertibleServiceReportHeader.put("merNo", ConvertibleServiceReport.MER_NO);
        ConvertibleServiceReportHeader.put("merName", ConvertibleServiceReport.MER_NAME);
        ConvertibleServiceReportHeader.put("integralValue", ConvertibleServiceReport.INTEGRAL_VALUE);
        ConvertibleServiceReportHeader.put("gigtCount", ConvertibleServiceReport.GIFT_COUNT);
        ConvertibleServiceReportHeader.put("orgId", ConvertibleServiceReport.ORG_ID);
        ConvertibleServiceReportHeader.put("name", ConvertibleServiceReport.NAME);

    }
    static {
        OrgChangeMxReportHeader.put("merNo", OrgChangeMxReport.MER_NO);
        OrgChangeMxReportHeader.put("merName", OrgChangeMxReport.MER_NAME);
        OrgChangeMxReportHeader.put("integralValue", OrgChangeMxReport.INTEGRAL_VALUE);
        OrgChangeMxReportHeader.put("orgName", OrgChangeMxReport.ORG_NAME);
        OrgChangeMxReportHeader.put("orgId", OrgChangeMxReport.ORG_ID);
        OrgChangeMxReportHeader.put("pOrgId", OrgChangeMxReport.P_ORG_ID);
        OrgChangeMxReportHeader.put("pOrgName", OrgChangeMxReport.P_ORG_NAME);
        OrgChangeMxReportHeader.put("customerName", OrgChangeMxReport.CUSTOMER_NAME);
        OrgChangeMxReportHeader.put("customerPhone", OrgChangeMxReport.CUSTOMER_PHONE);
        OrgChangeMxReportHeader.put("customerCardNo", OrgChangeMxReport.CUSTOMER_CARD_NO);
        OrgChangeMxReportHeader.put("certificateNo", OrgChangeMxReport.CERTIFICATE_NO);
        OrgChangeMxReportHeader.put("terminalNo", OrgChangeMxReport.TERMINAL_NO);
        OrgChangeMxReportHeader.put("traceNo", OrgChangeMxReport.TRACE_NO);
        OrgChangeMxReportHeader.put("tranState", OrgChangeMxReport.TRAN_STATE);
        OrgChangeMxReportHeader.put("state",OrgChangeMxReport.STATE);
        OrgChangeMxReportHeader.put("tranDate", OrgChangeMxReport.TRAN_DATE);
        OrgChangeMxReportHeader.put("tranTime", OrgChangeMxReport.TRAN_TIME);
        OrgChangeMxReportHeader.put("userId", OrgChangeMxReport.USER_ID);
        OrgChangeMxReportHeader.put("userName", OrgChangeMxReport.USER_NAME);
    }
    static {
        OrgChangeSumReportHeader.put("tranDate", OrgChangeSumReport.TRAN_DATE);
        OrgChangeSumReportHeader.put("merNo", OrgChangeSumReport.MER_NO);
        OrgChangeSumReportHeader.put("merName", OrgChangeSumReport.MER_NAME);
        OrgChangeSumReportHeader.put("allAmt", OrgChangeSumReport.ALLAMT);
        OrgChangeSumReportHeader.put("allCount", OrgChangeSumReport.ALLCOUNT);
        OrgChangeSumReportHeader.put("orgName", OrgChangeSumReport.ORG_NAME);
        OrgChangeSumReportHeader.put("orgId", OrgChangeSumReport.ORG_ID);
        OrgChangeSumReportHeader.put("pOrgId", OrgChangeSumReport.P_ORG_ID);
        OrgChangeSumReportHeader.put("pOrgName", OrgChangeSumReport.P_ORG_NAME);


    }
    static {
        SupplierChangeReportHeader.put("merNo", SupplierChangeReport.MER_NO);
        SupplierChangeReportHeader.put("merName", SupplierChangeReport.MER_NAME);
        SupplierChangeReportHeader.put("customerName", SupplierChangeReport.CUSTOMER_NAME);
        SupplierChangeReportHeader.put("customerPhone", SupplierChangeReport.CUSTOMER_PHONE);
        SupplierChangeReportHeader.put("customerCardNo", SupplierChangeReport.CUSTOMER_CARD_NO);
        SupplierChangeReportHeader.put("producrName", SupplierChangeReport.PRODUCT_NAME);
        SupplierChangeReportHeader.put("integralvalue", SupplierChangeReport.INTEGRAL_VALUE);
        SupplierChangeReportHeader.put("orgId", SupplierChangeReport.ORG_ID);
        SupplierChangeReportHeader.put("orgName", SupplierChangeReport.ORG_NAME);
        SupplierChangeReportHeader.put("tranDate", SupplierChangeReport.TRAN_DATE);
        SupplierChangeReportHeader.put("tranTime", SupplierChangeReport.TRAN_TIME);

    }
}
