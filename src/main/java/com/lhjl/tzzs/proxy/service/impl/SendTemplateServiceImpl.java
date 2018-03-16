package com.lhjl.tzzs.proxy.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.mapper.UsersWeixinMapper;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.model.UsersWeixin;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.SendTemplateService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendTemplateServiceImpl extends GenericService implements SendTemplateService {

    @Autowired
    private WxMaService wxService;

    @Autowired
    private UsersWeixinMapper usersWeixinMapper;

    @Autowired
    private UsersMapper usersMapper;


    /**
     * 发模板消息的接口
     * @param userId 用户id
     * @param status 1表示失败时候的发送模板,2表示成功后的模板
     * @param formId 用户formid
     * @param message 发送消息内容
     * @param title 消息标题
     * @return
     */
    @Override
    public CommonDto<String> sendTemplateByFormId(Integer userId, Integer status, String formId, String message ,String title) {

        CommonDto<String> result = new CommonDto<>();
        UsersWeixin userswx = new UsersWeixin();
        userswx.setUserId(userId);

        String openId = "";
        List<UsersWeixin> usersWeixins = usersWeixinMapper.select(userswx);
        if (usersWeixins.size() > 0){
            openId = usersWeixins.get(0).getOpenid();
        }else {
            result.setData(null);
            result.setMessage("没有找到用户的openId信息");
            result.setStatus(502);

            return result;
        }


        Users users = usersMapper.selectByPrimaryKey(userId);
        String desc = users.getActualName() +" " + users.getCompanyName() + " " + users.getCompanyDuties();


        try {
            List<WxMaTemplateMessage.Data> datas = new ArrayList<>();
            try {
                if (status == 1) {//失败后发的消息
                    datas.add(new WxMaTemplateMessage.Data("keyword1",title));
                    this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder().templateId("RcjdkVcWR9K3Jmfz2HVbMKKLoVHhXEJkpz42Lgr6t6E").formId(formId).toUser(openId).data(datas).build());
                }

                if (status == 2 ) { //成功后发的消息
                    datas.add(new WxMaTemplateMessage.Data("keyword1",title));
                    datas.add(new WxMaTemplateMessage.Data("keyword2",message,"#EA4343"));
                    datas.add(new WxMaTemplateMessage.Data("keyword3",desc));
                    this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder().templateId("IQL59_p78hezrN9Oz6UAStwSyFk8ZbLgVPaPqEi1KyA").formId(formId).toUser(openId).data(datas).page("pages/boot/boot").build());
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
                result.setStatus(502);
                result.setMessage("该formid已经被使用，或者无效");
                result.setData(null);

                return result;
            }
            result.setStatus(200);
            result.setMessage("发送成功");
            result.setData(null);

        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e);
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);

        }
        return result;
    }
}
