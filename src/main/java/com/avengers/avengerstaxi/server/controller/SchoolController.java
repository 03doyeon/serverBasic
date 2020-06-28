package com.avengers.avengerstaxi.server.controller;

import com.avengers.avengerstaxi.server.dto.SchoolDto;
import com.avengers.avengerstaxi.server.dto.SchoolListDto;
import com.avengers.avengerstaxi.server.model.SchoolEntity;
import com.avengers.avengerstaxi.server.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.graalvm.compiler.asm.sparc.SPARCAssembler.Fcn.Page;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping()
    public SchoolListDto getSchoolList(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "size", required = false) Integer size) { //리턴 값 = SchoolListDto
        ArrayList<SchoolEntity> schoolList = this.schoolService.findSchool();
        ArrayList<SchoolDto> list = new ArrayList<>();

        for (SchoolEntity entity : schoolList) {
            SchoolDto dto = new SchoolDto();
            dto.id = entity.id;
            dto.name = entity.name;
            dto.totalStudent = entity.totalStudent;
            dto.totalTeacher = entity.totalTeacher;
            list.add(dto);
        }
        SchoolListDto dto = new SchoolListDto();
        dto.schools = list;
        return dto;
    }

    @GetMapping("/{schoolId}")
    public SchoolDto getSchool(@PathVariable("schoolId") long schoolId) {
        SchoolEntity entity = this.schoolService.getSchool(schoolId);
        if(entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        SchoolDto dto = new SchoolDto();
        dto.id = entity.id;
        dto.name = entity.name;
        dto.totalStudent = entity.totalStudent;
        dto.totalTeacher = entity.totalTeacher;
        return dto;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addSchool(@RequestBody() SchoolDto newSchool) {
        SchoolEntity entity = new SchoolEntity();
        entity.name = newSchool.name;
        entity.totalStudent = newSchool.totalStudent;
        entity.totalTeacher = newSchool.totalTeacher;
        this.schoolService.addSchool(entity);
    }
}
