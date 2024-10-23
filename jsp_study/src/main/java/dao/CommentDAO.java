package dao;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int post(CommentVO cvo);

	List<CommentVO> getList(int bno);

}