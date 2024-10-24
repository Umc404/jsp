package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import domain.BoardVO;

public class BoardServiceImpl implements BoardService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	// dao 연결
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int register(BoardVO bvo) {
		log.info("register service in");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		
		return bdao.getList();
	}

	@Override
	public BoardVO getDetail(int bno) {
//		log.info("register service in");
		return bdao.getDetail(bno);
	}

	@Override
	public int update(BoardVO bvo) {
//		log.info("register service in");
		return bdao.update(bvo);
	}

	@Override
	public int delete(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.delete(bvo);
	}


	@Override
	public int readCount(int bno) {
		// TODO Auto-generated method stub
		return bdao.readCount(bno);
	}
}
