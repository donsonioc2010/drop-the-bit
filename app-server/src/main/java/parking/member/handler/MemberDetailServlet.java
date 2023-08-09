package parking.member.handler;

import parking.common.handler.InitServlet;
import parking.member.domain.Member;
import parking.member.type.MemberType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/member/detail")
public class MemberDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Member> optionalMember = InitServlet.getMemberDao().findById(Integer.parseInt(req.getParameter("id")));

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>회원 세부정보</title>");


        if (optionalMember.isEmpty()) {
            out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>해당 번호의 회원이 없습니다!</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        Member member = optionalMember.get();
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>회원</h1>");
        out.println("<form action='/member/update' method='post'>");
        out.println("<table border='1'>");
        out.printf("<tr><th style='width:120px;'>UID</th>"
                + " <td style='width:300px;'><input type='text' name='id' value='%d' readonly></td></tr>\n", member.getId());
        out.printf("<tr><th>아이디</th>"
                + " <td><input type='text' name='userId' value='%s' readonly></td></tr>\n", member.getUserId());
        out.println("<tr><th>암호</th>"
                + " <td><input type='password' name='password'></td></tr>");
        out.printf("<tr><th>권한</th>\n"
                + " <td><select name='memberType'>\n"
        );
        for (MemberType names : MemberType.values()) {
            out.printf("<option value='%s' %s>%s</option>\n", names, member.getMemberType().equals(names) ? "selected" : "", names);
        }
        out.println("</select></td></tr>");
        out.printf("<tr><th>최초 생성일</th><td>%s</td>", member.getCreatedAt());
        out.println("</table>");
        out.println("<div>");

        Member loginUser = (Member) req.getSession().getAttribute("loginUser");

        if (loginUser != null && (MemberType.ADMIN.equals(loginUser.getMemberType()) || loginUser.getId() == member.getId())) {
            out.println("<button type='reset'>초기화</button>");
            out.println("<button>변경</button>");
        }

        if (loginUser != null && MemberType.ADMIN.equals(loginUser.getMemberType())) {
            out.printf("<a href='/member/delete?id=%d'>삭제</a>\n", member.getId());
        }
        out.println("<a href='/member/list'>목록</a>\n");
        out.println("</div>");
        out.println("</form>");


        out.println("</body>");
        out.println("</html>");

    }
}
