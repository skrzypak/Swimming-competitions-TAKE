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

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idc")
public class Competition implements Serializable {
	
	private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idc;
    
    @Basic
	@Column(nullable = false)
    private String name;
    
    @Basic
	@Column(nullable = false)
    private String organizer;
    
    @Basic
	@Column(nullable = false)
    private String place;
    
    @Basic
	@Column(nullable = true)
    private LocalDateTime dateTime;
    
    @JsonIgnore
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Result> results = new HashSet<Result>();
    
    public Competition() {}
    
    public Competition(Competition competition) {
		this.idc = competition.getIdc();
		this.name = competition.getName();
		this.organizer = competition.getOrganizer();
		this.place = competition.getPlace();
		this.dateTime = competition.getDateTime();
		this.results = competition.getResults();
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public Set<Result> getResults() {
		return this.results;
	}
	
	public void addResult(Result result) {
		this.results.add(result);
		result.setCompetition(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + (int) (idc ^ (idc >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organizer == null) ? 0 : organizer.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
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
		Competition other = (Competition) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (idc != other.idc)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (organizer == null) {
			if (other.organizer != null)
				return false;
		} else if (!organizer.equals(other.organizer))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
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
		return "Competition [idc=" + idc + ", name=" + name + ", organizer=" + organizer + ", place=" + place
				+ ", dateTime=" + dateTime + ", results=" + results + "]";
	}
	
	
}
