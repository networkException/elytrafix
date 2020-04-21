package de.nwex.elytrafix.util;

public class TextUtil
{
    public static String stackTraceToString(Throwable e)
    {
        StringBuilder stringBuilder = new StringBuilder();

        boolean first = true;

        do
        {
            if(!first) e = e.getCause();

            for(StackTraceElement element : e.getStackTrace())
            {
                stringBuilder.append(element.toString());
                stringBuilder.append("\n");
            }

            first = false;
        }
        while(e.getCause() != null);

        return stringBuilder.toString();
    }
}
