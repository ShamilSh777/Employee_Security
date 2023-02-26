package az.spring.rest.demo.springrestdemo.rest.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private long id;

    private String name;

    private String surname;

    private int age;

    private double salary;
}
