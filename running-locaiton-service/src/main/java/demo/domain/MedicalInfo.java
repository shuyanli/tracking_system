package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

@JsonInclude(JsonInclude.Include.NON_NULL) //只引入非空field
@Data
@Embeddable
public class MedicalInfo {
    //private final String runningId; //todo json问价你有这个项, 但是为什么老师不在这里加runningid?
    private long bfr; //blood flow range, 血流量
    private long fmi; //first medical index, 不知道干嘛的


    //todo reminder: jackson要求如果使用了非default constructor, 需要加你个default显示出来
    public MedicalInfo(){

    }

    public MedicalInfo(long bfr, long fmi){
        this.bfr = bfr;
        this.fmi = fmi;
    }
}
