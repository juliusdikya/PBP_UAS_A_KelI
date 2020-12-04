package tugas.besar.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceAPI {

    @GET("motor")
    Call<MotorResponse> getAllMotor(@Query("data") String data);

    @GET("motor/{id}")
    Call<MotorResponse> getMotorById(@Path("id") String id,
                                   @Query("data") String data);

    @POST("motor")
    @FormUrlEncoded
    Call<MotorResponse> createMotor(@Field("nama_motor") String nama_motor,
                                  @Field("harga_sewa") String harga_sewa,
                                  @Field("durasi") String durasi,
                                  @Field("img_motor") String img_motor);

    @POST("motor/update/{id}")
    @FormUrlEncoded
    Call<MotorResponse> updateMotor(@Path("id") String id,
                                   @Field("nama_motor") String nama_motor,
                                   @Field("harga_sewa") String harga_sewa,
                                   @Field("durasi") String durasi,
                                   @Field("img_motor") String img_motor);

    @POST("motor/delete/{id}")
    @FormUrlEncoded
    Call<MotorResponse> deleteMotor(@Path("id") String id,
                                    @Field("nama_motor") String nama_motor,
                                    @Field("harga_sewa") String harga_sewa,
                                    @Field("durasi") String durasi,
                                    @Field("img_motor") String img_motor);

    @GET("penyewa")
    Call<PenyewaResponse> getAllPenyewa(@Query("data") String data);

    @GET("penyewa/{id}")
    Call<PenyewaResponse> getPenyewaById(@Path("id") String id,
                                     @Query("data") String data);

    @POST("penyewa")
    @FormUrlEncoded
    Call<PenyewaResponse> createPenyewa(@Field("nama_penyewa") String nama_penyewa,
                                        @Field("nama_motor") String nama_motor,
                                        @Field("durasi_sewa") String durasi_sewa,
                                        @Field("harga") String harga);

    @POST("penyewa/update/{id}")
    @FormUrlEncoded
    Call<PenyewaResponse> updatePenyewa(@Path("id") String id,
                                        @Field("nama_penyewa") String nama_penyewa,
                                        @Field("nama_motor") String nama_motor,
                                        @Field("durasi_sewa") String durasi_sewa,
                                        @Field("harga") String harga);

    @POST("penyewa/delete/{id}")
    @FormUrlEncoded
    Call<PenyewaResponse> deletePenyewa(@Path("id") String id,
                                        @Field("nama_penyewa") String nama_penyewa,
                                        @Field("nama_motor") String nama_motor,
                                        @Field("durasi_sewa") String durasi_sewa,
                                        @Field("harga") String harga);
}

