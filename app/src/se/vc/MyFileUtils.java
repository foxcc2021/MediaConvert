package se.vc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author huliqing
 */
public class MyFileUtils {

    /**
     * 保存文件
     * @param saveString 保存的字符串信息
     * @param saveFile (not null)保存的文件,必须指定路径
     * @throws IOException
     */
    public static void saveFile(String saveString, File saveFile) throws IOException {
        byte[] data = saveString != null ? saveString.getBytes() : "".getBytes();
        saveFile(data, saveFile);
    }
    
    /**
     * 保存字节文件
     * @param saveData
     * @param saveFile
     * @throws IOException 
     */
    public static void saveFile(byte[] saveData, File saveFile) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(saveFile);
            fos.write(saveData != null ? saveData : "".getBytes());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Logger.getLogger(MyFileUtils.class.getName())
                            .log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    /**
     * 保存文件
     * @param saveString 保存的字符串信息
     * @param savePath 保存的绝对路径
     * @return 
     * @throws java.io.IOException 
     */
    public static boolean saveFile(String saveString, String savePath) throws IOException {
        saveFile(saveString, new File(savePath));
        return true;
    }
    
    /**
     * 保存输入流到指定的输出流，保存后两个数据流都会被自动关闭,不需要再手动
     * 关闭数据流
     * @param in
     * @param out
     * @throws IOException 
     */
    public final static void saveFile(InputStream in, OutputStream out) throws IOException {
        byte[] buff = new byte[4096];
        int len;
        BufferedOutputStream bos = new BufferedOutputStream(out);
        BufferedInputStream bis = new BufferedInputStream(in);
        try {
            bos = new BufferedOutputStream(out);
            while ((len = bis.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } finally {
            try {
                bis.close();
            } catch (Exception e) {}
            try {
                bos.close();
            } catch (Exception e) {}
        }
    }
    
    /**
     * 从指定的File中读取信息
     * @param file
     * @param charset 指定的编码，默认"utf-8"
     * @return
     * @throws IOException 
     */
    public final static String readFile(File file, String charset) throws IOException {
        byte[] data = readFile(file);
        String result = new String(data, charset);
        return result;
    }
    
    /**
     * 读取文件
     * @param filePath 文件路径
     * @param charset 要使用的文件编码，如果没有指定则使用默认的utf-8
     * @return 
     * @throws java.io.IOException 
     */
    public final static String readFile(String filePath, String charset) throws IOException {
        return readFile(new File(filePath), charset);
    }
    
    /**
     * 读取文件流为指定编码的字符串，读取后流会自动被关闭。
     * @param is
     * @param charset
     * @return
     * @throws IOException 
     */
    public final static String readFile(InputStream is, String charset) throws IOException {
        byte[] data = readFile(is);
        return new String(data, 0, data.length, charset);
    }
    
    /**
     * 读取文件
     * @param file
     * @return
     * @throws IOException 
     */
    public static byte[] readFile(File file) throws IOException {
        return readFile(new FileInputStream(file));
    }
    
    /**
     * 读取文件流，读取完毕之后，流会自动关闭!
     * @param is
     * @return
     * @throws IOException 
     */
    public final static byte[] readFile(InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        try {
            byte[] buff = new byte[1024];
            int len;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = bis.read(buff, 0, buff.length)) != -1) {
                baos.write(buff, 0, len);
            }
            return baos.toByteArray();
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                Logger.getLogger(MyFileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * 读取文件，文件路径如: <br >
     * "/data/object/config.xml" <br>
     * "/data/ly16.png" <br>
     * ...
     * @param path 文件路径，必须是类路径下的文件
     * @return 
     */
    public final static InputStream readFile(String path) {
        InputStream is = MyFileUtils.class.getResourceAsStream(path);
        return is;
    }
    
    public final static void close(Closeable c) {
        if (c == null)
            return;
        
        try {
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取一个不重复的文件名，如果给定的defName在文件夹dir中不存在重名文件则直接返回，否则会给文件名加数字结尾，确
     * 保文件夹不重复后再返回。
     * @param dir
     * @param defName, 文件名，如：“readme.txt”
     * @return 返回不重复的文件名，如："readme1.txt"
     */
    public final static String makeUniqueFilename(File dir, String defName) {
        File file = new File(dir, defName);
        if (!file.exists())
            return defName;
        
        int idx = defName.lastIndexOf(".");
        if (idx != -1) {
            String baseName = defName.substring(0, idx);
            String suffix = defName.substring(idx + 1);
            return makeUniqueFilename(dir, baseName, suffix, 1);
        } else {
            return makeUniqueFilename(dir, defName, null, 1);
        }
    }
    
    private static String makeUniqueFilename(File dir, String baseName, String suffix, int idx) {
        String filename;
        if (suffix != null) {
            filename = baseName + "-" + idx + "." + suffix;
        } else {
            filename = baseName + "-" + idx;
        }
        File file = new File(dir, filename);
        if (!file.exists()) {
            return filename;
        } else {
            return makeUniqueFilename(dir, baseName, suffix, ++idx);
        }
    }
    
    private static void closeDirect(Closeable obj) {
       try {
           if (obj != null) 
            obj.close();
       } catch (Exception e){}
    }
    
    /**
     * Check and make dirs.
     * @param dir 
     */
    public static void checkAndMakeDirs(File dir) {
        if (dir == null)
            return;
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
    }
       
    /**
     * Check if the given file type if one of the fileTypes.
     * @param fileType
     * @param fileTypes
     * @return 
     */
    public static boolean isFileType(String fileType, String... fileTypes) {
        if (StringUtils.isEmpty(fileType))
            return false;
        for (String ft : fileTypes) {
            if (StringUtils.equalsIgnoreCase(ft, fileType)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check if the "child" file is real the child of the "parent" file.
     * @param child
     * @param parent
     * @return 
     */
    public static boolean isChildOf(File child, File parent) {
        if (child == null || parent == null)
            return false;
        
        File p = child.getParentFile();
        while (p != null) {
            if (ObjectUtils.equals(p, parent)) {
                return true;
            }
            p = p.getParentFile();
        }
        return false;
    }
    
    /**
     * Get file extension.
     * @param fileName
     * @return 
     */
    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return null;
        }
    }
 
    public static String getFileExtension(File file) {
        return getFileExtension(file.getName());
    }
}
