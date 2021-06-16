package pl.course.swimming.competitions;

import java.io.Serializable;
import java.util.HashSet;
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
    @OneToMany(mappedBy = "discipline", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Result> results  = new HashSet<Result>();

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
	
	public Set<Result> getResults() {
		return this.results;
	}
	
	public void addResult(Result result) {
		this.results.add(result);
		result.setDiscipline(this);
	}

	@Override
	public String toString() {
		return "Discipline [idc=" + idc + ", name=" + name + ", distanceInMeters=" + distanceInMeters + ", results="
				+ results + "]";
	}
	
	
}
