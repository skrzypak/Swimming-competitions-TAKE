package pl.course.swimming.competitions.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.internal.NotNull;

import java.lang.IllegalArgumentException;

/**
 * Result model
 *
 * @version 1.0
 * @category POJO
 * @see pl.course.swimming.competitions.dto.ResultDto
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idc")
public class Result implements Serializable {
	
	private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idc;
    
    private int timeObtainedSeconds;
    
    @Basic
    @Column(nullable=false)
    private int place;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_swimmer")
    @NotNull
    private Swimmer swimmer;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_discipline")
    @NotNull
    private Discipline discipline;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_competition")
    @NotNull
    private Competition competition;

    /**
     * Instantiates a new Result.
     */
    public Result() {}

    /**
     * Instantiates a new Result.
     *
     * @param result the result
     */
    public Result(Result result) {
		this.idc = result.getIdc();
		this.timeObtainedSeconds = result.getTimeObtainedSeconds();
		this.place = result.getPlace();		
		this.swimmer = new Swimmer(result.getSwimmer());
		this.discipline = new Discipline(result.getDiscipline());
		this.competition = new Competition(result.getCompetition());
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
     * Gets swimmer.
     *
     * @return the swimmer
     */
    public Swimmer getSwimmer() {
        return swimmer;
    }

    /**
     * Sets swimmer.
     *
     * @param swimmer the swimmer
     */
    public void setSwimmer(Swimmer swimmer) {
        this.swimmer = swimmer;
    }

    /**
     * Gets discipline.
     *
     * @return the discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Sets discipline.
     *
     * @param discipline the discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    /**
     * Gets competition.
     *
     * @return the competition
     */
    public Competition getCompetition() {
        return competition;
    }

    /**
     * Sets competition.
     *
     * @param competition the competition
     */
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    /**
     * Gets time obtained seconds.
     *
     * @return the time obtained seconds
     */
    public int getTimeObtainedSeconds() {
        return timeObtainedSeconds;
    }

    /**
     * Sets time obtained seconds.
     *
     * @param timeObtainedSeconds the time obtained seconds
     */
    public void setTimeObtainedSeconds(int timeObtainedSeconds) {
        if (timeObtainedSeconds > 0) {
            this.timeObtainedSeconds = timeObtainedSeconds;
            return;
        }

        throw new IllegalArgumentException("Time obtained must be greater than 0");
    }

    /**
     * Gets place.
     *
     * @return the place
     */
    public int getPlace() {
        return place;
    }

    /**
     * Sets place.
     *
     * @param place the place
     */
    public void setPlace(int place) {
        if (place > 0) {
            this.place = place;
            return;
        }

        throw new IllegalArgumentException("Place must be greater than 0");
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
		Result other = (Result) obj;
		if (competition == null) {
			if (other.competition != null)
				return false;
		} else if (!competition.equals(other.competition))
			return false;
		if (discipline == null) {
			if (other.discipline != null)
				return false;
		} else if (!discipline.equals(other.discipline))
			return false;
		if (idc != other.idc)
			return false;
		if (place != other.place)
			return false;
		if (swimmer == null) {
			if (other.swimmer != null)
				return false;
		} else if (!swimmer.equals(other.swimmer))
			return false;
		if (timeObtainedSeconds != other.timeObtainedSeconds)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Result [idc=" + idc + ", timeObtainedSeconds=" + timeObtainedSeconds + ", place=" + place + ", swimmer="
				+ swimmer + ", discipline=" + discipline + ", competition=" + competition + "]";
	}
    
    
}
