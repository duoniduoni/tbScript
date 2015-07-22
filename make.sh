ant -buildfile /home/zhoangsong/workspace/Demo1
adb push /home/zhoangsong/workspace/Demo1/bin/taobaotest.jar /data/local/tmp/
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#testDemo
