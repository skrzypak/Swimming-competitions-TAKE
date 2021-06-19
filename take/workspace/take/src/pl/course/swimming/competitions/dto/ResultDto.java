package pl.course.swimming.competitions.dto;

/**
 * Result class within foreign keys as long primitive type (swimmer, discipline, competition)
 * @category DTO
 * @see pl.course.swimming.competitions.model.Result
 * */
public class ResultDto {
	
    private int timeObtainedSeconds;
    private int place;
    private long swimmerIdc;
    private long disciplineIdc;
    private long competitionIdc;
    
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
	
	public long getSwimmerIdc() {
		return swimmerIdc;
	}
	
	public void setSwimmerIdc(long swimmer_id) {
		this.swimmerIdc = swimmer_id;
	}
	
	public long getDisciplineIdc() {
		return disciplineIdc;
	}
	
	public void setDisciplineIdc(long discipline_id) {
		this.disciplineIdc = discipline_id;
	}
	
	public long getCompetitionIdc() {
		return competitionIdc;
	}
	
	public void setCompetitionIdc(long competition_id) {
		this.competitionIdc = competition_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (competitionIdc ^ (competitionIdc >>> 32));
		result = prime * result + (int) (disciplineIdc ^ (disciplineIdc >>> 32));
		result = prime * result + place;
		result = prime * result + (int) (swimmerIdc ^ (swimmerIdc >>> 32));
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
		ResultDto other = (ResultDto) obj;
		if (competitionIdc != other.competitionIdc)
			return false;
		if (disciplineIdc != other.disciplineIdc)
			return false;
		if (place != other.place)
			return false;
		if (swimmerIdc != other.swimmerIdc)
			return false;
		if (timeObtainedSeconds != other.timeObtainedSeconds)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResultDto [timeObtainedSeconds=" + timeObtainedSeconds + ", place=" + place + ", swimmerIdc="
				+ swimmerIdc + ", disciplineIdc=" + disciplineIdc + ", competitionIdc=" + competitionIdc + "]";
	}
}
