package com.biiblesoft.currency.core;

import javax.microedition.io.HttpConnection;
import javax.microedition.io.Connector;
import java.io.IOException;
import java.io.InputStream;

/**
 * Html parser to be exetued as a Thread, which reads bus destionation and minute left.
 *
 * @author Shanbo Li
 * @author Sike Huang
 */
public class ConvertHelper {

    private HttpConnection httpConnection;
    private InputStream inputStream;
    private String DEFAULT_SKELETON = "http://www.google.com/finance/converter?a=";

    public String convert(String number, String from, String to) throws IOException {
        String result = "unknow~";
        if (from.equals(to))return number;

        String endLine = to + "</span>";
        try {
            String url = DEFAULT_SKELETON + number + "&from=" + from + "&to=" + to;
            httpConnection = (HttpConnection) Connector.open(url);
            inputStream = httpConnection.openInputStream();
            StringBuffer buffer = new StringBuffer();
            int ch;
            while ((ch = inputStream.read()) != -1) {
                if (ch != '\n') {
                    // accumulate characters in one line
                    buffer.append((char) ch);
                } else {
                    // read line by line
                    String line = new String(buffer.toString());

                    if (line.trim().endsWith(endLine)) {


                        // extract result
                    
                        String bldStart = "<span class=bld>";
                        String bldEnd = "&nbsp;" + to + "</span>";
                        String resultNumber = line.substring((line.indexOf(bldStart) + bldStart.length()), (line.indexOf(bldEnd)));

                        result = number + " " + from + " = " + resultNumber + " " + to;
                        break;

                    // set data moble (the bus)
                    }
                    buffer = new StringBuffer();
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
            if (httpConnection != null) {
                httpConnection.close();
                httpConnection = null;
            }
            System.gc();
        }
        return result;
    }

    /**
     * Cancel threading, abort parsing.
     */
    public void cancel() throws IOException {
        if (inputStream != null) {
            inputStream.close();
            inputStream = null;
        }
        if (httpConnection != null) {
            httpConnection.close();
            httpConnection = null;
        }
        System.gc();
    }
}
