package uz.pdp.restfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.restfullapi.entity.Address;
import uz.pdp.restfullapi.entity.Department;
import uz.pdp.restfullapi.entity.Worker;
import uz.pdp.restfullapi.payload.ApiResponse;
import uz.pdp.restfullapi.payload.WorkerDto;
import uz.pdp.restfullapi.repository.AddressRepository;
import uz.pdp.restfullapi.repository.DepartmentRepository;
import uz.pdp.restfullapi.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    public List<Worker> getWorker() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    public ApiResponse deleteWorker(Integer id) {
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("Worker deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse addWorker(WorkerDto workerDto) {
        Worker worker = new Worker();
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddress());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found",false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartment());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found",false);
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(worker.getDepartment());
        worker.setName(workerDto.getName());
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber){
            return new ApiResponse("There is such a worker",false);
        }
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("Company added", true);
    }

    public ApiResponse editWorker(WorkerDto workerDto, Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Worker not found", false);
        Worker worker = optionalWorker.get();
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddress());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found",false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartment());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found",false);
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(worker.getDepartment());
        worker.setName(workerDto.getName());
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(),id);
        if (existsByPhoneNumber){
            return new ApiResponse("There is such a worker",false);
        }
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("Worker edited", true);
    }


}
