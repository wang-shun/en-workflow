/**
 * @author nicolas.peters
 * 
 * Contains all strings for the default language (en-us).
 * Version 1 - 08/29/08
 */
if(!ORYX) var ORYX = {};

if(!ORYX.I18N) ORYX.I18N = {};

ORYX.I18N.Language = "zh_cn"; //Pattern <ISO language code>_<ISO country code> in lower case!

if(!ORYX.I18N.Oryx) ORYX.I18N.Oryx = {};

ORYX.I18N.Oryx.title		= "Oryx";
ORYX.I18N.Oryx.noBackendDefined	= "注意! \n没有后台定义。\n 请求的模型无法加载。尝试加载一个配置保存插件。";
ORYX.I18N.Oryx.pleaseWait 	= "请等待，正在加载...";
ORYX.I18N.Oryx.notLoggedOn = "没有登录";
ORYX.I18N.Oryx.editorOpenTimeout = "编辑似乎没有开始。请检查一下，你是否有一个弹出窗口拦截器启用和禁用它，或允许该站点的弹出窗口。";

if(!ORYX.I18N.AddDocker) ORYX.I18N.AddDocker = {};

ORYX.I18N.AddDocker.group = "折线";
ORYX.I18N.AddDocker.add = "添加折线点";
ORYX.I18N.AddDocker.addDesc = "点击工具栏按钮，再点击线条，为线条添加折线点，拖动使其变成折线";
ORYX.I18N.AddDocker.del = "删除折线";
ORYX.I18N.AddDocker.delDesc = "删除折线";

if(!ORYX.I18N.Arrangement) ORYX.I18N.Arrangement = {};

ORYX.I18N.Arrangement.groupZ = "顺序";
ORYX.I18N.Arrangement.btf = "带到前面";
ORYX.I18N.Arrangement.btfDesc = "带到前面";
ORYX.I18N.Arrangement.btb = "带回来";
ORYX.I18N.Arrangement.btbDesc = "带回来";
ORYX.I18N.Arrangement.bf = "置前";
ORYX.I18N.Arrangement.bfDesc = "置前";
ORYX.I18N.Arrangement.bb = "置后";
ORYX.I18N.Arrangement.bbDesc = "置后";
ORYX.I18N.Arrangement.groupA = "对齐";
ORYX.I18N.Arrangement.ab = "底部对齐";
ORYX.I18N.Arrangement.abDesc = "底部对齐";
ORYX.I18N.Arrangement.am = "水平居中对齐";
ORYX.I18N.Arrangement.amDesc = "水平居中对齐";
ORYX.I18N.Arrangement.at = "顶部对齐";
ORYX.I18N.Arrangement.atDesc = "顶部对齐";
ORYX.I18N.Arrangement.al = "左对齐";
ORYX.I18N.Arrangement.alDesc = "左对齐";
ORYX.I18N.Arrangement.ac = "垂直居中对齐";
ORYX.I18N.Arrangement.acDesc = "垂直居中对齐";
ORYX.I18N.Arrangement.ar = "右对齐";
ORYX.I18N.Arrangement.arDesc = "右对齐";
ORYX.I18N.Arrangement.as = "相同尺寸对齐";
ORYX.I18N.Arrangement.asDesc = "相同尺寸对齐";

if(!ORYX.I18N.Edit) ORYX.I18N.Edit = {};

ORYX.I18N.Edit.group = "编辑";
ORYX.I18N.Edit.cut = "剪贴";
ORYX.I18N.Edit.cutDesc = "剪贴所选内容到剪贴板";
ORYX.I18N.Edit.copy = "复制";
ORYX.I18N.Edit.copyDesc = "复制所选内容到剪贴板";
ORYX.I18N.Edit.paste = "粘贴";
ORYX.I18N.Edit.pasteDesc = "从剪贴板粘贴元素";
ORYX.I18N.Edit.del = "删除";
ORYX.I18N.Edit.delDesc = "删除所有选择项";

if(!ORYX.I18N.EPCSupport) ORYX.I18N.EPCSupport = {};

ORYX.I18N.EPCSupport.group = "EPC";
ORYX.I18N.EPCSupport.exp = "导出 EPC";
ORYX.I18N.EPCSupport.expDesc = "到处流程图到EPML";
ORYX.I18N.EPCSupport.imp = "导入EPC";
ORYX.I18N.EPCSupport.impDesc = "导入一个EPML文件";
ORYX.I18N.EPCSupport.progressExp = "正在到处模型";
ORYX.I18N.EPCSupport.selectFile = "选择一个EPML (.empl)文件导入";
ORYX.I18N.EPCSupport.file = "文件";
ORYX.I18N.EPCSupport.impPanel = "导入 EPML文件";
ORYX.I18N.EPCSupport.impBtn = "导入";
ORYX.I18N.EPCSupport.close = "关闭";
ORYX.I18N.EPCSupport.error = "错误";
ORYX.I18N.EPCSupport.progressImp = "导入中...";

if(!ORYX.I18N.ERDFSupport) ORYX.I18N.ERDFSupport = {};

ORYX.I18N.ERDFSupport.exp = "导出到ERDF";
ORYX.I18N.ERDFSupport.expDesc = "导出到ERDF";
ORYX.I18N.ERDFSupport.imp = "从ERDF导入";
ORYX.I18N.ERDFSupport.impDesc = "从ERDF导入";
ORYX.I18N.ERDFSupport.impFailed = "请求导入ERDF失败。";
ORYX.I18N.ERDFSupport.impFailed2 = "导入时出现错误! <br/>请检查错误信息: <br/><br/>";
ORYX.I18N.ERDFSupport.error = "错误";
ORYX.I18N.ERDFSupport.noCanvas = "xml文档没有包含Oryx画布节点!";
ORYX.I18N.ERDFSupport.noSS = "Oryx画布节点没有定义模板集!";
ORYX.I18N.ERDFSupport.wrongSS = "给定的模板集，不适应当前的编辑！";
ORYX.I18N.ERDFSupport.selectFile = "选择一个ERDF (.xml)文件 或者ERDF的类型导入!";
ORYX.I18N.ERDFSupport.file = "文件";
ORYX.I18N.ERDFSupport.impERDF = "导入ERDF";
ORYX.I18N.ERDFSupport.impBtn = "导入";
ORYX.I18N.ERDFSupport.impProgress = "导入中...";
ORYX.I18N.ERDFSupport.close = "关闭";
ORYX.I18N.ERDFSupport.deprTitle = "确定导出到ERDF?";
ORYX.I18N.ERDFSupport.deprText = "导出ERDF在将来版本的流程编辑器将不再支持。如果可以，请导出程json格式，你想要继续导出吗?";

if(!ORYX.I18N.jPDLSupport) ORYX.I18N.jPDLSupport = {};

ORYX.I18N.jPDLSupport.group = "执行BPMN标准";
ORYX.I18N.jPDLSupport.exp = "导出到jPDL";
ORYX.I18N.jPDLSupport.expDesc = "导出到jPDL";
ORYX.I18N.jPDLSupport.imp = "从jPDL导入";
ORYX.I18N.jPDLSupport.impDesc = "从jPDL导入";
ORYX.I18N.jPDLSupport.impFailedReq = "请求导入jPDL失败。";
ORYX.I18N.jPDLSupport.impFailedJson = "传输jPDL失败";
ORYX.I18N.jPDLSupport.impFailedJsonAbort = "导入失败。";
ORYX.I18N.jPDLSupport.loadSseQuestionTitle = "JBPM工作流模板集扩展需要加载。"; 
ORYX.I18N.jPDLSupport.loadSseQuestionBody = "导入jPDL，模板集扩展已经被加载。你想继续吗？";
ORYX.I18N.jPDLSupport.expFailedReq = "请求导出模型失败";
ORYX.I18N.jPDLSupport.expFailedXml = "导出到jPDL失败。 ";
ORYX.I18N.jPDLSupport.error = "错误";
ORYX.I18N.jPDLSupport.selectFile = "选择一个jPDL (.xml)文件orjPDL类型 导入!";
ORYX.I18N.jPDLSupport.file = "文件";
ORYX.I18N.jPDLSupport.impJPDL = "导入jPDL";
ORYX.I18N.jPDLSupport.impBtn = "导入";
ORYX.I18N.jPDLSupport.impProgress = "导入中...";
ORYX.I18N.jPDLSupport.close = "关闭";

if(!ORYX.I18N.Save) ORYX.I18N.Save = {};

ORYX.I18N.Save.group = "文件";
ORYX.I18N.Save.save = "保存";
ORYX.I18N.Save.saveDesc = "保存";
ORYX.I18N.Save.saveAs = "另存为...";
ORYX.I18N.Save.saveAsDesc = "另存为...";
ORYX.I18N.Save.unsavedData = "有未保存的数据，请先保存，否则数据将丢失!";
ORYX.I18N.Save.newProcess = "新的流程";
ORYX.I18N.Save.saveAsTitle = "另存为...";
ORYX.I18N.Save.saveBtn = "保存";
ORYX.I18N.Save.close = "关闭";
ORYX.I18N.Save.savedAs = "另存为";
ORYX.I18N.Save.saved = "已保存!";
ORYX.I18N.Save.failed = "保存失败.";
ORYX.I18N.Save.noRights = "你没有权利保存更改.";
ORYX.I18N.Save.saving = "保存中";
ORYX.I18N.Save.saveAsHint = "流程图存储在:";

if(!ORYX.I18N.File) ORYX.I18N.File = {};

ORYX.I18N.File.group = "文件";
ORYX.I18N.File.print = "打印";
ORYX.I18N.File.printDesc = "打印当前模型";
ORYX.I18N.File.pdf = "导出PDF";
ORYX.I18N.File.pdfDesc = "导出PDF";
ORYX.I18N.File.info = "信息";
ORYX.I18N.File.infoDesc = "信息";
ORYX.I18N.File.genPDF = "生成PDF";
ORYX.I18N.File.genPDFFailed = "生成PDF失败.";
ORYX.I18N.File.printTitle = "打印";
ORYX.I18N.File.printMsg = "我们当前的打印功能存在文件. 建议导出PDF后再去打印. 确定需要继续打印吗?";

if(!ORYX.I18N.Grouping) ORYX.I18N.Grouping = {};

ORYX.I18N.Grouping.grouping = "分组";
ORYX.I18N.Grouping.group = "分组";
ORYX.I18N.Grouping.groupDesc = "分组所有选择形状";
ORYX.I18N.Grouping.ungroup = "取消分组";
ORYX.I18N.Grouping.ungroupDesc = "取消分组所有选择形状";

if(!ORYX.I18N.Loading) ORYX.I18N.Loading = {};

ORYX.I18N.Loading.waiting ="请等待...";

if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};

ORYX.I18N.PropertyWindow.name = "属性名";
ORYX.I18N.PropertyWindow.value = "属性值";
ORYX.I18N.PropertyWindow.selected = "selected";
ORYX.I18N.PropertyWindow.clickIcon = "Click Icon";
ORYX.I18N.PropertyWindow.add = "添加";
ORYX.I18N.PropertyWindow.rem = "删除";
ORYX.I18N.PropertyWindow.complex = "编辑复杂类型";
ORYX.I18N.PropertyWindow.text = "编辑文本类型";
ORYX.I18N.PropertyWindow.ok = "确定";
ORYX.I18N.PropertyWindow.cancel = "取消";
ORYX.I18N.PropertyWindow.dateFormat = "m/d/y";

if(!ORYX.I18N.ShapeMenuPlugin) ORYX.I18N.ShapeMenuPlugin = {};

ORYX.I18N.ShapeMenuPlugin.drag = "点击拖拽";
ORYX.I18N.ShapeMenuPlugin.clickDrag = "点击拖拽";
ORYX.I18N.ShapeMenuPlugin.morphMsg = "缩放";

if(!ORYX.I18N.SyntaxChecker) ORYX.I18N.SyntaxChecker = {};

ORYX.I18N.SyntaxChecker.group = "验证";
ORYX.I18N.SyntaxChecker.name = "语法检查器";
ORYX.I18N.SyntaxChecker.desc = "检查语法";
ORYX.I18N.SyntaxChecker.noErrors = "没有语法错误。";
ORYX.I18N.SyntaxChecker.invalid = "服务器无反应";
ORYX.I18N.SyntaxChecker.checkingMessage = "检查 ...";

if(!ORYX.I18N.Deployer) ORYX.I18N.Deployer = {};

ORYX.I18N.Deployer.group = "部署";
ORYX.I18N.Deployer.name = "部署";
ORYX.I18N.Deployer.desc = "部署到引擎";

if(!ORYX.I18N.Undo) ORYX.I18N.Undo = {};

ORYX.I18N.Undo.group = "撤销";
ORYX.I18N.Undo.undo = "撤销";
ORYX.I18N.Undo.undoDesc = "撤销最近动作";
ORYX.I18N.Undo.redo = "恢复";
ORYX.I18N.Undo.redoDesc = "恢复最近撤销";

if(!ORYX.I18N.View) ORYX.I18N.View = {};

ORYX.I18N.View.group = "缩放";
ORYX.I18N.View.zoomIn = "获得焦点";
ORYX.I18N.View.zoomInDesc = "放大模型";
ORYX.I18N.View.zoomOut = "失去焦点";
ORYX.I18N.View.zoomOutDesc = "缩小模型";
ORYX.I18N.View.zoomStandard = "细化标准";
ORYX.I18N.View.zoomStandardDesc = "放大到标准水平";
ORYX.I18N.View.zoomFitToModel = "变焦拟合模型";
ORYX.I18N.View.zoomFitToModelDesc = "缩放到适合的模型尺寸";

/** New Language Properties: 08.12.2008 */

ORYX.I18N.PropertyWindow.title = "属性";

if(!ORYX.I18N.ShapeRepository) ORYX.I18N.ShapeRepository = {};
ORYX.I18N.ShapeRepository.title = "图形库";

ORYX.I18N.Save.dialogDesciption = "请输入 名称, 描述和备注。";
ORYX.I18N.Save.dialogLabelTitle = "标题";
ORYX.I18N.Save.dialogLabelDesc = "描述";
ORYX.I18N.Save.dialogLabelType = "类型";
ORYX.I18N.Save.dialogLabelComment = "Revision comment";

Ext.MessageBox.buttonText.yes = "是";
Ext.MessageBox.buttonText.no = "否";
Ext.MessageBox.buttonText.cancel = "取消";
Ext.MessageBox.buttonText.ok = "确定";

if(!ORYX.I18N.Perspective) ORYX.I18N.Perspective = {};
ORYX.I18N.Perspective.no = "不透明"
ORYX.I18N.Perspective.noTip = "卸载当前的视角"

/** New Language Properties: 21.04.2009 */
ORYX.I18N.JSONSupport = {
    imp: {
        name: "从JSON导入",
        desc: "从JSON导入模型",
        group: "导出",
        selectFile: "选择一个JSON (.json)文件类型导入!",
        file: "文件",
        btnImp: "导入",
        btnClose: "关闭",
        progress: "导入中 ...",
        syntaxError: "语法错误"
    },
    exp: {
        name: "导出到JSON",
        desc: "导出当前模型到JSON",
        group: "导出"
    }
};

/** New Language Properties: 09.05.2009 */
if(!ORYX.I18N.JSONImport) ORYX.I18N.JSONImport = {};

ORYX.I18N.JSONImport.title = "JSON导入";
ORYX.I18N.JSONImport.wrongSS = "导入的文件({0})不匹配加载的模板集({1})。"

/** New Language Properties: 14.05.2009 */
if(!ORYX.I18N.RDFExport) ORYX.I18N.RDFExport = {};
ORYX.I18N.RDFExport.group = "导出";
ORYX.I18N.RDFExport.rdfExport = "导出到RDF";
ORYX.I18N.RDFExport.rdfExportDescription = "导出当前模型到RDF的xml序列化定义";

/** New Language Properties: 15.05.2009*/
if(!ORYX.I18N.SyntaxChecker.BPMN) ORYX.I18N.SyntaxChecker.BPMN={};
ORYX.I18N.SyntaxChecker.BPMN_NO_SOURCE = "流路径必须有一个源。";
ORYX.I18N.SyntaxChecker.BPMN_NO_TARGET = "流路径必须有一个目标。";
ORYX.I18N.SyntaxChecker.BPMN_DIFFERENT_PROCESS = "一个流程必须包含源和目标节点。";
ORYX.I18N.SyntaxChecker.BPMN_SAME_PROCESS = "源和目标节点必须包含在不同的池。";
ORYX.I18N.SyntaxChecker.BPMN_FLOWOBJECT_NOT_CONTAINED_IN_PROCESS = "一个流对象必须包含在一个流程。";
ORYX.I18N.SyntaxChecker.BPMN_ENDEVENT_WITHOUT_INCOMING_CONTROL_FLOW = "结束事件必须有一个进入顺序流。";
ORYX.I18N.SyntaxChecker.BPMN_STARTEVENT_WITHOUT_OUTGOING_CONTROL_FLOW = "开始事件必须有一个外出顺序流。";
ORYX.I18N.SyntaxChecker.BPMN_STARTEVENT_WITH_INCOMING_CONTROL_FLOW = "启动事件不能进入顺序流。";
ORYX.I18N.SyntaxChecker.BPMN_ATTACHEDINTERMEDIATEEVENT_WITH_INCOMING_CONTROL_FLOW = "附加的中间事件不能进入顺序流。";
ORYX.I18N.SyntaxChecker.BPMN_ATTACHEDINTERMEDIATEEVENT_WITHOUT_OUTGOING_CONTROL_FLOW = "附加的中间事件必须只有一个外出顺序流。";
ORYX.I18N.SyntaxChecker.BPMN_ENDEVENT_WITH_OUTGOING_CONTROL_FLOW = "结束事件不能外出顺序流。";
ORYX.I18N.SyntaxChecker.BPMN_EVENTBASEDGATEWAY_BADCONTINUATION = "基于事件的网关不能跟着网关或过程。";
ORYX.I18N.SyntaxChecker.BPMN_NODE_NOT_ALLOWED = "节点类型是不允许的。";

if(!ORYX.I18N.SyntaxChecker.IBPMN) ORYX.I18N.SyntaxChecker.IBPMN={};
ORYX.I18N.SyntaxChecker.IBPMN_NO_ROLE_SET = "Interactions必须有发件人和接收人角色。";
ORYX.I18N.SyntaxChecker.IBPMN_NO_INCOMING_SEQFLOW = "该节点必须有进入顺序流。";
ORYX.I18N.SyntaxChecker.IBPMN_NO_OUTGOING_SEQFLOW = "该节点必须外出顺序流。";

if(!ORYX.I18N.SyntaxChecker.InteractionNet) ORYX.I18N.SyntaxChecker.InteractionNet={};
ORYX.I18N.SyntaxChecker.InteractionNet_SENDER_NOT_SET = "发件人未设置。";
ORYX.I18N.SyntaxChecker.InteractionNet_RECEIVER_NOT_SET = "接收人未设置。";
ORYX.I18N.SyntaxChecker.InteractionNet_MESSAGETYPE_NOT_SET = "消息类型未设置。";
ORYX.I18N.SyntaxChecker.InteractionNet_ROLE_NOT_SET = "角色未设置。";

if(!ORYX.I18N.SyntaxChecker.EPC) ORYX.I18N.SyntaxChecker.EPC={};
ORYX.I18N.SyntaxChecker.EPC_NO_SOURCE = "流必须有源。";
ORYX.I18N.SyntaxChecker.EPC_NO_TARGET = "流必须有目标。";
ORYX.I18N.SyntaxChecker.EPC_NOT_CONNECTED = "节点必须连接流。";
ORYX.I18N.SyntaxChecker.EPC_NOT_CONNECTED_2 = "节点必须连接更多的流。";
ORYX.I18N.SyntaxChecker.EPC_TOO_MANY_EDGES = "连接节点的流太多了。";
ORYX.I18N.SyntaxChecker.EPC_NO_CORRECT_CONNECTOR = "节点不是正确的连接者。";
ORYX.I18N.SyntaxChecker.EPC_MANY_STARTS = "只能有一个启动事件。";
ORYX.I18N.SyntaxChecker.EPC_FUNCTION_AFTER_OR = "在分支OR/XOR后面不能再有函数。";
ORYX.I18N.SyntaxChecker.EPC_PI_AFTER_OR = "在分支OR/XOR后面不能再有过程接口。";
ORYX.I18N.SyntaxChecker.EPC_FUNCTION_AFTER_FUNCTION =  "在函数后面不能再有函数。";
ORYX.I18N.SyntaxChecker.EPC_EVENT_AFTER_EVENT =  "在事件后面不能再有事件。";
ORYX.I18N.SyntaxChecker.EPC_PI_AFTER_FUNCTION =  "在函数后面不能再有过程接口。";
ORYX.I18N.SyntaxChecker.EPC_FUNCTION_AFTER_PI =  "在过程接口后面不能再有函数。";
ORYX.I18N.SyntaxChecker.EPC_SOURCE_EQUALS_TARGET = "流(Edge)必须连接两个不同的节点"

if(!ORYX.I18N.SyntaxChecker.PetriNet) ORYX.I18N.SyntaxChecker.PetriNet={};
ORYX.I18N.SyntaxChecker.PetriNet_NOT_BIPARTITE = "该图不是二分。";
ORYX.I18N.SyntaxChecker.PetriNet_NO_LABEL = "标签没有设置标签路劲";
ORYX.I18N.SyntaxChecker.PetriNet_NO_ID = "节点没有设置id";
ORYX.I18N.SyntaxChecker.PetriNet_SAME_SOURCE_AND_TARGET = "两个关联流具有相同的源和目标";
ORYX.I18N.SyntaxChecker.PetriNet_NODE_NOT_SET = "节点没有设置关联流";

/** New Language Properties: 02.06.2009*/
ORYX.I18N.Edge = "边缘";
ORYX.I18N.Node = "节点";

/** New Language Properties: 03.06.2009*/
ORYX.I18N.SyntaxChecker.notice = "移动鼠标到红十字图标查看错误消息。";

/** New Language Properties: 05.06.2009*/
if(!ORYX.I18N.RESIZE) ORYX.I18N.RESIZE = {};
ORYX.I18N.RESIZE.tipGrow = "增加画布尺寸:";
ORYX.I18N.RESIZE.tipShrink = "减少画布尺寸:";
ORYX.I18N.RESIZE.N = "顶部";
ORYX.I18N.RESIZE.W = "左边";
ORYX.I18N.RESIZE.S ="底部";
ORYX.I18N.RESIZE.E ="右边";

/** New Language Properties: 15.07.2009*/
if(!ORYX.I18N.Layouting) ORYX.I18N.Layouting ={};
ORYX.I18N.Layouting.doing = "布局...";

/** New Language Properties: 18.08.2009*/
ORYX.I18N.SyntaxChecker.MULT_ERRORS = "多个错误";

/** New Language Properties: 08.09.2009*/
if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};
ORYX.I18N.PropertyWindow.oftenUsed = "常用";
ORYX.I18N.PropertyWindow.moreProps = "更多属性";

/** New Language Properties 01.10.2009 */
if(!ORYX.I18N.SyntaxChecker.BPMN2) ORYX.I18N.SyntaxChecker.BPMN2 = {};

ORYX.I18N.SyntaxChecker.BPMN2_DATA_INPUT_WITH_INCOMING_DATA_ASSOCIATION = "输入数据必须没有任何传入的关联数据。";
ORYX.I18N.SyntaxChecker.BPMN2_DATA_OUTPUT_WITH_OUTGOING_DATA_ASSOCIATION = "输出数据必须没有任何传出的关联数据。";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_TARGET_WITH_TOO_MANY_INCOMING_SEQUENCE_FLOWS = "基于事件的网关的目标只能有一个进入顺序流。";

/** New Language Properties 02.10.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WITH_TOO_LESS_OUTGOING_SEQUENCE_FLOWS = "基于事件的网关必须有两个或两个以上的外出顺序流。";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_EVENT_TARGET_CONTRADICTION = "如果消息中间事件中使用了配置，则不得使用接受任务，反之亦然。";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WRONG_TRIGGER = "只有以下事件触发器是有效的：消息，信号，定时器，条件和多实例。";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WRONG_CONDITION_EXPRESSION = "事件网关的流出序列流必须无条件的表达。";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_NOT_INSTANTIATING = "网关不能满足流程实例化的条件，请为网关使用一个启动事件或初始化属性。";

/** New Language Properties 05.10.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAYDIRECTION_MIXED_FAILURE = "网关必须有多个输入和输出的顺序流。";
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAYDIRECTION_CONVERGING_FAILURE = "网关必须拥有多个进入但不能有多个外出顺序流。";
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAYDIRECTION_DIVERGING_FAILURE = "网关不能有多个进入但必须有多个外出顺序流。";
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAY_WITH_NO_OUTGOING_SEQUENCE_FLOW = "网关必须至少有一个外出顺序流。";
ORYX.I18N.SyntaxChecker.BPMN2_RECEIVE_TASK_WITH_ATTACHED_EVENT = "接收用于事件网关的配置任务必须没有任何附加的中间事件。";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_SUBPROCESS_BAD_CONNECTION = "一个事件不能有任何输入或输出流。";

/** New Language Properties 13.10.2009 */
ORYX.I18N.SyntaxChecker.BPMN_MESSAGE_FLOW_NOT_CONNECTED = "信息流的一侧必须被连接。";

/** New Language Properties 24.11.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_TOO_MANY_INITIATING_MESSAGES = "一个编排活动只能有一个启动消息。";
ORYX.I18N.SyntaxChecker.BPMN_MESSAGE_FLOW_NOT_ALLOWED = "这里不允许信息流。";

/** New Language Properties 27.11.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WITH_TOO_LESS_INCOMING_SEQUENCE_FLOWS = "基于事件的网关至少有一个进入顺序流。";
ORYX.I18N.SyntaxChecker.BPMN2_TOO_FEW_INITIATING_PARTICIPANTS = "编排活动必须有一个初始化参与者（白色）。";
ORYX.I18N.SyntaxChecker.BPMN2_TOO_MANY_INITIATING_PARTICIPANTS = "编排活动不能有多个初始化参与者（白色）。"

ORYX.I18N.SyntaxChecker.COMMUNICATION_AT_LEAST_TWO_PARTICIPANTS = "The communication必须连接到至少两个参与者";
ORYX.I18N.SyntaxChecker.MESSAGEFLOW_START_MUST_BE_PARTICIPANT = "消息流的源必须是一个参与者。";
ORYX.I18N.SyntaxChecker.MESSAGEFLOW_END_MUST_BE_PARTICIPANT = "消息流的目标必须是一个参与者。";
ORYX.I18N.SyntaxChecker.CONV_LINK_CANNOT_CONNECT_CONV_NODES = "The conversation link must connect a communication or sub conversation node with a participant.";
