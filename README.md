# MyFramework
In the development process, collect some tools


## Tool Classes

1. [ToastUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/ToastUtils.java "ToastUtils")
<br>**简化Toast的显示**
2. [SnackbarUtil ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/SnackbarUtil.java) 
<br>**简化Snackbar的显示**
3. [StringUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/StringUtils.java)
<br>**字符工具类（提取出城市或者县，手机号码验证，是否字符串由空格组成）** 
4. [StatusBarUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/StatusBarUtils.java)
5. [ScreenUtil ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/ScreenUtil.java)
<br>**获取屏幕宽度、高度、状态栏高度、获取当前屏幕截图（不含状态栏）、获取Actionbar像素高度**
6. [RegularUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/RegularUtils.java)
<br>**常用的正则表达式：<br> - 验证手机号格式<br> - 座机号格式<br> - 邮箱格式<br> - 网址格式<br> - 一般用户名格式（取值范围为a-z,A-Z,0-9,"\_",汉字，不能以"\_"结尾,用户名必须是6-20位）<br> - 汉字格式**
7. [PreferenceUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/PreferenceUtils.java)
<br>**简化SharedPreferences的读取**
8. [PinyinUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/PinyinUtils.java)
<br>**提取字符串的首个文字的首字母大写**
9. [NetStateUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/NetStateUtils.java)
<br>**判断网络状态**
10. [MD5Utils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/MD5Utils.java)
<br>**生成一次或多次16位或32位MD5码**
11. [JsonUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/JsonUtils.java)
<br>**通过Gson解析，返回bean类**
12. [FolderUtils ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/FolderUtils.java)
<br>**获取文件夹大小，删除指定目录文件及目录**
13. [DateUtil ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/DateUtil.java)
<br>**日期转换工具类**
14. [DataCleanManager ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/DataCleanManager.java)
<br>**清除应用的内外缓存**
15. [CrashHandler ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/CrashHandler.java)
<br>**记录软件的Crash信息,要在Application里面初始化**<br>
` CrashHandler crashHandler = CrashHandler.getInstance();
  crashHandler.init(this); `
16. [ClickUtil ](https://github.com/BisonQin/MyFramework/blob/master/app/src/main/java/cn/bisondev/myframework/utils/ClickUtil.java)
<br>**判断两次点击的间隔是否在规定时间段内**