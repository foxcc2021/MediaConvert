package se.vc;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoSize;
import ws.schild.jave.progress.EncoderProgressListener;

/**
 *
 * @author huliqing
 */
public class MediaConverter {
    
    // Encode formats: 
    // 3g2, 3gp, a64, ac3, adts, adx, aiff, alaw, alp, amr, amv, apm, apng, aptx, 
    // aptx_hd, argo_asf, asf, asf_stream, ass, ast, au, avi, avm2, avs2, bit, 
    // caf, cavsvideo, codec2, codec2raw, crc, dash, data, daud, dirac, dnxhd, 
    // dts, dv, dvd, eac3, f32be, f32le, f4v, f64be, f64le, ffmetadata, fifo, 
    // fifo_test, film_cpk, filmstrip, fits, flac, flv, framecrc, framehash, 
    // framemd5, g722, g723_1, g726, g726le, gif, gsm, gxf, h261, h263, h264, 
    // hash, hds, hevc, hls, ico, ilbc, image2, image2pipe, ipod, ircam, ismv, 
    // ivf, jacosub, kvag, latm, lrc, m4v, matroska, md5, microdvd, mjpeg, 
    // mkvtimestamp_v2, mlp, mmf, mov, mp2, mp3, mp4, mpeg, mpeg1video, mpeg2video, 
    // mpegts, mpjpeg, mulaw, mxf, mxf_d10, mxf_opatom, null, nut, oga, ogg, 
    // ogv, oma, opus, psp, rawvideo, rm, roq, rso, rtp, rtp_mpegts, rtsp, s16be, 
    // s16le, s24be, s24le, s32be, s32le, s8, sap, sbc, scc, sdl, sdl2, segment, 
    // singlejpeg, smjpeg, smoothstreaming, sox, spdif, spx, srt, stream_segment, 
    // ssegment, streamhash, sup, svcd, swf, tee, truehd, tta, ttml, u16be, 
    // u16le, u24be, u24le, u32be, u32le, u8, uncodedframecrc, vc1, vc1test, vcd, 
    // vidc, vob, voc, w64, wav, webm, webm_chunk, webm_dash_manifest, webp, 
    // webvtt, wtv, wv, yuv4mpegpipe

    // Decode formats: 
    // 3dostr, 4xm, aa, aac, aax, ac3, ace, acm, act, adf, adp, ads, adx, aea, 
    // afc, aiff, aix, alaw, alias_pix, alp, amr, amrnb, amrwb, anm, apc, ape, 
    // apm, apng, aptx, aptx_hd, aqtitle, argo_asf, argo_brp, asf, asf_o, ass, 
    // ast, au, av1, avi, avisynth, avr, avs, avs2, avs3, bethsoftvid, bfi, 
    // bfstm, bin, bink, binka, bit, bmp_pipe, bmv, boa, brender_pix, brstm, 
    // c93, caf, cavsvideo, cdg, cdxl, cine, codec2, codec2raw, concat, cri_pipe, 
    // dash, data, daud, dcstr, dds_pipe, derf, dfa, dhav, dirac, dnxhd, dpx_pipe, 
    // dsf, dshow, dsicin, dss, dts, dtshd, dv, dvbsub, dvbtxt, dxa, ea, ea_cdata, 
    // eac3, epaf, exr_pipe, f32be, f32le, f64be, f64le, ffmetadata, film_cpk, 
    // filmstrip, fits, flac, flic, flv, frm, fsb, fwse, g722, g723_1, g726, 
    // g726le, g729, gdigrab, gdv, genh, gif, gif_pipe, gsm, gxf, h261, h263, 
    // h264, hca, hcom, hevc, hls, hnm, ico, idcin, idf, iff, ifv, ilbc, image2, 
    // image2pipe, ingenient, ipmovie, ipu, ircam, iss, iv8, ivf, ivr, j2k_pipe, 
    // jacosub, jpeg_pipe, jpegls_pipe, jv, kux, kvag, lavfi, libgme, libopenmpt, 
    // live_flv, lmlm4, loas, lrc, luodat, lvf, lxf, m4v, matroska, webm, mca, mcc, 
    // mgsts, microdvd, mjpeg, mjpeg_2000, mlp, mlv, mm, mmf, mods, moflex, mov, 
    // mp4, m4a, 3gp, 3g2, mj2, mp3, mpc, mpc8, mpeg, mpegts, mpegtsraw, mpegvideo, 
    // mpjpeg, mpl2, mpsub, msf, msnwctcp, msp, mtaf, mtv, mulaw, musx, mv, mvi, 
    // mxf, mxg, nc, nistsphere, nsp, nsv, nut, nuv, obu, ogg, oma, paf, pam_pipe, 
    // pbm_pipe, pcx_pipe, pgm_pipe, pgmyuv_pipe, pgx_pipe, photocd_pipe, pictor_pipe, 
    // pjs, pmp, png_pipe, pp_bnk, ppm_pipe, psd_pipe, psxstr, pva, pvf, qcp, 
    // qdraw_pipe, r3d, rawvideo, realtext, redspark, rl2, rm, roq, rpl, rsd, 
    // rso, rtp, rtsp, s16be, s16le, s24be, s24le, s32be, s32le, s337m, s8, 
    // sami, sap, sbc, sbg, scc, sdp, sdr2, sds, sdx, ser, sga, sgi_pipe, shn, 
    // siff, simbiosis_imx, sln, smjpeg, smk, smush, sol, sox, spdif, srt, stl, 
    // subviewer, subviewer1, sunrast_pipe, sup, svag, svg_pipe, svs, swf, tak, 
    // tedcaptions, thp, tiertexseq, tiff_pipe, tmv, truehd, tta, tty, txd, ty, 
    // u16be, u16le, u24be, u24le, u32be, u32le, u8, v210, v210x, vag, vc1, vc1test, 
    // vfwcap, vidc, vividas, vivo, vmd, vobsub, voc, vpk, vplayer, vqf, w64, wav, 
    // wc3movie, webm_dash_manifest, webp_pipe, webvtt, wsaud, wsd, wsvqa, wtv, 
    // wv, wve, xa, xbin, xbm_pipe, xmv, xpm_pipe, xvag, xwd_pipe, xwma, yop, yuv4mpegpipe
    
    // Video bitrate https://riverside.fm/blog/what-is-bitrate
    //  360P: 1-2 Mbps
    //  480P: 2-5 Mbps
    //  720P: 5-10 Mbps
    // 1080P: 10-15 Mbps
    
    // e.g. 640*480(SD),1280*720(HD),1920*1080(FHD),2560*1440(UHD, 2K),3840*2160(4K),7680×4320(8K)
    private VideoSize size;
    // e.g. 90
    private Integer quality;
    // e.g. 30
    private Integer frameRate;
    private Integer bitRate;
    
    private static final Logger LOG = Logger.getLogger(MediaConverter.class.getName());

    public void setSize(VideoSize size) {
        this.size = size;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public void setFrameRate(Integer frameRate) {
        this.frameRate = frameRate;
    }

    public void setBitRate(Integer bitRate) {
        this.bitRate = bitRate;
    }
    
    /**
     * Convert media file.
     * @param source e.g. "C:\\MyVideo.avi"
     * @param format e.g. "mp4", "webm", "webp"
     * @param listener Nullable
     * @return target file
     */
    public File convert(File source, String format, EncoderProgressListener listener) {
        String targetPath = source.getAbsolutePath();
        String ext = MyFileUtils.getFileExtension(source);
        if (ext == null) {
            targetPath = targetPath + "." + format;
        } else {
            targetPath = targetPath.substring(0, targetPath.lastIndexOf(".") + 1) + format;
        }
        File target = new File(targetPath);
        convert(source, target, format, listener);
        return target;
    }
    
    /**
     * Convert media file.
     * @param source e.g. "C:\\MyVideo.avi"
     * @param target e.g. "C:\\MyVideo.mp4"
     */
    public void convert(File source, File target, EncoderProgressListener listener) {
        String format = MyFileUtils.getFileExtension(target);
        convert(source, target, format, listener);
    }
    
    private void convert(File source, File target, String format, EncoderProgressListener listener) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "Convert media, source={0}, target={1}, format={2}", new Object[] {source, target, format});
            LOG.log(Level.INFO, "Convert media, size={0}, quality={1}, frameRate={2}, bitRate={3}"
                    , new Object[] {size, quality, frameRate, bitRate});
        }
        
        AudioAttributes audio = new AudioAttributes();
        
        VideoAttributes video = new VideoAttributes();
        if (size != null) video.setSize(size);
        if (quality != null) video.setQuality(quality);
        if (frameRate != null) video.setFrameRate(frameRate);
        if (bitRate != null) video.setBitRate(bitRate);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat(format);
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        try {
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs, listener);
            LOG.log(Level.INFO, "Media file converted successfully");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not convert media! source=" + source + ", target=" + target, e);
        }
    }
}
