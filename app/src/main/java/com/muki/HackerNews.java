
package com.muki;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HackerNews {

    @SerializedName("readiness")
    @Expose
    private List<Readiness> readiness = new ArrayList<Readiness>();

    /**
     * 
     * @return
     *     The readiness
     */
    public List<Readiness> getReadiness() {
        return readiness;
    }

    /**
     * 
     * @param readiness
     *     The readiness
     */
    public void setReadiness(List<Readiness> readiness) {
        this.readiness = readiness;
    }

}
