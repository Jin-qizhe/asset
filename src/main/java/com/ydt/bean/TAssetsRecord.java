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
@TableName("T_ASSETS_RECORD")
public class TAssetsRecord extends Model<TAssetsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("ID")
    private String id;
    /**
     * 二维码编号
     */
    @TableField("QRCODE")
    private String qrcode;
    /**
     * 使用人名称
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 领用时间
     */
    @TableField("USETIME")
    private Date usetime;
    /**
     * 归还时间
     */
    @TableField("RETURNTIME")
    private Date returntime;
    /**
     * 对应钉钉用户ID
     */
    @TableField("USERID")
    private String userid;
    /**
     * 0表示在库,1表示已借出
     */
    @TableField("STATUS")
    private Double status;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
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
        return "TAssetsRecord{" +
                ", id=" + id +
                ", qrcode=" + qrcode +
                ", username=" + username +
                ", usetime=" + usetime +
                ", returntime=" + returntime +
                ", userid=" + userid +
                ", status=" + status +
                ", userdepart=" + userdepart +
                ", userarea=" + userarea +
                "}";
    }
}
