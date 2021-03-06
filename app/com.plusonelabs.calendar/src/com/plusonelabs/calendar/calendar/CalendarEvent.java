package com.plusonelabs.calendar.calendar;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.plusonelabs.calendar.model.Event;

public class CalendarEvent extends Event {

	private int eventId;
	private String title;
	private DateTime endDate;
	private int color;
	private boolean allDay;
	private String location;
	private boolean alarmActive;
	private boolean recurring;
	private boolean spansMultipleDays;
	private CalendarEvent originalEvent;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setAlarmActive(boolean active) {
		this.alarmActive = active;
	}

	public boolean isAlarmActive() {
		return alarmActive;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public boolean isPartOfMultiDayEvent() {
		return spansMultipleDays;
	}

	public void setSpansMultipleDays(boolean spansMultipleDays) {
		this.spansMultipleDays = spansMultipleDays;
	}

	public int daysSpanned() {
		DateMidnight startMidnight = getStartDate().toDateMidnight();
		DateMidnight endMidnight = getEndDate().toDateMidnight();
		int days = Days.daysBetween(startMidnight, endMidnight).getDays();
		if (!isAllDay() && !getEndDate().equals(endMidnight)) {
			days++;
		}
		return days;
	}

	public boolean isStartOfMultiDayEvent() {
		return isPartOfMultiDayEvent() && getOriginalEvent().getStartDate().equals(getStartDate());
	}

	public boolean isEndOfMultiDayEvent() {
		return isPartOfMultiDayEvent() && getOriginalEvent().getEndDate().equals(getEndDate());
	}

	public boolean spansOneFullDay() {
		return getStartDate().plusDays(1).isEqual(endDate);
	}

	public void setOriginalEvent(CalendarEvent originalEvent) {
		this.originalEvent = originalEvent;
	}

	public CalendarEvent getOriginalEvent() {
		return originalEvent;
	}

	public int compareTo(CalendarEvent otherEntry) {
		if (isSameDay(otherEntry.getStartDate())) {
			if (allDay) {
				return -1;
			} else if (otherEntry.allDay) {
				return 1;
			}
		}
		return super.compareTo(otherEntry);
	}

	@Override
	protected CalendarEvent clone() {
		CalendarEvent clone = new CalendarEvent();
		clone.setStartDate(getStartDate());
		clone.endDate = endDate;
		clone.eventId = eventId;
		clone.title = title;
		clone.allDay = allDay;
		clone.color = color;
		clone.alarmActive = alarmActive;
		clone.recurring = recurring;
		clone.spansMultipleDays = spansMultipleDays;
		return clone;
	}

	@Override
	public String toString() {
		return "CalendarEvent [eventId=" + eventId + ", "
				+ (title != null ? "title=" + title + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "") + "color=" + color
				+ ", allDay=" + allDay + ", alarmActive=" + alarmActive + ", recurring="
				+ recurring + ", spansMultipleDays=" + spansMultipleDays + ", "
				+ (originalEvent != null ? "originalEvent=" + originalEvent + ", " : "")
				+ (location != null ? "location=" + location : "") + "]";
	}

}
