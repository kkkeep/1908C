package com.m.k.video;

public interface MkVideoAdapter  {
    // 是否为视频
    boolean isVideo(int position);

    // 是否自动播放
    boolean isAutoPlay(int position);

    String getPlayTag();
}
