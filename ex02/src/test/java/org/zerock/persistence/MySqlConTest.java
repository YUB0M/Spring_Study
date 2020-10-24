package org.zerock.persistence;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;


@Log4j
public class MySqlConTest {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/springstudy?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PW = "1234";

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

