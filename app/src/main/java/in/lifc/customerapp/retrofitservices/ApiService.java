package in.lifc.customerapp.retrofitservices;

import in.lifc.customerapp.model.LoanRequestModel;
import in.lifc.customerapp.model.LoanTypeModel;
import in.lifc.customerapp.model.LoginModel;
import in.lifc.customerapp.model.MyLoanInfoModel;
import in.lifc.customerapp.model.Otpcustomer;
import in.lifc.customerapp.model.RequestDropdownModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> getLogin(@Field("mobileNumber") String mobileNumber
    );

    @FormUrlEncoded
    @POST("verify_otp")
    Call<Otpcustomer> getOtp(@Field("otp") int Otp
    );

    @FormUrlEncoded
    @POST("loan_request")
    Call<LoanRequestModel> loanRequest(@Header("Token") String token,
                                       @Field("loanType") String loanType,
                                       @Field("loanAmount") String loanAmount,
                                       @Field("loanTenure") String loan_Tenure,
                                       @Field("loanPurpose") String loanPurpose,
                                       @Field("offerScheme") String offerScheme


    );
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("loan_type")
    Call<LoanTypeModel> getLoanType(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("loan_info")
    Call<MyLoanInfoModel> getLoanInfo(@Header("Authorization") String auth);
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("request_type")
    Call<RequestDropdownModel> getRequestType(@Header("Authorization") String auth);


}

