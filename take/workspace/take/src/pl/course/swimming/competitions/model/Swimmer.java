package pl.course.swimming.competitions.model;

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

/**
 * Swimmer model
 * @version 1.0
 * @category POJO
 * */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idc")
public class Swimmer implements Serializable{
	
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
	private Character gender;
	
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
	@OneToMany(mappedBy = "swimmer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Result> results  = new HashSet<Result>();
	
	public Swimmer() {}
	
	public Swimmer(Swimmer swimmer) {
		this.idc = swimmer.getIdc();
		this.name = swimmer.getName();
		this.surname = swimmer.getSurname();
		this.gender = swimmer.getGender();
		this.age = swimmer.getAge();
		this.town = swimmer.getTown();
		this.phoneNumber = swimmer.getPhoneNumber();
		this.results = swimmer.getResults();
	}

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
	
	public Character getGender() {
		return this.gender;
	}
	
	public void set(Character gender) {
		if (gender.equals('M') || gender.equals('W')) {
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
	
	public void validate() {
		if(this.age < 0)
			throw new IllegalArgumentException("Age must be greather than 0");
		
		if (!(gender.equals('M') || gender.equals('W'))) 
			throw new IllegalArgumentException("Invalid gender value " + gender + " {M, W}");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + gender;
		result = prime * result + (int) (idc ^ (idc >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Swimmer other = (Swimmer) obj;
		if (age != other.age)
			return false;
		if (gender != other.gender)
			return false;
		if (idc != other.idc)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Swimmer [idc=" + idc + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", age=" + age
				+ ", town=" + town + ", phoneNumber=" + phoneNumber + ", results=" + results + "]";
	}
	
	

}
