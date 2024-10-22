package dao;

import java.util.List;

import domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

}