/**
 * 用户管理
 */
var pageCurr;
var form;
$(function() {
    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;

        tableIns=table.render({
            elem: '#userList',
            url:'/user/getUserList',
            method: 'post', //默认：get请求
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
                ,{field:'userEmail', title:'邮箱',align:'center'}
                ,{field:'sysUserName', title:'用户名',align:'center'}
                ,{field:'roleName', title:'角色类型',align:'center'}
                ,{field:'gender', title:'性别',align:'center'}
                ,{field:'location', title:'所在地',align:'center'}
                ,{field:'introduce', title:'个人介绍',align:'center'}
                ,{field:'userStatus', title: '账号状态',align:'center'}
                ,{field:'lastLoginTime', title: '最近一次登录时间',align:'center'}
                ,{field:'regTime', title: '注册时间',align:'center'}
                ,{title:'操作',align:'center', toolbar:'#optBar'}
            ]],
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);

                $("[data-field='userStatus']").children().each(function(){
                    if($(this).text()=='0'){
                        $(this).text("正常")
                    }else if($(this).text()=='1'){
                        $(this).text("已禁用")
                    }
                });

                $("[data-field='lastLoginTime']").children().each(function(){

                    if($(this).text()=='最近一次登录时间'){
                            $(this).text("最近一次登录时间")
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

                    $("[data-field='regTime']").children().each(function(){

                        if($(this).text()=='注册时间'){
                                $(this).text("注册时间")
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


                //得到数据总量
                //console.log(count);
                pageCurr=curr;
            }
        });

        //监听工具条
        table.on('tool(userTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                //删除
                delUser(data,data.id,data.sysUserName);
            } else if(obj.event === 'edit'){
                //编辑
                openUser(data,"编辑");
            }else if(obj.event === 'recover'){
                //恢复
                recoverUser(data,data.id);
            }
        });

        //监听提交
        form.on('submit(userSubmit)', function(data){
            // TODO 校验
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
        type: "POST",
        data: $("#userForm").serialize(),
        url: "/user/setUser",
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
                //加载load方法
                load(obj);//自定义
            });
        }
    });
}

//新增用户
function addUser(){
    openUser(null,"新增用户");
}

// 新增或编辑用户
function openUser(data, title) {
    var roleId = null;
    if (data == null || data == "") {
        // 新增用户，显示密码栏
        $("#password").parent().parent().show();
        $("#id").val("");
    } else {
        // 编辑用户，隐藏密码栏
        $("#password").parent().parent().hide();
        // 回显数据
        $("#id").val(data.id);
        $("#username").val(data.sysUserName);
        $("#userEmail").val(data.userEmail);
        $("#sysUserPwd").val(data.sysUserPwd);
        roleId = data.roleId;
    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);

    $.ajax({
        url:'/role/getRoles',
        dataType:'json',
        async: true,
        success:function(data){
            $.each(data,function(index,item){
                if(!roleId){
                    var option = new Option(item.roleName,item.id);
                }else {
                    var option = new Option(item.roleName,item.id);
                    // // 如果是之前的parentId则设置选中
                    if(item.id == roleId) {
                        option.setAttribute("selected",'true');
                    }
                }
                $('#roleId').append(option);//往下拉菜单里添加元素
                form.render('select'); //这个很重要
            })
        }
    });

    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setUser'),
        end:function(){
            cleanUser();
        }
    });
}

function delUser(obj,id,name) {
    var currentUser=$("#currentUser").html();
    if(null!=id){
        if(currentUser==id){
            layer.alert("对不起，您不能执行禁用自己的操作");
        }else{
            layer.confirm('您确定要禁用'+name+'用户吗？', {
                btn: ['确认','返回'] //按钮
            }, function(){
                $.post("/user/updateUserStatus",{"id":id,"status":1},function(data){
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
}
//恢复
function recoverUser(obj,id) {
    if(null!=id){
        layer.confirm('您确定要恢复吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/user/updateUserStatus",{"id":id,"status":0},function(data){
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

function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function cleanUser(){
    $("#username").val("");
    $("#userEmail").val("");
    $("#password").val("");
    $('#roleId').html("");
}
