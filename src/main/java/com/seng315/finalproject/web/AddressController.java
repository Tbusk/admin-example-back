package com.seng315.finalproject.web;

import com.seng315.finalproject.domain.Address;
import com.seng315.finalproject.domain.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    private String getUsername() {
        Object user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof UserDetails) {
            return ((UserDetails)user).getUsername();
        }
        return "Anonymous";
    }

    @GetMapping(path = "/list")
    public Iterable<Address> getAllAddresses() {

        logger.info("User " + getUsername() + " used getAllAddresses().") ;

        return addressRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Address> getAddress(@PathVariable int id) {

        logger.info("User " + getUsername() + " used getAddress().") ;

        return addressRepository.findById(id);
    }

    @PutMapping
    public String updateUserInfo(@RequestBody Map<String,String> formData) {
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
            logger.info("User " + getUsername() + " updated addressID " + address.getAddressID() + ".");
            return "AddressID " + address.getAddressID() + " updated.";
        }
        return "Issue with updating Address.";
    }

    @PostMapping
    public String addUserInfo(@RequestBody Map<String,String> formData) {

        Address address = new Address();

        address.setAddressOne(formData.get("addressOne"));
        address.setAddressTwo(formData.get("addressTwo"));
        address.setCity(formData.get("city"));
        address.setState(formData.get("state"));
        address.setCountry(formData.get("country"));
        address.setZip(formData.get("zip"));
        addressRepository.save(address);
        logger.info("User " + getUsername() + " created a new address. ");
        return "New address created.";
    }

    @DeleteMapping
    public String deleteAddress(@RequestBody Map<String, String> formData) {

        Optional<Address> addressOptional = addressRepository.findById(Integer.parseInt(formData.get("addressID")));

        if(addressOptional.isPresent()) {
            Address address = addressOptional.get();
            addressRepository.delete(address);
            logger.info("User " + getUsername() + " deleted addressID " + address.getAddressID() + ".");
        }
        return "AddressID " + addressOptional.get().getAddressID() + " deleted.";
    }
}
