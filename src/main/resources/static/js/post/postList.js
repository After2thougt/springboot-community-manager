
var pageCurr;
var form;

$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;

        tableIns=table.render({
            elem: '#postList',
            url:'/post/getPostList',
            method: 'get', //默认：get请求
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response:{
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type:'numbers'}
                                ,{field:'postId', title:'发帖id',align:'center'}
                                ,{field:'publishUserName', title:'发帖用户',align:'center'}
                                ,{field:'postTitle', title:'标题',align:'center'}
                                ,{field:'postCategoryName', title:'帖子分类',align:'center'}
                                ,{field:'postStatus', title:'帖子状态',align:'center'}
                                ,{field:'updateTime', title: '最后修改时间',align:'center'}
                                ,{field:'createTime', title: '发布时间',align:'center'}
                                ,{title:'操作',align:'center', toolbar:'#optBar'}
            ]],
            done: function(res, curr, count){
                $("[data-field='postStatus']").children().each(function(){
                    if($(this).text()=='1'){
                        $(this).text("正常")
                    }else if($(this).text()=='0'){
                        $(this).text("已删除")
                    }
                });

                $("[data-field='updateTime']").children().each(function(){

                    if($(this).text()=='最后修改时间'){
                            $(this).text("最后修改时间")
                        }else if($(this).text()==''||$(this).text()==null||$(this).text()==undefined){
                             $(this).text(" ")
                        }else {
                             var dT = $(this).text();
                             var date = new Date(dT);//这句就需要添加在这里面

                             var year = date.getFullYear(); //年
                             var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1; //月
                             var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate(); //日
                             var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours(); //时
                             var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes(); //分
                             var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds(); //秒

                             var datetime = year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;

                              $(this).text(datetime);
                         }

                    });

                pageCurr=curr;

            }
        });


        //监听工具条
        table.on('tool(postTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                //删除
                delPost(data,data.postId);
            } else if(obj.event === 'edit'){
                //编辑
                edit(data, "编辑");
            }else if(obj.event === 'recover'){
                //恢复
                recoverPost(data,data.postId);
            }
        });

        //监听提交
        form.on('submit(postSubmit)', function(data){
            formSubmit(data);
            return false;
        });

    });

    //搜索框
        layui.use(['form','laydate'], function(){
            var form = layui.form ,layer = layui.layer
                ,laydate = layui.laydate;
            //日期
            laydate.render({
                elem: '#startTime'
            });
            laydate.render({
                elem: '#endTime'
            });
            //TODO 数据校验
            //监听搜索框
            form.on('submit(searchSubmit)', function(data){
                //重新加载table
                load(data);
                return false;
            });
        });

});

//提交表单
function formSubmit(obj){
    $.ajax({
        type: "post",
        data: $("#postForm").serialize(),
        url: "/post/setPost",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg,function(){
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",function(){
                layer.closeAll();
                load(obj);
            });
        }
    });
}

//打开编辑框
function edit(data,title){

    var postCategoryId = null;

    if(data == null){
        $("#postId").val("");
    }else{
        // 回显数据
        $("#postId").val(data.postId);
        $("#postTitle").val(data.postTitle);
        $("#postCategoryName").val(data.postCategoryName);
        $("#postContent").val(data.postContent);

    }

    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);

    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px','350px'],
        content:$('#setPost'),
        end:function(){
            cleanPost();
        }
    });
}

//重新加载table
function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

//删除
function delPost(obj,post_id) {
    if(null!=post_id){
        layer.confirm('您确定要删除吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/post/updatePostStatus",{"postId":post_id,"postStatus":0},function(data){
                if (data.code == 1) {
                    layer.alert(data.msg,function(){
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}
//恢复
function recoverPost(obj,post_id) {
    if(null!=post_id){
        layer.confirm('您确定要恢复吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/post/updatePostStatus",{"postId":post_id,"postStatus":1},function(data){
                if (data.code == 1) {
                    layer.alert(data.msg,function(){
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}


function cleanPost() {
    $("#postId").val("");
    $("#postTitle").val("");
    $("#postCategoryName").val("");
}

