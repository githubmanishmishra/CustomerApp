package in.lifc.customerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyLoanInfoModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("loanId")
        @Expose
        private String loanId;
        @SerializedName("loanStatus")
        @Expose
        private String loanStatus;
        @SerializedName("product")
        @Expose
        private String product;
        @SerializedName("loanNo")
        @Expose
        private String loanNo;
        @SerializedName("disbursalStatus")
        @Expose
        private String disbursalStatus;
        @SerializedName("loanAmount")
        @Expose
        private String loanAmount;
        @SerializedName("paidEmi")
        @Expose
        private Integer paidEmi;
        @SerializedName("tenure")
        @Expose
        private String tenure;
        @SerializedName("dueEmiDate")
        @Expose
        private String dueEmiDate;

        public String getLoanId() {
            return loanId;
        }

        public void setLoanId(String loanId) {
            this.loanId = loanId;
        }

        public String getLoanStatus() {
            return loanStatus;
        }

        public void setLoanStatus(String loanStatus) {
            this.loanStatus = loanStatus;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getLoanNo() {
            return loanNo;
        }

        public void setLoanNo(String loanNo) {
            this.loanNo = loanNo;
        }

        public String getDisbursalStatus() {
            return disbursalStatus;
        }

        public void setDisbursalStatus(String disbursalStatus) {
            this.disbursalStatus = disbursalStatus;
        }

        public String getLoanAmount() {
            return loanAmount;
        }

        public void setLoanAmount(String loanAmount) {
            this.loanAmount = loanAmount;
        }

        public Integer getPaidEmi() {
            return paidEmi;
        }

        public void setPaidEmi(Integer paidEmi) {
            this.paidEmi = paidEmi;
        }

        public String getTenure() {
            return tenure;
        }

        public void setTenure(String tenure) {
            this.tenure = tenure;
        }

        public String getDueEmiDate() {
            return dueEmiDate;
        }

        public void setDueEmiDate(String dueEmiDate) {
            this.dueEmiDate = dueEmiDate;
        }
    }
}