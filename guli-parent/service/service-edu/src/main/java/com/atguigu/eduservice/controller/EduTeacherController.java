package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wwl
 * @since 2021-02-22
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    @ApiOperation(value = "根据id删除讲师" )

    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id",value = "讲师Id",required = true) @PathVariable String id){
       eduTeacherService.removeById(id);
       return R.ok();
    }

    @ApiOperation(value="分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(@ApiParam(name = "page",value = "当前页码",required = true)@PathVariable Long page,
            @ApiParam(name = "limit",value = "煤业记录数",required = true)@PathVariable Long limit
                      ){
        Page<EduTeacher> pageparam = new Page<>(page,limit);
        eduTeacherService.page(pageparam,null);
        List<EduTeacher> records = pageparam.getRecords();
        long total = pageparam.getTotal();
        return R.ok().data("total",total).data("rows",records);

    }

}

