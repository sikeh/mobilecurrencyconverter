package com.biiblesoft.currency.core;

import javax.microedition.io.HttpConnection;
import javax.microedition.io.Connector;
import java.io.IOException;
import java.io.InputStream;

/**
 * Html parser to be exetued as a Thread, which reads bus destionation and minute left.
 *
 * @author Sike Huang
 * @author Shanbo Li
 */
public class HtmlParser {

    private final static String startLine = "&nbsp;1&nbsp;SEK&nbsp;=<span class=bld>&nbsp;";
    private HttpConnection httpConnection;
    private InputStream inputStream;
    private String DEFAULT_URL = "http://www.google.com/finance?q=SEKCNY";

    public HtmlParser() {
    }

    public String getRate() throws IOException {
        return getRate(DEFAULT_URL);
    }

    public String getRate(String url) throws IOException {
        String result = "unknow~";
        try {
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

                    if (line.trim().startsWith(startLine)) {
                        // extract minute
                        int start = line.indexOf(">");
                        char c = '<';
                        int end = line.lastIndexOf(c);
                        String s = line.substring(start + 1, end);
                        // minute could be 2&nbsp; or &nbsp;&nbsp;
                        // therefore get rid of &nbsp;
                        s = s.replace('&', ' ').replace('n', ' ').replace('b', ' ').replace('s', ' ').replace('p', ' ').replace(';', ' ').
                                replace('C', ' ').replace('N', ' ').replace('Y', ' ').trim();

                        result = s;
                        break;

                        // set data moble (the bus)
                    }
                    buffer = new StringBuffer();
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (httpConnection != null) {
                httpConnection.close();
            }
        }
        return result;
    }

    /**
     * Cancel threading, abort parsing.
     */
    public void cancel() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
        if (httpConnection != null) {
            httpConnection.close();
        }
    }
}
