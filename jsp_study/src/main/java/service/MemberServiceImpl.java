package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CommentDAOImpl;
import dao.MemberDAO;
import dao.MemberDAOImpl;
import domain.MemberVO;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public int register(MemberVO mvo) {
		return mdao.register(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		return mdao.login(mvo);
	}

	@Override
	public int lastLogin(String id) {
		return mdao.lastLogin(id);
	}

	@Override
	public int update(MemberVO mvo) {
		return mdao.update(mvo);
	}

	@Override
	public int delete(MemberVO mvo) {
		return mdao.delete(mvo);
	}
}
