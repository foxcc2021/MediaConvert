/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.vc.log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

/**
 * 日志记录器，这个类会在运行时获取日志记录，然后把日志记录转发到所有已经添加的侦听器
 * 中，自身并不输出日志记录，所以必须向这个类中添加日志侦听器之后才有用。
 * @author huliqing
 */
public class OutputLogHandler extends StreamHandler {
    
    private final static List<LogListener> LISTENERS = new ArrayList();
    
    /**
     * 添加日志记录侦听
     * @param listener 
     */
    public final static synchronized void addLogListener(LogListener listener) {
        if (LISTENERS.contains(listener))
            return;
        LISTENERS.add(listener);
    }
    
    /**
     * 移除日志记录侦听
     * @param listener 
     */
    public final static synchronized void removeLogListener(LogListener listener) {
        LISTENERS.remove(listener);
    }
    
    public OutputLogHandler() {
        try {
            setEncoding("UTF-8");
        } catch (SecurityException ex) {
            Logger.getLogger(OutputLogHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ue) {
            Logger.getLogger(OutputLogHandler.class.getName()).log(Level.SEVERE, null, ue);
        }
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {}
        });
    }
    
    @Override
    public synchronized void publish(LogRecord record) {
        if (!isLoggable(record)) {
            return;
        }
        if (LISTENERS.isEmpty()) 
            return;
        
        String msg = getFormatter().format(record);
        for (LogListener ll : LISTENERS) {
            try {
                ll.onLog(record, msg);
            } catch (Exception e) {
                // ignore
            }
        }
    }
    
}
