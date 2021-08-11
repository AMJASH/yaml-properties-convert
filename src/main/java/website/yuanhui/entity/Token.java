package website.yuanhui.entity;

public class Token {
    private String val;
    private int line;
    private int pos;

    public Token(String val, int line, int pos) {
        this.val = val;
        this.line = line;
        this.pos = pos;
    }

    public String getVal() {
        return val;
    }

    public int getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }
}
