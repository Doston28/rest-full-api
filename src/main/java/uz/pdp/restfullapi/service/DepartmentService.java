package uz.pdp.restfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.restfullapi.entity.Company;
import uz.pdp.restfullapi.entity.Department;
import uz.pdp.restfullapi.payload.ApiResponse;
import uz.pdp.restfullapi.payload.DepartmentDto;
import uz.pdp.restfullapi.repository.CompanyRepository;
import uz.pdp.restfullapi.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public ApiResponse deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("Department deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompany());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found",false);
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department added", true);
    }

    public ApiResponse editDepartment(DepartmentDto departmentDto, Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found", false);
        Department department = optionalDepartment.get();
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompany());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found",false);
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department edited", true);
    }

}
