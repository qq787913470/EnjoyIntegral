package com.yada.enjoyIntegral.dao;

import org.springframework.stereotype.Component;

import com.yada.enjoyIntegral.model.MerchantEnjoy;
import com.yada.security.base.BaseDaoImpl;

import java.util.HashMap;
import java.util.Map;

@Component
public class MerchantEnjoyDaoImpl extends BaseDaoImpl<MerchantEnjoy, java.lang.String>{
    public void updateReview(Map<String,Object> map){
        this.getSqlSessionTemplate().update("MerchantEnjoy_update_review",map);
    }
    public void updateReason(String merNo,String serviceStare,String closeReason){
        Map<String,String> map = new HashMap();
        map.put("merNo",merNo);
        map.put("serviceStare",serviceStare);
        map.put("closeReason",closeReason);
        this.getSqlSessionTemplate().update("MerchantEnjoy_updateReason",map);
    }
}
