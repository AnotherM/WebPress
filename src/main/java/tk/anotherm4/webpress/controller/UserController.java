package tk.anotherm4.webpress.controller;

import tk.anotherm4.webpress.domain.Users;
import tk.anotherm4.webpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //在RequestMapping内使用method = RequestMethod.X时候要注意位置，以避免视图和方法不一致
    //若要使用则可用@GetMapping, @PostMapping
    //Model不可设置跳转，但是可以使用return控制器，ModelAndView可以设置跳转
    //总方法，通过http://localhost:8080/userList访问，后期会添加登陆
    @RequestMapping("/userList")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView("users/index");
        List<Users> usersList = userService.getUserList();
        modelAndView.addObject("users", usersList);
        return modelAndView;
    }

    //跳转到add.html
    @RequestMapping("/addUser")
    public ModelAndView addUser() {
        return new ModelAndView("users/add");
    }

    //回应数据添加，BindingResult暂且对应实体层用来显示错误
    @RequestMapping("/userAdd")
    public ModelAndView userAdd(@Valid Users users, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/userList");
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("users/add");
            List<ObjectError> objectErrorList = bindingResult.getAllErrors();
            for (ObjectError objectError : objectErrorList) {
                modelAndView.addObject("errors", objectError.getDefaultMessage());
            }
            return modelAndView;
        }
        userService.save(users);
        return modelAndView;
    }

    //Model和ModelAndView
    //跳转到edit.html
    @RequestMapping("/editUser")
    public ModelAndView editUser(Long uid) {
        ModelAndView modelAndView = new ModelAndView("users/edit");
        Users users = userService.findByUid(uid);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    //回应数据更新
    //需要传递UID
    @RequestMapping("/userEdit")
    public ModelAndView userEdit(Users users) {
        ModelAndView modelAndView = new ModelAndView("redirect:/userList");
        userService.edit(users);
        return modelAndView;
    }

    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(Long uid) {
        ModelAndView modelAndView = new ModelAndView("redirect:/userList");
        userService.deleteByUid(uid);
        return modelAndView;
    }
}
