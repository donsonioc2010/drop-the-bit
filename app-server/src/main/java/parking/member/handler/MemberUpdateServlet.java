package parking.member.handler;

import parking.common.handler.InitServlet;
import parking.common.utils.ResponseConstants;
import parking.member.domain.Member;
import parking.member.dto.MemberUpdateRequest;
import parking.member.type.MemberType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member loginUser = (Member) req.getSession().getAttribute("loginUser");
        Member m = MemberUpdateRequest.updateMemberVO(
                MemberUpdateRequest.builder()
                        .uid(Long.parseLong(req.getParameter("id")))
                        .password(req.getParameter("password"))
                        .memberType(MemberType.defaultStaffValueOf(req.getParameter("memberType")))
                        .build()
        );
        System.out.println(m.toString());

        Optional<Member> findUser = InitServlet.getMemberDao().findById(m.getId());

        resp.setContentType(ResponseConstants.CONTENT_TYPE);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
        out.println("<title>회원정보 수정</title>");
        out.println("</head><body>");

        if (loginUser == null) {
            out.println("<p>정보 수정에는 로그인이 필요합니다</p></body></html>");
            return;
        }

        if (MemberType.ADMIN.equals(m.getMemberType())) {
            out.println("<p>관리자 권한으로 수정은 불가능합니다..</p></body></html>");
            return;
        }
        if (findUser.isEmpty()) {
            out.println("<p>변경해야 하는 사용자가 존재하지 않습니다.</p></body></html>");
            return;
        }
        
        if (MemberType.ADMIN.equals(loginUser.getMemberType()) || findUser.get().getId() == m.getId()) {
            try {
                if (InitServlet.getMemberDao().update(m) > 0) {
                    InitServlet.getSqlSessionFactory().openSession(false).commit();
                    out.println("<p>변경했습니다!</p>");
                } else {
                    out.println("<p>변경해야 하는 사용자가 없습니다.</p>");
                }
            } catch (Exception e) {
                InitServlet.getSqlSessionFactory().openSession(false).rollback();
                out.println("<p>변경 실패입니다!</p>");
                e.printStackTrace();
            }
        } else {
            out.println("<p>관리자 또는 로그인 사용자 본인만 정보 수정이 가능합니다.</p>");
        }
        out.println("</body></html>");


    }
}
