package parking;

import static parking.utils.common.TitlePrintUtils.processStartTitle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.impl.MemberAddListener;
import parking.member.handler.impl.MemberDeleteListener;
import parking.member.handler.impl.MemberDetailListener;
import parking.member.handler.impl.MemberListListener;
import parking.member.handler.impl.MemberLoginInfoListener;
import parking.member.handler.impl.MemberLoginListener;
import parking.member.handler.impl.MemberLogoutListener;
import parking.member.handler.impl.MemberUpdateListener;
import parking.park.domain.ParkingCar;
import parking.park.handler.impl.ParkingAddListener;
import parking.park.handler.impl.ParkingDeleteListener;
import parking.park.handler.impl.ParkingDetailListener;
import parking.park.handler.impl.ParkingListListener;
import parking.park.handler.impl.ParkingUpdateListener;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.common.repository.MakeCSV;
import parking.utils.menu.Menu;
import parking.utils.menu.MenuGroup;

public class App {
	public static void main(String[] args) {
		new MainHandler().run();
	}
}

class MainHandler {
	private static boolean isRunning;
	private List<Member> memberList = new ArrayList<>();
	private List<ParkingCar> parkingList = new LinkedList<>();
	private BreadcrumbPrompt prompt;
	private MenuGroup menu = new MenuGroup("메인");

	public MainHandler() {
		isRunning = true;
		this.prompt = new BreadcrumbPrompt();
		this.renderMenu();
	}

	public void run() {
		processStartTitle();

		loadData();
		menu.execute(prompt);
		saveData();

		prompt.scClose();
	}

	/**
	 * 메뉴 생성
	 */
	private void renderMenu() {
		renderParkMenu();
		renderMemberMenu();
	}

	/**
	 * 주차 메뉴 추가
	 */
	private void renderParkMenu() {
		MenuGroup parkMenu = new MenuGroup("주차");
		parkMenu.add(new Menu("등록", new ParkingAddListener(parkingList)));
		parkMenu.add(new Menu("리스트 조회", new ParkingListListener(parkingList)));
		parkMenu.add(new Menu("세부조회", new ParkingDetailListener(parkingList)));
		parkMenu.add(new Menu("정보수정", new ParkingUpdateListener(parkingList)));
		parkMenu.add(new Menu("삭제", new ParkingDeleteListener(parkingList)));

		menu.add(parkMenu);
	}

	/**
	 * 회원 관리 메뉴 추가
	 */
	private void renderMemberMenu() {
		MenuGroup memberMenu = new MenuGroup("회원");
		memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
		memberMenu.add(new Menu("리스트 조회", new MemberListListener(memberList)));
		memberMenu.add(new Menu("세부 조회", new MemberDetailListener(memberList)));
		memberMenu.add(new Menu("수정", new MemberUpdateListener(memberList)));
		memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
		memberMenu.add(new Menu("로그인", new MemberLoginListener(memberList)));
		memberMenu.add(new Menu("로그아웃", new MemberLogoutListener(memberList)));
		memberMenu.add(new Menu("로그인정보", new MemberLoginInfoListener(memberList)));

		menu.add(memberMenu);
	}

	private void saveData() {
		MakeCSV.saveCsv("data/parking.csv", parkingList);
		MakeCSV.saveCsv("data/member.csv", memberList);
	}

	private void loadData() {
		MakeCSV.loadCsv("data/parking.csv", parkingList, ParkingCar.class);
		MakeCSV.loadCsv("data/member.csv", memberList, Member.class);
	}
}


