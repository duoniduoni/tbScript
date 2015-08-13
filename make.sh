ant -buildfile /home/zhoangsong/workspace/Demo1
adb push /home/zhoangsong/workspace/Demo1/bin/taobaotest.jar /data/local/tmp/
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#testDemo -e args 电视机*全高清#LED#超薄#24寸液晶#22寸液晶#液晶电视机*TRUE*30*20
