package parking.member.handler;

import parking.common.handler.InitServlet;
import parking.common.utils.ResponseConstants;
import parking.member.domain.Member;
import parking.member.type.MemberType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member loginUser = (Member) req.getSession().getAttribute("loginUser");
        resp.setContentType(ResponseConstants.CONTENT_TYPE);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>회원</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>회원 목록</h1>");
        if (loginUser != null && MemberType.ADMIN.equals(loginUser.getMemberType())) {
            out.println("<div style='margin:5px;'>");
            out.println("<a href='/member/signup.html'>사용자 추가</a>");
            out.println("</div>");
        }
        out.println("<table border='1'>");
        out.println("<thead>");
        out.println("  <tr><th>UID</th> <th>사용자 ID</th></tr>");
        out.println("</thead>");

        List<Member> list = InitServlet.getMemberDao().findAll();
        for (Member m : list) {
            out.printf("<tr>"
                            + " <td>%d</td>"
                            + " <td><a href='/member/detail?id=%d'>%s</a></td>",
                    m.getId(), m.getId(), m.getUserId());
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<a href='/'>메인</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
