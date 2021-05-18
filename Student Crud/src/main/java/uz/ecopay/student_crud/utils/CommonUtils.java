package uz.ecopay.student_crud.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uz.ecopay.student_crud.exceptions.SimpleException;

public class CommonUtils {
    public static void validatePageNumberOrSize(int page, int size) {
        if (page<0) {
            throw new SimpleException("The number of page can't be less than 0 !!");
        }
        if (size > ApplicationConstants.DEFAULT_MAX_SIZE) {
            throw new SimpleException("The size of page can't be more than "+ ApplicationConstants.DEFAULT_MAX_SIZE+" !!");
        }
    }

    public static Pageable simplePageable(int page, int size) {
        validatePageNumberOrSize(page,size);
        return PageRequest.of(page,size);
    }
}
