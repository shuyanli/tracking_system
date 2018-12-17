package demo.model;

import lombok.Data;

@Data
public class MedicalInfo {
    private long medicalInfoId;
    private String bandMake;
    private String medicalInfoClassificatiln;
    private String description;
    private String aidInstruction;
}
