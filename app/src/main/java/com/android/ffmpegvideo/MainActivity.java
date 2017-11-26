package com.android.ffmpegvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.ffmpegvideo.utils.FileUtils;
import com.android.ffmpegvideo.video.FFmpegCommands;
import com.android.ffmpegvideo.video.FFmpegRun;

public class MainActivity extends AppCompatActivity {
    public final static String TAG="MainActivity";

    private String mTargetPath;
    private FileUtils fileUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileUtils=new FileUtils(this);
        mTargetPath= fileUtils.getStorageDirectory();
        extractVideo();
        extractAudio();
    }

    /**
     * 提取视频
     */
    private void extractVideo() {
        final String outVideo = mTargetPath + "/video.mp4";
        String[] commands = FFmpegCommands.extractVideo(fileUtils.getStorageDirectory()+"/videox.mp4", outVideo);
        FFmpegRun.execute(commands, new FFmpegRun.FFmpegRunListener() {
            @Override
            public void onStart() {

                Log.e(TAG,"extractVideo ffmpeg start...");
            }

            @Override
            public void onEnd(int result) {
                Log.e(TAG,"extractVideo ffmpeg end...");

                extractAudio();
            }
        });
    }

    /**
     * 提取音频
     */
    private void extractAudio() {
        final String outVideo = mTargetPath + "/audio.aac";
        String[] commands = FFmpegCommands.extractAudio(fileUtils.getStorageDirectory()+"/videox.mp4", outVideo);
        FFmpegRun.execute(commands, new FFmpegRun.FFmpegRunListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onEnd(int result) {
                Log.e(TAG,"extractAudio ffmpeg end...");
            }
        });
    }

}
