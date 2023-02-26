package az.spring.rest.demo.springrestdemo.service;

import az.spring.rest.demo.springrestdemo.rest.model.DTO.EmployeeDTO;
import az.spring.rest.demo.springrestdemo.rest.model.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse getAllEmployees();
    EmployeeDTO getEmployee (long id);
    EmployeeResponse getEmployeeByNameAndSurname(String name,String surname);
    void insert(EmployeeDTO employeeDTO);

    void update(EmployeeDTO employeeDTO, long id);

    void updateSome(EmployeeDTO employeeDTO, long id);

    void delete(long id);
}
