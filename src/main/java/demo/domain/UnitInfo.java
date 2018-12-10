package demo.domain;


import lombok.Data;

@Data
public class UnitInfo {//手环信息
    private final String runningId;
    private String bandMake;
    private String customerName;
    private String unitNumber;
}
