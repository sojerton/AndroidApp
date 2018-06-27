package com.example.sojertondigma.firstapplication;

public class Schedule {

    private Long id;
    private String mTimeFrom;
    private String mTimeTill;
    private String mSubject;
    private String mPrepod;
    private String mRoom;

    public Schedule(Long id, String mTimeFrom, String mTimeTill, String mSubject, String mPrepod, String mRoom) {
        this.id = id;
        this.mTimeFrom = mTimeFrom;
        this.mTimeTill = mTimeTill;
        this.mSubject = mSubject;
        this.mPrepod = mPrepod;
        this.mRoom = mRoom;
    }

    public Long getId() {
        return id;
    }

    public String getmTimeFrom() {
        return mTimeFrom;
    }

    public String getmTimeTill() {
        return mTimeTill;
    }

    public String getmSubject() {
        return mSubject;
    }

    public String getmPrepod() {
        return mPrepod;
    }

    public String getmRoom() {
        return mRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (!id.equals(schedule.id)) return false;
        if (mTimeFrom != null ? !mTimeFrom.equals(schedule.mTimeFrom) : schedule.mTimeFrom != null)
            return false;
        if (mTimeTill != null ? !mTimeTill.equals(schedule.mTimeTill) : schedule.mTimeTill != null)
            return false;
        if (!mSubject.equals(schedule.mSubject)) return false;
        if (mPrepod != null ? !mPrepod.equals(schedule.mPrepod) : schedule.mPrepod != null)
            return false;
        return mRoom != null ? mRoom.equals(schedule.mRoom) : schedule.mRoom == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (mTimeFrom != null ? mTimeFrom.hashCode() : 0);
        result = 31 * result + (mTimeTill != null ? mTimeTill.hashCode() : 0);
        result = 31 * result + mSubject.hashCode();
        result = 31 * result + (mPrepod != null ? mPrepod.hashCode() : 0);
        result = 31 * result + (mRoom != null ? mRoom.hashCode() : 0);
        return result;
    }
}
