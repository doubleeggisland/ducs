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
@MapperScan(basePackages = { "com.ioiox.dei.duc.db.mapper.slave" }, sqlSessionTemplateRef = "ducSlaveMysqlSqlSessionTemplate")
public class DucSlaveDataSourceCfg {

    private static final Logger logger = LoggerFactory.getLogger(DucSlaveDataSourceCfg.class);

    @Value("${duc.jdbc.trvl.base.slave.driver-class-name}")
    private String driverClassName;
    @Value("${duc.jdbc.trvl.base.slave.url}")
    private String jdbcUrl;
    @Value("${duc.jdbc.trvl.base.slave.username}")
    private String username;
    @Value("${duc.jdbc.trvl.base.slave.password}")
    private String password;

    @Bean(name = "ducSlaveDataSource")
    public DataSource ducSlaveDataSource() {
        if (logger.isInfoEnabled()) {
            logger.info("prepare to instantiate ducSlaveDataSource");
        }
        DruidDataSource ducSlaveDataSource = new DruidDataSource();
        ducSlaveDataSource.setDriverClassName(driverClassName);
        ducSlaveDataSource.setUrl(jdbcUrl);
        ducSlaveDataSource.setUsername(username);
        ducSlaveDataSource.setPassword(password);
        ducSlaveDataSource.setValidationQuery("select 1");
        return ducSlaveDataSource;
    }


    private Resource[] resolveMapperLocations() {
        final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        final List<String> mapperLocations = Collections.singletonList("classpath*:mybatis/mapper/duc/slave/**/*.xml");
        final List<Resource> resources = new ArrayList<>();
        for (final String mapperLocation : mapperLocations) {
            try {
                resources.addAll(Arrays.asList(resourceResolver.getResources(mapperLocation)));
            } catch (IOException ignore) {
            }
        }
        return resources.toArray(new Resource[] {});
    }

    @Bean(name = "ducSlaveMysqlSqlSessionFactory")
    public SqlSessionFactory ducSlaveMysqlSqlSessionFactory(@Qualifier("ducSlaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean ducSlaveMysqlSqlSessionFactory = new SqlSessionFactoryBean();
        ducSlaveMysqlSqlSessionFactory.setDataSource(dataSource);
        ducSlaveMysqlSqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/config/configuration.xml"));
        ducSlaveMysqlSqlSessionFactory.setMapperLocations(resolveMapperLocations());
        return ducSlaveMysqlSqlSessionFactory.getObject();
    }

    @Bean(name = "ducSlaveMysqlTransactionManager")
    public DataSourceTransactionManager ducSlaveMysqlTransactionManager(@Qualifier("ducSlaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ducSlaveMysqlSqlSessionTemplate")
    public SqlSessionTemplate ducSlaveMysqlSqlSessionTemplate(@Qualifier("ducSlaveMysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
