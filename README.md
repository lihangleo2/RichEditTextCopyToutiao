# RichEditTextCopyToutiao
android高仿今日头条富文本编辑，如果图片显示不出来，可以去我的掘金[掘金地址](https://juejin.im/post/6874875969292173320/)

<br>

**前言：** 在经历了几个月的项目期限。我们遇到了前端发布文章，要用到富文本编辑的功能。在一番衡量下最终用到了[richeditor-android](https://github.com/wasabeef/richeditor-android)第三方框架。实现原理就是通过webView和js实现前端富文本。有想了解的可以点开上面链接，研究研究。虽然richeditor-android已经帮我们实现了大部分核心内容，但是很多细节它都没有完善，这也是为什么他的issues达到170多的原因。也不知道它有没有停更。接下来是作者踩了N多坑之后，把功能基本补全。可以说是高仿“今日头条富文本编辑”。

<br>
首先非常感谢[richeditor-android富文本](https://github.com/wasabeef/richeditor-android)

<br>

## 一、先来看看都实现了补全了哪些功能
（这里不会重复richeditor-android原有功能，只会详细讲解补全的细节功能）

<br>

### 1.1、在没有焦点时，主动获取焦点并弹出软键盘

**修改后：** 在第一打开富文本编辑时，点击加粗，下划线或上传图片时，主动获取焦点并弹出软键盘。
![](https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/richEditText1.gif)

<br>

### 1.2、在光标主动回到某处，下方tab选中当前的样式，同理撤销和回退

**修改后：** 在光标变化，撤销回退，或者主动点删除时，下方tab会根据当前样式选中要选中的样式
![](https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/richEditText2.gif)

<br>

### 1.3、上传图片，自动换行，且自动滚动到底部。且充满屏幕

**修改后：** 选中图片后，插入图片自动换行，自动滚动到底部，弹出软键盘。（图片充满屏幕）
![](https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/richEditText3.gif)

<br>

### 1.4、点击图片，可编辑图片（剪裁），或删除图片

**修改后：** 点击图片增加了删除和编辑功能，编辑剪裁图片也是模仿头条的。（注：这里的图片选择和图片剪裁是其他第三方，后续给上链接，作者修改后增加了比例切换的动画，且适配了全面屏和处理了虚拟键）
![](https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/richEditText4.gif)

<br>


### 1.5、根据头条的排版样式，修改了文字，图片还有序号的排版样式

**修改后：** 原样式，比较不理想。在看了头条的排版样式后，修改了css样式。尽量模仿出头条的感觉
![](https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/richEditText5.gif)

<br>

### 1.6、选中文字后，在不点击屏幕前，选中文字都不变。高度模仿头条

**修改后：** 之前点击1下或者2下加粗时，选中文字会取消。现在不是主动点击屏幕，选中文字不会取消
![](https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/richEditText6.gif)

<br>

### 1.7、在作者在实现这块功能时，还遇到了缺少ssl证书，图片没法显示出来。还加了忽略ssl证书
在华为和小米都有出现过。出现的时候，我用头条app去上传图片也是遇到上传图片失败。过一会，手机又正常了。反正加上忽略ssl是一定可以成功的。

<br>

## 二、现在让我们一起看看发布文章重新编辑的功能吧、
![](https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/richEditText7.gif)

<br>

## 三、总结。
至此一个高仿头条的富文本就此而生。如果你开发遇到了或者有什么古怪的功能还不满足你的要求，请联系我。
<br>
* [文中用到的图片选择框架，建议用demo的，是作者适配全面屏修改过的](https://github.com/jeasonlzy/ImagePicker)
* [文章用到的剪裁框架，建议用demo的，是作者适配全面屏修改过的](https://github.com/Yalantis/uCrop)
* 请注意：文中用到本地路径的图片，如果是网图，则可直接替换成网图即可

## 想和我一起成长吗？请关注我的公众号
<img src="https://github.com/lihangleo2/RichEditTextCopyToutiao/blob/master/gif/wx.png"/>

