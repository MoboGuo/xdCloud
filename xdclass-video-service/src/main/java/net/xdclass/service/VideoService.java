package net.xdclass.service;

import net.xdclass.domain.Video;

/**
 * 视频业务服务层
 */

public interface VideoService {

    Video findById(int videoId);
}
