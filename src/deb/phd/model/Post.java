package deb.phd.model;

public class Post {
	int id;
	int postTypeId;
	int acceptedAnswerId;
	int score;
	String body;
	String tags;
	String title;
	int parentId;
	
	
	public Post(int id, int postTypeId, int acceptedAnswerId,int score, String body, String tags,String title, int parentId) {
		super();
		this.id = id;
		this.postTypeId = postTypeId;
		this.acceptedAnswerId = acceptedAnswerId;
		this.score = score;
		this.body = body;
		this.tags = tags;
		this.title = title;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPostTypeId() {
		return postTypeId;
	}


	public void setPostTypeId(int postTypeId) {
		this.postTypeId = postTypeId;
	}


	public int getAcceptedAnswerId() {
		return acceptedAnswerId;
	}


	public void setAcceptedAnswerId(int acceptedAnswerId) {
		this.acceptedAnswerId = acceptedAnswerId;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
