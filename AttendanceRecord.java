import java.time.LocalDateTime;
import java.time.Duration;

public class AttendanceRecord{
    private LocalDateTime clockin;
    private LocalDateTime clockout;

    public void setClockIn(LocalDateTime time){
    this.clockin = time;
    }

    public void setClockOut(LocalDateTime time){
        this.clockout = time;
    }

    public LocalDateTime getClockIn(){ return clockin;}

    public LocalDateTime getClockOut(){ return clockout;}

    public Duration calculateDuration(){
        if (clockin != null && clockout != null && clockout.isAfter(clockin)){
            return Duration.between(clockin, clockout);
        }
        return null;
    }
}