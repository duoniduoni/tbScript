ant -buildfile /home/zhoangsong/workspace/Demo1
adb push /home/zhoangsong/workspace/Demo1/bin/taobaotest.jar /data/local/tmp/
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#testDemo2 -e args 泳镜*Lining#李宁#游泳镜男#防水#防雾#高清*TRUE*30*20
