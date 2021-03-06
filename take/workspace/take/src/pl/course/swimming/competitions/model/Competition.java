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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import pl.course.swimming.competitions.adapter.LocalDateTimeAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Competition model
 *
 * @version 1.0
 * @category POJO
 */
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
	@Column(nullable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dateTime;
    
    @JsonIgnore
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Result> results = new ArrayList<Result>();

	/**
	 * Instantiates a new Competition.
	 */
	public Competition() {}

	/**
	 * Instantiates a new Competition.
	 *
	 * @param competition the competition
	 */
	public Competition(Competition competition) {
		this.idc = competition.getIdc();
		this.name = competition.getName();
		this.organizer = competition.getOrganizer();
		this.place = competition.getPlace();
		this.dateTime = competition.getDateTime();
		this.results = competition.getResults();
	}

	/**
	 * Gets idc.
	 *
	 * @return the idc
	 */
	public long getIdc() {
        return idc;
    }

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
        return name;
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
	 * Gets organizer.
	 *
	 * @return the organizer
	 */
	public String getOrganizer() {
        return organizer;
    }

	/**
	 * Sets organizer.
	 *
	 * @param organizer the organizer
	 */
	public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

	/**
	 * Gets place.
	 *
	 * @return the place
	 */
	public String getPlace() {
        return place;
    }

	/**
	 * Sets place.
	 *
	 * @param place the place
	 */
	public void setPlace(String place) {
        this.place = place;
    }

	/**
	 * Gets date time.
	 *
	 * @return the date time
	 */
	public LocalDateTime getDateTime() {
        return dateTime;
    }

	/**
	 * Sets date time.
	 *
	 * @param dateTime the date time
	 */
	public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
		result.setCompetition(this);
	}

	/**
	 * Remove result.
	 *
	 * @param result the result
	 */
	public void removeResult(Result result) {
		this.results.remove(result);
		result.setCompetition(null);
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
