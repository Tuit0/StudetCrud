package uz.ecopay.student_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.ecopay.student_crud.entity.Student;
import uz.ecopay.student_crud.repository.StudentRepository;

import java.util.Optional;

@Controller
public class DirectController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/add")
    public String addStudent(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            Optional<Student> byId = studentRepository.findById(id);
            if (byId.isPresent()) {
                Student student = byId.get();
                model.addAttribute("id", student.getId());
                model.addAttribute("btn","Edit Student");
                model.addAttribute("firstname", student.getFirstname());
                model.addAttribute("lastname", student.getLastname());
                model.addAttribute("email", student.getEmail());
                model.addAttribute("phone", student.getPhone());
                return "add";
            }
            return "error";
        } else {
            model.addAttribute("btn","Add Student");
            return "add";
        }
    }
}
