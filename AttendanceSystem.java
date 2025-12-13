import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.Map;
import java.util.HashMap;

public class AttendanceSystem{
   
    private Map<Integer, Employee> employees = new HashMap<>();
    private Map<Integer, AttendanceRecord> records = new HashMap<>();

public String getNameById(int id){
        Employee emp = employees.get(id);
        if (emp != null){
            return emp.getName();
        }else{
            return null;
        }
   }
    public void addEmployees(int id, String name){
        if(getNameById(id) != null){
          System.out.println("すでに社員情報が登録されています。");
        }else{
            employees.put(id, new Employee(id, name));
            records.put(id, new AttendanceRecord());
        }
    }
    public void clockIn(int id, LocalDateTime time){
        AttendanceRecord r = records.get(id);
        if (r != null){
            r.setClockIn(time);
            System.out.println("出勤打刻完了");
        }else  {
            System.out.println("社員が存在しません");
        }
    }




    public void clockOut(int id, LocalDateTime time){
        AttendanceRecord r = records.get(id);
        if (r != null){
            r.setClockOut(time);
            System.out.println("退勤打刻完了");
        }else {
            System.out.println("社員が存在しません");
        }
    }
    public void show() {
        System.out.println("勤怠一覧を表示します。");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        for (Employee e : employees.values()){
         AttendanceRecord r = records.get(e.getId());

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
        if(! employees.containsKey(id)){
            System.out.println("社員登録がされていません。");
            System.out.println("1を選択して社員登録をしてください。");
        }
        AttendanceRecord rec = records.get(id);
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
        String a = employees.get(id).getName();

        System.out.println( a+"さんの実勤務時間は"
        +hours+"時間"+minutes+"分です。" );
    }
}