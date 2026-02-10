package org.palevo.infotest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.service.process.ProcessService;
import org.palevo.infotest.service.text.ModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
public class ApiController {
    @Autowired
    ProcessService processService;
    @PostMapping("/start")
    public ResponseEntity<Long> start(@RequestBody ProcessCreateDTO process,
                                HttpServletRequest request){
        String userIp= request.getRemoteAddr();
        return ResponseEntity.ok(processService.run(userIp,process));
    }

}
