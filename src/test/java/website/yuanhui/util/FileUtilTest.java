package website.yuanhui.util;


import website.yuanhui.entity.LineNumberString;

import java.io.File;

class FileUtilTest {

    public static void main(String[] args) {
        LineNumberString line = FileUtil.toLineNumberString(new File("C:\\Users\\Administrator\\IdeaProjects\\yaml-properties-convert\\src\\main\\resources\\application.properties"));
        print(line);
    }

    public static void print(LineNumberString o) {
        if (o == null) {
            return;
        }
        System.out.println(" " + o.getLineNumber() + " " + o.isStream() + " " + o.getSource());
        print(o.getNext());
    }
}