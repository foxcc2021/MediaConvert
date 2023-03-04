package se.vc.log;

import java.io.File;

/**
 * LogHandle for Window/PC
 * @author huliqing
 */
public final class SimpleLogHandlerWrap extends SimpleLogHandler {

    private final long maxSize = 1024 * 1024 * 5; // 5M
    private final String filename = "log";

    public SimpleLogHandlerWrap() {
        super();
        File logDir = new File("log");
        initConfig(logDir, filename, maxSize);
    }

}
