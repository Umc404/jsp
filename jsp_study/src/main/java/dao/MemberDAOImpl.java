package dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int register(MemberVO mvo) {
		log.info("member dao register in.");
		int isOk = sql.insert("MemberMapper.reg",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		// TODO Auto-generated method stub
		return sql.selectOne("MemberMapper.login", mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("lastLogin up dao in.");
		int isOk = sql.update("MemberMapper.last", id);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("member update dao in");
		int isOk = sql.update("MemberMapper.up", mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(MemberVO mvo) {
		log.info("member delete dao in {}", mvo);
		int isOk = sql.delete("MemberMapper.del", mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}
}
