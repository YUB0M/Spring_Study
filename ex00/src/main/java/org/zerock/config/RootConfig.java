package org.zerock.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

//@ComponentScan(basePackages = {"org.zerock.sample"}) -> Java를 이용한 의존성 주입
@Configuration
@ComponentScan(basePackages = {"org.zerock.sample"})
@MapperScan(basePackages = {"org.zerock.mapper"})
public class RootConfig {

    //HikariConfig
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        //hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/springstudy?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
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
