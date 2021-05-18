package uz.ecopay.student_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.ecopay.student_crud.payload.ApiResponse;
import uz.ecopay.student_crud.payload.StudentDto;
import uz.ecopay.student_crud.service.StudentService;
import uz.ecopay.student_crud.utils.ApplicationConstants;

@Controller
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping("/getAllStudents")
    @ResponseBody
    public HttpEntity<?> getAllStudents(@RequestParam(value = "page", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER)
                                                    Integer page,
                                        @RequestParam(value = "size", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE)
                                                    Integer size) {
        ApiResponse allStudent = service.getAllStudent(page, size);
        return ResponseEntity.ok(allStudent);
    }

    @GetMapping("/getStudentById")
    @ResponseBody
    public HttpEntity<?> getStudentById(@RequestParam Integer id) {
        ApiResponse apiResponse = service.getStudentById(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/addOrEditStudent")
    public String addOrEditStudent(
            @RequestParam(required = false) Integer id,
                                          @RequestParam String firstname,
                                          @RequestParam String lastname,
                                          @RequestParam String email,
                                          @RequestParam String phone
                                          ) {
        StudentDto dto = new StudentDto(id,firstname,lastname,email,phone);
        ApiResponse apiResponse = service.addOrEditStudent(dto);
        if (apiResponse.isSuccess()) {
            return "index";
        }
        return "error";
    }

    @GetMapping("/deleteStudentById/{id}")
    public String deleteStudentById(@PathVariable Integer id) {
        ApiResponse apiResponse = service.deleteStudent(id);
        if (apiResponse.isSuccess()) {
            return "index";
        }
        return "error";
    }
}
