package com.yada.enjoyIntegral.dao;

import org.springframework.stereotype.Component;

import com.yada.enjoyIntegral.model.MerchantEnjoy;
import com.yada.security.base.BaseDaoImpl;

import java.util.Map;

@Component
public class MerchantEnjoyDaoImpl extends BaseDaoImpl<MerchantEnjoy, java.lang.String>{
    public void updateReview(Map<String,Object> map){
        this.getSqlSessionTemplate().update("MerchantEnjoy_update_review",map);
    }
}
