package cn.majingjing.tmblog.view.util.api.interfaceApi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface TMServerApi {

	@FormUrlEncoded
	@POST("TM-web-s/api/rest/v2/common")
	Call<String> query(@Header("plantId") String plantId, @Field("api") String api, @Field("p") String p);

	@Multipart
	@POST("TM-web-s/api/rest/fbs/upload")
	Call<String> upload(@Part("fileName") RequestBody fileName, @Part MultipartBody.Part file);
}
