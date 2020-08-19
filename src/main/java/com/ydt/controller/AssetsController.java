package com.ydt.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.taobao.api.ApiException;
import com.ydt.bean.TAssets;
import com.ydt.bean.TAssetsAdmin;
import com.ydt.bean.TAssetsRecord;
import com.ydt.service.AssetsService;
import com.ydt.service.TAssetsAdminService;
import com.ydt.service.TAssetsRecordService;
import com.ydt.service.TAssetsService;
import com.ydt.util.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Autowired
    private TAssetsService tAssetsService;
    @Autowired
    private TAssetsRecordService tAssetsRecordService;
    @Autowired
    private AssetsService assetsService;
    @Autowired
    private TAssetsAdminService tAssetsAdminService;

    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("login");
        return mv;
    }

    //管理员登录
    @RequestMapping("login")
    public SysResult login(HttpServletRequest request,String username, String password){
        try{
            EntityWrapper<TAssetsAdmin> ew = new EntityWrapper<>();
            ew.eq("username",username).eq("password",password);
            TAssetsAdmin tAssetsAdmin=tAssetsAdminService.selectOne(ew);
            if(tAssetsAdmin!=null){
                request.getSession().setAttribute("username",tAssetsAdmin.getUsername());
                return SysResult.build(0, "成功", 1,tAssetsAdmin);
            }else{
                return SysResult.er();
            }
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.er();
        }
    }

    //插入资产信息
    @RequestMapping("insert")
    public SysResult isnertAssets(TAssets tAssets, TAssetsRecord tAssetsRecord){
        try{
            //EntityWrapper<TAssets> ew = new EntityWrapper<>();
            tAssets.setUsetime(new Date());
            tAssets.setStatus(1.0);
            tAssetsRecord.setUsetime(new Date());
            tAssetsRecord.setStatus(1.0);
            tAssetsService.insert(tAssets);
            tAssetsRecordService.insert(tAssetsRecord);
            return SysResult.build(0, "插入成功", 1,null);
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.er();
        }
    }

    //分页查询资产信息
    @RequestMapping("selectBypage")
    public SysResult selectBypage(int page,int limit){
        try{
            EntityWrapper<TAssets> ew = new EntityWrapper<>();
            //ew.eq("qrcode",qrcode);
            Page<TAssets> p=new Page<>(page,limit);
            Page<TAssets> tAssetsPage = tAssetsService.selectPage(p, ew);
            int num=tAssetsService.selectCount(ew);
            return SysResult.build(0, "查找成功", num,tAssetsPage.getRecords());
        }catch (Exception e){
            return SysResult.er();
        }
    }

    //根据条件分页查询资产信息
    @RequestMapping("selectBypageSearch")
    public SysResult selectBypageSearch(int page,int limit,String qrcode,String username,String purpose,String status,String userdepart){
        try{
            EntityWrapper<TAssets> ew = new EntityWrapper<>();
            //System.out.println("1:"+qrcode+",2:"+username+",3:"+purpose+",4:"+status+",5:"+userdepart);
            if(qrcode!=""){
                ew.eq("qrcode",qrcode);
            }
            if(username!=""){
                ew.like("username",username);
            }
            if(purpose!=""){
                ew.eq("purpose",purpose);
            }
            if(status!=""){
                ew.eq("status",status);
            }
            if(userdepart!=""){
                ew.like("userdepart",userdepart);
            }
            Page<TAssets> p=new Page<>(page,limit);
            Page<TAssets> tAssetsPage = tAssetsService.selectPage(p, ew);
            int num=tAssetsService.selectCount(ew);
            return SysResult.build(0, "查找成功", num,tAssetsPage.getRecords());
        }catch (Exception e){
            return SysResult.er();
        }
    }

    //根据id查询资产使用详细信息
    @PostMapping("/detail")
    public SysResult detail(TAssets tAssets,String id){
        try{
            tAssets=tAssetsService.selectById(id);
            return SysResult.build(0, "查找成功", 1,tAssets);
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.er();
        }
    }

    //根据二维码编号和使用状态查询资产使用详细信息
    @PostMapping("/detail1")
    public SysResult detail1(TAssets tAssets,String qrcode){
        try{
            EntityWrapper<TAssets> ew = new EntityWrapper<>();
            ew.eq("qrcode",qrcode).eq("status",1);
            tAssets=tAssetsService.selectOne(ew);
            return SysResult.build(0, "查找成功", 1,tAssets);
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.er();
        }
    }

    //资产归还
    @PostMapping("return")
    public SysResult returnAssets(TAssets tAssets,TAssetsRecord tAssetsRecord,String qrcode,String username){
        try{
            EntityWrapper<TAssets> ew = new EntityWrapper<>();
            ew.eq("qrcode",qrcode).eq("status",1);
            tAssets.setStatus(0.0);
            tAssetsService.update(tAssets,ew);

            EntityWrapper<TAssetsRecord> ew1 = new EntityWrapper<>();
            ew1.eq("qrcode",qrcode).eq("username",username).eq("status",1);
            tAssetsRecord.setReturntime(new Date());
            tAssetsRecord.setStatus(0.0);
            tAssetsRecordService.update(tAssetsRecord,ew1);
            return SysResult.build(0, "归还成功", 1,null);
        }catch(Exception e){
            return SysResult.er();
        }
    }

    //资产报废
    @PostMapping("scrap")
    public SysResult scrapAssets(TAssets tAssets,String qrcode){
        try{
            EntityWrapper<TAssets> ew = new EntityWrapper<>();
            System.out.println(qrcode);
            ew.eq("qrcode",qrcode);
            tAssets.setStatus(2.0);
            tAssetsService.update(tAssets,ew);
            return SysResult.build(0, "报废成功", 1,null);
        }catch(Exception e){
            return SysResult.er();
        }
    }

    //分页获取资产使用记录
    @RequestMapping("getUserecord")
    public SysResult getUserecord(int page,int limit,String qrcode){
        try{
            System.out.println(qrcode);
            EntityWrapper<TAssetsRecord> ew = new EntityWrapper<>();
            ew.eq("qrcode",qrcode).orderBy("usetime",false);
            Page<TAssetsRecord> p=new Page<>(page,limit);
            Page<TAssetsRecord> tAssetsRecordPage = tAssetsRecordService.selectPage(p, ew);
            int num=tAssetsRecordService.selectCount(ew);
            return SysResult.build(0, "查找成功", num,tAssetsRecordPage.getRecords());
        }catch (Exception e){
            return SysResult.er();
        }
    }

    //获取钉钉信息
    /*@RequestMapping("test/{code}")
    public SysResult test(@PathVariable String code){*/
    @RequestMapping("test")
    public SysResult test(String code){
        try{
            DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey("dinggcvm9sl0qmkzi9pp");
            request.setAppsecret("RwziU9SLk052fmR6DNesmtw6nCDQ0VYn0-J_Gx5I2qmgoE26eCaNBLHm_EbbLx2A");
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken=response.getAccessToken();
            System.out.println(response.getErrmsg());
            System.out.println("accessToken:"+accessToken);

            DingTalkClient client1 = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
            OapiUserGetuserinfoRequest request1 = new OapiUserGetuserinfoRequest();
            System.out.println("code:"+code);
            request1.setCode(code);
            request1.setHttpMethod("GET");
            OapiUserGetuserinfoResponse response1 = client1.execute(request1, accessToken);
            System.out.println(response1.getErrmsg());
            String userId = response1.getUserid();
            System.out.println("userId:"+userId);

            DingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
            OapiUserGetRequest request2 = new OapiUserGetRequest();
            request2.setUserid(userId);
            request2.setHttpMethod("GET");
            OapiUserGetResponse response2 = client2.execute(request2, accessToken);
            System.out.println(response2.getErrmsg());
            System.out.println("response2:"+ response2.getName());
            /*System.out.println(qrcode);
            EntityWrapper<TAssets> ew = new EntityWrapper<>();
            ew.eq("status",1).eq("qrcode",qrcode);
            List<TAssets> list= tAssetsService.selectList(ew);
            System.out.println(list.size());

            EntityWrapper<TAssetsRecord> ew1 = new EntityWrapper<>();
            ew1.eq("userid",userId).eq("qrcode",qrcode);
            List<TAssetsRecord> list1= tAssetsRecordService.selectList(ew1);
            System.out.println(list1.size());*/

            return SysResult.build(0, "查找成功", 1,response2);
        }catch (Exception e){
            return SysResult.er();
        }
    }

    //查询资产是否被借用
    @RequestMapping("assetState")
    public SysResult assetState(String qrcode,String userid){
        try{
            EntityWrapper<TAssets> ew = new EntityWrapper<>();
            ew.eq("status",1).eq("qrcode",qrcode);
            List<TAssets> list= tAssetsService.selectList(ew);
            System.out.println(list.size());

            EntityWrapper<TAssetsRecord> ew1 = new EntityWrapper<>();
            ew1.eq("userid",userid).eq("qrcode",qrcode).eq("status",1);
            List<TAssetsRecord> list1= tAssetsRecordService.selectList(ew1);
            System.out.println(list1.size());
            return SysResult.build(0, "查找成功", list.size(),list1.size());
        }catch(Exception e){
            e.printStackTrace();
            return SysResult.er();
        }
    }


}
