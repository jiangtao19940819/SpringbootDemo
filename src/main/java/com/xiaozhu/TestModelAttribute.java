package com.xiaozhu;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TestModelAttribute {
    @ModelAttribute(value="userInfo")
    public Map<String,String> setGlobalConfig(){
        Map<String,String> globalConfig = new HashMap<String,String>();
        globalConfig.put("name","jiangtao");
        globalConfig.put("sex","man");
        return globalConfig;
    }

}
