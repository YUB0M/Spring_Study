package org.zerock.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"org.zerock.domain"})
@MapperScan(basePackages = {"org.zerock.mapper"})
public class RootConfig {

    //HikariConfig
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springstudy?serverTimezone=Asia/Seoul");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("1234");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

    //SQLSessionFactory - 스프링과 연동 작업 처리하는 mybatis-spring 라이브러리 클래스
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws  Exception {
        SqlSessionFactoryBean sqlSessionFactory = new
                SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

}
