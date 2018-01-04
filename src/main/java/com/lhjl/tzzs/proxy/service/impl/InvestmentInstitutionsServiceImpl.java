package com.lhjl.tzzs.proxy.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ImageHandlerDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionSearchOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsAddressMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsStageMapper;
import com.lhjl.tzzs.proxy.mapper.MetaProjectStageMapper;
import com.lhjl.tzzs.proxy.mapper.MetaSegmentationMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddress;
import com.lhjl.tzzs.proxy.model.MetaProjectStage;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import me.chanjar.weixin.common.exception.WxErrorException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

@Service
public class InvestmentInstitutionsServiceImpl implements InvestmentInstitutionsService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentInstitutionsServiceImpl.class);

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private InvestmentInstitutionsSegmentationMapper investmentInstitutionsSegmentationMapper;

    @Autowired
    private InvestmentInstitutionsStageMapper investmentInstitutionsStageMapper;

    @Autowired
    private InvestmentInstitutionsAddressMapper investmentInstitutionsAddressMapper;
    
    @Autowired
    private WxMaService qrcodeService;
    
    @Autowired
    private MetaProjectStageMapper metaProjectStageMapper;
    
    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;

    @Override
    public CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComlexInfo(Map<String,Integer> body){
        CommonDto<InvestmentInstitutionComplexOutputDto> result =  new CommonDto<>();

        if (body.get("investorInstitutionId") == null){
            result.setStatus(50001);
            result.setMessage("机构id不能为空");
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("机构id不能为空");

            return result;
        }

        InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(body.get("investorInstitutionId"));
        if (investmentInstitutions == null){
            result.setMessage("当前机构id没有找到对应的机构");
            result.setStatus(50001);
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("当前机构id没有找到对应的机构");

            return result;
        }else {
            InvestmentInstitutionComplexOutputDto investmentInstitutionComplexOutputDto = new InvestmentInstitutionComplexOutputDto();
            if (StringUtils.isEmpty(investmentInstitutions.getKenelCase())){

                investmentInstitutionComplexOutputDto.setInvestmentInstitutionDesc(investmentInstitutions.getComment());
            }else {
                investmentInstitutionComplexOutputDto.setInvestmentInstitutionDesc(investmentInstitutions.getKenelCase());
            }
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionLogo(investmentInstitutions.getLogo());
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionName(investmentInstitutions.getShortName());

            result.setData(investmentInstitutionComplexOutputDto);
            result.setMessage("success");
            result.setStatus(200);

        }



        return result;
    }

    /**
     * 根据输入词搜索机构信息接口
     * @param inputsWords 输入的词
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionSearchOutputDto>> getInstitutionIntelligentSearch(String inputsWords,Integer pageSize){
        CommonDto<List<InvestmentInstitutionSearchOutputDto>> result = new CommonDto<>();
        List<InvestmentInstitutionSearchOutputDto> list = new ArrayList<>();

        if (inputsWords == null || "undefined".equals(inputsWords)){
           inputsWords = "";
        }
        if (pageSize == null || pageSize <= 0){
            pageSize = 5;
        }


        List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.findeInvestmentByShortName(inputsWords,0,pageSize);
        if (investmentInstitutionsList.size() > 0){
            for(InvestmentInstitutions ii:investmentInstitutionsList){
                InvestmentInstitutionSearchOutputDto investmentInstitutionSearchOutputDto = new InvestmentInstitutionSearchOutputDto();
                investmentInstitutionSearchOutputDto.setInstitutionFullName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionShortName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionId(ii.getId());

                list.add(investmentInstitutionSearchOutputDto);
            }

            result.setData(list);
            result.setMessage("success");
            result.setStatus(200);
        }else {
            result.setStatus(200);
            result.setMessage("success");
            result.setData(list);
        }

        return result;
    }

    /**
     * 获取机构详情的接口
     * @param institutionId
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> findInstitutionDetails(Integer institutionId){
        CommonDto<Map<String,Object>> result  = new CommonDto<>();

        if (institutionId == null){
            result.setMessage("机构id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        Map<String,Object> map = new HashMap<>();

        //先获取机构简介
        InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(institutionId);
        if (investmentInstitutions == null){
            result.setMessage("输入的机构id不存在，请检查");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        //获取机构的关注领域
        List<Map<String,Object>> segmentList = new ArrayList<>();
        segmentList = investmentInstitutionsSegmentationMapper.findSegment(institutionId);
        for (Map<String,Object> m:segmentList){
            if (m.get("name") == null ){
                m.put("name","");
            }

            if (m.get("segmentation_logo") == null){
                m.put("segmentation_logo","http://img.idatavc.com/static/seg/jiaoyu.png");
            }
        }

        //获取机构的关注阶段
        List<Map<String,Object>> stageList = new ArrayList<>();
        stageList = investmentInstitutionsStageMapper.findInstitutionStage(institutionId);

        //获取机构的城市、地址、邮箱、电话
        Example iiaExample = new Example(InvestmentInstitutionsAddress.class);
        iiaExample.and().andEqualTo("investmentInstitutionId",institutionId);

        List<Map<String,Object>> listiia = new ArrayList<>();
        List<InvestmentInstitutionsAddress> investmentInstitutionsAddressList = investmentInstitutionsAddressMapper.selectByExample(iiaExample);
        if (investmentInstitutionsAddressList.size() > 0){
            for (InvestmentInstitutionsAddress iia:investmentInstitutionsAddressList){
                Map<String,Object> mapForY = new HashMap<>();
                if (iia.getTown() == null){
                    mapForY.put("town","");
                }else {
                    mapForY.put("town",iia.getTown());
                }

                if (iia.getDetailAddress() == null){
                    mapForY.put("detailAddress","");
                }else {
                    mapForY.put("detailAddress",iia.getDetailAddress());
                }

                if (iia.getEmail() == null){
                    mapForY.put("email","");
                }else {
                    mapForY.put("email",iia.getEmail());
                }

                if (iia.getPhoneCountryCode() == null && iia.getPhoneDistrictCode() == null && iia.getPhoneNumber() ==null){
                    mapForY.put("phoneNumber","");
                }else {
                    String phonenumber = "";
                    if (iia.getPhoneCountryCode() != null){
                        phonenumber = phonenumber + iia.getPhoneCountryCode() + "-";
                    }
                    if (iia.getPhoneDistrictCode() != null){
                        phonenumber = phonenumber + iia.getPhoneDistrictCode() + "-";
                    }
                    if (iia.getPhoneNumber() != null){
                        phonenumber = phonenumber + iia.getPhoneNumber();
                    }

                    mapForY.put("phoneNumber",phonenumber);
                }

                listiia.add(mapForY);
            }
        }

        //组装返回数据
        if (investmentInstitutions.getKenelCase() == null){
            investmentInstitutions.setKenelCase("");
        }
        if (StringUtils.isEmpty(investmentInstitutions.getKenelCase())){

            map.put("institutionDesc",investmentInstitutions.getComment());
        }else {
            map.put("institutionDesc", investmentInstitutions.getKenelCase());
        }
        map.put("institutionSegmentation",segmentList);
        map.put("institutionStage",stageList);
        map.put("address",listiia);
        map.put("institutionLogo",investmentInstitutions.getLogo());
        map.put("institutionName",investmentInstitutions.getShortName());

        result.setStatus(200);
        result.setData(map);
        result.setMessage("success");

        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> findFliterInfo(Integer institutionId){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        Map<String,Object> map = new HashMap<>();
        //获取机构的关注领域
        List<Map<String,Object>> segmentList = new ArrayList<>();
        segmentList = investmentInstitutionsSegmentationMapper.findSegment(institutionId);
        for (Map<String,Object> m:segmentList){
            if (m.get("name") == null ){
                m.put("name","");
            }

            if (m.get("segmentation_logo") == null){
                m.put("segmentation_logo","http://img.idatavc.com/static/seg/jiaoyu.png");
            }
        }

        List<Map<String,Object>> yearList = new ArrayList<>();
        yearList = investmentInstitutionsSegmentationMapper.findYear(institutionId);
        for (int i = 0;i < yearList.size();i++){

            yearList.get(i).put("ydate",yearList.get(i).get("financing_time_year"));

        }

        map.put("segmentation",segmentList);
        map.put("years",yearList);

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Transactional
    @Override
    public InputStream imageHandler(ImageHandlerDto reqDto) {

        InvestmentInstitutions query = new InvestmentInstitutions();
        query.setShortName(reqDto.getName());
        List<InvestmentInstitutions> investmentInstitutions = investmentInstitutionsMapper.select(query);
        if (null == investmentInstitutions || investmentInstitutions.size() == 0){
            return null;
        }
        InvestmentInstitutions ii = investmentInstitutions.get(0);
        if (null == ii.getKeyWords() || ii.getKeyWords().equals("")){
            try {
                String keyWords = MD5Util.md5Encode(PinyinHelper.convertToPinyinString(reqDto.getName(), "", PinyinFormat.WITHOUT_TONE).toUpperCase(),"").substring(12,32);
                ii.setKeyWords(keyWords);
                investmentInstitutionsMapper.updateByPrimaryKey(ii);
            } catch (PinyinException e) {
                log.error(e.getLocalizedMessage(),e.fillInStackTrace());
            }
        }


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {

            File img = qrcodeService.getQrcodeService().createWxCode(reqDto.getPath()+"&jg="+ii.getKeyWords(),reqDto.getW());
//
//            File img =  qrcodeService.getQrcodeService().createWxCode().createWxCodeLimit(ii.getKeyWords()+"_"+reqDto.getActivityId(),reqDto.getPath(),reqDto.getW(),true,null);
            BufferedImage qcode = ImageIO.read(img);
            Thumbnails.of(new URL(reqDto.getTemplateUrl()).openStream()).size(750,7452).watermark(new Position() {
                @Override
                public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
                    return new Point(reqDto.getX(),reqDto.getY());
                }
            }, qcode, 1.0f).toOutputStream(os);
//            Thumbnails.of(new File("/Users/zhhu/Downloads/1.jpg")).size(750,1334).watermark(Positions.CENTER, qcode,1.0f).toFile("/Users/zhhu/Downloads/b.jpg");
//            ImageIO.write(scaledImage, "jpg", os);
//            ImageIO.write()
//            ImageUtils.pressImage(new URL(reqDto.getTemplateUrl()),img,os,750,7452,1f);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(),e.fillInStackTrace());
        } catch (WxErrorException e) {
            log.error(e.getLocalizedMessage(),e.fillInStackTrace());
        } catch (Exception e){
            log.error(e.getLocalizedMessage(),e.fillInStackTrace());
        }

        return new ByteArrayInputStream(os.toByteArray());
    }

	@Override
	public CommonDto<List<MetaProjectStage>> listInvestementStages() {
		CommonDto<List<MetaProjectStage>> result= new CommonDto<>();
		
		List<MetaProjectStage> stages = metaProjectStageMapper.findAll();
		result.setData(stages);
		result.setMessage("success");
		result.setStatus(200);
		
		return result;
	}

	@Override
	public CommonDto<List<MetaSegmentation>> listInvestementFields() {
		CommonDto<List<MetaSegmentation>> result=new CommonDto<>();
		
		List<MetaSegmentation> segs = metaSegmentationMapper.findAll();
		 result.setData(segs);
		 result.setMessage("success");
		 result.setStatus(200);
		 
		return result;
	}
}
