package com.text2net.console;

/**
 * Created by rafa on 03/04/2016.
 */
public class Publication {
    private String text;
    private String sourceFile;

    public float getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(float endOffset) {
        this.endOffset = endOffset;
    }

    private float iniOffset;
    private float endOffset;

    public float getIniOffset() {
        return iniOffset;
    }

    public void setIniOffset(float iniOffset) {
        this.iniOffset = iniOffset;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }


}
