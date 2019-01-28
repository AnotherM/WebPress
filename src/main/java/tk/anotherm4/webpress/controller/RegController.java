package tk.anotherm4.webpress.controller;

import tk.anotherm4.webpress.domain.Users;
import tk.anotherm4.webpress.service.LoginService;
import tk.anotherm4.webpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegController {
    private final UserService userService;
    private final LoginService loginService;

    @Autowired
    public RegController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @RequestMapping("/regUser")
    public ModelAndView regUser() {
        return new ModelAndView("reg");
    }

    @RequestMapping("/userReg")
    public ModelAndView userReg(
            @RequestParam(value = "user") String user,
            ModelAndView modelAndView) {
        if (loginService.findByUser(user) != null) {
            modelAndView.addObject("errors", "用户名已存在");
            modelAndView.setViewName("reg");
        } else {
            Users users = loginService.findByUser(user);
            modelAndView.addObject("errors", "注册成功");
            modelAndView = new ModelAndView("redirect:/loginUser");
            userService.save(users);
        }

        return modelAndView;
    }
}
