package io.wjh.wcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wjh.wcartadministrationback.dto.out.AddressListOutDTO;
import io.wjh.wcartadministrationback.dto.out.AddressShowOutDTO;
import io.wjh.wcartadministrationback.po.Address;
import io.wjh.wcartadministrationback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/address")
@RestController
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getListByCustomerId")
    public List<AddressListOutDTO> getListByCustomerId(@RequestParam Integer customerId){
        List<Address> addressesPage=addressService.getListByCustomerId(customerId);
        List<AddressListOutDTO> addressList = addressesPage.stream().map(address -> {
            AddressListOutDTO addressListOutDTO = new AddressListOutDTO();
            addressListOutDTO.setAddressId(address.getAddressId());
            addressListOutDTO.setContent(address.getContent());
            addressListOutDTO.setReceiverMobile(address.getReceiverMobile());
            addressListOutDTO.setReceiverName(address.getReceiverName());
            addressListOutDTO.setTag(address.getTag());
            return addressListOutDTO;
        }).collect(Collectors.toList());
        return addressList;
    }

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
