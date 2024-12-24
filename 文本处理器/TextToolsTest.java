import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 目前代码暂时没有进行安全问题的考虑，在之后会进行
 */
public class TextToolsTest {
    public static void main(String[] args) {
        TextProcessor.getInstance().addText();
        TextProcessor.getInstance().toPrint();
        TextProcessor.getInstance().shareFormatChineseText();
        TextProcessor.getInstance().shareFormatChineseTextBaseList();
        TextProcessor.getInstance().reverseText();
        TextProcessor.getInstance().reverseTextBaseList();
        TextProcessor.getInstance().toPrint();
        TextProcessor.getInstance().resetStringList();
    }
}

class TextProcessor {
    private List<String> strList;

    private TextProcessor() {
        strList = new ArrayList<>();
    }

    private static final TextProcessor INSTANCE = new TextProcessor();

    /**
     * 单例模式
     *
     * @return 返回该类的对象
     */
    public static TextProcessor getInstance() {
        return INSTANCE;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    /**
     * 添加文本
     */
    public void addText() {
        String str = "";
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            str = scan.nextLine();
            if (str.isEmpty()) {
                break;
            }
            strList.add(str);
        }
        scan.close();
    }

//    public void reverseText() {
//        List<String> newStrList = new ArrayList<>();
//        StringBuilder str = new StringBuilder();
//        for (String line : strList) {
//            str.append(line).reverse();
//            newStrList.add(str.toString());
//            str.setLength(0);
//        }
//        strList = newStrList;
//    }

    /**
     * 基于列表更改的文本反转
     */
    //你应该也看到了上面被注释的代码了吧，那是另外一种实现方式，也是最初的设计思路
    public void reverseTextBaseList() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            strList.set(i, str.append(strList.get(i)).reverse().toString());
            str.setLength(0);
        }
    }

    /**
     * 文本反转
     */
    public void reverseText() {
        StringBuilder str = new StringBuilder();
        for (String s : strList) {
            System.out.println(str.append(s).reverse());
            str.setLength(0);
        }
        System.out.println();
    }

    /**
     * 基于列表更改的中文“文本”格式化
     */
    public void shareFormatChineseTextBaseList() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < strList.size(); i++) {
            str.append(strList.get(i));
            if (str.charAt(str.length() - 1) == '，'
                    || str.charAt(str.length() - 1) == '。'
                    || str.charAt(str.length() - 1) == '；'
                    || str.charAt(str.length() - 1) == '：'
                    || str.charAt(str.length() - 1) == '！') {
                str.delete(str.length() - 1, str.length());
            }
            strList.set(i, "“" + str + "”");
            str.setLength(0);
        }
    }

    /**
     * 中文“文本”格式化
     */
    public void shareFormatChineseText() {
        StringBuilder str = new StringBuilder();

        for (String s : strList) {
            str.append(s);
            if (str.charAt(str.length() - 1) == '，'
                    || str.charAt(str.length() - 1) == '。'
                    || str.charAt(str.length() - 1) == '；'
                    || str.charAt(str.length() - 1) == '：'
                    || str.charAt(str.length() - 1) == '！') {
                str.delete(str.length() - 1, str.length());
            }
            System.out.println("“" + str + "”");
            str.setLength(0);
        }
        System.out.println();
    }

    /**
     * 遍历列表
     */
    public void toPrint() {
        for (String line : getStrList()) {
            System.out.println(line);
        }
        System.out.println();
    }

    /**
     * 列表初始化
     */
    public void resetStringList() {
        strList.clear();
    }
}
