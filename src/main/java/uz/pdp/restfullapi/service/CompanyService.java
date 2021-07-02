package uz.pdp.restfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.restfullapi.entity.Address;
import uz.pdp.restfullapi.entity.Company;
import uz.pdp.restfullapi.payload.ApiResponse;
import uz.pdp.restfullapi.payload.CompanyDto;
import uz.pdp.restfullapi.repository.AddressRepository;
import uz.pdp.restfullapi.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompany() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse deleteCompany(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Company deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse addCompany(CompanyDto companyDto) {
        Company company = new Company();
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddress());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found",false);
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("Company added", true);
    }

    public ApiResponse editCompany(CompanyDto companyDto, Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Company company = optionalCompany.get();
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("Company edited", true);
    }


}
