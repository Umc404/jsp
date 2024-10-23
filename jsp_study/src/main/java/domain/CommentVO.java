package domain;

public class CommentVO {

//			 cno int auto_increment
//			 bno int not null
//			 content varchar(2000)
//			 writer varchar(500) default "unknown"
//			 regdate datetime default now()
	
	private int cno;
	private int bno;
	private String content;
	private String writer;
	private String regdate;
	
	public CommentVO() {}
	
	// 등록 : bno, content, writer
	public CommentVO(int bno, String content, String writer) {
		this.bno = bno;
		this.content = content;
		this.writer = writer;
	}
	// 수정 : cno, content
	public CommentVO(int cno, String content) {
		this.cno = cno;
		this.content = content;
	}
	// 리스트(all)
	public CommentVO(int cno, int bno, String content, String writer, String regdate) {
		this.cno = cno;
		this.bno = bno;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
	}
	
	
	
	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + "]";
	}

	// getter / setter
	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
