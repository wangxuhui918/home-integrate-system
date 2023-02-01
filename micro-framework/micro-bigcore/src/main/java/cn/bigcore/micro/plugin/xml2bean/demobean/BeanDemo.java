package cn.bigcore.micro.plugin.xml2bean.demobean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


@Data
public class BeanDemo {

    @JSONField(name = "INHOSP_INDEX_NO")  // 住院索引号
    private String inpVisitId;

    @JSONField(name = "MR_NO")  // 病案号
    private String mrNo;

    @JSONField(name = "ANAMNESIS_NO")  // 病历号
    private String anamnesisNo;

    @JSONField(name = "INHOSP_NUM")  // 住院次数
    private String inhospNum;

    @JSONField(name = "INHOSP_NO")  // 住院流水号
    private String inHosNo;

    @JSONField(name = "ELECTR_REQUISITION_NO")  // 电子申请单编号
    private String requisitionNo;

    @JSONField(name = "PAT_INDEX_NO")  // 患者索引号
    private String patId;

    @JSONField(name = "REQUISITION_NAME")  // 申请单名称
    private String requisitionName;

    @JSONField(name = "BLOOD_PAT_SOURCE_CODE")  // 输血患者来源代码
    private String bloodPatResourceCode;

    @JSONField(name = "BLOOD_PAT_SOURCE_NAME")  // 输血患者来源名称
    private String bloodPatResourceName;

    @JSONField(name = "ABO_TYPE_CODE")  // 患者ABO血型代码
    private String aboTypeCode;

    @JSONField(name = "ABO_TYPE_NAME")  // 患者ABO血型名称
    private String aboTypeName;

    @JSONField(name = "RH_TYPE_CODE")  // 患者RH血型代码
    private String rhTypeCode;

    @JSONField(name = "RH_TYPE_NAME")  // 患者RH血型名称
    private String rhTypeName;

    @JSONField(name = "APPLY_DATETIME")  // 申请日期
    private String applyDate;//YYYY-MM-DD HH24:MI:SS

    @JSONField(name = "REQUISITION_STATUS")  // 申请单状态
    private String requisitionStatus;//1开立

    @JSONField(name = "BLOOD_AIM")  // 输血目的
    private String bloodAim;

    @JSONField(name = "RESERVE_BLOOD_DATETIME")  // 预约输血日期
    private String reserveBloodDate;//YYYY-MM-DD HH24:MI:SS

    @JSONField(name = "BLOOD_DATETIME")  // 输血日期
    private String bloodDate;

    @JSONField(name = "TOTAL_MONEY")  // 总金额
    private String totalMoney;

    @JSONField(name = "CHARGE_FLAG")  // 收费标志
    private String chargeFlag;//0否1是

    @JSONField(name = "NOTE")  // 备注
    private String note;

    @JSONField(name = "PRIORITY_FLAG")  // 优先级标志
    private String priorityFlag;

    @JSONField(name = "INVALID_FLAG")  // 作废标志
    private String invalidFlag;//0-有效，1-作废

    @JSONField(name = "APPLY_MI_CODE")  // 申请医疗机构代码
    private String applyMiCode;

    @JSONField(name = "APPLY_MI_NAME")  // 申请医疗机构名称
    private String applyMiName;

    @JSONField(name = "APPLY_CAMPUS_CODE")  // 申请院区代码
    private String applyCampusCode;

    @JSONField(name = "APPLY_CAMPUS_NAME")  // 申请院区名称
    private String applyCampusName;

    @JSONField(name = "APPLY_DR_INDEX_NO")  // 申请医生索引号
    private String applyDrIndexNo;

    @JSONField(name = "APPLY_DR_CODE")  // 申请医生工号
    private String applyDrCode;

    @JSONField(name = "APPLY_DR_NAME")  // 申请医生姓名
    private String applyDrName;

    @JSONField(name = "EXECUT_DR_INDEX_NO")  // 执行医生索引号
    private String executDrIndexNo;

    @JSONField(name = "EXECUT_DR_CODE")  // 执行医生工号
    private String executeDrCode;

    @JSONField(name = "EXECUT_DR_NAME")  // 执行医生姓名
    private String executeDrName;

    @JSONField(name = "APPLY_DEPT_INDEX_NO")  // 申请科室索引号
    private String applyDeptIndexNo;

    @JSONField(name = "APPLY_DEPT_CODE")  // 申请科室代码
    private String applyDeptCode;

    @JSONField(name = "APPLY_DEPT_NAME")  // 申请科室名称
    private String applyDeptName;

    @JSONField(name = "EXECUT_DEPT_INDEX_NO")  // 执行科室索引号
    private String executDeptIndexNo;

    @JSONField(name = "EXECUT_DEPT_CODE")  // 执行科室代码
    private String executeDeptCode;

    @JSONField(name = "EXECUT_DEPT_NAME")  // 执行科室名称
    private String executeDeptName;

    @JSONField(name = "DIAG_CODE")  // 诊断代码
    private String diagCode;

    @JSONField(name = "DIAG_NAME")  // 诊断名称
    private String diagName;

    @JSONField(name = "ID_NUMBER")  // 身份证号码
    private String idNumber;

    @JSONField(name = "PAT_NAME")  // 患者姓名
    private String patName;

    @JSONField(name = "PHYSI_SEX_CODE")  // 生理性别代码
    private String physSexCode;

    @JSONField(name = "PHYSI_SEX_NAME")  // 生理性别名称
    private String physSexName;

    @JSONField(name = "BIRTH_DATE")  // 出生日期
    private String birthDate;

    @JSONField(name = "BIRTH_TIME")  // 出生时间
    private String birthTime;

    @JSONField(name = "AGE")  // 年龄
    private String age;

    @JSONField(name = "ETHNIC_CODE")  // 民族代码
    private String ethnicCode;

    @JSONField(name = "ETHNIC_NAME")  // 民族名称
    private String ethnicName;

    @JSONField(name = "MARITAL_STATUS_CODE")  // 婚姻状况代码
    private String maritalStatusCode;

    @JSONField(name = "MARITAL_STATUS_NAME")  // 婚姻状况名称
    private String maritalStatusName;

    @JSONField(name = "RECORD_DATETIME")  // 录入日期时间
    private String recordDatetime;

    @JSONField(name = "UPDATE_DATETIME")  // 更新日期时间
    private String updateDatetime;

    @JSONField(name = "CURR_DEPT_CODE")  // 当前科室代码
    private String currDeptCode;

    @JSONField(name = "CURR_DEPT_NAME")  // 当前科室名称
    private String currDeptName;

    @JSONField(name = "CURR_WARD_CODE")  // 当前病区代码
    private String currWardCode;

    @JSONField(name = "CURR_WARD_NAME")  // 当前病区名称
    private String currWardName;

    @JSONField(name = "CURR_SICKROOM_CODE")  // 当前病房代码
    private String currSickroomCode;

    @JSONField(name = "CURR_SICKROOM_NAME")  // 当前病房名称
    private String currSickroomName;

    @JSONField(name = "CURR_BED_CODE")  // 当前病床代码
    private String currBedCode;

    @JSONField(name = "CURR_BED_NAME")  // 当前病床名称
    private String currBedName;
    //    -----------------以上字段为滨医和海南共有字段----------------------
//    -----------------海南人民新增以下字段start---------------------
    @JSONField(name = "USE_BLOOD_MODEL")  // 用血模式
    private String useBloodModel;

    @JSONField(name = "INFORMED_CONSENT_NO")  // 知情同意书编号
    private String informedConsentNo;

    @JSONField(name = "MEDICARE_FLAG")  // 医保标志
    private String medicareFlag;

    @JSONField(name = "METACHYSIS_HISTORY")  // 输血史
    private String metachysisHistory;

    @JSONField(name = "ADVERSE_REACTION_HISTORY")  // 不良反应史
    private String adverseReactionHistory;

    @JSONField(name = "GESTATION_HISTORY")  // 妊娠史
    private String gestationHistory;

    @JSONField(name = "PREGNANCY_NUM")  // 孕次
    private String pregnancyNum;

    @JSONField(name = "CHILDBIRTH_NUM")  // 产次
    private String childbirthNum;

    @JSONField(name = "BLOOD_TRANSFER_NUM")  // 输血次数
    private String bloodTransferNum;

    @JSONField(name = "PHONE_NO")  // 联系电话
    private String phoneNo;

    @JSONField(name = "CURR_ADDR")  // 现住地址
    private String currAddr;

    @JSONField(name = "ANTIBODY_SCREENING")  // 抗体筛查
    private String antibodyScreening;

    @JSONField(name = "IF_SAMLPING")  // 本申请是否抽血
    private String ifSamlping;

    @JSONField(name = "SUPERIOR_PROOF_DR_CODE")  // 上级审核职工工号
    private String ifsamsuperiorProofDrCodelping;

    @JSONField(name = "SUPERIOR_PROOF_DR_NAME")  // 上级审核职工姓名
    private String superiorProofDrName;

    @JSONField(name = "SUPERIOR_PROOF_DATETIME")  // 上级审核日期时间
    private String superiorProofDatetime;

    @JSONField(name = "CHIEF_PROOF_DR_CODE")  // 主任审核职工工号
    private String chiefProofDrCode;

    @JSONField(name = "CHIEF_PROOF_DR_NAME")  // 主任审核职工姓名
    private String chiefProofDrName;

    @JSONField(name = "CHIEF_PROOF_DATETIME")  // 主任审核日期时间
    private String chiefProofDatetime;

    @JSONField(name = "SURGERY_LEVEL_NAME")  // 手术级别名称
    private String surgeryLevelName;

    @JSONField(name = "SURGERY_LEVEL_CODE")  // 手术级别代码
    private String surgeryLevelCode;


    @JSONField(name = "DETAILS")
    private BeanDetailTwo details;

    @JSONField(name = "TEST_DETAILS")
    private BeanDetailThrid testDetails;

}
