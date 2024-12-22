import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArbTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入你的生日(yyyy-mm-dd)>");
        try {
            String inputBirthday = scan.nextLine();
            LocalDate myBirthday = Birthday2.formatTheDate(inputBirthday);
            Birthday2.getInstance().setMyBirthday(myBirthday);
            Birthday2.getInstance().isBirthday();
        } catch (DateTimeException e) {
            throw new RuntimeException("输入的日期非法或者格式错误");
        } finally {
            scan.close();
        }
    }
}

class Birthday2 {
    private LocalDate myBirthday;
    private static final LocalDate today = LocalDate.now();
    private DayOfWeek dayOfWeek;

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
        this.dayOfWeek = dayOfWeek;
    }

    public static LocalDate formatTheDate(String myBirthdayInput) throws DateTimeException {
        String character = "[ ,./-]";
        Pattern regex = Pattern.compile(character);
        Matcher matcher = regex.matcher(myBirthdayInput);
        String formattedDate = matcher.replaceAll("");
        StringBuilder str = new StringBuilder(formattedDate);

        if (formattedDate.length() == 6) {
            str.insert(4, "-0").insert(7, "-0");
        } else if (formattedDate.length() == 8) {
            str.insert(4, "-").insert(7, "-");
        } else if (formattedDate.length() == 9) {
            str.insert(4, "-0").insert(7, "-0");
        } else if (formattedDate.length() == 10) {
            str.insert(4, "-").insert(7, "-");
        } else {
            throw new RuntimeException("输入的日期格式错误或输入的日期非法");
        }
        return LocalDate.parse(str.toString());
    }

    public String dayOfWeek() {
        dayOfWeek = myBirthday.getDayOfWeek();
        return switch (dayOfWeek.toString()) {
            case "MONDAY" -> "星期一";
            case "TUESDAY" -> "星期二";
            case "WEDNESDAY" -> "星期三";
            case "THURSDAY" -> "星期四";
            case "FRIDAY" -> "星期五";
            case "SATURDAY" -> "星期六";
            case "SUNDAY" -> "星期日";
            default -> "";
        };
    }

    public static int findNextLeapYear(int year) {
        int nextYear = year + 1;
        while (!isLeapYear(nextYear)) {
            nextYear++;
        }
        return nextYear;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void setThisYears() {
        if (myBirthday.isLeapYear() && myBirthday.getMonthValue() == 2 && myBirthday.getDayOfMonth() == 29) {
            myBirthday = myBirthday.withYear(today.getYear());
            myBirthday = myBirthday.withYear(findNextLeapYear(myBirthday.getYear()));
        } else {
            myBirthday = myBirthday.withYear(today.getYear() + 1);
        }
    }

    public long daysUntilMyBirthday() {
        return ChronoUnit.DAYS.between(today, myBirthday);
    }

    public void isBirthday() {
        if (today.isEqual(myBirthday)) {
            System.out.println("今天是" + dayOfWeek());
            System.out.println("是你的生日！生日快乐！");
        } else if (today.isBefore(myBirthday) && myBirthday.getYear() == today.getYear()) {
            System.out.println("你的生日是" + dayOfWeek());
            System.out.println("你的生日在今年，距离生日的到来还有" + daysUntilMyBirthday() + "天");
        } else if (today.isAfter(myBirthday)) {
            setThisYears();
            System.out.println("你的生日是" + dayOfWeek());
            System.out.println("你的生日已经过去了哦，距离你生日到来还有" + daysUntilMyBirthday() + "天");
        } else {
            System.out.println("你的生日是" + dayOfWeek());
            System.out.println("未来的生日！请保持这份对未来的热情，距离生日到来还有" + daysUntilMyBirthday() + "天");
        }
    }
}
