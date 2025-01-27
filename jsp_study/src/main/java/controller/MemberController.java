package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import service.BoardService;
import service.BoardServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet({ "/MemberController", "/mem/*" })
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 로그 객체
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	// 동기방식 : requestDispatcher : request에 대한 응답 데이터를 jsp(html) 화면으로 전송하는 역할
	private RequestDispatcher rdp;
	// 목적지 주소
	private String destPage;
	private int isOk;
	private BoardService bsv;
	
	// service
	private MemberService msv;
	
	
    public MemberController() {
        msv = new MemberServiceImpl();
        bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청주소 추출
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>>> member path > {}", path);
		
		switch(path) {
		case "join":
			destPage = "/member/join.jsp";
			break;
		case "register":
			// jsp에서 온 파라미터 받기
			// member 객체 생성 후 service 에게 등록 요청
			
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				
				MemberVO mvo = new MemberVO(id, pwd, email, phone);
				log.info(">>>> mvo 객체 > {}", mvo);
				
				isOk = msv.register(mvo);
				log.info(">>>> member register > " + (isOk>0? "성공":"실패"));
				
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("");
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				// 로그인 : id, pwd 파라미터로 받아서 DB에 해당 id가 있는지 확인, pwd가 일치하는지 확인
				// 정보를 가져와서 session 객체에 저장
				// session : 모든 jsp에 공유되는 객체 / 브라우저가 종료되면 삭제
				// ${변수명}
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				
				MemberVO mvo = new MemberVO(id, pwd);
				// 로그인 정보가 잘못되면 login
				MemberVO loginMvo = msv.login(mvo);
				
				log.info(">>>> loginMvo > {}", loginMvo);
				
				if(loginMvo != null) {
					// 로그인 성공했다면 세션에 저장
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					// 로그인 유지시간 초단위로 설정 : 10분
					ses.setMaxInactiveInterval(60*10);
					
				} else {
					// 로그인 실패했다면
					request.setAttribute("msg_login", -1);
				}
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("login error");
				e.printStackTrace();
			}
			break;
		case "logout":
			try {
				// session에 값이 있다면 해당 세션을 끊어라.
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				log.info(">>> ses 추출 mvo > {}",mvo);
				
				// lastlogin update
				isOk = msv.lastLogin(mvo.getId());
				log.info(">>> lastLogin update >> " + (isOk>0? "OK":"Fail"));
				
				ses.invalidate();		// 세션 무효화(끊기)
				
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("logout error");
				e.printStackTrace();
			}
			break;
		case "modify":
				destPage = "/member/modify.jsp";
			break;
		case "update":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				System.out.println(id);
				System.out.println(pwd);
				System.out.println(email);
				System.out.println(phone);
				MemberVO mvo = new MemberVO(id, pwd, email, phone);
				log.info(">>>> mvo 객체 > {}", mvo);
				
				isOk = msv.update(mvo);
				log.info(">>>> member update > " + (isOk>0? "성공":"실패"));
				
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("member update error");
				e.printStackTrace();
			}
			break;
		case "delete":
			try {
				HttpSession ses = request.getSession();
				String id = ((MemberVO)ses.getAttribute("ses")).getId();
				System.out.println(id);
				
				MemberVO mvo = new MemberVO(id);
				isOk = msv.delete(mvo);
				log.info(">>>> member delete > " + (isOk>0? "성공":"실패"));
				
				ses.invalidate();			// 세션 끊기
				destPage = "../index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		case "mylist":
			try {
				HttpSession ses = request.getSession();
				String id = ((MemberVO)ses.getAttribute("ses")).getId();
				List<BoardVO> myList = bsv.getMyList(id);
				request.setAttribute("list", myList);
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "memlist":
			
			break;
		}
		// rdp 전송
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
