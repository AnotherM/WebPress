package tk.anotherm4.webpress.controller;

import tk.anotherm4.webpress.domain.Posts;
import tk.anotherm4.webpress.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    private final PostService postService;

    @Autowired
    public AdminController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/postList")
    public ModelAndView postList() {
        ModelAndView modelAndView = new ModelAndView("admin/index");
        List<Posts> postsList = postService.getPostList();
        modelAndView.addObject("posts", postsList);
        return modelAndView;
    }

    @RequestMapping("/addPost")
    public ModelAndView addPost() {
        return new ModelAndView("admin/add");
    }

    @RequestMapping("/postAdd")
    public ModelAndView postAdd(Posts posts) {
        ModelAndView modelAndView = new ModelAndView("redirect:/postList");
        postService.save(posts);
        return modelAndView;
    }

    @RequestMapping("/editPost")
    public ModelAndView editPost(Long pid) {
        ModelAndView modelAndView = new ModelAndView("admin/edit");
        Posts posts = postService.findByPid(pid);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping("/postEdit")
    public ModelAndView postEdit(Posts posts) {
        ModelAndView modelAndView = new ModelAndView("redirect:/postList");
        postService.edit(posts);
        return modelAndView;
    }

    @RequestMapping("/deletePost")
    public ModelAndView deletePost(Long pid) {
        ModelAndView modelAndView = new ModelAndView("redirect:/postList");
        postService.deleteByPid(pid);
        return modelAndView;
    }
}
