package parking.park.handler;

import parking.common.handler.InitServlet;
import parking.common.utils.ResponseConstants;
import parking.member.domain.Member;
import parking.member.type.MemberType;
import parking.park.domain.ParkSubscribe;
import parking.park.dto.SubscribeAddRequest;
import parking.park.util.ParkPageConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Optional;

//사용자 추가및 페이지 로드는 관리자 또는 직원만 가능하다.
@WebServlet("/park/subscribe/add")
public class ParkSubAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(ResponseConstants.CONTENT_TYPE);

        if (!isValidAuthUser(req)) {
            noAuthPage(resp);
            return;
        }
        req.getRequestDispatcher(ParkPageConstants.SUBSCRIBE_ADD).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(ResponseConstants.CONTENT_TYPE);

        if (!isValidAuthUser(req)) {
            noAuthPage(resp);
            return;
        }
        ParkSubscribe subScribe = SubscribeAddRequest.addRequestVO(
                SubscribeAddRequest.builder()
                        .carNumber(req.getParameter("carNumber"))
                        .phoneNumber(req.getParameter("phoneNumber"))
                        .subscribeStAt(LocalDateTime.now())
                        .expireMonth(Long.parseLong(req.getParameter("expireMonth")))
                        .build()
        );

        Optional<ParkSubscribe> optionalParkSubscribe = InitServlet.getParkingSubscribeDao().findByCarNumber(subScribe.getCarNumber());

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='refresh' content='1;url=/park/subscribe/list'>");
        out.println("<title>주차 정기 구독 추가</title>");
        out.println("</head><body>");
        if (optionalParkSubscribe.isPresent()) {
            out.println("<p>이미 존재하는 차량 정보입니다. 구독권 갱신으로 진행하세요</p></body></html>");
            return;
        }

        try {
            InitServlet.getParkingSubscribeDao().insert(subScribe);
            InitServlet.getSqlSessionFactory().openSession(false).commit();
            out.println("<p>주차권 추가 완료되었습니다</p>");
        } catch (Exception e) {
            InitServlet.getSqlSessionFactory().openSession(false).rollback();
            out.println("<p>주차권 추가 완료되었습니다</p>");
        }
        out.println("</body></html>");
    }

    private boolean isValidAuthUser(HttpServletRequest req) {
        Member m = (Member) req.getSession().getAttribute("loginUser");
        if (m != null && (MemberType.isADMIN(m.getMemberType()) || MemberType.isSTAFF(m.getMemberType())))
            return true;
        return false;
    }

    private void noAuthPage(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='refresh' content='1;url=/park/subscribe/list'>");
        out.println("<title>주차 정기 구독 추가</title>");
        out.println("</head><body><p>권한이 없습니다</p></body></html");
    }

}
