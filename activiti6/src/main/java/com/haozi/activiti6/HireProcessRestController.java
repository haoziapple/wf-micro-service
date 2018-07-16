package com.haozi.activiti6;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class HireProcessRestController {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ApplicantRepository applicantRepository;

//    @ResponseStatus(value = HttpStatus.OK)
//    @RequestMapping(value = "/start-hire-process", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/start-hire-process")
    public String startHireProcess(@RequestBody Map<String, String> data) {

        Applicant applicant = new Applicant(data.get("name"), data.get("email"), data.get("phoneNumber"));
        applicantRepository.save(applicant);

        Map<String, Object> vars = Collections.<String, Object>singletonMap("applicant", applicant);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("hireProcessWithJpa", vars);
        return processInstance.getProcessInstanceId();
    }

    @GetMapping("/get-applicant")
    public List<Applicant> getApplicants() {
        return applicantRepository.findAll();
    }
}
