package roomdemo.wiseass.com.roomdemo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Room will use the Class name as a default table name.
 *
 * This can be chagned by setting @Entity(tableName = "tableName")
 */
@Entity
public class ListItem implements Serializable {

    //itemId is simply a value based on the current date, to the second. This kinda ensures it's uniqueness
    @PrimaryKey
    @NonNull
    private String itemId;
    private String message;
    private long preparation;
    private long travail;
    private long reposcourt;
    private long nbrCycles;
    private long nbrSeries;
    private long reposLong;
    private int colorResource;

    public ListItem(String itemId, String message, Long preparation, Long reposLong,
                    Long travail, Long reposcourt, Long nbrCycles, Long nbrSeries,
                    int colorResource) {
        this.itemId = itemId;
        this.message = message;
        this.preparation = preparation;
        this.travail = travail;
        this.reposcourt = reposcourt;
        this.nbrCycles = nbrCycles;
        this.nbrSeries = nbrSeries;
        this.reposLong = reposLong;
        this.colorResource = colorResource;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getPreparation() {
        return preparation;
    }

    public void setPreparation(long preparation) {
        this.preparation = preparation;
    }

    public long getTravail() {
        return travail;
    }

    public void setTravail(long travail) {
        this.travail = travail;
    }

    public long getReposcourt() {
        return reposcourt;
    }

    public void setReposcourt(long reposcourt) {
        this.reposcourt = reposcourt;
    }

    public long getNbrCycles() {
        return nbrCycles;
    }

    public void setNbrCycles(long nbrCycles) {
        this.nbrCycles = nbrCycles;
    }

    public long getNbrSeries() {
        return nbrSeries;
    }

    public void setNbrSeries(long nbrSeries) {
        this.nbrSeries = nbrSeries;
    }

    public long getReposLong() {
        return reposLong;
    }

    public void setReposLong(long reposLong) {
        this.reposLong = reposLong;
    }
}
