# V创平台App - 毕设项目  



> 项目成员：赵乐玮，贾雅宇  
>
> 项目内容： 创业平台  
>
> 项目分工： 赵乐玮：V圈广场、主界面 模块的开发  
>
> ​         贾雅宇：项目库、投资人资料、活动页面、发现模块开发  



----------------------------------------------

（赵乐玮部分：）  

### 项目技术要点：  

1.TabLayout+ViewPager实现主界面的切换  
2.使用CoordinatorLayout+ToolBar+FloatingActionButton+RecycleView实现列表滑动特效  
3.使用RippleLayout(自定义控件)+CardView美化界面  

### 自定义控件的使用：  

1.RippleLayout : 具有水波纹特效的 RelativeLayout;  
2.CircleImageView:圆形图片的ImageVIew  



RippleLayout 自定义控件详细介绍：

核心：事件分发


问题收集：

1.在使用RecycleView时出现：因系统复用item导致的图片错乱以及使用Glide无法设置Tag 的问题：

> 解决方案：使用SetTag(int res,String url) 和getTag(int res)来代替以前的set/getTag()

2.V圈首页的Item中还有0-3，如何根据数量来控制图片大小和位置


（贾雅宇部分：）

### 项目职责：

1.负责统筹兼顾，分配团队工作
2.负责发现界面的设计实现（一些动画特效的制作）
3.负责项目库列表的设计（使用viewHolder优化listview）
4. 负责投资人列表的设计
5. 文档的编写
6.负责对程序的拼接
7.负责界面的美化，图片的制作、采集
8.部分服务器的搭建

自定义控件：

ImageViewForUrl，通过url直接加载图片。

问题跟踪：

1.ListView滚动和item中的组件点击事件复用混乱

>解决方案：滚动的同时刷新状态，掩饰点击的效果

2.服务发布后出现404

>解决方案：xml文件配置错误，加上完整的包名就ok
















