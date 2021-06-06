package pl.course.swimming.competitions;

import java.io.Serializable;

import javax.persistence.Basic;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToMany(mappedBy = "competition", fetch = FetchType.EAGER)
	private Set<Result> results = new HashSet<Result>();

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
	public String toString() {
		return "Competition [idc=" + idc + ", name=" + name + ", organizer=" + organizer + ", place=" + place
				+ ", dateTime=" + dateTime + ", results=" + results + "]";
	}
	
	
}
