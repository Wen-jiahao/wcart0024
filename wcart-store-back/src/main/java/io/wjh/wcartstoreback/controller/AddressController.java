package io.wjh.wcartstoreback.controller;

import io.wjh.wcartstoreback.dto.in.AddressCreateInDTO;
import io.wjh.wcartstoreback.dto.in.AddressUpdateInDTO;
import io.wjh.wcartstoreback.dto.out.AddressListOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
    @GetMapping("/getAddressByCustomerId")
    public List<AddressListOutDTO> getAddressByCustomerId(@RequestAttribute Integer customerId){
        return null;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AddressCreateInDTO addressCreateInDTO,
                          @RequestAttribute Integer customerId){
        return null;
    }

    @PostMapping("/update")
    public void update(@RequestBody AddressUpdateInDTO addressUpdateInDTO,
                       @RequestAttribute Integer customerId){

    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer addressId){

    }
}
