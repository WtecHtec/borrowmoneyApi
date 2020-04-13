package com.example.demo.serviceImp;

import com.example.demo.dao.mapper.RecordMapper;
import com.example.demo.dao.pojo.Record;
import com.example.demo.request.RecordRequest;
import com.example.demo.service.RecordService;
import com.example.demo.uitl.BaseResponse;
import com.example.demo.uitl.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RecordServiceImp implements RecordService {


    @Autowired
    private RecordMapper recordMapper;

    private IdWorker idWorker = new IdWorker(2,2);

    @Override
    public BaseResponse<PageInfo<Record>> getRecordDatas(RecordRequest recordRequest) {
        log.info("分页获取记录");
        BaseResponse<PageInfo<Record>> result = new BaseResponse<>();
        PageHelper.startPage(recordRequest.getPageNum(),recordRequest.getPageSize());
        List<Record> lists = new ArrayList<>();
        lists = recordMapper.selectAll();

        PageInfo<Record> data = new PageInfo<Record>(lists);
        result.setStatusCode(200);
        result.setResponseData(data);
        return result;
    }

    @Override
    public BaseResponse<String> createRecordInfo(Record recordInfo) {
        BaseResponse<String> resulet = new BaseResponse<>();
        log.info("创建记录");
        Long id = idWorker.nextId();
        recordInfo.setRecordid(String.valueOf(id));
        recordInfo.setCreatetime(new Date());
        recordInfo.setDel(0);
        recordInfo.setStatus("0");
        int rs =  recordMapper.insert(recordInfo);
        if (rs > 0) {
            resulet.setStatusCode(200);
            resulet.setStatusMsg("创建成功");
            resulet.setResponseData(String.valueOf(id));
        } else {
            resulet.setStatusCode(201);
            resulet.setStatusMsg("创建失败");
        }
        return resulet;
    }

    @Override
    public BaseResponse<String> updateRecordInfo(Record recordInfo) {
        BaseResponse<String> resulet = new BaseResponse<>();
        int rs =  recordMapper.updateRcord(recordInfo);
        if (rs > 0) {
            resulet.setStatusCode(200);
            resulet.setStatusMsg("修改成功");

        } else {
            resulet.setStatusCode(201);
            resulet.setStatusMsg("修改失败");
        }
        return resulet;
    }

    @Override
    public BaseResponse<List<Record>> getRecordReports(String createTime) {
        log.info("报表");
        BaseResponse<List<Record>> result = new BaseResponse<>();
        List<Record> data = new ArrayList<>();
        data = recordMapper.reportData(createTime);
        result.setStatusCode(200);
        result.setResponseData(data);
        return result;
    }

    @Override
    public BaseResponse<Record> getRecordDataInfo(String recordId) {
        log.info("记录详情");
        BaseResponse<Record> result = new BaseResponse<>();

        Record data = recordMapper.selectById(recordId);
        if (null == data ) {
            result.setStatusCode(201);

        } else  {
            if ("0".equals(data.getStatus())) {
                result.setStatusCode(200);
            } else  {
                result.setStatusCode(201);
            }

        }
        result.setResponseData(data);
        return result;
    }
}
