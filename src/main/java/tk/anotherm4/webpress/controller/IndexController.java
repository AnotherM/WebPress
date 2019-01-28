package tk.anotherm4.webpress.controller;

import tk.anotherm4.webpress.domain.Posts;
import tk.anotherm4.webpress.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    private final PostService postService;

    @Autowired
    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Posts> postsList = postService.getPostList();
        modelAndView.addObject("posts", postsList);
        return modelAndView;
    }
}
