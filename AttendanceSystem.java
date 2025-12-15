import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class AttendanceSystem{
    
    private final AttendanceBasicSystem basic;
    public AttendanceSystem(AttendanceBasicSystem basic){
       this.basic = basic;
    }

    public void show() {
        System.out.println("勤怠一覧を表示します。");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        for (Employee e : basic.getEmployees()){
         AttendanceRecord r = basic.getRecord(e.getId());

         String clockInStr;
         String clockOutStr;
         if(r.getClockIn() != null){
            clockInStr = r.getClockIn().format(formatter);
         }else{
            clockInStr = "未打刻";
         }
         if(r.getClockOut() != null){
            clockOutStr = r.getClockOut().format(formatter);
         }else{
            clockOutStr = "未打刻";
         }
         System.out.printf("ID:%d / 名前:%s / 出勤:%s / 退勤:%s%n", 
            e.getId(),
            e.getName(),
            clockInStr,
            clockOutStr);

        }
    } 


    public void workingDuration(int id){
        if(! basic.keyContained(id)){
            System.out.println("社員登録がされていません。");
            System.out.println("1を選択して社員登録をしてください。");
        }
        AttendanceRecord rec = basic.getRecord(id);
        if(rec.getClockIn() == null){
           System.out.println("出勤時の打刻が済んでいません。");
           return;
        }
        Duration duration = rec.calculateDuration();
        if(duration == null){
           System.out.println("正常に出勤時間の計算がされませんでした。");
           return;
        }
        if(duration.isNegative()){
           System.out.println("正常に出勤時間の計算がされませんでした。");
           return;
        }
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        String hisName = basic.getNameById(id);
        System.out.println( hisName +"さんの実勤務時間は"
        +hours+"時間"+minutes+"分です。" );
    }
}
    