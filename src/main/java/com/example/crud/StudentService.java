package com.example.crud;

import com.example.crud.model.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // 복수의 StudentDTO를 담는 멤버변수 (메소드)
    private final List<StudentDTO> studentList = new ArrayList<>();
    private Long nextId = 1L;

    public StudentService() {
        createStudent("alex", "alex@gmail.com");
        createStudent("brad", "brad@gmail.com");
        createStudent("chad", "chad@gmail.com");
    }


    // 새로운 StudentDTO를 생성하는 메소드
    public StudentDTO createStudent(String name, String email) {
        StudentDTO newStudent = new StudentDTO(
                nextId, name, email
        );
        nextId++;
        studentList.add(newStudent);
        return newStudent;
    }

    public List<StudentDTO> readStudentAll() {
        return studentList;
    }

    public StudentDTO readStudent(Long id) {
        // TODO
        // 여기는 그냥 java 코드가 들어감.
        for (StudentDTO studentDTO : studentList) {
            if (studentDTO.getId().equals(id))
                return studentDTO;
        }

        return null;
    }

    public StudentDTO updateStudent(Long id, String name, String email) {
        int target = -1;

//        input 요소의 name 과 Controller에 매개변수 이름이 같을 때 @RequestParam  을 생략해도 무관한지 확인 코드
//        StudentDTO targetDto = this.readStudent(id);
//        if (targetDto != null) {
//            targetDto.setName(name);
//            targetDto.setEmail(email);
//            return targetDto;
//        } else return null;

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }

        if (target != -1) {
            studentList.get(target).setName(name);
            studentList.get(target).setEmail(email);
            return studentList.get(target);
        }
        else return null;
    }

    public boolean deleteStudent(Long id) {
        int target = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }

        if (target != -1) {
            studentList.remove(target);
            return true;
        }
        else return false;
    }
}




