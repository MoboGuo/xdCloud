package net.xdclass.controller;


import net.xdclass.domain.Video;
import net.xdclass.domain.VideoOrder;
import net.xdclass.service.OrderService;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/find_by_id")
    public Object findById(int videoId) {

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

    @RequestMapping("/save")
    public Object save(@RequestBody Video video) {
        return videoService.save(video);
    }

    int temp = 0;
    @RequestMapping("/list")
    public Object list(HttpServletRequest request){
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        temp++;
//        if(temp%3 == 0){
//            throw  new RuntimeException();
//        }
        Map<String,String> map  = new HashMap<>();
        map.put("title1","ALibabaCloud微服务专题");
        map.put("title2","小滴课堂面试专题第一季");
        map.put("ip", request.getServerPort() + "");
        return map;
    }
}
