package uz.ecopay.student_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.ecopay.student_crud.entity.Student;
import uz.ecopay.student_crud.exceptions.SimpleException;
import uz.ecopay.student_crud.payload.ApiResponse;
import uz.ecopay.student_crud.payload.StudentDto;
import uz.ecopay.student_crud.repository.StudentRepository;
import uz.ecopay.student_crud.utils.CommonUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public ApiResponse getAllStudent(Integer page, Integer size) {
        try {
            Page<Student> studentPage = studentRepository.findAll(CommonUtils.simplePageable(page, size));
            return new ApiResponse("Successfully",true,studentPage.getContent().stream().map(this::getStudentDtoFromStudent).collect(Collectors.toList()));
        } catch (SimpleException e) {
            return new ApiResponse("Error",false);
        }
    }

    public StudentDto getStudentDtoFromStudent(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstname(),
                student.getLastname(),
                student.getEmail(),
                student.getPhone()
        );
    }

    public ApiResponse getStudentById(Integer id) {
        try {
            Optional<Student> optional = studentRepository.findById(id);
            return optional.map(student -> new ApiResponse("Success", true, student)).orElseGet(() -> new ApiResponse("Not found", true, "Not found"));
        } catch (SimpleException e) {
            return new ApiResponse("Error",false);
        }
    }


    public ApiResponse deleteStudent(Integer id) {
        try {
            studentRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        } catch (SimpleException e) {
            return new ApiResponse("Error in Deleting",false);
        }
    }

    public ApiResponse addOrEditStudent(StudentDto dto) {
        try {
            Student student = new Student();
            if (dto.getId() != null) {
                student = studentRepository.findById(dto.getId()).orElseThrow(() -> new SimpleException("Student not found"));
            }
            student.setFirstname(dto.getFirstname());
            student.setLastname(dto.getLastname());
            student.setEmail(dto.getEmail());
            student.setPhone(dto.getPhone());
            studentRepository.save(student);
            return new ApiResponse(dto.getId()==null?"Added":"Edited",true);
        } catch (Exception e) {
            return new ApiResponse("Error",false);
        }
    }
}
