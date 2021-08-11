package website.yuanhui.util;

import website.yuanhui.entity.LineNumberString;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {
    private static final String FILE_TYPE = "properties";

    public static List<String> propertiesPath(String filePath) {
        //properties
        if (filePath.endsWith(FILE_TYPE)) {
            return Collections.singletonList(filePath);
        }
        File file = new File(filePath);
        return propertiesPath(file);
    }

    public static List<String> propertiesPath(File file) {
        List<String> target = new ArrayList<>();
        if (file.isFile()) {
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith(FILE_TYPE)) {
                target.add(absolutePath);
            }
            return target;
        }
        File[] files = file.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        for (File temp : files) {
            String absolutePath = temp.getAbsolutePath();
            if (absolutePath.endsWith(FILE_TYPE)) {
                target.add(absolutePath);
            } else {
                target.addAll(propertiesPath(temp));
            }
        }
        return target;
    }

    /**
     * 将properties 文件转为
     * 以行为对象的java对象
     * 保存行号以及 next对象,上一行对象
     *
     * @param file
     * @return
     */
    public static List<LineNumberString> toLineNumberStringList(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String[] strings = bufferedReader.lines().toArray(String[]::new);
            List<LineNumberString> list = new ArrayList<>();
            LineNumberString previous = null;
            LineNumberString next = null;
            for (int i = 0; i < strings.length; i++) {
                LineNumberString current = LineNumberString.of(i, strings[i]);
                if (i != 0) {
                    previous = list.get(i - 1);
                }
                current.setPrevious(previous);
                if (previous != null) {
                    previous.setNext(current);
                }
                list.add(current);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static LineNumberString toLineNumberString(File file) {
        LineNumberString result = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String[] strings = bufferedReader.lines().toArray(String[]::new);
            LineNumberString[] arr = new LineNumberString[strings.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = LineNumberString.of(i, strings[i]);
                if (i != 0) {
                    arr[i].setPrevious(arr[i - 1]);
                    arr[i - 1].setNext(arr[i]);
                }
            }
            result = arr[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
