package uz.ecopay.student_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ecopay.student_crud.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
