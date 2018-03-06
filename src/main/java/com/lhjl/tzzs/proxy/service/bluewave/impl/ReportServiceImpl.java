package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.github.pagehelper.PageRowBounds;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.CommonTotal;
import com.lhjl.tzzs.proxy.dto.EventDto;
import com.lhjl.tzzs.proxy.dto.ProInfoDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.*;
import me.chanjar.weixin.common.exception.WxErrorException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

@Service
public class ReportServiceImpl extends GenericService implements ReportService {


    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private ReportColumnService columnService;
    @Autowired
    private ReportSegmentationService segmentationService;
    @Autowired
    private ReportLabelService labelService;
    @Resource
    private UserLoginService userLoginService;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ReportColumnMapper reportColumnMapper;
    @Autowired
    private ReportSegmentationMapper reportSegmentationMapper;
    @Autowired
    private ReportLabelMapper reportLabelMapper;
    
    @Autowired
    private MetaColumnMapper metaColumnMapper;
    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;
    @Autowired
    private ReportCompanyLabelMapper reportCompanyLabelMapper;
    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ReportInstitutionRelationMapper reportInstitutionRelationMapper;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Value("${event.trigger.url}")
    private String eventUrl;

    @Value("${report.template.path}")
    private String templatePath;


    @Transactional(readOnly = true)
    @Override
    public CommonDto<List<Map<String,Object>>> queryReport(Integer appId, ReportReqBody reqBody) {
    	
        CommonTotal<List<Map<String,Object>>> result = new CommonTotal<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Report report = new Report();
        report.setComments(reqBody.getComments());
        report.setContent(reqBody.getContent());
        report.setCoverUrl(reqBody.getCoverUrl());
        report.setCreater(reqBody.getCreater());
        report.setFromRul(reqBody.getFromRul());
        report.setId(reqBody.getId());
        report.setSourceTextUrl(reqBody.getSourceTextUrl());
        report.setStatus(reqBody.getStatus());
        report.setSubTitle(reqBody.getSubTitle());
        report.setTitle(reqBody.getTitle());
        report.setWeightingFactor(reqBody.getWeightingFactor());
        report.setAuthor(reqBody.getAuthor());
        report.setYn(reqBody.getYn());
        report.setStatus(reqBody.getStatus());
//        System.err.println(report+"*****report");
        
        int offset = (reqBody.getPageNo() - 1) * reqBody.getPageSize();
        int limit = reqBody.getPageSize();
        List<Report>  list = null;
        PageRowBounds rowBounds = new PageRowBounds(offset, limit);
        if (reqBody.getColumnId()!=null) {
        	list = new ArrayList<Report>();
        	list = reportMapper.selectReport(reqBody.getInvestmentInstitutionId(),reqBody.getColumnId(),offset,limit);
        	Integer allCount = reportMapper.selectReportCount(reqBody.getInvestmentInstitutionId(),reqBody.getColumnId(),offset,limit);
        	rowBounds.setTotal(Long.valueOf(allCount));
//        	ReportColumn queryReportColumn = new ReportColumn();
//        	queryReportColumn.setColumnId(reqBody.getColumnId());
//        	List<ReportColumn> reportColumns = reportColumnMapper.selectByRowBounds(queryReportColumn, rowBounds);
//        	for(ReportColumn rc : reportColumns) {
//        	    ReportInstitutionRelation queryInstitutionRelation = new ReportInstitutionRelation();
//        	    queryInstitutionRelation.setReportId(rc.getReportId());
//        	    queryInstitutionRelation.setInstitutionId(reqBody.getInvestmentInstitutionId());
//        	    if (null != reportInstitutionRelationMapper.selectOne(queryInstitutionRelation)) {
//                    list.add(reportMapper.selectByPrimaryKey(rc.getReportId()));
//                }
//        	}
        }else {
            if (reqBody.getTitle() != null && !"".equals(reqBody.getTitle())){
                String title = "%" + reqBody.getTitle() + "%";
                reqBody.setTitle(title);
            }
            if (null != reqBody.getAuthor() && !"".equals(reqBody.getAuthor())){
                String author = "%" + reqBody.getAuthor() + "%";
                reqBody.setAuthor(author);
            }

            Example reportExample = new Example(Report.class);
            reportExample.and().andEqualTo("comments",reqBody.getComments())
            .andEqualTo("content",reqBody.getContent()).andEqualTo("coverUrl",reqBody.getCoverUrl())
            .andEqualTo("creater",reqBody.getCreater()).andEqualTo("fromRul",reqBody.getFromRul())
            .andEqualTo("id",reqBody.getId()).andEqualTo("sourceTextUrl",reqBody.getSourceTextUrl())
            .andEqualTo("status",reqBody.getStatus()).andEqualTo("subTitle",reqBody.getSubTitle())
            .andLike("title",reqBody.getTitle()).andEqualTo("weightingFactor",reqBody.getWeightingFactor())
            .orLike("author",reqBody.getAuthor()).andNotEqualTo("yn",reqBody.getYn())
            .andEqualTo("status",reqBody.getStatus());
            if (null == reqBody.getOrderAsc() || reqBody.getOrderAsc() != 1){
                reportExample.setOrderByClause("update_time desc");
            }else {
                reportExample.setOrderByClause("update_time");
            }


            list = reportMapper.selectByExampleAndRowBounds(reportExample,rowBounds);
            rowBounds.setTotal((long) reportMapper.selectCountByExample(reportExample));
//        	list = reportMapper.selectByRowBounds(report, rowBounds);
        }
        
        List<Map<String,Object>> lists=new ArrayList<>();
        for(Report tmp:list) {
        	Map<String,Object> map=new HashMap<>();
        	Integer releaseId = tmp.getId();
        	map.put("report", tmp);
        	
        	ReportColumn rc =new ReportColumn();
        	rc.setReportId(releaseId);
        	List<ReportColumn> ReportColumns = reportColumnMapper.select(rc);
        	List<Integer> columns=new ArrayList<>();
        	if(ReportColumns!=null) {
        		ReportColumns.forEach((e)->{
            		columns.add(e.getColumnId());
            	});
        	}
        	map.put("columns", columns);
        	
        	ReportSegmentation rs =new ReportSegmentation();
        	rs.setReportId(releaseId);
        	List<ReportSegmentation> ReportSegmentations = reportSegmentationMapper.select(rs);
        	List<Integer> segmentations=new ArrayList<>();
        	if(ReportSegmentations!=null) {
        		ReportSegmentations.forEach((e)->{
        			segmentations.add(e.getSegmentationId());
            	});
        	}
        	map.put("segmentations", segmentations);
        	
        	ReportLabel rl=new ReportLabel();
        	rl.setReportId(releaseId);
        	List<ReportLabel> ReportLabels = reportLabelMapper.select(rl);
        	List<String> labels =new ArrayList<>();
        	if(ReportLabels != null) {
        		ReportLabels.forEach((e)->{
        			labels.add(e.getName());
        		});
        	}
        	map.put("lables", labels);

        	//格式化时间
            if (null != tmp.getUpdateTime()){
                Date updateTime = tmp.getUpdateTime();
                String updateTimeString = sdf.format(updateTime);
                map.put("updateTimeString",updateTimeString);
            }

        	lists.add(map);
        }
        result.setData(lists);
        result.setMessage("success");
        result.setStatus(200);
        result.setTotal(rowBounds.getTotal());
        result.setCurrentPage(reqBody.getPageNo());
        result.setPageSize(reqBody.getPageSize());

        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public CommonDto<Map<String,Object>> getReportById(Integer appId, Integer id) {
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> map=new HashMap<>();
        
        Report report = reportMapper.selectByPrimaryKey(id);
        Integer reportId = report.getId();
        map.put("report", report);
//        获取report的相关附属信息
        ReportColumn rc =new ReportColumn();
    	rc.setReportId(reportId);
    	List<ReportColumn> ReportColumns = reportColumnMapper.select(rc);
    	List<Integer> columns=new ArrayList<>();
    	if(ReportColumns!=null) {
    		ReportColumns.forEach((e)->{
        		columns.add(e.getColumnId());
        	});
    	}
    	map.put("columns", columns);
//        ReportColumn rc =new ReportColumn();
//    	rc.setReportId(reportId);
//    	List<ReportColumn> rcs = reportColumnMapper.select(rc);
//    	
//    	List<MetaColumn> metaColumns = new ArrayList<>();
//    	if(rcs != null) {
//    		rcs.forEach((e)->{
//    			MetaColumn mc=new MetaColumn();
//    			mc.setId(e.getColumnId());
//    			MetaColumn metaColumn = metaColumnMapper.selectOne(mc);
//    			metaColumns.add(metaColumn);
//        	});
//    	}
//    	report.setColumns(metaColumns);
//**************************
//    	ReportSegmentation  rs=new ReportSegmentation();
//    	rs.setReportId(reportId);
//    	List<ReportSegmentation> rss = reportSegmentationMapper.select(rs);
//    	
//    	List<MetaSegmentation> metaSegmentations = new ArrayList<>();
//    	if(rss != null) {
//    		rss.forEach((e)->{
//    			MetaSegmentation ms=new MetaSegmentation();
//    			ms.setId(e.getSegmentationId());
//    			MetaSegmentation metaSegmentation = metaSegmentationMapper.selectOne(ms);
//    			metaSegmentations.add(metaSegmentation);
//        	});
//    	}
//    	report.setSegmentations(metaSegmentations);
//    	
    	ReportSegmentation rs =new ReportSegmentation();
    	rs.setReportId(reportId);
    	List<ReportSegmentation> ReportSegmentations = reportSegmentationMapper.select(rs);
    	List<Integer> segmentations=new ArrayList<>();
    	if(ReportSegmentations!=null) {
    		ReportSegmentations.forEach((e)->{
    			segmentations.add(e.getSegmentationId());
        	});
    	}
    	map.put("segmentations", segmentations);
//**************************** 
//    	ReportLabel rl=new ReportLabel();
//    	rl.setReportId(reportId);
//    	List<ReportLabel> reportLabels = reportLabelMapper.select(rl);
//    	report.setReportLabels(reportLabels);
    	
    	ReportLabel rl=new ReportLabel();
    	rl.setReportId(reportId);
    	List<ReportLabel> ReportLabels = reportLabelMapper.select(rl);
    	List<String> labels =new ArrayList<>();
    	if(ReportLabels != null) {
    		ReportLabels.forEach((e)->{
    			labels.add(e.getName());
    		});
    	}
    	map.put("labels", labels);

    	//设置返回机构id
        ReportInstitutionRelation reportInstitutionRelation = new ReportInstitutionRelation();
        reportInstitutionRelation.setReportId(reportId);
        reportInstitutionRelation.setAppid(appId);

        List<Integer> reportInstitutionRelations = new ArrayList<>();
        List<String> institutionList = new ArrayList<>();
        List<Map<String,Object>> institutionMap = new ArrayList<>();

        List<ReportInstitutionRelation> reportInstitutionRelationList = reportInstitutionRelationMapper.select(reportInstitutionRelation);
        for (ReportInstitutionRelation rir:reportInstitutionRelationList){
            reportInstitutionRelations.add(rir.getInstitutionId());
            InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(rir.getInstitutionId());
            if (investmentInstitutions != null){
                institutionList.add(investmentInstitutions.getShortName());
                Map<String,Object> insMap = new HashMap<>();
                insMap.put("id",rir.getInstitutionId());
                insMap.put("name",investmentInstitutions.getShortName());

                institutionMap.add(insMap);
            }
        }
        map.put("institutionId",reportInstitutionRelations);
        map.put("institutionString",institutionList);
        map.put("institutionMap",institutionMap);
    	
    	//设置相关的项目信息
    	ReportCompanyLabel rcl=new ReportCompanyLabel();
    	rcl.setReportId(reportId);
    	//取得ReportCompanyLabel的多个映射实体
    	List<ReportCompanyLabel> reportCompanyLabels = reportCompanyLabelMapper.select(rcl);
    	List<String> companyLabels =new ArrayList<>();
    	/**
    	 * 项目的相关信息
    	 */
    	List<ProInfoDto> proInfoList = new ArrayList<>();
    	if(reportCompanyLabels != null) {
    		
    		reportCompanyLabels.forEach((e)->{
        		companyLabels.add(e.getCompanyName());
        	});
        	//companyLabels表示的是该report关联的额所有的项目的简称
        	companyLabels.forEach((e)->{
        		Projects pro=new Projects();
        		pro.setShortName(e);
        		try { //根据简称搜索唯一的一条项目信息
        			pro = projectsMapper.selectOne(pro);
        			System.err.println(pro+"****pro");
        		}catch(Exception ex) {
        			this.LOGGER.info(ex.getMessage(), ex.fillInStackTrace());
        			result.setData(null);
        			result.setMessage("公司的简称不唯一");
        			result.setStatus(500);
//        			return result;
        		}
        		//根据该项目信息获取该项目的相关  简称、 logo、 地域 、 一句话简介、 轮次 、领域  
        		if(pro != null) {
        			ProInfoDto projectsSimpleInfo = projectsMapper.getProjectsSimpleInfos(pro.getId());
            		proInfoList.add(projectsSimpleInfo);  
        		}
        	});
    	}
    	
    	map.put("proInfos", proInfoList);
    	
        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    @Transactional
    @Override
    public CommonDto<String> saveOrUpdate(Integer appId, ReportReqBody reqBody) {
       
    	
    	CommonDto<String> result = new CommonDto<>();

        Report report = new Report();
        report.setComments(reqBody.getComments());
        report.setContent(reqBody.getContent());
        report.setCoverUrl(reqBody.getCoverUrl());
        report.setCreater(reqBody.getCreater());
        report.setFromRul(reqBody.getFromRul());
        report.setId(reqBody.getId());
        report.setSourceTextUrl(reqBody.getSourceTextUrl());
        report.setStatus(reqBody.getStatus());
        report.setSubTitle(reqBody.getSubTitle());
        report.setTitle(reqBody.getTitle());
        report.setWeightingFactor(reqBody.getWeightingFactor());
        report.setCreater(reqBody.getCreater());
        report.setYn(reqBody.getYn());
        report.setSubTitle(reqBody.getSubTitle());
        report.setAuthor(reqBody.getAuthor());
        Integer num = null;
        Integer reportId = null;
        if (null == report.getId()){
        	report.setCreateTime(new Date());
            num = reportMapper.insert(report);
            reportId = report.getId();
            this.sendNewsEvent(reqBody.getCreater(),num,reqBody.getColumns());
        }else{
        	report.setUpdateTime(new Date());
        	reportId = reqBody.getId();
            num = reportMapper.updateByPrimaryKeySelective(report);
        }
        List<MetaColumn> columns = reqBody.getColumns();
        List<Integer> segmentations = reqBody.getSegmentations();
        List<String> labels = reqBody.getReportLabels();
        List<String> reportCompanyLabels = reqBody.getReportCompanyLabels();
        
        //进行关联公司标签的删除插入
        ReportCompanyLabel rcl=new ReportCompanyLabel();
        rcl.setReportId(report.getId());
        reportCompanyLabelMapper.delete(rcl);
        reportCompanyLabels.forEach((e)->{
        	rcl.setCompanyName(e);
        	reportCompanyLabelMapper.insertSelective(rcl);
        });
        
       ReportColumn metaColumn =new ReportColumn();
        metaColumn.setReportId(report.getId());

        columnService.deleteAll(report.getId());
        for (MetaColumn column : columns){
            metaColumn.setColumnId(column.getId());
            columnService.save(metaColumn);
        }

        ReportSegmentation reportSegmentation = new ReportSegmentation();
        reportSegmentation.setReportId(report.getId());
        segmentationService.deleteAll(report.getId());
        for (Integer segmentation : segmentations){
            reportSegmentation.setSegmentationId(segmentation);
            segmentationService.save(reportSegmentation);
        }

        ReportLabel reportLabel = new ReportLabel();
        reportLabel.setReportId(report.getId());

        labelService.deleteAll(report.getId());
        for (String lablel : labels){
            reportLabel.setName(lablel);
            labelService.save(reportLabel);
        }

        //插入文章相关机构信息
        //先删除原理的机构id
        ReportInstitutionRelation reportInstitutionRelationForDelete = new ReportInstitutionRelation();
        reportInstitutionRelationForDelete.setAppid(appId);
        reportInstitutionRelationForDelete.setReportId(report.getId());

        reportInstitutionRelationMapper.delete(reportInstitutionRelationForDelete);

        //插入新的
        if (reqBody.getInstitutionId() != null && reqBody.getInstitutionId().size() > 0){
            for (Integer i: reqBody.getInstitutionId()){
                ReportInstitutionRelation reportInstitutionRelation = new ReportInstitutionRelation();
                reportInstitutionRelation.setReportId(reportId);
                reportInstitutionRelation.setAppid(appId);
                reportInstitutionRelation.setInstitutionId(i);

                reportInstitutionRelationMapper.insertSelective(reportInstitutionRelation);
            }
        }


        result.setMessage("success");
        result.setStatus(200);
        result.setData(String.valueOf(num));

        return result;
    }

    @Override
    public InputStream generateReportShareImage(Integer reportId) {

        CommonDto<String> result = new CommonDto<>();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
//            File qrcode = qrcodeService.getQrcodeService().createWxCode(path,width);
//            BufferedImage qcode = ImageIO.read(qrcode);;
//            //处理图片将其压缩成正方形的小图
//            BufferedImage  convertImage= scaleByPercentage(qcode, width,width);
            //裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
            //生成的图片位置
//            ImageIO.write(convertImage, "png", os);
//        } catch (WxErrorException e) {
//            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
//        } catch (IOException e) {
//            this.LOGGER.error(e.getMessage(), e.fillInStackTrace());


//            Thumbnails.of(new File("/Users/zhhu/template/a.png")).size(375,667).watermark(this.createBufferedImage(21,15,333,90,"PingFang-SC",Font.BOLD,21,Color.blue,1.0f,"李丰：从iPhone X、智能音箱到新药研发，「深科技」为什么值得期待？",3)).toFile(templatePath+"/Users/zhhu/template/b.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(os.toByteArray());

    }

    public static void main(String[] args) {
        try {
//            Thumbnails.of(new File("/Users/zhhu/template/a.png")).size(375,667).watermark(createBufferedImage(21,15,333,90,"雅黑",Font.BOLD,21,Color.blue,0.0f,"李丰：从iPhone X、智能音箱到新药研发，「深科技」为什么值得期待？",1)).toFile("/Users/zhhu/template/b.png");
            BufferedImage img = Thumbnails.of(new File("/Users/zhhu/template/a.png")).size(750,1334).asBufferedImage();
            img = createBufferedImage(img,42,60,750,1334, 666,180,"PingFang-SC",Font.BOLD,42,Color.BLACK, 1.0f,"李丰：从iPhone X、智能音箱到新药研发，「深科技」为什么值得期待？", 3);
            img = createBufferedImage(img,42,218,750,1334, 200,32,"PingFang-SC",Font.BOLD,26,new Color(Integer.parseInt("808080",16)), 1.0f,"转载自：天天投 ", 1);
            img = createBufferedImage(img,538,218,750,1334, 200,32,"PingFang-SC",Font.BOLD,26,new Color(Integer.parseInt("808080",16)), 1.0f,"09-09 14:00", 1);
            img = createBufferedImage(img,42,300,750,1334, 666,262,"PingFang-SC",Font.BOLD,36,new Color(Integer.parseInt("333333",16)), 1.0f,"【摘要】口腔行业是市场化程度非常高的消费医疗领域，在资本的推动下，大型连锁连锁纷纷布啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 啊啊 啊啊 啊 啊啊啊啊啊啊…", 5);
            img = Thumbnails.of(img).scale(1.0).watermark(new Position() {
                @Override
                public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
                    return new Point(282,612);
                }
            },Thumbnails.of(new URL("http://ss-lhjl.oss-cn-zhangjiakou.aliyuncs.com/gh_03a936eae289_1280.jpg").openStream()).size(190,190).asBufferedImage(),1.0f).asBufferedImage();
            img = Thumbnails.of(img).scale(1.0).watermark(new Position() {
                @Override
                public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
                    return new Point(42,844);
                }
            },Thumbnails.of(new URL("https://img.idatavc.com/upload/b495ce63ede0f4efc9eec62cb947c162_848_index.jpg").openStream()).size(666,400).keepAspectRatio(false).asBufferedImage(),1.0f).asBufferedImage();

            Thumbnails.of(img).size(750,1334).toFile("/Users/zhhu/template/b.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage createBufferedImage(BufferedImage img, Integer x, Integer y, Integer width, Integer height, Integer wordWidth, Integer wordHeight, String fontName, Integer fontStyle, Integer fontSize, Color color, Float alpha, String pressText, Integer lineNum){
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(img,0,0, 750, 1334,null);
        g.setFont(new Font(fontName, fontStyle, fontSize));
        g.setColor(color);
        g.setStroke(new BasicStroke(1f));


        StringBuilder sb = new StringBuilder();
        int tempW = 0;
        int tempLine = -1;
        for (int i = 0 ;  i < pressText.length(); i++){
            tempW += getCharLen(pressText.charAt(i), g);
            if (tempW >= wordWidth){
                g.drawString(sb.toString(), x, y);
                tempLine ++;
                sb.delete(0,sb.length());
                tempW = 0;
                y = y + fontSize;
            }
            if (tempLine > lineNum){
                break;
            }
            sb.append(pressText.charAt(i));
        }

        g.drawString(sb.toString(), x, y);
        g.dispose();



        return bufferedImage;
    }

    public static int getCharLen(char c,Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charWidth(c);
    }

    public static int getWatermarkLength(String
                                         waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(),0,
                waterMarkContent.length());
    }

    /**
     * 计算文字像素长度
     * @param text
     * @return
     */
    private static int getTextLength(String text){
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            int wordLength = String.valueOf(text.charAt(i)).getBytes().length;
            if(wordLength > 1){
                length+=(wordLength-1);
            }
        }

        return length%2==0 ? length/2:length/2+1;
    }


    private void sendNewsEvent(String creater, Integer projectId, List<MetaColumn> columnList) {
        EventDto eventDto = new EventDto();
        List<Integer> projectIds = new ArrayList<>();
        projectIds.add(projectId);
        eventDto.setFromUser(creater);
        eventDto.setProjectIds(projectIds);
        eventDto.setColumnList(columnList);
        eventDto.setEventType("NEWS");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EventDto> entity = new HttpEntity<>(eventDto, headers);
        HttpEntity<CommonDto<String>> investors =  restTemplate.exchange(eventUrl+"/trigger/event", HttpMethod.POST,entity,new ParameterizedTypeReference<CommonDto<String>>(){} );
    }
}
