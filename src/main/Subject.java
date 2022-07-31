package main;

public class Subject {

	private String subjectName;
	private int subjectID;
	private int subjectCount;


	public String getSubjectName() {
		return this.subjectName;
	}

	public int getSubjectID() {
		return this.subjectID;
	}

	public int getSubjectCount() {
		return this.subjectCount;
	}

	public void setSubjectCount(int subjectCount) {
		this.subjectCount = subjectCount;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}




	public Subject() {
	}
}