package in.lifc.customerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class The_Slide_Items_Model_Class {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data;

/*
    public The_Slide_Items_Model_Class(List<Datum> data) {
        this.data = data;
    }
*/


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status)
    {
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

        @SerializedName("offer_name")
        @Expose
        private String offerName;
        @SerializedName("offer_url")
        @Expose
        private String offerUrl;

        public String getOfferName() {
            return offerName;
        }

        public void setOfferName(String offerName) {
            this.offerName = offerName;
        }

        public String getOfferUrl() {
            return offerUrl;
        }

        public void setOfferUrl(String offerUrl) {
            this.offerUrl = offerUrl;
        }

    }
}