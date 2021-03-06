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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Swimmer model
 *
 * @version 1.0
 * @category POJO
 */
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
	
	/***
	 * @warning Legal arguments are: M or W
	 * */
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
	private List<Result> results = new ArrayList<Result>();

	/**
	 * Instantiates a new Swimmer.
	 */
	public Swimmer() {}

	/**
	 * Instantiates a new Swimmer.
	 *
	 * @param swimmer the swimmer
	 */
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

	/**
	 * Gets idc.
	 *
	 * @return the idc
	 */
	public long getIdc() {
		return this.idc;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return this.surname;
	}

	/**
	 * Sets surname.
	 *
	 * @param surname the surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets gender.
	 *
	 * @return the gender
	 */
	public Character getGender() {
		return this.gender;
	}

	/**
	 * Set.
	 *
	 * @param gender the gender
	 */
	public void set(Character gender) {
		if (gender.equals('M') || gender.equals('W')) {
			this.gender = gender;
			return;
		}
		
		throw new IllegalArgumentException("Invalid gender value {M, W}");
	}

	/**
	 * Gets age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Sets age.
	 *
	 * @param age the age
	 */
	public void setAge(int age) {
		if (age > 0) {
			this.age = age;
			return;
		}
		
		throw new IllegalArgumentException("Age must be greather than 0");
	}

	/**
	 * Gets town.
	 *
	 * @return the town
	 */
	public String getTown() {
		return this.town;
	}

	/**
	 * Sets town.
	 *
	 * @param town the town
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * Gets phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets phone number.
	 *
	 * @param phoneNumber the phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets results.
	 *
	 * @return the results
	 */
	public List<Result> getResults() {
    	return this.results;
	}

	/**
	 * Add result.
	 *
	 * @param result the result
	 */
	public void addResult(Result result) {
		this.results.add(result);
		result.setSwimmer(this);
	}

	/**
	 * Remove result.
	 *
	 * @param result the result
	 */
	public void removeResult(Result result) {
		this.results.remove(result);
		result.setSwimmer(null);
	}

	/**
	 * Validate.
	 */
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
		result = prime * result + (int) (idc ^ (idc >>> 32));
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
