package com.yada.security.conteoller.customPropertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.yada.security.model.Role;

public class RolePropertiesEditor extends PropertyEditorSupport {

	public void setAsText(String text)  
    {  
        if (text == null || !StringUtils.hasText(text)) {  
            throw new IllegalArgumentException("参数不能为空！");  
        }  
        else 
        {  
        	Role role = new Role();
        	role.setRoleId(Long.parseLong(text));
            setValue(role);
              
        }  
    }   

}

