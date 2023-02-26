package az.spring.rest.demo.springrestdemo.rest.model.response;

import az.spring.rest.demo.springrestdemo.rest.model.DTO.EmployeeDTO;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private List<EmployeeDTO>employees;
}
