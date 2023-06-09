package com.example.crud;

import com.example.crud.model.StudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model
    ) {
//        studentService.readStudent(id);
        model.addAttribute(
                "student",
                studentService.readStudent(id));
        return "read";
    }

    @GetMapping("/{id}/update-view")
    public String updateView(
            @PathVariable("id") Long id,
            Model model
    ) {
        model.addAttribute(
                "student",
                studentService.readStudent(id)
        );
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(
            // pathvariale = path에 있는 변수라는 뜻
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ){
        studentService.updateStudent(id, name, email);

        return String.format("redirect/%s", id);
    }

    @PostMapping("/{id}/delete-view")
    public String deleteView(
            @PathVariable("id") Long id,
            Model model
    ) {
        StudentDTO studentDTO = studentService.readStudent(id);
        model.addAttribute("student", studentDTO);
//        model.addAttribute(
//                "student",
//                studentService.readStudent(id)
//        );

        return "delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id") Long id
    ) {
        studentService.deleteStudent(id);
        // update때는 데이터가 남아있지만
        // delete는 돌아갈 상세보기가 없음.
        // 그래서 홈으로 돌아간다.
        return "redirect:/home";
    }
}

