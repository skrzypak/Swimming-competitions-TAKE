package pl.course.swimming.competitions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

/**
 * Discipline model
 * @version 1.0
 * @category POJO
 * */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idc")
public class Discipline implements Serializable {
	
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idc;
	
	@Basic
	@Column(unique = true, nullable = false)
	private String name;
	
	@Basic
	@Column(nullable = false)
	private int distanceInMeters;
	
	@JsonIgnore
    @OneToMany(mappedBy = "discipline", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Result> results  = new ArrayList<Result>();;

	public Discipline() {}
	
	public Discipline(Discipline discipline) {
		this.idc = discipline.getIdc();
		this.name = discipline.getName();
		this.distanceInMeters = discipline.getDistanceInMeters();
		this.results = discipline.getResults();
	}

	public long getIdc() {
		return idc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistanceInMeters() {
		return distanceInMeters;
	}

	public void setDistanceInMeters(int distanceInMeters) {
		if (distanceInMeters > 0) {
			this.distanceInMeters = distanceInMeters;
            return;
        }

        throw new IllegalArgumentException("Distanced must be greater than 0");
	}
	
	public List<Result> getResults() {
		return this.results;
	}
	
	public void addResult(Result result) {
		this.results.add(result);
		result.setDiscipline(this);
	}
	
	public void removeResult(Result result) {
		this.results.remove(result);
		result.setDiscipline(null);
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
		Discipline other = (Discipline) obj;
		if (distanceInMeters != other.distanceInMeters)
			return false;
		if (idc != other.idc)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Discipline [idc=" + idc + ", name=" + name + ", distanceInMeters=" + distanceInMeters + ", results="
				+ results + "]";
	}
	
	
}
