import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Birthday {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入你的生日(yyyy-mm-dd)>");
        String myBirthdayInput = "";
        LocalDate myBirthday = null;
        try {
            myBirthdayInput = scan.nextLine();
            myBirthdayInput = formatTheDate(myBirthdayInput);
            myBirthday = createBirthdayWithCurrentYear(myBirthdayInput);
            dayOfWeek(myBirthday);
            isBirthday(myBirthdayInput, myBirthday);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("输入的日期格式错误或输入的日期非法");
        } finally {
            scan.close();
        }
    }

    public static String formatTheDate(String myBirthdayInput) {
        String str = "";

        switch (myBirthdayInput.length()) {
            case 6:
                for (int i = 0; i < 4; i++) {
                    str += myBirthdayInput.charAt(i);
                }
                str += "-0" + myBirthdayInput.charAt(4) + "-0" + myBirthdayInput.charAt(5);
                break;

            case 8:
                for (int i = 0; i < 4; i++) {
                    str += myBirthdayInput.charAt(i);
                }
                str += "-";

                for (int i = 0; i < 2; i++) {
                    if (myBirthdayInput.charAt(i + 4) == ' ' || myBirthdayInput.charAt(i + 4) == ','
                            || myBirthdayInput.charAt(i + 4) == '.' || myBirthdayInput.charAt(i + 4) == '/'
                            || myBirthdayInput.charAt(i + 4) == '-') {
                        str += "0";
                        continue;
                    }
                    str += myBirthdayInput.charAt(i + 4);
                }
                str += "-";

                for (int i = 0; i < 2; i++) {
                    if (myBirthdayInput.charAt(i + 6) == ' ' || myBirthdayInput.charAt(i + 6) == ','
                            || myBirthdayInput.charAt(i + 6) == '.' || myBirthdayInput.charAt(i + 6) == '/'
                            || myBirthdayInput.charAt(i + 6) == '-') {
                        str += "0";
                        continue;
                    }
                    str += myBirthdayInput.charAt(i + 6);
                }
                break;

            case 9:
                for (int i = 0; i < 4; i++) {
                    str += myBirthdayInput.charAt(i);
                }
                str += "-0" + myBirthdayInput.charAt(5) + "-0" + myBirthdayInput.charAt(7);
                break;

            case 10:
                for (int i = 0; i < myBirthdayInput.length(); i++) {
                    if (myBirthdayInput.charAt(i) == ' ' || myBirthdayInput.charAt(i) == '/'
                            || myBirthdayInput.charAt(i) == ',' || myBirthdayInput.charAt(i) == '.') {
                        str += '-';
                    } else {
                        str += myBirthdayInput.charAt(i);
                    }
                }
                break;

            default:
                throw new RuntimeException("输入的日期格式错误或输入的日期非法");
        }
        return str;
    }

    public static LocalDate createBirthdayWithCurrentYear(String formattedBirthday) {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        LocalDate birthday = LocalDate.parse(formattedBirthday);
        LocalDate realBirthday = null;

        if (compareYears(getYearsToString(formattedBirthday), String.valueOf(currentYear)) == -1) {
            realBirthday = birthday.withYear(currentYear);
        } else if (compareDate(String.valueOf(now), formatTheDate(formattedBirthday)) == 1) {
            realBirthday = birthday.withYear(currentYear + 1);
        } else if (compareDate(String.valueOf(now), formatTheDate(formattedBirthday)) == -1) {
            realBirthday = birthday.withYear(Integer.parseInt(getYearsToString(formattedBirthday)));
        } else {
            realBirthday = birthday.withYear(currentYear + 1);
        }
        return realBirthday;
    }

    public static String getYearsToString(String myBirthdayInput) {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str += myBirthdayInput.charAt(i);
        }
        return str;
    }

    public static int getYearsToInt(String myBirthdayInput) {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str += myBirthdayInput.charAt(i);
        }
        return Integer.parseInt(str);
    }

    public static int compareYears(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new RuntimeException("字符串内容为空");
        }

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                count = 1;
                break;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                count = -1;
                break;
            }
        }
        return count;
    }

    public static int compareDate(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new RuntimeException("字符串内容为空");
        }

        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                count = 1;
                break;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                count = -1;
                break;
            }
        }
        return count;
    }

    public static void dayOfWeek(LocalDate myBirthday) {
        DayOfWeek dayOfWeek = myBirthday.getDayOfWeek();
        switch (String.valueOf(dayOfWeek)) {
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

    public static void isBirthday(String myBirthdayInput, LocalDate myBirthday) {
        LocalDate today = LocalDate.now();
        long daysUntilMyBirthday = ChronoUnit.DAYS.between(today, myBirthday);
        if (daysUntilMyBirthday < 0) {
            if ((getYearsToInt(myBirthdayInput) % 4 == 0)
                    && (getYearsToInt(myBirthdayInput) % 100 != 0)) {
                daysUntilMyBirthday += 366;
            } else if (getYearsToInt(myBirthdayInput) % 400 == 0) {
                daysUntilMyBirthday += 366;
            } else {
                daysUntilMyBirthday += 365;
            }
        }

        System.out.println("今年是" + today.getYear() + "年");
        if (compareDate(String.valueOf(today), formatTheDate(myBirthdayInput)) == 0) {
            System.out.println("今天就是你的生日！生日快乐！");
        } else if (compareDate(String.valueOf(today), formatTheDate(myBirthdayInput)) == 1) {
            System.out.println("你的生日已经过去了哦，距离你明年的生日到来还有" + daysUntilMyBirthday + "天");
        } else if (compareYears(String.valueOf(today), formatTheDate(myBirthdayInput)) == 0) {
            System.out.println("你的生日在今年，距离生日的到来还有" + daysUntilMyBirthday + "天");
        } else {
            System.out.println("未来的生日！请保持这份对未来的热情，距离生日到来还有" + daysUntilMyBirthday + "天");
        }
    }
}
