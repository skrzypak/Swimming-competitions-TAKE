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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.internal.NotNull;

import java.lang.IllegalArgumentException;

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
    
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_swimmer")
    @NotNull
    private Swimmer swimmer;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_discipline")
    @NotNull
    private Discipline discipline;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_competition")
    @NotNull
    private Competition competition;

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
	public String toString() {
		return "Result [idc=" + idc + ", timeObtainedSeconds=" + timeObtainedSeconds + ", place=" + place + ", swimmer="
				+ swimmer + ", discipline=" + discipline + ", competition=" + competition + "]";
	}
    
    
}
