package com.example.crud;

import com.example.crud.model.StudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.synth.SynthFormattedTextFieldUI;

@Controller
public class StudentController {
    // StudentService를 Controller에서 사용
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("email")
            String email
    ) {
        System.out.println(name);
        System.out.println(email);
        StudentDTO studentDTO
                = studentService.createStudent(name, email);
        System.out.println(studentDTO.toString());
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute(
                "studentList",
                studentService.readStudentAll()
        );
        return "home";
    }
}
