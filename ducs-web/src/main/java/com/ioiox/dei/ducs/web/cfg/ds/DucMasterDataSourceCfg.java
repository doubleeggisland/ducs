package com.ioiox.dei.ducs.web.cfg.ds;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@MapperScan(basePackages = { "com.ioiox.dei.duc.db.mapper.master" }, sqlSessionTemplateRef = "ducMasterMysqlSqlSessionTemplate")
public class DucMasterDataSourceCfg {

    private static final Logger logger = LoggerFactory.getLogger(DucMasterDataSourceCfg.class);

    @Value("${duc.jdbc.trvl.base.master.driver-class-name}")
    private String driverClassName;
    @Value("${duc.jdbc.trvl.base.master.url}")
    private String jdbcUrl;
    @Value("${duc.jdbc.trvl.base.master.username}")
    private String username;
    @Value("${duc.jdbc.trvl.base.master.password}")
    private String password;

    @Primary
    @Bean(name = "ducMasterDataSource")
    public DataSource ducMasterDataSource() {
        if (logger.isInfoEnabled()) {
            logger.info("prepare to instantiate ducMasterDataSource");
        }
        DruidDataSource ducMasterDataSource = new DruidDataSource();
        ducMasterDataSource.setDriverClassName(driverClassName);
        ducMasterDataSource.setUrl(jdbcUrl);
        ducMasterDataSource.setUsername(username);
        ducMasterDataSource.setPassword(password);
        ducMasterDataSource.setValidationQuery("select 1");
        return ducMasterDataSource;
    }


    private Resource [] resolveMapperLocations() {
        final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        final List<String> mapperLocations = Collections.singletonList("classpath*:mybatis/mapper/duc/master/**/*.xml");
        final List<Resource> resources = new ArrayList<>();
        for (final String mapperLocation : mapperLocations) {
            try {
                resources.addAll(Arrays.asList(resourceResolver.getResources(mapperLocation)));
            } catch (IOException ignore) {
            }
        }
        return resources.toArray(new Resource[] {});
    }

    @Primary
    @Bean(name = "ducMasterMysqlSqlSessionFactory")
    public SqlSessionFactory ducMasterMysqlSqlSessionFactory(@Qualifier("ducMasterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean ducMasterMysqlSqlSessionFactory = new SqlSessionFactoryBean();
        ducMasterMysqlSqlSessionFactory.setDataSource(dataSource);
        ducMasterMysqlSqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/config/configuration.xml"));
        ducMasterMysqlSqlSessionFactory.setMapperLocations(resolveMapperLocations());
        return ducMasterMysqlSqlSessionFactory.getObject();
    }

    @Primary
    @Bean(name = "ducMasterMysqlTransactionManager")
    public DataSourceTransactionManager ducMasterMysqlTransactionManager(@Qualifier("ducMasterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "ducMasterMysqlSqlSessionTemplate")
    public SqlSessionTemplate ducMasterMysqlSqlSessionTemplate(@Qualifier("ducMasterMysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

