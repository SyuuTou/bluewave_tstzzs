package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "datas_operation_manage")
public class DatasOperationManage {
    /**
     * 数据ID
     * 投资人id或者是项目id等等其他领域模型的id
     */
    @Id
    @Column(name = "data_id")
    private Integer dataId;

    /**
     * 数据类型表：关联基础数据类型元数据的key
     */
    @Id
    @Column(name = "data_type") 
    private String dataType;  

    /**
     * 基础推荐值
     */ 
    @Column(name = "basics_recommend")
    private Integer basicsRecommend;

    /**
     * 运营推荐值
     */
    @Column(name = "operation_recommend")
    private Integer operationRecommend;

    /**
     * 动态推荐值
     */
    @Column(name = "dynamic_recommand")
    private Integer dynamicRecommand;

    /**
     * 合作关系
     */
    @Column(name = "cooperative_relationship")
    private String cooperativeRelationship;

    /**
     * 客户等级
     */
    @Column(name = "customer_grade")
    private String customerGrade;

    /**
     * 来源类型
     */
    @Column(name = "source_type")
    private String sourceType;

    /**
     * IR负责人
     * 此处的负责人存储的是负责人的姓名
     */
    @Column(name = "ir_principal")
    private String irPrincipal;

    /**
     * 所在群号
     */
    @Column(name = "wechat_group_id")
    private String wechatGroupId;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 是否隐藏。0：隐藏；1:不隐藏
     */
    @Column(name = "is_hide")
    private Integer isHide;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 行业指数类型：0:50指数,1：非50行业指数,2：其他
     */
    @Column(name = "index_type")
    private Integer indexType;
    /**
     * 总推荐值
     */
    @Transient
    private Integer recommand;
    /**
     * 主体类型
     * 1 项目
     * 2 机构
     */
    @Transient
    private Integer subjectType;

    /**
     * 获取数据ID
     *
     * @return data_id - 数据ID
     */
    public Integer getDataId() {
        return dataId;
    }

    /**
     * 设置数据ID
     *
     * @param dataId 数据ID
     */
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    /**
     * 获取数据类型表：关联基础数据类型元数据的key
     *
     * @return data_type - 数据类型表：关联基础数据类型元数据的key
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型表：关联基础数据类型元数据的key
     *
     * @param dataType 数据类型表：关联基础数据类型元数据的key
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取基础推荐值
     *
     * @return basics_recommend - 基础推荐值
     */
    public Integer getBasicsRecommend() {
        return basicsRecommend;
    }

    /**
     * 设置基础推荐值
     *
     * @param basicsRecommend 基础推荐值
     */
    public void setBasicsRecommend(Integer basicsRecommend) {
        this.basicsRecommend = basicsRecommend;
    }

    /**
     * 获取运营推荐值
     *
     * @return operation_recommend - 运营推荐值
     */
    public Integer getOperationRecommend() {
        return operationRecommend;
    }

    /**
     * 设置运营推荐值
     *
     * @param operationRecommend 运营推荐值
     */
    public void setOperationRecommend(Integer operationRecommend) {
        this.operationRecommend = operationRecommend;
    }

    /**
     * 获取动态推荐值
     *
     * @return dynamic_recommand - 动态推荐值
     */
    public Integer getDynamicRecommand() {
        return dynamicRecommand;
    }

    /**
     * 设置动态推荐值
     *
     * @param dynamicRecommand 动态推荐值
     */
    public void setDynamicRecommand(Integer dynamicRecommand) {
        this.dynamicRecommand = dynamicRecommand;
    }

    /**
     * 获取合作关系
     *
     * @return cooperative_relationship - 合作关系
     */
    public String getCooperativeRelationship() {
        return cooperativeRelationship;
    }

    /**
     * 设置合作关系
     *
     * @param cooperativeRelationship 合作关系
     */
    public void setCooperativeRelationship(String cooperativeRelationship) {
        this.cooperativeRelationship = cooperativeRelationship;
    }

    /**
     * 获取客户等级
     *
     * @return customer_grade - 客户等级
     */
    public String getCustomerGrade() {
        return customerGrade;
    }

    /**
     * 设置客户等级
     *
     * @param customerGrade 客户等级
     */
    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }

    /**
     * 获取来源类型
     *
     * @return source_type - 来源类型
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 设置来源类型
     *
     * @param sourceType 来源类型
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 获取IR负责人
     *
     * @return ir_principal - IR负责人
     */
    public String getIrPrincipal() {
        return irPrincipal;
    }

    /**
     * 设置IR负责人
     *
     * @param irPrincipal IR负责人
     */
    public void setIrPrincipal(String irPrincipal) {
        this.irPrincipal = irPrincipal;
    }

    /**
     * 获取所在群号
     *
     * @return wechat_group_id - 所在群号
     */
    public String getWechatGroupId() {
        return wechatGroupId;
    }

    /**
     * 设置所在群号
     *
     * @param wechatGroupId 所在群号
     */
    public void setWechatGroupId(String wechatGroupId) {
        this.wechatGroupId = wechatGroupId;
    }

    /**
     * 获取备注信息
     *
     * @return note - 备注信息
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注信息
     *
     * @param note 备注信息
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取是否隐藏。0：隐藏；1:不隐藏
     *
     * @return is_hide - 是否隐藏。0：隐藏；1:不隐藏
     */
    public Integer getIsHide() {
        return isHide;
    }

    /**
     * 设置是否隐藏。0：隐藏；1:不隐藏
     *
     * @param isHide 是否隐藏。0：隐藏；1:不隐藏
     */
    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取行业指数类型：0:50指数,1：非50行业指数,2：其他
     *
     * @return index_type - 行业指数类型：0:50指数,1：非50行业指数,2：其他
     */
    public Integer getIndexType() {
        return indexType;
    }

    /**
     * 设置行业指数类型：0:50指数,1：非50行业指数,2：其他
     *
     * @param indexType 行业指数类型：0:50指数,1：非50行业指数,2：其他
     */
    public void setIndexType(Integer indexType) {
        this.indexType = indexType;
    }

	public Integer getRecommand() {
		return recommand;
	}

	public void setRecommand(Integer recommand) {
		this.recommand = recommand;
	}

	public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	@Override
	public String toString() {
		return "DatasOperationManage [dataId=" + dataId + ", dataType=" + dataType + ", basicsRecommend="
				+ basicsRecommend + ", operationRecommend=" + operationRecommend + ", dynamicRecommand="
				+ dynamicRecommand + ", cooperativeRelationship=" + cooperativeRelationship + ", customerGrade="
				+ customerGrade + ", sourceType=" + sourceType + ", irPrincipal=" + irPrincipal + ", wechatGroupId="
				+ wechatGroupId + ", note=" + note + ", isHide=" + isHide + ", creator=" + creator + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", indexType=" + indexType + ", recommand=" + recommand
				+ ", subjectType=" + subjectType + "]";
	}

    
}