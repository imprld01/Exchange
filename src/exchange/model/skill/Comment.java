package exchange.model.skill;

public class Comment {
	private String comment;
	private String date;

	public Comment(String comment, String date) {
		this.comment = comment;
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Comment[comment=" + comment + ", date=" + date + "]";
	}
}
