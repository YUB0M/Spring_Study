package org.zerock.persistence;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;


@Log4j
public class MySqlConTest {
    private static final String DRIVER = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
    private static final String URL = "jdbc:log4jdbc:mysql://localhost:3306/springstudy?serverTimezone=Asia/Seoul";
    private static final String USER = "root"; //DB 사용자명
    private static final String PW = "1234";   //DB 사용자 비밀번호

    @Test
    public void testConnection() throws Exception{
        Class.forName(DRIVER);
        try(Connection con = DriverManager.getConnection(URL, USER, PW)){
            System.out.println("성공");
            System.out.println(con);
        }catch (Exception e) {
            System.out.println("에러발생");
            e.printStackTrace();
        }
    }
}

