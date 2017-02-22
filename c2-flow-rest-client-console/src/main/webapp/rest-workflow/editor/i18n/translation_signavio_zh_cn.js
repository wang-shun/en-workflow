ORYX.I18N.PropertyWindow.dateFormat = "d/m/y";

ORYX.I18N.View.East = "属性";
ORYX.I18N.View.West = "模型元素";

ORYX.I18N.Oryx.title	= "流程编辑器";
ORYX.I18N.Oryx.pleaseWait = "请等待，流程编辑器正在启动...";
ORYX.I18N.Edit.cutDesc = "剪切所选内容到剪贴板中";
ORYX.I18N.Edit.copyDesc = "复制所选内容到剪贴板中";
ORYX.I18N.Edit.pasteDesc = "粘贴剪贴板上";
ORYX.I18N.ERDFSupport.noCanvas = "xml文档没有包含节点!";
ORYX.I18N.ERDFSupport.noSS = "流程编辑器画布节点不符合规范!";
ORYX.I18N.ERDFSupport.deprText = "不推荐导出eRDF，因为在将来的版本将不再支持，如果可能的话可以导出json格式模型，你想要导出吗 ?";
ORYX.I18N.Save.pleaseWait = "请等待<br/>正在保存...";

ORYX.I18N.Save.saveAs = "保存复制...";
ORYX.I18N.Save.saveAsDesc = "保存复制...";
ORYX.I18N.Save.saveAsTitle = "保存复制...";
ORYX.I18N.Save.savedAs = "复制已保存";
ORYX.I18N.Save.savedDescription = "流程图存储在";
ORYX.I18N.Save.notAuthorized = "你当前没有登录。 请重新登陆，以便你能保存流程图"
ORYX.I18N.Save.transAborted = "存储请求花费的时间太长。你可以使用一个更快的网络连接。如果你使用无线网络，请检查您的连接强度。";
ORYX.I18N.Save.noRights = "你没有权限保存流程图。";
ORYX.I18N.Save.comFailed = "与服务器通讯失败。请检查您的网络连接。";
ORYX.I18N.Save.failed = "保存流程失败，请检查流程格式是否正确。";
ORYX.I18N.Save.exception = "保存流程出现异常，请重试。";
ORYX.I18N.Save.retrieveData = "请稍等，正在检索数据。";

/** New Language Properties: 10.6.09*/
if(!ORYX.I18N.ShapeMenuPlugin) ORYX.I18N.ShapeMenuPlugin = {};
ORYX.I18N.ShapeMenuPlugin.morphMsg = "变换形状";
ORYX.I18N.ShapeMenuPlugin.morphWarningTitleMsg = "变换形状";
ORYX.I18N.ShapeMenuPlugin.morphWarningMsg = "变换元素不能有子形状.<br/>你想变换吗?";

if (!Signavio) { var Signavio = {}; }
if (!Signavio.I18N) { Signavio.I18N = {} }
if (!Signavio.I18N.Editor) { Signavio.I18N.Editor = {} }

if (!Signavio.I18N.Editor.Linking) { Signavio.I18N.Editor.Linking = {} }
Signavio.I18N.Editor.Linking.CreateDiagram = "新建流程图";
Signavio.I18N.Editor.Linking.UseDiagram = "使用已有流程图";
Signavio.I18N.Editor.Linking.UseLink = "使用网络链接";
Signavio.I18N.Editor.Linking.Close = "关闭";
Signavio.I18N.Editor.Linking.Cancel = "取消";
Signavio.I18N.Editor.Linking.UseName = "采用图的名称";
Signavio.I18N.Editor.Linking.UseNameHint = "采用链接图的名称替换模型元素({type}) 的名称";
Signavio.I18N.Editor.Linking.CreateTitle = "建立链接";
Signavio.I18N.Editor.Linking.AlertSelectModel = "请选择模型.";
Signavio.I18N.Editor.Linking.ButtonLink = "链接流程图";
Signavio.I18N.Editor.Linking.LinkNoAccess = "你没有改流程图权限.";
Signavio.I18N.Editor.Linking.LinkUnavailable = "流程图不可用.";
Signavio.I18N.Editor.Linking.RemoveLink = "删除链接";
Signavio.I18N.Editor.Linking.EditLink = "编辑链接";
Signavio.I18N.Editor.Linking.OpenLink = "打开";
Signavio.I18N.Editor.Linking.BrokenLink = "链接被破坏!";
Signavio.I18N.Editor.Linking.PreviewTitle = "预览";

if(!Signavio.I18N.Glossary_Support) { Signavio.I18N.Glossary_Support = {}; }
Signavio.I18N.Glossary_Support.renameEmpty = "目录不存在";
Signavio.I18N.Glossary_Support.renameLoading = "搜索...";

/** New Language Properties: 08.09.2009*/
if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};
ORYX.I18N.PropertyWindow.oftenUsed = "主属性";
ORYX.I18N.PropertyWindow.moreProps = "更多属性";

ORYX.I18N.PropertyWindow.btnOpen = "打开";
ORYX.I18N.PropertyWindow.btnRemove = "删除";
ORYX.I18N.PropertyWindow.btnEdit = "编辑";
ORYX.I18N.PropertyWindow.btnUp = "上移";
ORYX.I18N.PropertyWindow.btnDown = "下移";
ORYX.I18N.PropertyWindow.createNew = "新建";

if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};
ORYX.I18N.PropertyWindow.oftenUsed = "主要属性";
ORYX.I18N.PropertyWindow.moreProps = "更多属性";
ORYX.I18N.PropertyWindow.characteristicNr = "成本 &amp; 资源分析";
ORYX.I18N.PropertyWindow.meta = "自定义属性";

if(!ORYX.I18N.PropertyWindow.Category){ORYX.I18N.PropertyWindow.Category = {}}
ORYX.I18N.PropertyWindow.Category.popular = "主要属性";
ORYX.I18N.PropertyWindow.Category.characteristicnr = "成本 &amp; 资源分析";
ORYX.I18N.PropertyWindow.Category.others = "更多属性";
ORYX.I18N.PropertyWindow.Category.meta = "自定义属性";

if(!ORYX.I18N.PropertyWindow.ListView) ORYX.I18N.PropertyWindow.ListView = {};
ORYX.I18N.PropertyWindow.ListView.title = "编辑: ";
ORYX.I18N.PropertyWindow.ListView.dataViewLabel = "实体已存在.";
ORYX.I18N.PropertyWindow.ListView.dataViewEmptyText = "列表没有实体.";
ORYX.I18N.PropertyWindow.ListView.addEntryLabel = "添加新实体";
ORYX.I18N.PropertyWindow.ListView.buttonAdd = "添加";
ORYX.I18N.PropertyWindow.ListView.save = "保存";
ORYX.I18N.PropertyWindow.ListView.cancel = "取消";

if(!Signavio.I18N.Buttons) Signavio.I18N.Buttons = {};
Signavio.I18N.Buttons.save		= "保存";
Signavio.I18N.Buttons.cancel 	= "取消";
Signavio.I18N.Buttons.remove	= "删除";

if(!Signavio.I18N.btn) {Signavio.I18N.btn = {};}
Signavio.I18N.btn.btnEdit = "编辑";
Signavio.I18N.btn.btnRemove = "删除";
Signavio.I18N.btn.moveUp = "上移";
Signavio.I18N.btn.moveDown = "下移";

if(!Signavio.I18N.field) {Signavio.I18N.field = {};}
Signavio.I18N.field.Url = "URL";
Signavio.I18N.field.UrlLabel = "标签";
