import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main{
    public static void main(String[] args){
        AttendanceSystem system = new AttendanceSystem();
        Scanner sc = new Scanner(System.in);
        
        
        while (true){
            System.out.println("\\n--- 勤怠管理 --");
            System.out.println("1;社員登録");
            System.out.println("2:出勤打刻");  
            System.out.println("3:退勤打刻");
            System.out.println("4:一覧表示");
            System.out.println("5;実勤務時間一覧表示");
            System.out.println("0:終了");

            System.out.println("番号を入力してください");
            String choice = sc.nextLine();
            try{
            switch (choice){
                case "1":
                  System.out.print("社員ID:");
                  int id = Integer.parseInt(sc.nextLine());
                  System.out.print("名前");
                  String name = sc.nextLine();
                  if (name == ""){
                    System.out.println("名前が入力されませんでした。");
                    System.out.println("入力しなおしてください。");
                    break;
                  }
                  system.addEmployees(id, name);
                  System.out.println(name+"さんの社員登録が完了しました。");
                  break;

                case "2":
                  System.out.print("社員ID:");
                  Integer id1 = Integer.parseInt(sc.nextLine());
                  if(system.getNameById(id1) == null){
                    System.out.println("そのidは登録されていません。");
                    break;
                  }else{
                    LocalDateTime time1 = LocalDateTime.now();
                    system.clockIn(id1, time1);
                    System.out.println(system.getNameById(id1)+"さんが出勤しました。");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    System.out.println("ただ今の時刻は"+time1.format(formatter)+"です。");
                    break;
                   }

                case "3":
                  System.out.print("社員ID:");
                  Integer id2 = Integer.parseInt(sc.nextLine());
                  if(system.getNameById(id2) == null){
                    System.out.println("そのidは登録されていません。");
                    break;
                   }
                  LocalDateTime time2 = LocalDateTime.now();
                  system.clockOut(id2, time2);
                  System.out.println(system.getNameById(id2)+"さんが退勤しました。");
                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                  System.out.println("ただ今の時刻は"+time2.format(formatter)+"です。");
                  break;

                case "4":
                 system.show();
                 break;

                case "5":
                 System.out.print("社員ID:");
                 int id3 = Integer.parseInt(sc.nextLine());
                 system.workingDuration(id3);
                 break;
        
                case "0":
                 System.out.println("終了します。");
                 return;
                default:
                 System.out.println("もう一度入力してください。");
              }
            }catch (NumberFormatException e){
            System.out.println("数字を入力してください");
            }catch (Exception e) {
             System.out.println("予期せぬエラーが発生しました" + e.getMessage());
             e.printStackTrace();
            }
        }
     }
  }
    