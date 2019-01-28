package tk.anotherm4.webpress.controller;

import tk.anotherm4.webpress.domain.Users;
import tk.anotherm4.webpress.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/loginUser")
    public ModelAndView loginUser() {
        return new ModelAndView("login");
    }

    //使用@RequestParam定义从form中取的值，value用于传参
    @RequestMapping("/userLogin")
    public ModelAndView login(
            @RequestParam(value = "user") String user,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "access", required = false) String access,
            HttpSession httpSession,
            Users users) {
        ModelAndView modelAndView = new ModelAndView();

        //输出信息到Console
//        if (users.getUser() != null && users.getPassword() != null) {
//            modelAndView.setViewName("redirect:/");
//            return modelAndView;
//        }
//        if (!Objects.equals(users.getAccess(), "") || !Objects.equals(access, "")) {
//            if (!Objects.equals(users.getAccess(), "") && !Objects.equals(users.getAccess(), access)) {
//                modelAndView.addObject("errors", "请确认你拥有正确的权限");
//                modelAndView.setViewName("login");
//                return modelAndView;
//            } else if (Objects.equals(users.getAccess(), access)) {
//                modelAndView.setViewName("redirect:/");
//                return modelAndView;
//            }
//        }

        if (user != null && password != null) {
            if (loginService.findByUser(user) != null) {
                users = loginService.findByUser(user);
            }
            if (loginService.findByUser(user) == null) {
                modelAndView.addObject("errors", "用户不存在");
                modelAndView.setViewName("login");
            } else if (Objects.equals(users.getUser(), user) && !Objects.equals(users.getPassword(), password)) {
                modelAndView.addObject("errors", "用户名或密码验证失败");
                modelAndView.setViewName("login");
            } else if (Objects.equals(users.getUser(), user) && Objects.equals(users.getPassword(), password)) {
                httpSession.setAttribute("user", user);
                if (users.getAccess() != null) {
                    httpSession.setAttribute("access", access);
                    System.out.println(users.getUser() + users.getPassword() + users.getAccess());
                }
                System.out.println("验证成功");
                if (Objects.equals(users.getAccess(), access)) {
                    System.out.println("登陆成功");
                    modelAndView.setViewName("redirect:/");
                } else {
                    System.out.println("权限错误");
                    modelAndView.addObject("errors", "权限验证失败");
                    modelAndView.setViewName("login");
                }
            }
        }
        return modelAndView;
    }

    //重定向到IndexController的@RequestMapping("/")，若要直接return "index"则页面直接返回空视图而不是有数据的视图
    @RequestMapping("/logoutUser")
    public ModelAndView logoutUser(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("access");
        return new ModelAndView("redirect:/");
    }
}

