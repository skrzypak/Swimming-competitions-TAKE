package pl.course.swimming.competitions.dto;

/**
 * Result class within foreign keys as long primitive type (swimmer, discipline, competition)
 *
 * @category DTO
 * @see pl.course.swimming.competitions.model.Result
 */
public class ResultDto {
	
    private int timeObtainedSeconds;
    private int place;
    private long swimmerIdc;
    private long disciplineIdc;
    private long competitionIdc;

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
		this.timeObtainedSeconds = timeObtainedSeconds;
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
		this.place = place;
	}

	/**
	 * Gets swimmer idc.
	 *
	 * @return the swimmer idc
	 */
	public long getSwimmerIdc() {
		return swimmerIdc;
	}

	/**
	 * Sets swimmer idc.
	 *
	 * @param swimmer_id the swimmer id
	 */
	public void setSwimmerIdc(long swimmer_id) {
		this.swimmerIdc = swimmer_id;
	}

	/**
	 * Gets discipline idc.
	 *
	 * @return the discipline idc
	 */
	public long getDisciplineIdc() {
		return disciplineIdc;
	}

	/**
	 * Sets discipline idc.
	 *
	 * @param discipline_id the discipline id
	 */
	public void setDisciplineIdc(long discipline_id) {
		this.disciplineIdc = discipline_id;
	}

	/**
	 * Gets competition idc.
	 *
	 * @return the competition idc
	 */
	public long getCompetitionIdc() {
		return competitionIdc;
	}

	/**
	 * Sets competition idc.
	 *
	 * @param competition_id the competition id
	 */
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
