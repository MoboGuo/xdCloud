package net.xdclass.service.fallback;

import net.xdclass.domain.Video;
import net.xdclass.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VideoServiceFallback implements VideoService {
    @Override
    public Video findById(int videoId) {
        Video video = new Video();
        video.setTitle("熔断兜底数据");
        video.setCreateTime(new Date());
        video.setPrice(666);
        return video;
    }

    @Override
    public Object save(Video video) {
        return null;
    }
}
