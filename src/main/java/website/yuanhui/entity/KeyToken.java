package website.yuanhui.entity;

import java.util.Iterator;

public class KeyToken extends Token implements Iterator<KeyToken> {
    //级别 转成树的时候需要
    private int level;
    private KeyToken next;

    public KeyToken(String val, int line, int pos) {
        super(val, line, pos);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setNext(KeyToken next) {
        this.next = next;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public KeyToken next() {
        return next;
    }
}
