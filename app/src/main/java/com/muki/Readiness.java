
package com.muki;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Readiness {

    @SerializedName("score_sleep_balance")
    @Expose
    private Integer scoreSleepBalance;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("summary_date")
    @Expose
    private String summaryDate;
    @SerializedName("score_activity_balance")
    @Expose
    private Integer scoreActivityBalance;
    @SerializedName("period_id")
    @Expose
    private Integer periodId;
    @SerializedName("score_temperature")
    @Expose
    private Integer scoreTemperature;
    @SerializedName("score_resting_hr")
    @Expose
    private Integer scoreRestingHr;
    @SerializedName("score_previous_day")
    @Expose
    private Integer scorePreviousDay;
    @SerializedName("score_previous_night")
    @Expose
    private Integer scorePreviousNight;
    @SerializedName("score_recovery_index")
    @Expose
    private Integer scoreRecoveryIndex;

    /**
     * 
     * @return
     *     The scoreSleepBalance
     */
    public Integer getScoreSleepBalance() {
        return scoreSleepBalance;
    }

    /**
     * 
     * @param scoreSleepBalance
     *     The score_sleep_balance
     */
    public void setScoreSleepBalance(Integer scoreSleepBalance) {
        this.scoreSleepBalance = scoreSleepBalance;
    }

    /**
     * 
     * @return
     *     The score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 
     * @return
     *     The summaryDate
     */
    public String getSummaryDate() {
        return summaryDate;
    }

    /**
     * 
     * @param summaryDate
     *     The summary_date
     */
    public void setSummaryDate(String summaryDate) {
        this.summaryDate = summaryDate;
    }

    /**
     * 
     * @return
     *     The scoreActivityBalance
     */
    public Integer getScoreActivityBalance() {
        return scoreActivityBalance;
    }

    /**
     * 
     * @param scoreActivityBalance
     *     The score_activity_balance
     */
    public void setScoreActivityBalance(Integer scoreActivityBalance) {
        this.scoreActivityBalance = scoreActivityBalance;
    }

    /**
     * 
     * @return
     *     The periodId
     */
    public Integer getPeriodId() {
        return periodId;
    }

    /**
     * 
     * @param periodId
     *     The period_id
     */
    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    /**
     * 
     * @return
     *     The scoreTemperature
     */
    public Integer getScoreTemperature() {
        return scoreTemperature;
    }

    /**
     * 
     * @param scoreTemperature
     *     The score_temperature
     */
    public void setScoreTemperature(Integer scoreTemperature) {
        this.scoreTemperature = scoreTemperature;
    }

    /**
     * 
     * @return
     *     The scoreRestingHr
     */
    public Integer getScoreRestingHr() {
        return scoreRestingHr;
    }

    /**
     * 
     * @param scoreRestingHr
     *     The score_resting_hr
     */
    public void setScoreRestingHr(Integer scoreRestingHr) {
        this.scoreRestingHr = scoreRestingHr;
    }

    /**
     * 
     * @return
     *     The scorePreviousDay
     */
    public Integer getScorePreviousDay() {
        return scorePreviousDay;
    }

    /**
     * 
     * @param scorePreviousDay
     *     The score_previous_day
     */
    public void setScorePreviousDay(Integer scorePreviousDay) {
        this.scorePreviousDay = scorePreviousDay;
    }

    /**
     * 
     * @return
     *     The scorePreviousNight
     */
    public Integer getScorePreviousNight() {
        return scorePreviousNight;
    }

    /**
     * 
     * @param scorePreviousNight
     *     The score_previous_night
     */
    public void setScorePreviousNight(Integer scorePreviousNight) {
        this.scorePreviousNight = scorePreviousNight;
    }

    /**
     * 
     * @return
     *     The scoreRecoveryIndex
     */
    public Integer getScoreRecoveryIndex() {
        return scoreRecoveryIndex;
    }

    /**
     * 
     * @param scoreRecoveryIndex
     *     The score_recovery_index
     */
    public void setScoreRecoveryIndex(Integer scoreRecoveryIndex) {
        this.scoreRecoveryIndex = scoreRecoveryIndex;
    }

}
