package com.korzhuevadaria.catpic.network;

import com.korzhuevadaria.catpic.models.VkPhotosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkApiInterface {
    /* Получение фотографий из альбома
     * owner - идентификатор владельца альбома
     * album - идентификатор альбома
     * rev - порядок сортировки фотографий
     * count - количество фотографий
     * token - ключ доступа
     * version - версия API */
    @GET("photos.get?")
    Call<VkPhotosResponse> getWallPhotos(@Query("owner_id") String owner, @Query("album_id") String album,
                                         @Query("rev") int rev, @Query("count") int count,
                                         @Query("access_token") String token, @Query("v") String version);

    /* Получение последующих фотографий из альбома
     * owner - идентификатор владельца альбома
     * album - идентификатор альбома
     * rev - порядок сортировки фотографий
     * count - количество фотографий
     * offset - отступ, необходимый для получения определенного подмножества записей
     * token - ключ доступа
     * version - версия API */
    @GET("photos.get?")
    Call<VkPhotosResponse> getFromPositionWallPhotos(@Query("owner_id") String owner, @Query("album_id") String album,
                                             @Query("rev") int rev, @Query("count") int count, @Query("offset") int offset,
                                             @Query("access_token") String token, @Query("v") String version);
}
