package net.xdclass.controller;

import net.xdclass.domain.Video;
import net.xdclass.service.VideoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 视频服务控制层
 */
@RestController
@RequestMapping("api/v1/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @RequestMapping("find_by_id")
    public Object findById(int videoId, HttpServletRequest request) {
        Video video =  videoService.findById(videoId);
        video.setServerInfo(request.getServerName() + ":" + request.getServerPort());
        return video;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Object save(@RequestBody Video video) {
        Map<String, String> map = new HashMap<>();
        map.put("title", video.getTitle());
        return map;
    }
}
