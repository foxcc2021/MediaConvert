/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.vc.log;

import java.util.logging.LogRecord;

/**
 * 日志记录侦听器
 * @author huliqing
 */
public interface LogListener {
    
    /**
     * 当发生日志记录时该方法被调用。
     * @param record 日志记录
     * @param text 日志记录内容
     */
    void onLog(LogRecord record, String text);
}
