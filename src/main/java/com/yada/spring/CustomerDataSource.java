package com.yada.spring;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Sungang on 2018/10/11.
 */
public class CustomerDataSource extends DataSource {
    private final static Logger log = LoggerFactory
            .getLogger(CustomerDataSource.class);
    private String sourceFilePath;

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void initDataSource() {
        if (StringUtils.isNotEmpty(sourceFilePath)) {
            log.info("读取存储数据库密码的文件" + sourceFilePath);
            try {
                Resource resource = new FileSystemResource(sourceFilePath);
                BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
                String username = this.getPoolProperties().getUsername();
                String password = "";
                String connStr = "";
                while((connStr=reader.readLine())!=null){
                    if (connStr.startsWith(username)) {
                        password = connStr.split(" ")[1].trim();
                        break;
                    }

                } ;
                log.info("获取新的数据库密码");
                if (StringUtils.isNotEmpty(password))
                    this.getPoolProperties().setPassword(password);
                reader.close();
            } catch (IOException e) {
                log.error("获取文件密码失败，使用默认的数据库密码", e);
            }
        }
    }
}
