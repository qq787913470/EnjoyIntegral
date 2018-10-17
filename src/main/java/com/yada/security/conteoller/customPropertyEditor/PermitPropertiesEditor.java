package com.yada.security.conteoller.customPropertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.yada.security.model.Permit;


public class PermitPropertiesEditor extends PropertyEditorSupport {

	public void setAsText(String text)  
    {  
        if (text == null || !StringUtils.hasText(text)) {  
            throw new IllegalArgumentException("参数不能为空！");  
        }  
        else 
        {  
        	Permit permit = new Permit();
        	permit.setPermitId(Long.parseLong(text));
            setValue(permit);
              
        }  
    }   

}

