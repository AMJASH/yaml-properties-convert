package website.yuanhui.entity;

public class LineNumberString {
    private LineNumberString previous;
    private LineNumberString next;
    private int lineNumber;
    private String source;

    public static LineNumberString of(int i, String string) {
        LineNumberString target = new LineNumberString();
        target.setLineNumber(i);
        target.setSource(string);
        return target;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LineNumberString getPrevious() {
        return previous;
    }

    public void setPrevious(LineNumberString previous) {
        this.previous = previous;
    }


    public LineNumberString getNext() {
        return next;
    }

    public void setNext(LineNumberString next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    /**
     * #xxxxx
     * ----#xxxx
     * --#xxxx
     * --
     * '\n'
     *
     * @return
     */
    public boolean isStream() {
        char[] chars = source.toCharArray();
        //空白行
        if (chars.length == 0) {
            return true;
        }
        for (char c : chars) {
            if (Character.isSpaceChar(c)) {
                continue;
            }
            return c == '#';
        }
        return true;
    }
}
