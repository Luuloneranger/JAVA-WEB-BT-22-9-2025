package Luuloneranger.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Luuloneranger.Entity.Users;
import Luuloneranger.Repository.UsersRepository;
import Luuloneranger.Service.UsersService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/accounts")
public class UsersController {
	 @Autowired
	 private UsersService US;

	 @GetMapping("")
	 public String listUsers(HttpSession session, ModelMap model) {
//	     Users current = (Users) session.getAttribute("currentUser");
//	     if (current == null) {
//	         return "redirect:/login"; 
//	     }
		 List<Users> users = US.findAll();
	     model.addAttribute("users", users);
//	     model.addAttribute("currentUser", current); 
	     return "admin/accounts/list";
	 }
	 
	

	 @GetMapping("/add")
	    public String add(ModelMap model) {
	        model.addAttribute("user", new Users());
	        return "admin/accounts/addOrEdit";
	    }

	    @GetMapping("/edit/{id}")
	    public String edit(@PathVariable("id") Long id, ModelMap model) {
	        Users user = US.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	        model.addAttribute("user", user);
	        return "admin/accounts/addOrEdit";
	    }

	    @PostMapping("/saveOrUpdate")
	    public String saveOrUpdate(Users user) {
	        US.save(user);
	        return "redirect:/admin/accounts";
	    }

	    @GetMapping("/delete/{id}")
	    public String delete(@PathVariable("id") Long id) {
	        US.deleteById(id);
	        return "redirect:/admin/accounts";
	    }

}
