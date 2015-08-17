adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#startTaobao
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#entryMainActivity
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#entrySearchConditionActivity -e args 牦牛酸奶
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#entrySearchResultActivity -e args 青海藏家自制牦牛酸奶#游客吃过都说好
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#entryCommodityActivity -e args 6
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#entryEvaluationActivity -e args 6
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#entryCommodityActivityRandomly
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#entryCommodityActivity -e args 2
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#exitCommodityActivity
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#exitShopActivity
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#exitCommodityActivity
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#exitSearchResultActivity
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#exitSearchConditionActivity
adb shell uiautomator runtest taobaotest.jar -c com.uiautomatortest.Test#exitMainActivity