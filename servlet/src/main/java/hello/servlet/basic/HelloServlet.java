package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //위 urlPatterns에 적힌 위치로 url입력시 아래위 문장이 실행
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // http://localhost:8080/hello?username=%22kim%22
        // 해당 url에 ? 이후에는 "쿼리 파라미터"이다.

        //request.getParameter 쉽게 쿼리 파라미터를 확인 가능하다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //응답의 contentType헤더를 text/plain 으로 설정
        response.setContentType("text/plain");
        // 응답의 문자 인코딩을 UTF-8로 설정
        response.setCharacterEncoding("utf-8");
        // 응답 본문에 "hello"와 변수 username 값을 연결한 문자열을 작성
        response.getWriter().write("hello " + username);
    }
}
