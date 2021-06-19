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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.internal.NotNull;

import pl.course.swimming.competitions.dto.ResultDto;

import java.lang.IllegalArgumentException;

/**
 * Result model
 * @version 1.0
 * @category POJO
 * @see pl.course.swimming.competitions.dto.ResultDto
 * */
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
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_swimmer")
    @NotNull
    private Swimmer swimmer;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_discipline")
    @NotNull
    private Discipline discipline;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_competition")
    @NotNull
    private Competition competition;
    
    public Result() {}
    
	public Result(Result result) {
		this.idc = result.getIdc();
		this.timeObtainedSeconds = result.getTimeObtainedSeconds();
		this.place = result.getPlace();
		this.swimmer = new Swimmer(result.getSwimmer());
		this.discipline = new Discipline(result.getDiscipline());
		this.competition = new Competition(result.getCompetition());
	}
	
	public void update(ResultDto resultDto, Swimmer swimmer, Discipline discipline, Competition competition) {
		this.setTimeObtainedSeconds(resultDto.getTimeObtainedSeconds());
		this.setPlace(resultDto.getPlace());
		this.setSwimmer(swimmer);
		this.setDiscipline(discipline);
		this.setCompetition(competition);
	}

	public long getIdc() {
        return idc;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public void setSwimmer(Swimmer swimmer) {
        this.swimmer = swimmer;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public int getTimeObtainedSeconds() {
        return timeObtainedSeconds;
    }

    public void setTimeObtainedSeconds(int timeObtainedSeconds) {
        if (timeObtainedSeconds > 0) {
            this.timeObtainedSeconds = timeObtainedSeconds;
            return;
        }

        throw new IllegalArgumentException("Time obtained must be greater than 0");
    }

    public int getPlace() {
        return place;
    }

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
		result = prime * result + ((competition == null) ? 0 : competition.hashCode());
		result = prime * result + ((discipline == null) ? 0 : discipline.hashCode());
		result = prime * result + (int) (idc ^ (idc >>> 32));
		result = prime * result + place;
		result = prime * result + ((swimmer == null) ? 0 : swimmer.hashCode());
		result = prime * result + timeObtainedSeconds;
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
