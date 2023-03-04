package se.vc;

/**
 *
 * @author huliqing
 */
public class Config {
    
    // e.g. "C:\\MyVideo.avi"
    private String file;
    
    // e.g. "mp4", "webm", "webp"
    private String format = "mp4";
    
    // e.g. 30
    private Integer frameRate;
    
    private Integer bitRate;
    
    private Integer quality;
    
    private Integer width;
    private Integer height;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Integer frameRate) {
        this.frameRate = frameRate;
    }

    public Integer getBitRate() {
        return bitRate;
    }

    public void setBitRate(Integer bitRate) {
        this.bitRate = bitRate;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ConvertConfig{" + "file=" + file + ", format=" + format + ", frameRate=" + frameRate + ", bitRate=" + bitRate + ", quality=" + quality + ", width=" + width + ", height=" + height + '}';
    }
    


}
