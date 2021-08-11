package website.yuanhui.handler;

import website.yuanhui.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class YamlFileWriter implements IYamlHandler {
    private String filePath;

    public YamlFileWriter(String filePath) {
        this.filePath = filePath;
    }

    public static void write(List<TokenStream> list, FileOutputStream fileOutputStream) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (TokenStream tokenStream : list) {
            fileOutputStream.write((tokenStream.getVal() + ":").getBytes());
            write(tokenStream.getChildren(), Main.prefix, fileOutputStream);
        }
        fileOutputStream.close();
    }

    private static void write(List<TokenStream> list, String prefix, FileOutputStream fileOutputStream) throws IOException {
        fileOutputStream.write("\n".getBytes());
        for (TokenStream tokenStream : list) {
            boolean empty = tokenStream.getChildren().isEmpty();
            if (empty && tokenStream.getResultStream().hasNode()) {
                fileOutputStream.write(tokenStream.getResultStream().getNode().getBytes());
                fileOutputStream.write("\n".getBytes());
            }
            fileOutputStream.write((prefix + tokenStream.getVal() + ":").getBytes());
            if (empty) {
                fileOutputStream.write((' ' + tokenStream.getResultStream().getVal()).getBytes());
            }
            write(tokenStream.getChildren(), prefix + Main.prefix, fileOutputStream);
        }
    }

    @Override
    public void handler(List<TokenStream> roots) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            write(roots, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
