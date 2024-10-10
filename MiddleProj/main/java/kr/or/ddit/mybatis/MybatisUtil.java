package kr.or.ddit.mybatis;

import java.io.Reader;
import java.nio.charset.Charset;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
    private static SqlSessionFactory sessionFactory;
    
    static {
        try {
            // 1-1 설정 파일 읽어오기
            Charset charSet = Charset.forName("UTF-8");
            Resources.setCharset(charSet);
            
            Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
            
            // 1-2 위에서 생성한 Reader 객체를 사용하여 sqlSessionFactory 객체 생성하기
            sessionFactory = new SqlSessionFactoryBuilder().build(rd);
            
            // 1-3 다 사용한 Reader 객체 반납
            rd.close();
            
        } catch (Exception e) {
            System.err.println("MyBatis 초기화 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SqlSession getInstance() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SqlSessionFactory가 초기화되지 않았습니다.");
        }
        return sessionFactory.openSession();
    }
    
    public static SqlSession getInstance(boolean autoCommit) {
        if (sessionFactory == null) {
            throw new IllegalStateException("SqlSessionFactory가 초기화되지 않았습니다.");
        }
        return sessionFactory.openSession(autoCommit);
    }
    
    public static SqlSessionFactory getSqlSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SqlSessionFactory가 초기화되지 않았습니다.");
        }
        return sessionFactory;
    }
}