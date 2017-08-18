package com.jiyun.da_one_eleme.modle.Okhttp;

import android.os.Handler;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 张凯雅 on 2017/8/10.
 */

public class OkHttpManager {
    private OkHttpClient client;
    Handler handler;
    private static OkHttpManager OkHttpManager;

    public OkHttpManager() {
        handler=new Handler();
        client = new OkHttpClient();
    }


    public static OkHttpManager getInstance() {
        if (OkHttpManager == null) {
            synchronized (OkHttpManager.class) {
                if (OkHttpManager == null) {
                    OkHttpManager = new OkHttpManager();
                }
            }

        }
        return OkHttpManager;
    }



    //创建接口是为了实现异步调用
    public interface CallBacks{

        void getString(String s);
    }


    public void getNet(String url, final CallBacks callBacks) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                //为什么要使用handler.post是为了把内容发送到主线程中
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //run里面的方法都在主线中
                       callBacks.getString(string);
                    }
                });
            }
        });
    }


    //参数为要上传的网址，本地照片在本地的地址，我们自己定义的接口
    public static void doPostPicture(String url, File file, final CallBacks callback) {
        //OkHttpClient作为全局静态变量已经定义过了
        OkHttpClient client=new OkHttpClient();
        //2.创建一个请求体
        RequestBody body;
        //3.创建一个请求体建造器
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart("headPicture", "headPicture.jpg", RequestBody.create(MediaType.parse("image/jpg"), file));

        body = builder.build();

        //3.创建一个请求，利用构建器方式添加url和请求体。
        Request request = new Request.Builder().post(body).url(url).build();

        //4.定义一个call，利用okhttpclient的newcall方法来创建对象。因为Call是一个接口不能利用构造器实例化。
        Call call = client.newCall(request);

        //5.这是异步调度方法，上传和接受的工作都在子线程里面运作，如果要使用同步的方法就用call.excute(),此方法返回的就是Response
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.getString(response.body().string());
               //把服务器发回来的数据response解析成string传入方法
            }
        });
    }
}
