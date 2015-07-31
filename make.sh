ant -buildfile /home/zhoangsong/workspace/Demo1
adb push /home/zhoangsong/workspace/Demo1/bin/taobaotest.jar /data/local/tmp/
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#testDemo2 -e SC 鹦鹉活体 -e CD 小鸟活体牡丹鹦鹉活鸟绿鹦鹉棕头鹦鹉鸟活体 -e IEOC TRUE -e SCT 30 -e SET 20
