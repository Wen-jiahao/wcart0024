package io.wjh.wcartstoreback.controller;

import io.wjh.wcartstoreback.dto.in.AddressCreateInDTO;
import io.wjh.wcartstoreback.dto.in.AddressUpdateInDTO;
import io.wjh.wcartstoreback.dto.out.AddressListOutDTO;
import io.wjh.wcartstoreback.dto.out.AddressShowOutDTO;
import io.wjh.wcartstoreback.po.Address;
import io.wjh.wcartstoreback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("/getById")
    public AddressShowOutDTO getById(@RequestParam Integer addressId){
        Address address= addressService.getById(addressId);
        AddressShowOutDTO addressShowOutDTO = new AddressShowOutDTO();
        addressShowOutDTO.setAddressId(address.getAddressId());
        addressShowOutDTO.setTag(address.getTag());
        addressShowOutDTO.setReceiverName(address.getReceiverName());
        addressShowOutDTO.setReceiverMobile(address.getReceiverMobile());
        addressShowOutDTO.setContent(address.getContent());
        return addressShowOutDTO;
    }

    @GetMapping("/getCustomerAddress")
    public List<AddressListOutDTO> getCustomerAddress(@RequestAttribute Integer customerId){
        List<Address> addresses = addressService.getByCustomerId(customerId);

        List<AddressListOutDTO> addressListOutDTOS = addresses.stream().map(address -> {
            AddressListOutDTO addressListOutDTO = new AddressListOutDTO();
            addressListOutDTO.setAddressId(address.getAddressId());
            addressListOutDTO.setTag(address.getTag());
            addressListOutDTO.setReceiverName(address.getReceiverName());
            addressListOutDTO.setReceiverMobile(address.getReceiverMobile());
            addressListOutDTO.setContent(address.getContent());
            return addressListOutDTO;
        }).collect(Collectors.toList());

        return addressListOutDTOS;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AddressCreateInDTO addressCreateInDTO,
                          @RequestAttribute Integer customerId){
        Address address = new Address();
        address.setCustomerId(customerId);
        address.setContent(addressCreateInDTO.getContent());
        address.setReceiverMobile(addressCreateInDTO.getReceiverMobile());
        address.setReceiverName(addressCreateInDTO.getReceiverName());
        address.setTag(addressCreateInDTO.getTag());
        addressService.create(address);
        Integer addressId = address.getAddressId();
        return addressId;
    }

    @PostMapping("/update")
    public void update(@RequestBody AddressUpdateInDTO addressUpdateInDTO){
        Address address = new Address();
        address.setAddressId(addressUpdateInDTO.getAddressId());
        address.setContent(addressUpdateInDTO.getContent());
        address.setReceiverMobile(addressUpdateInDTO.getReceiverMobile());
        address.setReceiverName(addressUpdateInDTO.getReceiverName());
        address.setTag(addressUpdateInDTO.getTag());
        addressService.update(address);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer addressId){
        addressService.delete(addressId);
    }
}
