package com.example.demo.dao.mapper;

import com.example.demo.dao.pojo.Record;
import java.util.List;

public interface RecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbg.generated Wed Apr 01 21:12:49 CST 2020
     */
    int insert(Record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbg.generated Wed Apr 01 21:12:49 CST 2020
     */
    List<Record> selectAll();

    int updateRcord(Record record);

    List<Record>  reportData(String createTime);

    List<Record> getSendInfo(String date);

    Record selectById(String recordId);
}