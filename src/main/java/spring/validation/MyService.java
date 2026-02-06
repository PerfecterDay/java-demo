package spring.validation;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Author: zhongzhu.wang
 * Date:2026/2/6 16:45
 */


@Validated
@Component
public class MyService {

    void addStudent(@Valid Person person, @Max(2) int degrees) {
        System.out.println("validate success");
    }
}
