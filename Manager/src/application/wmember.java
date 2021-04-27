package application;

public class wmember {
	
	String gender;
	String name;
	int age;
	String tel;
	String job;
	
	public wmember() {
		// TODO Auto-generated constructor stub
	}

	public wmember(String gender, String name, int age, String tel, String job) {
		super();
		this.gender = gender;
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.job = job;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
}
