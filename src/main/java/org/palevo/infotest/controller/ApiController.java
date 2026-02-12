package org.palevo.infotest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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



    @Operation(summary = "Запуск процесса", description = "В ответе возвращается id созданного процесса")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Процесс успешно создан")
    })
    @PostMapping("/start")
    public ResponseEntity<Long> start(@Parameter(
                                                  description = "Процесс который необходимо создать",
                                                  required = true)
                                          @Valid @RequestBody ProcessCreateDTO process,
                                HttpServletRequest request){
        String userIp= request.getRemoteAddr();
        return ResponseEntity.ok(processService.run(userIp,process));
    }




    @Operation(summary = "Получить результат по id", description = "В ответе возвращается поле result")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Результат успешно получен")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(
            @Parameter(
            description = "ID процесса, данные по которому запрашиваются",
            required = true)
                                              @PathVariable String id){
        System.out.println(id);
        return processService.findResultById(id);
//        return ResponseEntity.ok(processService.findResultById(id));
    }



    @Operation(summary = "Показать все запущенные процессы",
            description = "В ответе возвращается List со всеми процессами перевода")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Процессы успешно получены")
    })
    @GetMapping("/running")
    public ResponseEntity<List<RunningTranslateProcess>> findAllRunningProcess(){
        return processService.findAllRunningProcess();
    }



    @Operation(summary = "Удалить процесс", description = "В ответе возвращается id удаленного процесса")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Процесс успешно удалён")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@Parameter(
            description = "ID процесса для удаления",
            required = true)@PathVariable String id){
        return processService.deleteById(id);
    }

}
