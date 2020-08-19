package com.ydt.bean;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jqz123
 * @since 2020-08-18
 */
@TableName("T_ASSETS")
public class TAssets extends Model<TAssets> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
    private String id;
    /**
     * 二维码编号
     */
    @TableField("QRCODE")
    private String qrcode;
    /**
     * 创建时间
     */
    @TableField("USETIME")
    private Date usetime;
    /**
     * 品牌
     */
    @TableField("BRAND")
    private String brand;
    /**
     * cpu
     */
    @TableField("CPU")
    private String cpu;
    /**
     * 内存
     */
    @TableField("RAM")
    private String ram;
    /**
     * 0表示内网,1表示外网,2表示视频专用
     */
    @TableField("PURPOSE")
    private Double purpose;
    /**
     * 0表示在库,1表示已借出,2表示报废
     */
    @TableField("STATUS")
    private Double status;
    /**
     * 使用人
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 使用部门:亿点通:0-总经办、1-综合部、2-市场部、3-移动开发部、4-情指项目部、5-金华项目部、6-治安项目部、7-产品部、8-设计部;计算所：9-市场部、10-集成技术部、11-软件开发部
     */
    @TableField("USERDEPART")
    private Double userdepart;
    /**
     * 使用区域:0-公司、1-市局、2-市政府、3-省厅、4-义乌、5-盐城
     */
    @TableField("USERAREA")
    private Double userarea;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Double getPurpose() {
        return purpose;
    }

    public void setPurpose(Double purpose) {
        this.purpose = purpose;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getUserdepart() {
        return userdepart;
    }

    public void setUserdepart(Double userdepart) {
        this.userdepart = userdepart;
    }

    public Double getUserarea() {
        return userarea;
    }

    public void setUserarea(Double userarea) {
        this.userarea = userarea;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TAssets{" +
                ", id=" + id +
                ", qrcode=" + qrcode +
                ", usetime=" + usetime +
                ", brand=" + brand +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", purpose=" + purpose +
                ", status=" + status +
                ", username=" + username +
                ", userdepart=" + userdepart +
                ", userarea=" + userarea +
                "}";
    }
}
