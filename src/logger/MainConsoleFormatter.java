/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 *
 * @author bickhart
 */
public class MainConsoleFormatter extends Formatter{
    private final String NL = System.lineSeparator();
    
    @Override
    public String format(LogRecord lr) {
        
        if(lr.getThrown() == null)
            return String.format("| %30s | %250s | %s |%s", lr.getLoggerName(), lr.getMessage(), new Date(lr.getMillis()), NL);
        else{
            StringBuilder sb = new StringBuilder();
        
            sb.append(new Date(lr.getMillis()))
                .append(" ")
                .append(lr.getLevel().getLocalizedName())
                .append(": ")
                .append(formatMessage(lr))
                .append(NL);
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                lr.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch (Exception ex) {
                // ignore
            }
            return sb.toString();
        }
    }
    
}
