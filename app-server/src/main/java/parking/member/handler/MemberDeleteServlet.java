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
import java.util.Optional;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //삭제는 관리자만 가능하며, 관리자 계정의 삭제는 불가능하다
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Member m = (Member) req.getSession().getAttribute("loginUser");
        resp.setContentType(ResponseConstants.CONTENT_TYPE);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='ko'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
        out.println("<title>회원삭제</title>");
        out.println("<head>");
        out.println("<body>");
        if (!MemberType.ADMIN.equals(m.getMemberType())) {
            out.println("<p>회원 삭제는 관리자만 가능합니다.</p></body></html>");
            return;
        }
        long deleteUserNo = Integer.parseInt(req.getParameter("id"));
        Optional<Member> deleteMemberOptional = InitServlet.getMemberDao().findById(deleteUserNo);
        try {
            if (deleteMemberOptional.isPresent()) {
                //삭제대상이 관리자인 경우는 삭제 불가
                if (MemberType.ADMIN.equals(deleteMemberOptional.get().getMemberType())) {
                    out.println("<p>관리자는 삭제가 불가능합니다.</p>");
                } else {
                    int delRow = InitServlet.getMemberDao().deleteById(deleteUserNo);
                    out.println(delRow == 0 ? "<p>존재하는 사용자가 없습니다</p>" : "<p>삭제가 완료되었습니다.</p>");
                    InitServlet.getSqlSessionFactory().openSession(false).commit();
                }
            } else {
                out.println("<p>존재하는 사용자가 없습니다</p>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            InitServlet.getSqlSessionFactory().openSession(false).rollback();
        }
    }
}
