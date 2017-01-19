package com.brettonw.bag.entry;

/**
 * supporting a parsed format that uses an entry delimiter, with entries being quoted
 */
public class HandlerArrayFromParsed extends HandlerArray {
    private char delimiter;

    public HandlerArrayFromParsed (char delimiter, Handler handler) {
        super (handler);
        this.delimiter = delimiter;
    }

    @Override
    public Object getEntry (String input) {
        return null;
    }
}
