package org.palevo.infotest.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.palevo.infotest.DTO.ProcessCreateDTO;
import org.palevo.infotest.model.Process;
import org.palevo.infotest.model.RunningTranslateProcess;
import org.palevo.infotest.service.process.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/process")
public class ApiController {
    @Autowired
    ProcessService processService;
    @PostMapping("/start")
    public ResponseEntity<Long> start(@Valid @RequestBody ProcessCreateDTO process,
                                HttpServletRequest request){
        String userIp= request.getRemoteAddr();
        return ResponseEntity.ok(processService.run(userIp,process));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable String id){
        System.out.println(id);
        return processService.findResultById(id);
//        return ResponseEntity.ok(processService.findResultById(id));
    }
    @GetMapping("/running")
    public ResponseEntity<List<RunningTranslateProcess>> findAllRunningProcess(){
        return processService.findAllRunningProcess();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        return processService.deleteById(id);
    }

}
