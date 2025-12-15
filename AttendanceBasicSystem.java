import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class AttendanceBasicSystem{
    private Map<Integer, Employee> employees = new HashMap<>();
    private Map<Integer, AttendanceRecord> records = new HashMap<>();

    public Collection<Employee> getEmployees(){
         return employees.values();
    }

    public AttendanceRecord getRecordById(){
        Employee em = new Employee();
        return records.get(em.getId());
    }

    public AttendanceRecord getRecord(int id){
        return records.get(id);
    }

    public boolean keyContained(int id){
        return employees.containsKey(id);
    }


    public String getNameById(int id){
        Employee emp = employees.get(id);
        if (emp != null){
            return emp.getName();
        }else{
            return null;
        }
    }

    public boolean addEmployees(int id, String name){
        if(getNameById(id) != null){
            return false;
        }else{
            employees.put(id, new Employee(id, name));
            records.put(id, new AttendanceRecord());
            return true;
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
}