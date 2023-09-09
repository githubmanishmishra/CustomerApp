package in.lifc.customerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmiDueModel {

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

        @SerializedName("loanNo")
        @Expose
        private String loanNo;
        @SerializedName("product")
        @Expose
        private String product;
        @SerializedName("loanStatus")
        @Expose
        private String loanStatus;
        @SerializedName("emiDate")
        @Expose
        private String emiDate;
        @SerializedName("emiAmount")
        @Expose
        private String emiAmount;
        @SerializedName("NumEmiDue")
        @Expose
        private String numEmiDue;
        @SerializedName("totalEmiDue")
        @Expose
        private String totalEmiDue;

        public String getLoanNo() {
            return loanNo;
        }

        public void setLoanNo(String loanNo) {
            this.loanNo = loanNo;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getLoanStatus() {
            return loanStatus;
        }

        public void setLoanStatus(String loanStatus) {
            this.loanStatus = loanStatus;
        }

        public String getEmiDate() {
            return emiDate;
        }

        public void setEmiDate(String emiDate) {
            this.emiDate = emiDate;
        }

        public String getEmiAmount() {
            return emiAmount;
        }

        public void setEmiAmount(String emiAmount) {
            this.emiAmount = emiAmount;
        }

        public String getNumEmiDue() {
            return numEmiDue;
        }

        public void setNumEmiDue(String numEmiDue) {
            this.numEmiDue = numEmiDue;
        }

        public String getTotalEmiDue() {
            return totalEmiDue;
        }

        public void setTotalEmiDue(String totalEmiDue) {
            this.totalEmiDue = totalEmiDue;
        }

    }
}
