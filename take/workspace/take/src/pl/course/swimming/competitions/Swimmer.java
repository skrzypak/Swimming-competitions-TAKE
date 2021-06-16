package pl.course.swimming.competitions;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.lang.IllegalArgumentException;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idc")
public class Swimmer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idc;
	
	@Basic
	@Column(nullable = false)
	private String name;
	
	@Basic
	@Column(nullable = false)
	private String surname;
	
	@Basic
	@Column(nullable = false)
	private byte gender;
	
	@Basic
	@Column(nullable = false)
	private int age;
	
	@Basic
	@Column(nullable = false)
	private String town;
	
	@Basic
	@Column(nullable = false)
	private String phoneNumber;
	
	@JsonIgnore
	@OneToMany(mappedBy = "swimmer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Result> results  = new HashSet<Result>();
	
	public long getIdc() {
		return this.idc;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public byte getGender() {
		return this.gender;
	}
	
	public void set(byte gender) {
		if (gender == 'M' || gender == 'W') {
			this.gender = gender;
			return;
		}
		
		throw new IllegalArgumentException("Invalid gender value {M, W}");
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		if (age > 0) {
			this.age = age;
			return;
		}
		
		throw new IllegalArgumentException("Age must be greather than 0");
	}
	
	public String getTown() {
		return this.town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Set<Result> getResults() {
    	return this.results;
	}
	
	public void addResult(Result result) {
		this.results.add(result);
		result.setSwimmer(this);
	}

	@Override
	public String toString() {
		return "Swimmer [idc=" + idc + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", age=" + age
				+ ", town=" + town + ", phoneNumber=" + phoneNumber + ", results=" + results + "]";
	}
	
	

}
