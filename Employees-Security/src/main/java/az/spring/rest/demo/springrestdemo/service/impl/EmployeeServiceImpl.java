package az.spring.rest.demo.springrestdemo.service.impl;

import az.spring.rest.demo.springrestdemo.enums.ErrorCodeEnum;
import az.spring.rest.demo.springrestdemo.exception.CustomRestException;
import az.spring.rest.demo.springrestdemo.model.Employee;
import az.spring.rest.demo.springrestdemo.repository.EmployeeRepository;
import az.spring.rest.demo.springrestdemo.rest.model.DTO.EmployeeDTO;
import az.spring.rest.demo.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.demo.springrestdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeRepository.findAll().
                stream().
                map(employee -> convertToDto(employee)).
                collect(Collectors.
                        toList());
        return makeEmployeeResponse(employeeDTOList);
    }

    @Override
    public EmployeeDTO getEmployee(long id) {
        return employeeRepository.findById(id)
                .map(employee -> convertToDto(employee))
                .orElseThrow(() -> new CustomRestException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name, String surname) {
        List<EmployeeDTO> employees = employeeRepository.findByNameAndSurname(name, surname)
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());


        return makeEmployeeResponse(employees);
    }

    @Override
    public void insert(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDTO employeeDTO, long id) {
        Employee employee = getEmployeeById(id);
       employee.setName(employee.getName());
       employee.setSurname(employee.getSurname());
       employee.setAge(employee.getAge());
       employee.setSalary(employee.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDTO employeeDTO, long id) {
        Employee employee = getEmployeeById(id);
        if (employeeDTO.getName()!=null)
        employee.setName(employee.getName());

        if (employeeDTO.getSurname()!=null)
        employee.setSurname(employee.getSurname());

        if (employeeDTO.getAge()>0)
        employee.setAge(employee.getAge());

        if (employeeDTO.getSalary()>0)
        employee.setSalary(employee.getSalary());

        employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    private Employee getEmployeeById(long id){
return employeeRepository.findById(id)
        .orElseThrow(() -> new CustomRestException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
    }

    private EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private EmployeeResponse makeEmployeeResponse(List<EmployeeDTO> employees) {
        return EmployeeResponse.builder()
                .employees(employees)
                .build();
    }

}
