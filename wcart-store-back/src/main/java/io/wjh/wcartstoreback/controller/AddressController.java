package io.wjh.wcartstoreback.controller;

import io.wjh.wcartstoreback.dto.in.AddressCreateInDTO;
import io.wjh.wcartstoreback.dto.in.AddressUpdateInDTO;
import io.wjh.wcartstoreback.dto.out.AddressListOutDTO;
import io.wjh.wcartstoreback.po.Address;
import io.wjh.wcartstoreback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("/getAddressByCustomerId")
    public List<AddressListOutDTO> getAddressByCustomerId(@RequestAttribute Integer customerId){
        return null;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AddressCreateInDTO addressCreateInDTO,
                          @RequestAttribute Integer customerId){
        Address address = new Address();
        address.setAddressId(customerId);
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
