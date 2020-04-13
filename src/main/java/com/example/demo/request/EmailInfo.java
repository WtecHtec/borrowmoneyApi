package com.example.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailInfo {

   private String fromEmail; // 发件邮箱
    private String toEmail; //  收件邮箱
    private String title;  // 标题
    private String context; //内容


}
