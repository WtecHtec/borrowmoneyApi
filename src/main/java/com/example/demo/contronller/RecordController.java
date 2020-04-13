package com.example.demo.contronller;

import com.example.demo.dao.mapper.RecordMapper;
import com.example.demo.dao.pojo.Record;
import com.example.demo.dao.pojo.User;
import com.example.demo.jwt.JwtIgnore;
import com.example.demo.request.RecordRequest;
import com.example.demo.serviceImp.RecordServiceImp;
import com.example.demo.uitl.BaseResponse;
import com.example.demo.uitl.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RecordController {

     @Autowired
     private RecordServiceImp recordServiceImp;



    //分页获取
    @RequestMapping(value = "/getRecordRecord",method =  RequestMethod.GET)
    public BaseResponse<PageInfo<Record>> getRecordDatas(
            Integer pageNum,Integer pageSize
    ) {
        log.info("分页获取");
        BaseResponse<PageInfo<Record>> result = new BaseResponse<>();
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setPageNum(pageNum);
        recordRequest.setPageSize(pageSize);
        result = recordServiceImp.getRecordDatas(recordRequest);
        return result;
    }

    //报表
    @RequestMapping(value = "/getRecordReports",method = RequestMethod.GET)
    public BaseResponse<List<Record>> getRecordReports(String createTime) {
        log.info("报表");
        BaseResponse<List<Record>> result = new BaseResponse<>();
        result = recordServiceImp.getRecordReports(createTime);
        return result;
    }

    //新增
    @RequestMapping(value = "/createRecord",method =  RequestMethod.POST)
    public BaseResponse<String> registered(@RequestBody  Record record) {
        log.info("新增记录");
        BaseResponse<String> result = new BaseResponse<>();
        result = recordServiceImp.createRecordInfo(record);
        return result;
    }

    // 更新
    @JwtIgnore
    @RequestMapping(value = "/updateRecord",method =  RequestMethod.POST)
    public BaseResponse<String> updateRecord(@RequestBody Record record) {
        log.info("提交记录");
        BaseResponse<String> result = new BaseResponse<>();
        result = recordServiceImp.updateRecordInfo(record);
        return result;
    }

    // 更新
    @RequestMapping(value = "/delRecord",method =  RequestMethod.POST)
    public BaseResponse<String> delRecord(Record record) {
        log.info("删除记录");
        BaseResponse<String> result = new BaseResponse<>();
        record.setDel(1);
        result = recordServiceImp.updateRecordInfo(record);
        return result;
    }

    // 获取记录详情
    @JwtIgnore
    @RequestMapping(value = "/getRecordInfo",method =  RequestMethod.GET)
    public BaseResponse<Record> getRecordInfo(String recordId) {
        log.info("获取记录详情");
        BaseResponse<Record> result = new BaseResponse<>();
        result =  recordServiceImp.getRecordDataInfo(recordId);
        return result;
    }
}
