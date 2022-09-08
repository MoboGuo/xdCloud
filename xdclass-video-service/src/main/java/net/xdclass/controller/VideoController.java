package net.xdclass.controller;

import net.xdclass.service.VideoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 视频服务控制层
 */
@RestController
@RequestMapping("api/v1/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @RequestMapping("find_by_id")
    public Object findById(int videoId) {
        return  videoService.findById(videoId);
    }
}
