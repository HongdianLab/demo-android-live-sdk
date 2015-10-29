
# 红点直播云Demo for Android #

## Installation  ##

 在使用之前，请首先确保你也添加了如下依赖库：
 
  * hdmedia.jar
  * libgnustl_shared.so
  * libhdcodec.so
  * libopenh264.so
  * libopustool.so
  * libx264.a

如果编译时候开启了minifyEnabled，则需要在proguard-rules.pro中设置：
 -keep class hd.hdmedia.** {*;}

[下载地址](https://github.com/HongdianLab/android-live-sdk)

### Manually ####



### CocoaPods ###

## API ##
 以下介绍请同时参考demo

#### 初始化配置 ####

    初始化SDK内部配置，此方法只有第一次调用的时候生效，
    AppId:用于安全校验,必须设置，否则无法实际录制或播放

    HDMediaModule.getInstance().setAppId("hongdianhongdian");


#### 音频 ####
  对于音频直播，都是在一个直播间内，录制者上传，需要传入录制者的id，收听者也需要传入自己的id
  
  1.开始音频录制并上传至服务器
 
     HDMediaModule.getInstance().startAudioRecord(_roomIDEditText.getText().toString(), _selfIDEditText.getText().toString());
 
  2.结束音频录制
 
    HDMediaModule.getInstance().stopAudioRecord();
 
  1.开始从服务器获取音频数据并播放
 
     播放一个人的音频：
     HDMediaModule.getInstance().startAudioPlay(_roomIDEditText.getText().toString(), _userIDEditText.getText().toString(), _selfIDEditText.getText().toString());
     播放多个人的音频：
     HDMediaModule.getInstance().startAudioPlay(roomID, userIDList, HDApp.getInstance().getCurrUser().getUserId().toString());

 
   2.停止音频播放
 
     停止一个人的音频：
     HDMediaModule.getInstance().stopAudioPlay(_roomIDEditText.getText().toString(), _userIDEditText.getText().toString(), _selfIDEditText.getText().toString());
     停止所有人的音频：
     HDMediaModule.getInstance().stopAudioPlay();

#### 视频 ####

  视频直播，也是在一个直播间内，录制者上传，需要传入录制者的id，收看者也需要传入自己的id
  流程是生成视频画面视图,绑定到对应的userid上，开启视频录制或播放，结束录制或停止播放
  
  注意：preview目前必须在xml中生成，不能用代码生成，而且大小不能为0dp*0dp，建议使用如下示例
  
  		<SurfaceView
                android:id="@+id/preview"
                android:layout_width="0.0000001dp"
                android:layout_height="0.0000001dp" />

  对于录制者:
  
  1. 生成视频画面视图：
  
         _localView = new GLSurfaceView(getApplicationContext());
  
  2. 绑定：
  
         HDMediaModule.getInstance().bindPreview((SurfaceView)findViewById(R.id.preview), _localView, (float)(displayMetrics.widthPixels * 1.0)/390.0f);（由于是本地录制，所以不需要userid，最后一个参数是double类型，意思是视频画面的宽高比）
  
  3. 开始录制：
  
         HDMediaModule.getInstance().startVideoRecord(_roomIDEditText.getText().toString(), _selfIDEditText.getText().toString());
  
  4. 结束录制： 
  
         HDMediaModule.getInstance().stopVideoRecord();

  
对于收看者：
  
  1. 生成视频画面视图： 
  
         _netView = new GLSurfaceView(this);
  
  2. 绑定：
  
         HDMediaModule.getInstance().bindViewToUserId(_userIDEditText.getText().toString(), _netView, (float)(displayMetrics.widthPixels * 1.0)/390.0f);
  
  3. 开始播放：
  
        播放一个人的视频：
        HDMediaModule.getInstance().startVideoPlay(_roomIDEditText.getText().toString(), _userIDEditText.getText().toString(), _selfIDEditText.getText().toString());
        播放多人的视频：
        HDMediaModule.getInstance().startAudioPlay(_roomIDEditText.getText().toString(), list ,_selfIDEditText.getText().toString());

 
  4. 停止播放：
  
        停止一个人的视频：
        HDMediaModule.getInstance().stopVideoPlay(_roomIDEditText.getText().toString(), _userIDEditText.getText().toString());
        停止所有人的视频：
        HDMediaModule.getInstance().stopVideoPlay();
 
 
举个例子：在roomid=123，userid=456录制视频，userid=789收看，那么调用HDMediaModule.getInstance().startVideoPlay("123", "456", "789");

注意：停止录制或结束播放后视图自动解绑，重新开始录制或播放需要重新绑定视图
  
  切换摄像头
  
     HDMediaModule.getInstance().changeCameraPosition();
  
  开启/关闭闪光灯
  
     HDMediaModule.getInstance().changeTorchStatus(); 
     
 其他：
 
    
    
    设置监听函数，监听房间内信息变化
	roomStatusListener 监听的回调block，具体参数格式见类型说明
	比如：视频第一帧到达的通知就可以通过这个方法获取
	
	HDMediaModule.getInstance().setRoomStatusListener(new HDMediaModule.HDRoomStatusListener() {
            @Override
            public void roomStatusChanged(String s, String s1, boolean b, boolean b1, HDMediaModule.HDEvent hdEvent) {
                
            }
    });

## Requirements ##

 requires android 4.0 or greater.

## Contact ##


## License ##

Copyright 2013 Hongdian, Inc.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. [See the License](LICENSE.txt) for the specific language governing permissions and limitations under the License.
