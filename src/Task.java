import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String status;
    private String dayOfWeek;

    public Task(String title, String dayOfWeek) {
        this.title = title;
        this.status = "Pending";
        this.dayOfWeek = dayOfWeek;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    @Override
    public String toString() {
        return title + " (" + status + ") - Day: " + dayOfWeek;
    }
}
