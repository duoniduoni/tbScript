ant -buildfile /home/zhoangsong/workspace/Demo1
adb push /home/zhoangsong/workspace/Demo1/bin/taobaotest.jar /data/local/tmp/
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#testDemo2 -e key_first 11111 -e key_second 22222
