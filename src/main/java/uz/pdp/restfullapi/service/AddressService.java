package uz.pdp.restfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.restfullapi.entity.Address;
import uz.pdp.restfullapi.payload.AddressDto;
import uz.pdp.restfullapi.payload.ApiResponse;
import uz.pdp.restfullapi.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public ApiResponse deleteAddress(Integer id) {
        try {
            addressRepository.deleteById(id);
            return new ApiResponse("Address deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address added", true);
    }

    public ApiResponse editAddress(AddressDto addressDto, Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(address.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address edited", true);
    }


}
