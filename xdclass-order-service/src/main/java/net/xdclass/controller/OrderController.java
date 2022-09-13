package net.xdclass.controller;


import net.xdclass.domain.Video;
import net.xdclass.domain.VideoOrder;
import net.xdclass.service.OrderService;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/video_order")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private VideoService videoService;
    //使用autowired注入，推荐使用构造函数注入的方式
//    @Autowired
//    public OrderController(RestTemplate restTemplate, DiscoveryClient discoveryClient, VideoService videoService){
//        this.restTemplate = restTemplate;
//        this.discoveryClient = discoveryClient;
//        this.videoService = videoService;
//    }

    @RequestMapping("/save")
    public Object save(int videoId) {

//        List<ServiceInstance> list =  discoveryClient.getInstances("xdclass-video-service");

//        ServiceInstance serviceInstance = list.get(0);

//        Video video = restTemplate.getForObject("http://xdclass-video-service/api/v1/video/find_by_id?videoId=" + videoId, Video.class);
        Video video = videoService.findById(videoId);
        VideoOrder videoOrder = new VideoOrder();
        if (video != null){
            videoOrder.setVideoId(video.getId());
            videoOrder.setVideoTitle(video.getTitle());
            videoOrder.setCreateTime(new Date());
            videoOrder.setServerInfo(video.getServerInfo());
        }

        return videoOrder;
    }
}
