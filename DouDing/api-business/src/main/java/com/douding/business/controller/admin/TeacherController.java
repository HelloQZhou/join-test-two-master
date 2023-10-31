package com.douding.business.controller.admin;


import com.douding.server.dto.*;
import com.douding.server.service.TeacherService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    public static final String BUSINESS_NAME = "讲师";

    @Resource
    private TeacherService teacherService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto) {
        teacherService.list(pageDto);
        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }


    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto) {
        ValidatorUtil.require(teacherDto.getName(), "姓名");
        ValidatorUtil.require(teacherDto.getNickname(), "昵称");
        ValidatorUtil.require(teacherDto.getPosition(), "职位");
        ValidatorUtil.require(teacherDto.getMotto(), "座右铭");
        ValidatorUtil.require(teacherDto.getIntro(), "简介");
        ResponseDto<TeacherDto> responseDto = new ResponseDto<>();
        teacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto<TeacherDto> responseDto = new ResponseDto<>();
        teacherService.delete(id);
        return responseDto;
    }

    @RequestMapping("/all")
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<TeacherDto> teacherDtoList = teacherService.all();
        responseDto.setContent(teacherDtoList);
        return responseDto;
    }

}