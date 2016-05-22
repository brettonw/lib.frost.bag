package com.brettonw.bag;

public class JsonBuilder {
    private static final String SQUARE_BRACKETS[] = { "[", "]" };
    private static final String CURLY_BRACKETS[] = { "{", "}" };
    private static final String QUOTES[] = { "\"" };

    JsonBuilder () {}

    static String enclose (String input, String bracket[]) {
        String bracket0 = bracket[0];
        String bracket1 = (bracket.length > 1) ? bracket[1] : bracket0;
        return bracket0 + input + bracket1;
    }

    static String quote (String input) {
        return enclose (input, QUOTES);
    }

    static String getJsonString (Object object) {
        if (object != null) {
            switch (object.getClass ().getName ()) {
                case "java.lang.String": return quote ((String) object);
                case "com.brettonw.bag.BagObject": return toJsonString ((BagObject) object);
                case "com.brettonw.bag.BagArray": return toJsonString ((BagArray) object);

                // we omit the default case, because there should not be any other types stored in
                // the Bag class - as in, they would not make it into the container, as the
                // "objectify" method will gate that
            }
        }
        // if we stored a null, we need to emit it as a value. This will only happen in the
        // array types, and is handled on the parsing side with a special case for reading
        // the bare value 'null' (not quoted)
        return "null";
    }

    public static String toJsonString (BagObject bagObject) {
        StringBuilder stringBuilder = new StringBuilder ();

        String keys[] = bagObject.keys ();
        String separator = "";
        for (int i = 0, end = keys.length; i < end; ++i) {
            stringBuilder
                    .append (separator)
                    .append (quote (keys[i]))
                    .append (":")
                    .append (getJsonString (bagObject.getObject (keys[i])));
            Object object = bagObject.getObject (keys[i]);
            separator = ",";
        }
        return enclose (stringBuilder.toString (), CURLY_BRACKETS);
    }

    public static String toJsonString (BagArray bagArray) {
        StringBuilder stringBuilder = new StringBuilder ();
        String separator = "";
        for (int i = 0, end = bagArray.getCount (); i < end; ++i) {
            stringBuilder
                    .append (separator)
                    .append (getJsonString (bagArray.getObject (i)));
            separator = ",";
        }
        return enclose (stringBuilder.toString (), SQUARE_BRACKETS);
    }
}
