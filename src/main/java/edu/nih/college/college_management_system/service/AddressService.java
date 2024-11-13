package edu.nih.college.college_management_system.service;

import edu.nih.college.college_management_system.model.Address;
import edu.nih.college.college_management_system.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // Find all addresses for a specific student
    @Transactional(readOnly = true)
    public List<Address> getAddressesByStudentId(Long studentId) {
        return addressRepository.findByStudentId(studentId);
    }

    // Add a new address
    @Transactional
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    // Update an address
    @Transactional
    public Address updateAddress(Address address) {
        Optional<Address> existingAddress = addressRepository.findById(address.getId());
        if (existingAddress.isPresent()) {
            return addressRepository.save(address);
        } else {
            throw new RuntimeException("Address not found");
        }
    }

    // Delete address by student ID
    @Transactional
    public void deleteAddressByStudentId(Long studentId) {
        addressRepository.deleteAddressesByStudentId(studentId);
    }

    // Find unique addresses for a specific student
    @Transactional(readOnly = true)
    public List<Address> getUniqueAddressesByStudentId(Long studentId) {
        return addressRepository.findUniqueAddressesByStudentId(studentId);
    }
}
