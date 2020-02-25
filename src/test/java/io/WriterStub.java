package io;

import com.tddapps.io.Writer;

public class WriterStub implements Writer {
    private final StringBuilder output = new StringBuilder();

    @Override
    public void display(String str) {
        output.append(str);
        output.append("\n");
    }

    public void reset(){
        output.delete(0, output.length());
    }

    public boolean contains(String str){
        return output.toString().toLowerCase().contains(str.toLowerCase());
    }

    public boolean contains(String str, boolean caseSensitive){
        if (!caseSensitive){
            return contains(str);
        }

        return output.toString().contains(str);
    }
}
