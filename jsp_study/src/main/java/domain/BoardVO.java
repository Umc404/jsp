package domain;

public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private String moddate;
	
	public BoardVO() {}

	// insert : title, writer, content
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	// list : bno, title, writer, moddate
	public BoardVO(int bno, String title, String writer, String moddate) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.moddate = moddate;
	}

	// update : bno, title, content
	public BoardVO(int bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}

	// detail : all
	public BoardVO(int bno, String title, String writer, String content, String regdate, String moddate) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
	}
	// delete : bno
	public BoardVO(int bno) {
		this.bno = bno;
	}
	@Override
	public String toString() {
		return "제목 : " + title + "\n작성자 : " + writer +  "\t작성 날짜 : " + regdate+"(최근수정일 : " + moddate + ")"
				+ "내용\n" + content ;
	}

	// getter / setter
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate.substring(0,11);
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModdate() {
		return moddate.substring(0,11);
	}

	public void setModdate(String moddate) {
		this.moddate = moddate;
	}
	
}
