package com.sist.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
 *     오라클 (SQL) => 데이터를 저장하는 장소 (서버 => 모든 데이터는 공유) 
 *     ----------
 *      DML => 데이터를 조작하는 언어 
 *      ---- 오라클  (명령문) 
 *        1) 데이터 검색 (SELECT)
 *           = 형식)
 *                  SELECT [ALL|DISTINCT] * | 컬럼명1,컬러명2....
 *                  FROM table명 (관련된 데이터를 저장) = 파일 
 *                  ----------------------------------- 필수
 *                  [
 *                      WHERE 조건문 (컬럼명 연산자 값)
 *                                  sal = 800
 *                      GROUP BY 그룹컬럼명(함수)
 *                      HAVING 그룹조건 
 *                      ORDER BY 컬럼명|함수
 *                  ]
 *           = 연산자
 *             1. 산술연산자 ( + , - , * , / )
 *                         ---         --- 0으로 나눌 수 없다
 *                                     --- 정수/정수=실수
 *                         문자열 결합은 할 수 없다 , 순수하게 산술 
 *                         -------- ||
 *                         => 사용되는 위치 SELECT뒤에 (통계,평균...)
 *             2. 비교연산자 ( = , !=(<>) , < , > , <= ,>=)
 *                         결과값은 boolean(true/false) 
 *             3. 논리연산자 ( AND , OR )
 *             --------------------
 *             4. IN ==> OR가 많은 경우 
 *             5. BETWEEN ~ AND : 기간 , 범위   >= AND <=
 *             6. NULL :  데이터가 없는 경우 => 연산처리가 않된다 
 *                        NULL을 연산처리 
 *                        IS NULL , IS NOT NULL
 *             7. LIKE : 
 *                       % => 문자 갯수를 알 수 없는 경우 
 *                       _ => 한글자 
 *                       
 *                       'A%' => A로 시작하는 문자열 
 *                       '%A' => A로 끝나는 문자열 
 *                       '%A%' => A가 포함된 문자열 
 *                       '__A%' => 3번째 글자가 A인 문자열 
 *             8. NOT : 부정연산자 
 *                      NOT IN  , NOT LIKE , NOT BETWEEN ~ AND
 *                      
 *                      
 *             자바 (응용프로그램) =====> 오라클 서버 
 *                             SQL전송 
 *                             실행된 결과 ===> 브라우저로 전송 
 *                             
 *             1) 드라이버 등록 
 *             2) 오라클 연결 
 *             3) SQL문장 제작 
 *             4) SQL문장을 오라클로 전송 
 *             5) 실행후에 결과값 받기 
 *             6) 출력 
 *             
 *              
 *                  
 *        
 */
import java.util.*;
public class MainClass1 {
    // 전체 데이터 읽기
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.print("이름 입력:");
		String name=scan.next();
		name=name.toUpperCase();
		
        try
        {
        	// 드라이버 등록 
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	// 연결 
        	String url="jdbc:oracle:thin:@localhost:1521:xe";
        	Connection conn=DriverManager.getConnection(url,"hr","happy");
        	// SQL문장 
        	String sql="SELECT empno,ename,job,hiredate,sal "
        			  +"FROM emp "
        			  +"WHERE ename IN('WARD','SCOTT')";
        	// '%a%'
        	//                   
        	// SQL문장 전송
        	PreparedStatement ps=conn.prepareStatement(sql);
        	//ps.setString(1, name);
        	// 실행후에 결과값을 가지고 온다 
        	ResultSet rs=ps.executeQuery();
        	while(rs.next())
        	{
        		System.out.println(
        		   rs.getInt(1)+" "
        		   +rs.getString(2)+" "
        		   +rs.getString(3)+" "
        		   +rs.getDate(4)+" "
        		   +rs.getInt(5)
        		);
        	}
        	
        	rs.close();
        	ps.close();
        	conn.close();
        	
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        }
	}

}






