
package se.vc.log;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import se.vc.MyFileUtils;

/**
 * 日志记录工厂类
 * @author huliqing
 */
public class SELogFactory {
    
    /**
     * Init log
     * @param logLevel
     * @param logginProperties e.g. "res/logging.properties", DO NOT START WITH "/"
     */
    public final static void initLog(Level logLevel, String logginProperties) {
        initialize(logginProperties);
        resetLogger(logLevel, true);
    }

    /**
     * Init log.
     * @param loggingProperties e.g. "res/logging.properties"
     */
    private final static void initialize(String loggingProperties) {
        InputStream is = null;
        try {
            is = SELogFactory.class.getResourceAsStream("/" + loggingProperties);
            LogManager.getLogManager().readConfiguration(is);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MyFileUtils.close(is);
        }
    }
    
    /**
     * 重新设置 Log
     * @param level 在开发状态时设置{@link Level#INFO}, 发布状态一般设置为{@link Level#WARNING}
     * @param useParentHandlers 在开发状态时应该设置为true，这允许在控制台输出log信息
     * 在发布状态时可设置为false
     */
    public final static void resetLogger(Level level, boolean useParentHandlers) {
        LogManager lm = LogManager.getLogManager();
        Enumeration<String> names = lm.getLoggerNames();
        if (names != null) {
            while (names.hasMoreElements()) {
                Logger logger = lm.getLogger(names.nextElement());
                if (logger != null) {
                    logger.setLevel(level);
                    logger.setUseParentHandlers(useParentHandlers);
                }
            }
        }
    }
    
}
