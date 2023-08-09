package parking.auth.handler;

import parking.auth.util.AuthPageConstants;
import parking.common.handler.InitServlet;
import parking.common.utils.ResponseConstants;
import parking.member.domain.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AuthPageConstants.LOIGN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Member m = Member.builder()
                .userId(req.getParameter("userId"))
                .password(req.getParameter("password"))
                .build();

        Optional<Member> loginUser = InitServlet.getMemberDao().findByUserIdAndPassword(m);

        if (loginUser.isPresent()) {
            req.getSession().setAttribute("loginUser", loginUser.get());
            resp.sendRedirect("/");
            return;
        }

        resp.setContentType(ResponseConstants.CONTENT_TYPE);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='refresh' content='1;url=" + AuthPageConstants.LOIGN + "'>");
        out.println("<title>로그인</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>로그인</h1>");
        out.println("<p>회원 정보가 일치하지 않습니다.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
