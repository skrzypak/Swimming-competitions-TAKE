package pl.course.swimming.competitions;

public class ResultDto {
	
    private int timeObtainedSeconds;
    private int place;
    private long swimmer_id;
    private long discipline_id;
    private long competition_id;
    
	public int getTimeObtainedSeconds() {
		return timeObtainedSeconds;
	}
	
	public void setTimeObtainedSeconds(int timeObtainedSeconds) {
		this.timeObtainedSeconds = timeObtainedSeconds;
	}
	
	public int getPlace() {
		return place;
	}
	
	public void setPlace(int place) {
		this.place = place;
	}
	
	public long getSwimmer_id() {
		return swimmer_id;
	}
	
	public void setSwimmer_id(long swimmer_id) {
		this.swimmer_id = swimmer_id;
	}
	
	public long getDiscipline_id() {
		return discipline_id;
	}
	
	public void setDiscipline_id(long discipline_id) {
		this.discipline_id = discipline_id;
	}
	
	public long getCompetition_id() {
		return competition_id;
	}
	
	public void setCompetition_id(long competition_id) {
		this.competition_id = competition_id;
	}
	
	@Override
	public String toString() {
		return "ResultDto [timeObtainedSeconds=" + timeObtainedSeconds + ", place=" + place + ", swimmer_id="
				+ swimmer_id + ", discipline_id=" + discipline_id + ", competition_id=" + competition_id + "]";
	}

    
}
