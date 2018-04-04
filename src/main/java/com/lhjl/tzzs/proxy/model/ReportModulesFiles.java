package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "report_modules_files")
public class ReportModulesFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文件路径
     */
    @Column(name = "file_url")
    private String fileUrl;

    /**
     * 文件类型
     */
    @Column(name = "file_type")
    private Integer fileType;

    /**
     * 文件标签
     */
    @Column(name = "file_icon")
    private String fileIcon;

    /**
     * 文件大小(KB)
     */
    @Column(name = "file_size")
    private Integer fileSize;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作人
     */
    private String creator;

    /**
     * 作者/发布机构名称
     */
    private String author;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    private Integer sort;

    /**
     * 相关文章模块ID
     */
    @Column(name = "report_modules_id")
    private Integer reportModulesId;

    @Transient
    private List<ReportModulesFilesLabels> reportModulesFilesLabels;

    public List<ReportModulesFilesLabels> getReportModulesFilesLabels() {
        return reportModulesFilesLabels;
    }

    public void setReportModulesFilesLabels(List<ReportModulesFilesLabels> reportModulesFilesLabels) {
        this.reportModulesFilesLabels = reportModulesFilesLabels;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文件路径
     *
     * @return file_url - 文件路径
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置文件路径
     *
     * @param fileUrl 文件路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * 获取文件类型
     *
     * @return file_type - 文件类型
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型
     *
     * @param fileType 文件类型
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取文件标签
     *
     * @return file_icon - 文件标签
     */
    public String getFileIcon() {
        return fileIcon;
    }

    /**
     * 设置文件标签
     *
     * @param fileIcon 文件标签
     */
    public void setFileIcon(String fileIcon) {
        this.fileIcon = fileIcon;
    }

    /**
     * 获取文件大小(KB)
     *
     * @return file_size - 文件大小(KB)
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小(KB)
     *
     * @param fileSize 文件大小(KB)
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
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
     * 获取操作人
     *
     * @return creator - 操作人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置操作人
     *
     * @param creator 操作人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取作者/发布机构名称
     *
     * @return author - 作者/发布机构名称
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者/发布机构名称
     *
     * @param author 作者/发布机构名称
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取摘要
     *
     * @return digest - 摘要
     */
    public String getDigest() {
        return digest;
    }

    /**
     * 设置摘要
     *
     * @param digest 摘要
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 获取发布时间
     *
     * @return release_time - 发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置发布时间
     *
     * @param releaseTime 发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取相关文章模块ID
     *
     * @return report_modules_id - 相关文章模块ID
     */
    public Integer getReportModulesId() {
        return reportModulesId;
    }

    /**
     * 设置相关文章模块ID
     *
     * @param reportModulesId 相关文章模块ID
     */
    public void setReportModulesId(Integer reportModulesId) {
        this.reportModulesId = reportModulesId;
    }
}