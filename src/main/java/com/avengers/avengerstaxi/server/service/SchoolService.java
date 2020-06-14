package com.avengers.avengerstaxi.server.service;

import com.avengers.avengerstaxi.server.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    private SchoolRepository schoolRepository;

    @Autowired //스쿨 레파지토리를 자동으로 넣어줘 (넘겨받을 수 있는 건 자동으로 넘겨주세요! @가 적혀있는 것들만 받을 수 있음)
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }
}
