package com.avengers.avengerstaxi.server.service;

import com.avengers.avengerstaxi.server.model.SchoolEntity;
import com.avengers.avengerstaxi.server.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SchoolService { //가장 기본적인 틀 (비슷하게 복사 붙여넣기 해서 택시 컨트롤러 만들어!)
    private SchoolRepository schoolRepository;

    @Autowired //스쿨 레파지토리를 자동으로 넣어줘 (넘겨받을 수 있는 건 자동으로 넘겨주세요! @가 적혀있는 것들만 받을 수 있음)
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }
    public ArrayList<SchoolEntity> findSchool(){
        ArrayList<SchoolEntity> schoolList = new ArrayList<>(schoolRepository.findAll());
        return schoolList;
    }
    public void addSchool(SchoolEntity school){
        schoolRepository.save(school);
    }
    public SchoolEntity getSchool(long id) {
        Optional<SchoolEntity> school = schoolRepository.findById(id);
        if (!school.isPresent()) { //isEmpty 와 isPresent 는 반대이기 때문에 !를 통해 반대 의미 부여
            return null;
        }
        return  school.get();
    }
}
