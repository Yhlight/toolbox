import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ArbTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入你的生日(yyyy-mm-dd)>");
        String inputBirthday = scan.nextLine();
        LocalDate myBirthday = LocalDate.parse(Birthday2.formatTheDate(inputBirthday));
        Birthday2.getInstance().setMyBirthday(myBirthday);
        Birthday2.getInstance().setThisYears();
        Birthday2.getInstance().dayOfWeek();
        Birthday2.getInstance().isBirthday();
    }
}

class Birthday2 {
    private LocalDate myBirthday;
    private static final LocalDate today = LocalDate.now();
    private static DayOfWeek dayOfWeek;

    private Birthday2() {
    }

    private static final Birthday2 INSTANCE = new Birthday2();

    public static Birthday2 getInstance() {
        return INSTANCE;
    }

    public LocalDate getMyBirthday() {
        return myBirthday;
    }

    public void setMyBirthday(LocalDate myBirthday) {
        this.myBirthday = myBirthday;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        Birthday2.dayOfWeek = dayOfWeek;
    }

    //待重写
    public static String formatTheDate(String myBirthdayInput) {
        StringBuilder str = new StringBuilder();

        switch (myBirthdayInput.length()) {
            case 6:
                for (int i = 0; i < 4; i++) {
                    str.append(myBirthdayInput.charAt(i));
                }
                str.append("-0").append(myBirthdayInput.charAt(4)).append("-0").append(myBirthdayInput.charAt(5));
                break;

            case 8:
                for (int i = 0; i < 4; i++) {
                    str.append(myBirthdayInput.charAt(i));
                }
                str.append("-");

                for (int i = 0; i < 2; i++) {
                    if (myBirthdayInput.charAt(i + 4) == ' ' || myBirthdayInput.charAt(i + 4) == ','
                            || myBirthdayInput.charAt(i + 4) == '.' || myBirthdayInput.charAt(i + 4) == '/'
                            || myBirthdayInput.charAt(i + 4) == '-') {
                        str.append("0");
                        continue;
                    }
                    str.append(myBirthdayInput.charAt(i + 4));
                }
                str.append("-");

                for (int i = 0; i < 2; i++) {
                    if (myBirthdayInput.charAt(i + 6) == ' ' || myBirthdayInput.charAt(i + 6) == ','
                            || myBirthdayInput.charAt(i + 6) == '.' || myBirthdayInput.charAt(i + 6) == '/'
                            || myBirthdayInput.charAt(i + 6) == '-') {
                        str.append("0");
                        continue;
                    }
                    str.append(myBirthdayInput.charAt(i + 6));
                }
                break;

            case 9:
                for (int i = 0; i < 4; i++) {
                    str.append(myBirthdayInput.charAt(i));
                }
                str.append("-0").append(myBirthdayInput.charAt(5)).append("-0").append(myBirthdayInput.charAt(7));
                break;

            case 10:
                for (int i = 0; i < myBirthdayInput.length(); i++) {
                    if (myBirthdayInput.charAt(i) == ' ' || myBirthdayInput.charAt(i) == '/'
                            || myBirthdayInput.charAt(i) == ',' || myBirthdayInput.charAt(i) == '.') {
                        str.append('-');
                    } else {
                        str.append(myBirthdayInput.charAt(i));
                    }
                }
                break;

            default:
                throw new RuntimeException("输入的日期格式错误或输入的日期非法");
        }
        return str.toString();
    }

    public void dayOfWeek() {
        dayOfWeek = myBirthday.getDayOfWeek();
        switch (dayOfWeek.toString()) {
            case "MONDAY":
                System.out.println("你的生日是:星期一");
                break;
            case "TUESDAY":
                System.out.println("你的生日是:星期二");
                break;
            case "WEDNESDAY":
                System.out.println("你的生日是:星期三");
                break;
            case "THURSDAY":
                System.out.println("你的生日是:星期四");
                break;
            case "FRIDAY":
                System.out.println("你的生日是:星期五");
                break;
            case "SATURDAY":
                System.out.println("你的生日是:星期六");
                break;
            case "SUNDAY":
                System.out.println("你的生日是:星期日");
                break;
        }
    }

    public void setThisYears() {
        if (myBirthday.getYear() < today.getYear()) {
            myBirthday = myBirthday.withYear(today.getYear());
        }
    }

    public void isBirthday() {
        long daysUntilMyBirthday = ChronoUnit.DAYS.between(today, myBirthday);
        if (daysUntilMyBirthday < 0) {
            if ((myBirthday.getYear() % 4 == 0)
                    && (myBirthday.getYear() % 100 != 0)) {
                daysUntilMyBirthday += 366;
            } else if (myBirthday.getYear() % 400 == 0) {
                daysUntilMyBirthday += 366;
            } else {
                daysUntilMyBirthday += 365;
            }
        }

        if (today.isEqual(myBirthday)) {
            System.out.println("今天就是你的生日！生日快乐！");
        } else if (today.isBefore(myBirthday) && myBirthday.getYear() == today.getYear()) {
            System.out.println("你的生日在今年，距离生日的到来还有" + daysUntilMyBirthday + "天");
        } else if (today.isAfter(myBirthday)) {
            System.out.println("你的生日已经过去了哦，距离你明年的生日到来还有" + daysUntilMyBirthday + "天");
        } else {
            System.out.println("未来的生日！请保持这份对未来的热情，距离生日到来还有" + daysUntilMyBirthday + "天");
        }
    }
}
