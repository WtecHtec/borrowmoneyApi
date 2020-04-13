package com.example.demo.service;

import com.example.demo.dao.pojo.Record;
import com.example.demo.dao.pojo.User;
import com.example.demo.request.RecordRequest;
import com.example.demo.uitl.BaseResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RecordService {

    //分页获取
    BaseResponse<PageInfo<Record>> getRecordDatas(RecordRequest recordRequest);

    // 新增
    BaseResponse<String> createRecordInfo(Record recordInfo);

    // 修改
    BaseResponse<String> updateRecordInfo(Record recordInfo);

    // 获取报表
    BaseResponse<List<Record>> getRecordReports(String createTime);


    BaseResponse<Record> getRecordDataInfo(String recordId);

}
