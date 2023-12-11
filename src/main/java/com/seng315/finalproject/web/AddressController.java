package com.seng315.finalproject.web;

import com.seng315.finalproject.domain.address.Address;
import com.seng315.finalproject.domain.address.AddressRepository;
import com.seng315.finalproject.security.AccountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path = "/list")
    public Iterable<Address> getAllAddresses() {

        logger.info("User " + AccountUtil.getUsername() + " used getAllAddresses().") ;

        return addressRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Address> getAddress(@PathVariable int id) {

        logger.info("User " + AccountUtil.getUsername() + " used getAddress().") ;

        return addressRepository.findById(id);
    }

    @PutMapping
    public String updateAddress(@RequestBody Map<String,String> formData) {
        Optional<Address> addressOptional = addressRepository.findById(Integer.parseInt(formData.get("addressID")));

        if(addressOptional.isPresent()) {
            Address address = addressOptional.get();
            address.setAddressOne(formData.get("addressOne"));
            address.setAddressTwo(formData.get("addressTwo"));
            address.setCity(formData.get("city"));
            address.setState(formData.get("state"));
            address.setCountry(formData.get("country"));
            address.setZip(formData.get("zip"));
            addressRepository.save(address);
            logger.info("User " + AccountUtil.getUsername() + " updated addressID " + address.getAddressID() + ".");
            return "AddressID " + address.getAddressID() + " updated.";
        }
        return "Issue with updating Address.";
    }

    @PostMapping
    public String addAddress(@RequestBody Map<String,String> formData) {

        Address address = new Address();

        address.setAddressOne(formData.get("addressOne"));
        address.setAddressTwo(formData.get("addressTwo"));
        address.setCity(formData.get("city"));
        address.setState(formData.get("state"));
        address.setCountry(formData.get("country"));
        address.setZip(formData.get("zip"));
        addressRepository.save(address);
        logger.info("User " + AccountUtil.getUsername() + " created a new address. ");
        return "New address created.";
    }

    @DeleteMapping
    public String deleteAddress(@RequestBody Map<String, String> formData) {

        Optional<Address> addressOptional = addressRepository.findById(Integer.parseInt(formData.get("addressID")));

        if(addressOptional.isPresent()) {
            Address address = addressOptional.get();
            addressRepository.delete(address);
            logger.info("User " + AccountUtil.getUsername() + " deleted addressID " + address.getAddressID() + ".");
        }
        return "AddressID " + addressOptional.get().getAddressID() + " deleted.";
    }
}
