package se.vc;

import java.io.File;
import ws.schild.jave.progress.EncoderProgressListener;

/**
 * https://github.com/a-schild/jave2
 * @author huliqing
 */
public class MediaUtils {

    /**
     * Convert media file.
     * @param source e.g. "C:\\MyVideo.avi"
     * @param format e.g. "mp4", "webm", "webp"
     * @param listener Nullable
     */
    public static void convert(File source, String format, EncoderProgressListener listener) {
        MediaConverter converter = new MediaConverter();
        converter.convert(source, format, listener);
    }
    
    /**
     * Convert media file.
     * @param source e.g. "C:\\MyVideo.avi"
     * @param target e.g. "C:\\MyVideo.mp4"
     * @param listener Nullable
     */
    public static void convert(File source, File target, EncoderProgressListener listener) {
        MediaConverter converter = new MediaConverter();
        converter.convert(source, target, listener);
    }
}
