package com.lhjl.tzzs.proxy.dto;

/**
 * Created by zyy on 2017/11/24.
 */
public class TouZiNewDto {

        private String dateName;  //投资
        private String compellation; // 姓名
        private String organization;  // 所在公司
        private String fillOffice;  //担任职务
        private String evaContent;  //说明
        private String tempFilePaths; //图片
        private String token;  //认证id
        private String investorsApprovalcolCase;//投资人投资案例
        private String formId;

    public Integer getInvesId() {
        return invesId;
    }

    public void setInvesId(Integer invesId) {
        this.invesId = invesId;
    }

    private Integer invesId;



        public String getFormId() {
            return formId;
        }

        public void setFormId(String formId) {
            this.formId = formId;
        }

        public String getInvestorsApprovalcolCase() {
            return investorsApprovalcolCase;
        }

        public void setInvestorsApprovalcolCase(String investorsApprovalcolCase) {
            this.investorsApprovalcolCase = investorsApprovalcolCase;
        }

        public String getDateName() {
            return dateName;
        }
        public void setDateName(String dateName) {
            this.dateName = dateName;
        }
        public String getCompellation() {
            return compellation;
        }
        public void setCompellation(String compellation) {
            this.compellation = compellation;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getFillOffice() {
            return fillOffice;
        }
        public void setFillOffice(String fillOffice) {
            this.fillOffice = fillOffice;
        }
        public String getEvaContent() {
            return evaContent;
        }
        public void setEvaContent(String evaContent) {
            this.evaContent = evaContent;
        }
        public String getTempFilePaths() {
            return tempFilePaths;
        }
        public String getToken() {
            return token;
        }
        public void setToken(String token) {
            this.token = token;
        }
        public void setTempFilePaths(String tempFilePaths) {
            this.tempFilePaths = tempFilePaths;
        }



    }

