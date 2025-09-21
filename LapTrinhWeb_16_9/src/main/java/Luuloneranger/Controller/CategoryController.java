package Luuloneranger.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Luuloneranger.Entity.Category;
import Luuloneranger.Service.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // ADMIN 
    @RequestMapping("admin/categories")
    public String redirectToSearch() {
        return "forward:/admin/categories/searchpaginated";
    }
    
//  USER 
    @GetMapping("/user/categories")
    public String listForUser(ModelMap model) {
        model.addAttribute("categories", categoryService.findAll(Pageable.unpaged()).getContent());
        return "user/categories/list"; // => tạo file list.html trong templates/user/categories
    }

    @GetMapping("admin/categories/searchpaginated")
    public String searchPaginated(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            ModelMap model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Category> resultPage;

        if (StringUtils.hasText(name)) {
            resultPage = categoryService.findByCategoryNameContaining(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = categoryService.findAll(pageable);
        }

        model.addAttribute("categoryPage", resultPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resultPage.getTotalPages());

        return "admin/categories/list";
    }

    @GetMapping("admin/categories/add")
    public String add(ModelMap model) {
        model.addAttribute("category", new Category());
        return "admin/categories/addOrEdit";
    }

    @GetMapping("admin/categories/edit/{id}")
    public String edit(@PathVariable("id") int id, ModelMap model) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        return "admin/categories/addOrEdit";
    }

    @PostMapping("admin/categories/saveOrUpdate")
    public String saveOrUpdate(@RequestParam("fileImage") MultipartFile fileImage,
                               Category category,
                               ModelMap model) {
        try {
            if (!fileImage.isEmpty()) {
                String uploadDir = "src/main/resources/static/uploads/";

                String fileName = fileImage.getOriginalFilename();

                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Files.copy(fileImage.getInputStream(),
                           uploadPath.resolve(fileName),
                           StandardCopyOption.REPLACE_EXISTING);
                category.setImages(fileName);
            }

            categoryService.save(category);
            model.addAttribute("message", "Category is saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Error: " + e.getMessage());
        }

        return "redirect:/admin/categories";
    }

    @GetMapping("admin/categories/delete/{id}")
    public String delete(@PathVariable("id") int id, ModelMap model) {
        categoryService.deleteById(id);
        model.addAttribute("message", "Category is deleted!!!");
        return "redirect:/admin/categories";
    }

    
}
