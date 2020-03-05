package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.out.AddressShowOutDTO;
import io.wjh.wcartadministrationback.po.Address;
import io.wjh.wcartadministrationback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/address")
@RestController
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getById")
    public AddressShowOutDTO getById(@RequestParam Integer addressId) {
        Address address = addressService.getById(addressId);
        AddressShowOutDTO addressShowOutDTO = new AddressShowOutDTO();
        addressShowOutDTO.setAddressId(address.getAddressId());
        addressShowOutDTO.setContent(address.getContent());
        addressShowOutDTO.setReceiverMobile(address.getReceiverMobile());
        addressShowOutDTO.setReceiverName(address.getReceiverName());
        addressShowOutDTO.setTag(address.getTag());
        return addressShowOutDTO;
    }
}
