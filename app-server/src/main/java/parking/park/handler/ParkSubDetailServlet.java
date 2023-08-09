package parking.park.handler;

import parking.common.handler.InitServlet;
import parking.common.utils.LocalDateFormat;
import parking.common.utils.ResponseConstants;
import parking.member.domain.Member;
import parking.member.type.MemberType;
import parking.park.domain.ParkSubscribe;
import parking.park.util.ParkPageConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/park/subscribe/detail")
public class ParkSubDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(ResponseConstants.CONTENT_TYPE);
        String paramId = req.getParameter("id");

        if (paramId == null) {
            req.getRequestDispatcher(ParkPageConstants.SUBSCRIBE_DETAIL_ID_NOTFOUND).forward(req, resp);
            return;
        }

        Optional<ParkSubscribe> optionalSubscribe = InitServlet.getParkingSubscribeDao().findById(Long.parseLong(paramId));
        if (optionalSubscribe.isEmpty()) {
            req.getRequestDispatcher(ParkPageConstants.SUBSCRIBE_DETAIL_ID_NOTFOUND).forward(req, resp);
            return;
        }

        ParkSubscribe subscribe = optionalSubscribe.get();
        Member loginUser = (Member) req.getSession().getAttribute("loginUser");

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>주차 구독권 세부 조회</title>");
        out.println("</head><body>");
        out.println("<table border='1'>");
        out.println("<tr>" +
                "<th>NO</th>" +
                "<td><input type='text' value='" + subscribe.getId() + "' readonly/></td>" +
                "</tr>");

        out.println("<tr>" +
                "<th>차량 번호</th>" +
                "<td><input type='text' value='" + subscribe.getCarNumber() + "' readonly/></td>" +
                "</tr>");

        out.println("<tr>" +
                "<th>휴대폰 번호</th>" +
                "<td><input type='text' value='" + subscribe.getPhoneNumber() + "' /></td>" +
                "</tr>");

        out.println("<tr>" +
                "<th>주차권 시작일</th>" +
                "<td><input type='date' value='" + LocalDateFormat.getDate(subscribe.getSubscribeStAt()) + "' readonly/></td>" +
                "</tr>");
        out.println("<tr>" +
                "<th>주차권 종료일</th>" +
                "<td><input type='date' value='" + LocalDateFormat.getDate(subscribe.getSubscribeEdAt()) + "' /></td>" +
                "</tr>");
        out.println("</table>");
        out.println("</body></html>");

        if (loginUser != null || (MemberType.isSTAFF(loginUser.getMemberType()) || MemberType.isADMIN(loginUser.getMemberType()))) {
            out.println("<div style='margin:5px;'>");
            out.println("<a href='/park/subscribe/update'>구독권 수정</a>");
            out.println("</div>");
        }
    }
}
