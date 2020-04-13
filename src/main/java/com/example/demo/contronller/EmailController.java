package com.example.demo.contronller;

import com.example.demo.dao.mapper.RecordMapper;
import com.example.demo.dao.pojo.Record;
import com.example.demo.emailUtil.EmailTool;
import com.example.demo.timedtask.SendEmailRunnable;
import com.example.demo.uitl.DateTool;
import com.example.demo.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class EmailController {

    @Autowired
    private EmailTool emailTool;
    @Autowired
    private RecordMapper recordMapper;

    private  String fromEmail = "1079463751@qq.com";
    @RequestMapping(value = "/sendEmail",method =  RequestMethod.GET)
    public String startCron() {
        emailTool.sendOneEmail(fromEmail,"1159928224@qq.com","测试","这是测试邮件");
        return "success";
    }

    @GetMapping("/recordtest")
    public Result userList() {
        log.info("### 发送邮件 ###");
        List<Record> dataInfo =   recordMapper.getSendInfo(DateTool.getDayAfter());
        if (dataInfo .size() > 0) {
            for (int i = 0; i <  dataInfo.size(); i++){
                Record item = dataInfo.get(i);
                String msg = "尊敬的" +item.getPayeename() +",您于"
                        + item.getCreatetimeStr() +"向" + item.getUsername()
                        + "借款" + item.getMoney() +"RMB"
                        + "并承诺在" + item.getRepaytimeStr() +"还款，请及时还款。" +
                        "此邮件若有打扰您的生活，十分抱歉" ;
                emailTool.sendOneEmail(fromEmail,item.getPayeeemail(),"BM",msg);
                Record updateInfo = new Record();
                updateInfo.setRecordid(item.getRecordid());
                updateInfo.setStatus("2");
                updateInfo.setRemindtime(new Date());
                recordMapper.updateRcord(updateInfo);
            }
        }

        return Result.SUCCESS();
    }

}
