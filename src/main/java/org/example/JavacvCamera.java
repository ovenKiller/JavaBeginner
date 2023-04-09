package org.example;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.FrameGrabber.Exception;

public class JavacvCamera implements Camera {
    OpenCVFrameGrabber grabber = null;
    int fps = 30;
    public JavacvCamera(){
        this(0);
    }
    public JavacvCamera(int diviceNumber){
        this.grabber = new OpenCVFrameGrabber(diviceNumber);
    }

    @Override
    public int shotVideo(int time, String savePath) {
        try {
            grabber.start();
            // 3.定义视频编码器
            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(savePath, grabber.getImageWidth(), grabber.getImageHeight(), 0);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("mp4");
            recorder.setFrameRate(fps);
            recorder.start();
            // 4.录制视频
//            long startTime = System.currentTimeMillis();
//            while (System.currentTimeMillis() - startTime < 10000) {
//                Frame frame = grabber.grab();
//                recorder.record(frame);
//            }
            int i = 0;
            while(i < time * fps){
                Frame frame = grabber.grab();
                recorder.record(frame);
                i++;
            }
            recorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
