(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3556c2f7"],{"09f4":function(t,e,a){"use strict";a.d(e,"a",(function(){return i})),Math.easeInOutQuad=function(t,e,a,n){return t/=n/2,t<1?a/2*t*t+e:(t--,-a/2*(t*(t-2)-1)+e)};var n=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function r(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function o(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function i(t,e,a){var i=o(),s=t-i,c=20,u=0;e="undefined"===typeof e?500:e;var l=function t(){u+=c;var o=Math.easeInOutQuad(u,i,s,e);r(o),u<e?n(t):a&&"function"===typeof a&&a()};l()}},"1e38":function(t,e,a){},5843:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"cloudAccountManagement"},[a("div",{staticClass:"top"},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"请输入关键字"},model:{value:t.serchValue,callback:function(e){t.serchValue=e},expression:"serchValue"}}),t._v(" "),a("el-button",{staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.search}},[t._v("搜索")]),t._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.addCloud}},[t._v("添加系统盘任务")]),t._v(" "),a("el-button",{attrs:{type:"primary",icon:"el-icon-refresh"},on:{click:t.refreshList}},[t._v("刷新列表")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],attrs:{data:t.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"任务名称"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.name))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"源主机",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.sourceServerName))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"主机类型",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[0==e.row.sourceServerSystemType?a("span",[t._v("Linux")]):t._e(),t._v(" "),1==e.row.sourceServerSystemType?a("span",[t._v("Windows")]):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"云账户",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.cloudAccountName))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"目标地域",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.targetArea))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"迁移状态",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[0==e.row.status?a("span",[t._v("未启动")]):t._e(),t._v(" "),1==e.row.status?a("span",[t._v("失败")]):t._e(),t._v(" "),2==e.row.status?a("span",[t._v("运行中")]):t._e(),t._v(" "),3==e.row.status?a("span",[t._v("成功")]):t._e(),t._v(" "),4==e.row.status?a("span",[t._v("停止")]):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"操作",align:"center",width:"400"},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.row;return[0===n.status||1===n.status||4===n.status?a("el-button",{attrs:{type:"primary",size:"mini",loading:t.startLoading["bool"+n.id]},on:{click:function(e){return t.startDiskTask(n)}}},[t._v("启动")]):t._e(),t._v(" "),3===n.status?a("el-button",{attrs:{type:"primary",disabled:""},on:{click:function(e){return t.startDiskTask(n)}}},[t._v("启动")]):t._e(),t._v(" "),2===n.status?a("el-button",{attrs:{type:"primary",size:"mini",loading:t.stopLoading["bool"+n.id]},on:{click:function(e){return t.stopTencentCloud(n)}}},[t._v("停止")]):t._e(),t._v(" "),0===n.status||1===n.status||3===n.status||4===n.status?a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.handleUpdate(n)}}},[t._v("编辑")]):t._e(),t._v(" "),2===n.status?a("el-button",{attrs:{type:"primary",disabled:"",size:"mini"},on:{click:function(e){return t.handleUpdate(n)}}},[t._v("编辑")]):t._e(),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.tailLogs(n)}}},[t._v("查看日志")]),t._v(" "),"deleted"===n.status||0!==n.status&&1!==n.status&&3!==n.status&&4!==n.status?t._e():a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(e){return t.handleDelete(n)}}},[t._v("删除")]),t._v(" "),"deleted"!==n.status&&2===n.status?a("el-button",{attrs:{disabled:"",size:"mini",type:"danger"},on:{click:function(e){return t.handleDelete(n)}}},[t._v("删除")]):t._e()]}}])})],1),t._v(" "),a("Pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.current,limit:t.listQuery.size},on:{"update:page":function(e){return t.$set(t.listQuery,"current",e)},"update:limit":function(e){return t.$set(t.listQuery,"size",e)},pagination:t.fetchData}}),t._v(" "),a("el-dialog",{attrs:{width:"600px",title:"创建系统盘任务",visible:t.dialogFormVisible,"before-close":t.handleClose},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"500px","margin-left":"0px"},attrs:{rules:t.rules,model:t.temp,"show-message":!0,"label-position":"right","label-width":"100px"}},[a("el-form-item",{attrs:{label:"任务名称",prop:"name"}},[a("el-input",{attrs:{placeholder:"请输入任务名称"},model:{value:t.temp.name,callback:function(e){t.$set(t.temp,"name",e)},expression:"temp.name"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"源主机",prop:"sourceServerId"}},[a("el-select",{staticClass:"filter-item",attrs:{placeholder:"请选择源主机"},model:{value:t.temp.sourceServerId,callback:function(e){t.$set(t.temp,"sourceServerId",e)},expression:"temp.sourceServerId"}},t._l(t.sourceServerList,(function(t,e){return a("el-option",{key:e,attrs:{label:t.name,value:t.id}})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"云账户",prop:"cloudAccountId"}},[a("el-select",{staticClass:"filter-item",attrs:{placeholder:"请选择云账户"},on:{change:function(e){return t.getlistDescribeRegions()}},model:{value:t.temp.cloudAccountId,callback:function(e){t.$set(t.temp,"cloudAccountId",e)},expression:"temp.cloudAccountId"}},t._l(t.accountList,(function(t,e){return a("el-option",{key:e,attrs:{label:t.name,value:t.id}})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"目标地域",prop:"targetArea"}},[a("el-select",{staticClass:"filter-item",attrs:{placeholder:"请选择目标地域"},on:{change:function(e){return t.getInstanceIdList()}},model:{value:t.temp.targetArea,callback:function(e){t.$set(t.temp,"targetArea",e)},expression:"temp.targetArea"}},t._l(t.reginsList,(function(t,e){return a("el-option",{key:e,attrs:{label:t,value:t}})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"实例名称",prop:"cloudExampleName"}},[a("el-select",{staticClass:"filter-item",attrs:{placeholder:"请选择实例名称"},model:{value:t.temp.cloudExampleName,callback:function(e){t.$set(t.temp,"cloudExampleName",e)},expression:"temp.cloudExampleName"}},t._l(t.cloudExampleNameList,(function(t,e){return a("el-option",{key:e,attrs:{label:t,value:t}})})),1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:t.handleClose}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){"create"===t.dialogStatus?t.createData():t.updataData()}}},[t._v(t._s("create"===t.dialogStatus?"添加":"更新"))])],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"服务器迁移日志",visible:t.dialogTableVisible},on:{"update:visible":function(e){t.dialogTableVisible=e}}},[a("div",{staticClass:"log_list"},t._l(t.gridData,(function(e,n){return a("p",{key:n},[t._v(t._s(e))])})),0)])],1)},r=[],o=a("5530"),i=a("333d"),s=a("78d1"),c={name:"systemDiskTask",components:{Pagination:i["a"]},data:function(){return{serchValue:"",listLoading:!1,list:null,total:0,listQuery:{current:1,size:10,username:void 0},dialogFormVisible:!1,dialogStatus:"",sourceServerList:"",accountList:[],reginsList:[],temp:{name:"",sourceServerName:"",cloudAccountName:"",targetArea:"",cloudExampleName:""},resetTemp:function(){this.temp=this.$options.data().temp},servers:[],clouds:[],targets:[],rules:{name:[{required:!0,message:"请输入任务名称",trigger:"blur"}],sourceServerId:[{required:!0,message:"请选择源主机",trigger:"blur"}],cloudAccountId:[{required:!0,message:"请输入云账户",trigger:"blur"}],targetArea:[{required:!0,message:"请输入目标地域",trigger:"blur"}],cloudExampleName:[{required:!0,message:"请输入实例名称",trigger:"blur"}]},marks:{50:"50M",200:"200M",500:"500M"},status:0,dialogTableVisible:!1,gridData:null,startLoading:{},stopLoading:{},cloudExampleNameList:null}},created:function(){this.fetchData()},methods:{fetchData:function(){this.listLoading=!0;var t=this,e={current:this.listQuery.current,size:this.listQuery.size,username:this.serchValue};s["r"](e).then((function(e){console.log(e);var a=e.content;console.log(a),t.list=a.data,t.listLoading=!1,t.total=e.content.recordsTotal;for(var n=0;n<e.content.recordsTotal.length;n++)t.startLoading["bool"+a.data[n].id]=!1,t.stopLoading["bool"+a.data[n].id]=!1}))},accountidchange:function(t){console.log(t)},search:function(){this.listQuery={current:1,size:10,username:void 0},this.listLoading=!0,this.fetchData(),this.serchValue=""},addCloud:function(){var t=this;this.resetTemp(),this.getAccountList(),this.getSourceServerList(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["dataForm"].clearValidate()}))},createData:function(){var t=this;this.$refs["dataForm"].validate((function(e){e&&s["e"](t.temp).then((function(){t.fetchData(),t.dialogFormVisible=!1,t.$notify({title:"Success",message:"Created Successfully",type:"success",duration:2e3})}))}))},getSourceServerList:function(){var t=this;s["t"]().then((function(e){var a=e.content;t.sourceServerList=a}))},getAccountList:function(){var t=this;s["s"]().then((function(e){console.log("getNoPageList",e);var a=e.content;t.accountList=a}))},getlistDescribeRegions:function(){var t=this;this.temp.targetArea="";var e={accountid:this.temp.cloudAccountId};s["u"](e).then((function(e){var a=e.content;t.reginsList=a}))},startDiskTask:function(t){var e=this,a=Object(o["a"])({},t);this.startLoading["bool"+t.id]=!0,s["w"](a).then((function(a){e.fetchData(),e.startLoading["bool"+t.id]=!1,e.$notify({title:"Success",message:"start Successfully",type:"success",duration:2e3})})).catch((function(a){e.startLoading["bool"+t.id]=!1}))},stopTencentCloud:function(t){var e=this,a=Object(o["a"])({},t);this.stopLoading["bool"+t.id]=!0,s["y"](a).then((function(a){e.fetchData(),e.stopLoading["bool"+t.id]=!1,e.$notify({title:"Success",message:"stop Successfully",type:"success",duration:2e3})})).catch((function(a){e.stopLoading["bool"+t.id]=!1}))},tailLogs:function(t){var e=this;console.log(t);var a={sourceServerId:t.sourceServerId};s["z"](a).then((function(t){e.dialogTableVisible=!0,e.gridData=t.content}))},clearData:function(){this.temp={name:"",sourceServerName:"",cloudAccountName:"",targetArea:"",cloudExampleName:""},this.temp.startdate=""},handleUpdate:function(t){console.log(t),this.dialogStatus="updata",this.dialogFormVisible=!0,this.temp=Object(o["a"])({},t)},updataData:function(){var t=this,e=Object(o["a"])({},this.temp);s["C"](e).then((function(e){console.log("response",e),t.dialogFormVisible=!1,t.fetchData(),t.$notify({title:"Success",message:"updata Successfully",type:"success",duration:2e3})}))},handleDelete:function(t){var e=this,a=this;this.$confirm("确定删除此条信息？",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(){s["j"](t.id).then((function(t){console.log(t),a.fetchData()}))})).catch((function(){e.$message({type:"info",message:"已取消删除"})}))},handleClose:function(){this.clearData(),this.dialogFormVisible=!1},refreshList:function(){this.fetchData()},getInstanceIdList:function(){var t=this,e={accountid:this.temp.cloudAccountId,region:this.temp.targetArea};s["o"](e).then((function(e){console.log("response",e),t.cloudExampleNameList=e.content}))}}},u=c,l=(a("f42d"),a("2877")),d=Object(l["a"])(u,n,r,!1,null,null,null);e["default"]=d.exports},"78d1":function(t,e,a){"use strict";a.d(e,"p",(function(){return o})),a.d(e,"a",(function(){return i})),a.d(e,"f",(function(){return s})),a.d(e,"A",(function(){return c})),a.d(e,"q",(function(){return u})),a.d(e,"d",(function(){return l})),a.d(e,"i",(function(){return d})),a.d(e,"B",(function(){return p})),a.d(e,"r",(function(){return m})),a.d(e,"e",(function(){return f})),a.d(e,"j",(function(){return b})),a.d(e,"C",(function(){return g})),a.d(e,"s",(function(){return h})),a.d(e,"t",(function(){return v})),a.d(e,"u",(function(){return _})),a.d(e,"w",(function(){return O})),a.d(e,"y",(function(){return y})),a.d(e,"z",(function(){return j})),a.d(e,"o",(function(){return k})),a.d(e,"b",(function(){return S})),a.d(e,"l",(function(){return L})),a.d(e,"m",(function(){return T})),a.d(e,"k",(function(){return w})),a.d(e,"D",(function(){return x})),a.d(e,"g",(function(){return D})),a.d(e,"n",(function(){return A})),a.d(e,"c",(function(){return C})),a.d(e,"h",(function(){return I})),a.d(e,"E",(function(){return N})),a.d(e,"v",(function(){return V})),a.d(e,"x",(function(){return $}));var n=a("5530"),r=a("b775");function o(t){return Object(r["a"])({url:"/api/cloud/pageList",method:"get",params:Object(n["a"])({},t)})}function i(t){return Object(r["a"])({url:"/api/cloud/add",method:"POST",data:Object(n["a"])({},t)})}function s(t){return Object(r["a"])({url:"/api/cloud/del?id=".concat(t),method:"POST",data:Object(n["a"])({},t)})}function c(t){return Object(r["a"])({url:"/api/cloud/update",method:"POST",data:Object(n["a"])({},t)})}function u(t){return Object(r["a"])({url:"/api/source/pageList",method:"get",params:Object(n["a"])({},t)})}function l(t){return Object(r["a"])({url:"/api/source/add",method:"POST",data:Object(n["a"])({},t)})}function d(t){return Object(r["a"])({url:"/api/source/del?id=".concat(t),method:"POST",data:Object(n["a"])({},t)})}function p(t){return Object(r["a"])({url:"/api/source/update",method:"POST",data:Object(n["a"])({},t)})}function m(t){return Object(r["a"])({url:"/api/task/pageList",method:"get",params:Object(n["a"])({},t)})}function f(t){return Object(r["a"])({url:"/api/task/add",method:"POST",data:Object(n["a"])({},t)})}function b(t){return Object(r["a"])({url:"/api/task/del?id=".concat(t),method:"POST",data:Object(n["a"])({},t)})}function g(t){return Object(r["a"])({url:"/api/task/update",method:"POST",data:Object(n["a"])({},t)})}function h(t){return Object(r["a"])({url:"/api/cloud/list",method:"get",params:Object(n["a"])({},t)})}function v(t){return Object(r["a"])({url:"/api/source/list",method:"get",params:Object(n["a"])({},t)})}function _(t){return Object(r["a"])({url:"/api/cloud/listRegions",method:"get",params:Object(n["a"])({},t)})}function O(t){return Object(r["a"])({url:"/api/task/startTencentCloud",method:"POST",data:Object(n["a"])({},t)})}function y(t){return Object(r["a"])({url:"/api/task/stopTencentCloud",method:"POST",data:Object(n["a"])({},t)})}function j(t){return Object(r["a"])({url:"/api/task/tailLogs",method:"get",params:Object(n["a"])({},t)})}function k(t){return Object(r["a"])({url:"/api/task/getInstanceIdList",method:"get",params:Object(n["a"])({},t)})}function S(t){return Object(r["a"])({url:"/api/container/add",method:"POST",data:Object(n["a"])({},t)})}function L(t){return Object(r["a"])({url:"/api/container/pageList",method:"get",params:Object(n["a"])({},t)})}function T(t){return Object(r["a"])({url:"/api/container/list",method:"get",params:Object(n["a"])({},t)})}function w(t){return Object(r["a"])({url:"/api/container/listBucket",method:"get",params:Object(n["a"])({},t)})}function x(t){return Object(r["a"])({url:"/api/container/update",method:"POST",data:Object(n["a"])({},t)})}function D(t){return Object(r["a"])({url:"/api/container/del?id=".concat(t),method:"POST"})}function A(t){return Object(r["a"])({url:"/api/containertask/pageList",method:"get",params:Object(n["a"])({},t)})}function C(t){return Object(r["a"])({url:"/api/containertask/add",method:"post",data:Object(n["a"])({},t)})}function I(t){return Object(r["a"])({url:"/api/containertask/del?id=".concat(t),method:"post"})}function N(t){return Object(r["a"])({url:"/api/containertask/update",method:"post",data:Object(n["a"])({},t)})}function V(t){return Object(r["a"])({url:"/api/containertask/startContainerTask",method:"post",data:Object(n["a"])({},t)})}function $(t){return Object(r["a"])({url:"/api/containertask/stopContainerTask",method:"post",data:Object(n["a"])({},t)})}},f42d:function(t,e,a){"use strict";var n=a("1e38"),r=a.n(n);r.a}}]);