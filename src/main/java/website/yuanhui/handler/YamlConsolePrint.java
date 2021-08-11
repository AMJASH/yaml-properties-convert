package website.yuanhui.handler;

import website.yuanhui.Main;

import java.util.List;

public class YamlConsolePrint implements IYamlHandler {

    public static void print(List<TokenStream> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (TokenStream tokenStream : list) {
            System.out.print(tokenStream.getVal() + ":");
            print(tokenStream.getChildren(), Main.prefix);
        }
    }

    private static void print(List<TokenStream> list, String prefix) {
        System.out.print('\n');
        for (TokenStream tokenStream : list) {
            boolean empty = tokenStream.getChildren().isEmpty();
            if (empty && tokenStream.getResultStream().hasNode()){
                System.out.println(tokenStream.getResultStream().getNode());
            }
            System.out.print(prefix + tokenStream.getVal() + ":");
            if (tokenStream.getChildren().isEmpty()) {
                System.out.print(' ' + tokenStream.getResultStream().getVal());
            }
            print(tokenStream.getChildren(), prefix + Main.prefix);
        }
    }

    @Override
    public void handler(List<TokenStream> roots) {
        print(roots);
    }
}
