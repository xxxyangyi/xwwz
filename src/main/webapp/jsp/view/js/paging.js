/**
 * Created by tuberose on 16-9-6.
 */

/*
 *  InsertID    :   插入位置的DIV 的ID
 *  ModelId     :   模板的 DIV 的 ID
 *  actionName  :   action
 *  PageNo  :   当前的页数
 * */
function pagingStart(InsertID, ModelId, ActionName, isPaging) {
    paging(InsertID, ModelId, ActionName,1,isPaging)

}

function paging(InsertID, ModelId, ActionName,PageNo,isPaging) {
    console.log("AJAX 发送请求 " + ActionName+ "  PageNo = "+ PageNo)
    var totelNumber = 0;
    var PageSize = 0;
    var PageNo = PageNo;
    var json = null;
    $.ajax({
        type: "post",
        data: {"PageNo":PageNo},
        url: ActionName,
        async: false,
        dataType: 'json',
        error : function() {
            alert("分页AJAX 出错");
        },
        success: function (msg) {
            console.log(msg);
            totelNumber = msg.rowCount;
            PageSize    = msg.pageSize;
            PageNo      = msg.pageNo;
            json        = msg.result;
        }
    })

    console.log("获取totelNumber---获取PageSize---获取PageNumber")
    // var json = ' { "programmers": [{ "firstName": "1", "lastName":"11", "email": "111" },' +
    //     '{ "firstName": "2", "lastName":"22", "email": "222" },' +
    //     '{ "firstName": "3", "lastName":"33", "email": "333" },' +
    //     '{ "firstName": "4", "lastName":"44", "email": "444" },' +
    //     '{ "firstName": "5", "lastName":"55", "email": "555" },' +
    //     '{ "firstName": "6", "lastName":"66", "email": "666" },' +
    //     '{ "firstName": "7", "lastName":"77", "email": "777" },' +
    //     '{ "firstName": "8", "lastName":"88", "email": "888" },' +
    //     '{ "firstName": "9", "lastName":"99", "email": "999" },' +
    //     '{ "firstName": "0", "lastName":"00", "email": "000" },' +
    //     ' ]} ';
    // json = eval("(" + json + ")");
    $(InsertID).children().remove();
    if(totelNumber==0 || json.length == 0){
        $(InsertID).append("<h4>没有更多数据了</h4>");
        return;

    }
    // 获取数据完成 end

    // 加载页面 ----   加载数据   加载分页

    // 1. 加载数据

    console.log("加载页面");
    var model_html = $(ModelId).html();
    var map = new UtilMap();

    var patt = new RegExp(/[@]{3}\w+[@]{1}/,"gmi");
    var result;
    while ((result = patt.exec(model_html)) != null)  {
        result = result.toString().replace(/([@]{3})(\w+)([@]{1})/, "$2");
        console.log(result+"----");
        map.put("@@@"+result+"@",result);
        console.log(map.arr)
    }
    // 进行循环打印
    for (var i = 0; i < json.length; i++) {
        var my_json = json[i];
        var linshiModelHtml = model_html;
        for (var j = 0; j < map.size(); j++) {
            linshiModelHtml = (linshiModelHtml.replace(map.getKey(j), my_json[map.get(map.getKey(j))])).toString();

            // console.log(map.getKey(j) + "   " + map.get(map.getKey(j)));
            // console.log("linshiModelHtml :" + linshiModelHtml);
        }

        $(InsertID).append(linshiModelHtml);

    }

    // 2 加载 分页信息
    if(isPaging==false){

    }else{
        console.log(Math.ceil(totelNumber/PageSize));

        var html = getPagingHtmlString(InsertID, ModelId,ActionName,Math.ceil(totelNumber/PageSize),PageNo)

        $(InsertID).append(html);
    }
}


// 总页数  当前页数  之前的页数
function getPagingHtmlString(InsertID, ModelId,ActionName,PageNumber,PageNo) {

    var html_head = '';
    var html_body = '';
    var html_foot = '';
    var PAGESIZE = 5;
    var position = PageNo % PAGESIZE;
    position == 0 ? position = PAGESIZE : position = position ;

    for(var i = 0; i < PAGESIZE; i++){
        var number = PageNo+(1 - position + i);
        if (number > PageNumber){
            html_body += '<li class="disabled" ><a>&nbsp&nbsp</a></li>';
        }else{
            if((1 - position + i) == 0){
                html_body += '<li class="active"><a>'+ number +'</a></li>';
            }else{
                html_body += (
                '<li><a href="javascript:paging(\''+InsertID+'\',\''+ModelId+'\',\''+ActionName+'\','+number+')">'+ number +'</a></li>');
            }
        }
    }
    if((PageNo-position)<=0){
        html_head = '<nav class="col-md-8 col-md-offset-2"><ul class="pagination"><li class="disabled" ><a >Prev</a></li>' ;
    }else{
        html_head = '<nav><ul class="pagination"><li><a href="javascript:paging(\''+InsertID+'\',\''+ModelId+'\',\''+ActionName+'\','+(PageNo-position)+')">Prev</a></li>' ;
    }

    if((PageNo-position+PAGESIZE+1)>PageNumber){
        html_foot = '<li class="disabled" ><a >Next</a></li></ul></nav>';
    }else{
        html_foot = '<li><a href="javascript:paging(\''+InsertID+'\',\''+ModelId+'\',\''+ActionName+'\','+(PageNo-position+PAGESIZE+1)+')">Next</a></li></ul></nav>';

    }
    return html_head+html_body+html_foot;

}


// MAP  方法
function UtilMap() {
    var struct = function (key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function (key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    };

    var get = function (key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    };
    var getKey = function (index) {
        if (index > -1 && index < this.arr.length) {
            return this.arr[index].key;
        }
        return null;
    };
    var remove = function (key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
        }
    };

    var size = function () {
        return this.arr.length;
    };

    var isEmpty = function () {
        return this.arr.length <= 0;
    };
    this.arr = new Array();
    this.get = get;
    this.getKey = getKey;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}