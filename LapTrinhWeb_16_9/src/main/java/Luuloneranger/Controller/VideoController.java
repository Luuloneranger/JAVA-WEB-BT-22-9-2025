package Luuloneranger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Luuloneranger.Entity.Video;
import Luuloneranger.Repository.VideoRepository;

@Controller
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    // User xem list
    @GetMapping("/user/videos")
    public String listVideos(ModelMap model) {
        model.addAttribute("videos", videoRepository.findAll());
        return "user/videos/list"; // => tạo file list.html trong templates/user/videos
    }

    // Admin CRUD
    @GetMapping("/admin/videos")
    public String listVideosAdmin(ModelMap model) {
        model.addAttribute("videos", videoRepository.findAll());
        return "admin/videos/list";
    }

    @GetMapping("/admin/videos/add")
    public String addVideo(ModelMap model) {
        model.addAttribute("video", new Video());
        return "admin/videos/addOrEdit";
    }

    @PostMapping("/admin/videos/saveOrUpdate")
    public String saveOrUpdate(Video video) {
        videoRepository.save(video);
        return "redirect:/admin/videos";
    }

    @GetMapping("/admin/videos/edit/{id}")
    public String editVideo(@PathVariable("id") Integer id, ModelMap model) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid video Id:" + id));
        model.addAttribute("video", video);
        return "admin/videos/addOrEdit";
    }

    @GetMapping("/admin/videos/delete/{id}")
    public String deleteVideo(@PathVariable("id") Integer id) {
        videoRepository.deleteById(id);
        return "redirect:/admin/videos";
    }
}
