package com.avengers.avengerstaxi.server;

import com.avengers.avengerstaxi.server.dto.SchoolDto;
import com.avengers.avengerstaxi.server.dto.SchoolListDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.graalvm.compiler.asm.sparc.SPARCAssembler.Fcn.Page;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    @GetMapping()
    public SchoolListDto getSchoolList(@RequestParam("page") int page, @RequestParam("size") int size) { //리턴 값 = SchoolListDto

        SchoolListDto dto = new SchoolListDto(); //new = 클래스 객체를 새로 만들게요!

        int startPosition = page * size;
        int endPosition = (page + 1) * size;

        //현재 페이지 위치가 전체 데이터 크기를 벗어나지 않게 함
        if(startPosition >= this.schools.size()) { // 시작점 > 전체 데이터 크기
            dto.schools = new ArrayList();
            return dto;
        }

        if(endPosition >= this.schools.size()) { //페이지 끝의 데이터 값
            endPosition = this.schools.size();
        }

        dto.schools = new ArrayList(this.schools.subList(startPosition, endPosition));
        return dto;
    }

    @GetMapping("/{schoolId}")
    public SchoolDto getSchool(@PathVariable("schoolId") int schoolId) {
        SchoolDto dto = this.schools.get(schoolId - 1); //데이터 시작 과 요청을 받는 시작 값이 다름 (두 수의 차이 = 1)
        return dto;
    }

    private ArrayList<SchoolDto> schools;

    public SchoolController() {
        this.schools = new ArrayList<>();
        SchoolDto school1 = new SchoolDto();
        SchoolDto school2 = new SchoolDto();
        SchoolDto school3 = new SchoolDto();
        school1.id = 1;
        school1.name = "명원초등학교";
        school1.totalStudent = 605;
        school1.totalTeacher = 53;
        school2.id = 2;
        school2.name = "명일중학교";
        school2.totalStudent = 309;
        school2.totalTeacher = 10;
        school3.id = 3;
        school3.name = "선사고등학교";
        school3.totalStudent = 305;
        school3.totalTeacher = 01;
        this.schools.add(school1);
        this.schools.add(school2);
        this.schools.add(school3);
    }
}
