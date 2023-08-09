package parking.member.handler;

import parking.common.handler.InitServlet;
import parking.common.utils.ResponseConstants;
import parking.member.domain.Member;
import parking.member.dto.MemberAddRequest;
import parking.member.type.MemberType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //관리자 추가는 불가능하다
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member m = MemberAddRequest.addMemberVO(
                MemberAddRequest.builder()
                        .userId(req.getParameter("userId"))
                        .password(req.getParameter("password"))
                        .memberType(MemberType.defaultStaffValueOf(req.getParameter("memberType")))
                        .build()
        );

        resp.setContentType(ResponseConstants.CONTENT_TYPE);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>회원가입</title>");

        // 사용중인 ID인지를 검증한다
        int isUseId = InitServlet.getMemberDao().findByUserIdCount(m.getUserId());
        if (isUseId > 0) {
            validUserId(out);
            return;
        }

        if (MemberType.ADMIN.equals(m.getMemberType())) {
            isNotAbleCreateAdmin(out);
            return;
        }

        out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>회원 등록</h1>");


        try {
            InitServlet.getMemberDao().insert(m);
            InitServlet.getSqlSessionFactory().openSession(false).commit();
            out.println("<p>회원 등록이 완료되었습니다</p>");
        } catch (Exception e) {
            InitServlet.getSqlSessionFactory().openSession(false).rollback();
            out.println("<p>회원 등록에 실패하였습니다.</p>");
        }
        out.println("</body></html>");
    }

    private void validUserId(PrintWriter out) throws ServletException, IOException {
        out.println("<meta http-equiv='refresh' content='1;url=/member/signup.html'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>이미 사용중인 사용자 ID입니다</p>");
        out.println("</body>");
        out.println("</html>");
    }

    private void isNotAbleCreateAdmin(PrintWriter out) throws ServletException, IOException {
        out.println("<meta http-equiv='refresh' content='1;url=/member/signup.html'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>관리자의 추가는 불가능합니다.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
