package parking.park.handler;

import parking.common.handler.InitServlet;
import parking.common.utils.ResponseConstants;
import parking.member.domain.Member;
import parking.member.type.MemberType;
import parking.park.domain.ParkSubscribe;
import parking.park.type.SubscribeSearchType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/park/subscribe/list")
public class ParkSubListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(ResponseConstants.CONTENT_TYPE);
        Member loginUser = (Member) req.getSession().getAttribute("loginUser");
        SubscribeSearchType type = SubscribeSearchType.valueOfDefaultALL(req.getParameter("status"));


        List<ParkSubscribe> list = null;
        if (SubscribeSearchType.ALL.equals(type)) {
            list = InitServlet.getParkingSubscribeDao().findAll();
        } else if (SubscribeSearchType.EXPIRE.equals(type)) {
            list = InitServlet.getParkingSubscribeDao().findExpiredList();
        } else {
            list = InitServlet.getParkingSubscribeDao().findNotExpiredList();
        }

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>주차 정기 구독 리스트</title>");
        out.println("</head><body>");
        out.println("<table>" +
                "<tr> " +
                getSubscribeSearchHTML(type) +
                "</tr>" +
                "</table>");

        out.println("<hr />");
        out.println("<table border='1'>");
        out.println("<tr>" +
                "<th>NO</th>" +
                "<th>차량번호</th>" +
                "<th>휴대폰 번호</th>" +
                "<th>주차권 시작일</th>" +
                "<th>주차권 종료일</th>" +
                "</tr>");
        for (ParkSubscribe p : list) {
            out.println("<tr>" +
                    "<td><a href='/park/subscribe/detail?id=" + p.getId() + "'>" + p.getId() + "</a></td>" +
                    "<td><a href='/park/subscribe/detail?id=" + p.getId() + "'>" + p.getCarNumber() + "</a></td>" +
                    "<td>" + p.getPhoneNumber() + "</td>" +
                    "<td>" + p.getSubscribeStAt() + "</td>" +
                    "<td>" + p.getSubscribeEdAt() + "</td>" +
                    "</tr>");
        }

        out.println("</table>");
        if (loginUser != null || (MemberType.isSTAFF(loginUser.getMemberType()) || MemberType.isADMIN(loginUser.getMemberType()))) {
            out.println("<div style='margin:5px;'>");
            out.println("<a href='/park/subscribe/add'>구독권 추가</a>");
            out.println("</div>");
        }
        out.println("</body></html>");
    }

    private String getSubscribeSearchHTML(SubscribeSearchType type) {
        return Arrays.stream(SubscribeSearchType.values()).map(value -> {
            if (value.equals(type)) {
                return "<td>" + value + "</td>";
            }
            return "<td><a href='/park/subscribe/list?status=" + value + "'>" + value + "</a></td>";
        }).collect(Collectors.joining());
    }
}
