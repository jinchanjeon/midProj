package kr.or.ddit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/getToken")
public class getTokenController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String impKey = System.getenv(""); // 환경 변수에서 키 읽기 
        String impSecret = System.getenv(""); // 환경 변수에서 시크릿 읽기

        // Token 요청
        String json = String.format("{\"imp_key\":\"%s\", \"imp_secret\":\"%s\"}", impKey, impSecret);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // HTTP 클라이언트 생성
            URL url = new URL("https://api.iamport.kr/users/getToken");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // 데이터 전송
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            // 응답 처리
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            in.close();

            // 응답 전송
            response.getWriter().write(responseBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"code\":-1, \"message\":\"Error occurred\"}");
        }
    }
}
