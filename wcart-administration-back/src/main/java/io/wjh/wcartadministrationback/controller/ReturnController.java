package io.wjh.wcartadministrationback.controller;

import io.wjh.wcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wjh.wcartadministrationback.dto.in.ReturnUpdateActionInDTO;
import io.wjh.wcartadministrationback.dto.out.PageOutDTO;
import io.wjh.wcartadministrationback.dto.out.ReturnListOutDTO;
import io.wjh.wcartadministrationback.dto.out.ReturnShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnController {

    /*   1.	return search
        2.return show（returnhistory list）
         3.	return action update
        4.	returnhistory create*/
    @GetMapping("search")
    public PageOutDTO<ReturnListOutDTO> search(@RequestBody ReturnSearchInDTO returnSearchInDTO, @RequestParam Integer pageNum){
        return null;
    }
    @GetMapping("/getById")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId){
        return null;
    }

    @PostMapping("/updateAction")
    public void updateAction(@RequestBody ReturnUpdateActionInDTO returnUpdateActionInDTO){

    }

}
