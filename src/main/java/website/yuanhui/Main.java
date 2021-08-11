package website.yuanhui;

import website.yuanhui.entity.LineNumberString;
import website.yuanhui.handler.YamlConsolePrint;
import website.yuanhui.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String prefix = "  ";

    public static void main(String[] args) {

        {//处理字符问题
            String x = "\\u4E00\\u5206\\u949F\\u6570\\u636E";
            String[] split = x.split("\\\\u");
            for (String s : split) {
                if (s.isEmpty()) {
                    continue;
                }
                Integer decode = Integer.decode("0x" + s);
                System.out.print((char) decode.intValue());
            }
            System.out.println(x);
        }

        String filePath = "C:\\Users\\Administrator\\IdeaProjects\\cloud-config-center\\api\\control\\pre\\application.properties";
        List<String> strings = FileUtil.propertiesPath(filePath);
        for (String string : strings) {
            System.out.println(string);
            extracted(string);
        }
    }

    private static void extracted(String filePath) {
        int i1 = filePath.lastIndexOf(".");
        String outFilePath = filePath.substring(0, i1 + 1).concat("yaml");
        System.out.println(outFilePath);
        List<LineNumberString> list = FileUtil.toLineNumberStringList(new File(filePath));
        for (LineNumberString temp2 : list) {
            System.out.println(temp2.getSource());
//            if (temp2.getCache().getResult().hasNode()) {
//                System.out.println(temp2.getCache().getResult().getNode());
//            }
        }
        //writeToYaml(outFilePath, list);
    }

    public static void writeToYaml(String filePath, List<LineNumberString> list) {
        List<TokenStream> roots = getTokenStreams(list);
        new YamlConsolePrint().handler(roots);
        //new YamlFileWriter(filePath).handler(roots);
    }

    private static List<TokenStream> getTokenStreams(List<LineNumberString> list) {
        List<TokenStream> roots = new ArrayList<>();
        for (LineNumberString line : list) {
//            if (line.isNode()) {
//                continue;
//            }
            load(roots, line);
        }
        return roots;
    }

    private static void load(List<TokenStream> roots, LineNumberString line) {
//        CacheKV cache = line.getCache();
//        TokenStream token = cache.getToken();
//        load(roots, token);
    }

    private static void load(List<TokenStream> roots, TokenStream tokenStream) {
        if (tokenStream == null) {
            return;
        }
        boolean isCache = false;
        TokenStream cache = null;
        for (TokenStream root : roots) {
            if (root.getVal().equals(tokenStream.getVal())) {
                isCache = true;
                cache = root;
                break;
            }
        }
        if (!isCache) {
            cache = new TokenStream();
            cache.setVal(tokenStream.getVal());
            cache.setChildren(new ArrayList<>());
            cache.setResultStream(tokenStream.getResultStream());
            roots.add(cache);
        } else {
            if (cache.getChildren() == null) {
                cache.setChildren(new ArrayList<>());
            }
        }
        load(cache.getChildren(), tokenStream.getNext());
    }

}
