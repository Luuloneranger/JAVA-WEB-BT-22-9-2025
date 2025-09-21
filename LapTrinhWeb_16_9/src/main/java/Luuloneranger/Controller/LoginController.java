package Luuloneranger.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Luuloneranger.Entity.Users;
import Luuloneranger.Repository.UsersRepository;
import Luuloneranger.Service.UsersService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsersRepository US;

    @GetMapping("/login")
    public String loginForm(ModelMap model) {
        model.addAttribute("user", new Users());
        return "login"; // file login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        ModelMap model) {
        Optional<Users> opt = US.findByUsernameAndPassword(username, password);
        if (opt.isPresent()) {
            session.setAttribute("user", opt.get());
            return "redirect:/"; // login xong về trang chủ
        } else {
            model.addAttribute("error", "Sai username hoặc password!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String register() {
        return "register"; // file templates/register.html
    }
    @PostMapping("/register")
    public String register(Users user, ModelMap model) {
        try {
            user.setRole("USER");
            user.setEnabled(true);

            US.save(user);

            model.addAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login"; 
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
            return "register";
        }
    }
}
