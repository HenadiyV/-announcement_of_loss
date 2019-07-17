


function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var categoryApi = Vue.resource('/test/category{/id}');

// Vue.component('category-form', {
//     props: ['categorys', 'categoryAttr'],
//     data: function() {
//         return {
//             adver:'',
//             name: '',
//             id: ''
//         }
//     },
//     watch: {
//         categoryAttr: function(newVal, oldVal) {
//             this,adver = newVal.adver;
//             this.name = newVal.name;
//             this.id = newVal.id;
//         }
//     },
//
//     template:
//     ' <form class="col-md-12">'+
//     '<div >' +
//     '<input type="text"  v-model="name" placeholder="Назва категорії" />' +
//     '<input class="btn green accent-2" type="button" value="Зберегти" @click="save" />' +
//     '<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"  />' +
//     '</div></form>',
//     methods: {
//         save: function() {
//             var category = { name: this.name };
//
//             if (this.id) {
//                 categoryApi.update({id: this.id}, category).then(result =>
//                 result.json().then(data => {
//                     var index = getIndex(this.categorys, data.id);
//                 this.categorys.splice(index, 1, data);
//                 this.name = ''
//                 this.id = ''
//             })
//             )
//             } else {
//                 categoryApi.save({}, category).then(result =>
//                 result.json().then(data => {
//                     this.categorys.push(data);
//                 this.name = ''
//             })
//             )
//             }
//         }
//     }
//
// });
//
Vue.component('category-row', {
    props: ['category'],
    template: '<div><div>'+
    '<i>({{ category.id }})</i> {{ category.name }}</div>'+
    // '<span style="position: absolute;  right: 0">' +
    // '<input class="waves-effect waves-light btn" type="button" value="Змінити" @click="edit" />' +
    // '<input class="waves-effect btn red darken-1" type="button" value="Видалити" @click="del" />' +
    // '</span>' +
    '</div>'
    // methods: {
    //     edit: function() {
    //         this.editMethod(this.category);
    //     },
    //     del: function() {
    //         categoryApi.remove({id: this.category.id}).then(result => {
    //             if (result.ok) {
    //             this.categorys.splice(this.categorys.indexOf(this.category), 1)
    //         }
    //     })
    //     }
    // }


});

Vue.component('categorys-list', {
    props: ['categorys'],
    // data: function() {
    //     return {
    //         category: null
    //     }
    // },
    template:
    '<div class="collection" style="position: relative; width: 640px;">' +
    // '<category-form :categorys="categorys" :categoryAttr="category" />' +
    '<category-row class="collection-item" v-for="category in categorys" :key="category.id" :category="category" />' +
    // ':editMethod="editMethod" :categorys="categorys" />' +
    '<a class="btn blue darken-1" href="/admin">Адмінка</a></div>'
    // created: function() {
    //     categoryApi.get().then(result =>
    //     result.json().then(data =>
    //     data.forEach(category => this.categorys.push(category))
    // )
    // )
    // },
    // methods: {
    //     editMethod: function(category) {
    //         this.category = category;
    //     }
    // }
});

var app = new Vue({
    el: '#app',
    template: '<categorys-list :categorys="categorys" />',
    data: {
        categorys: []
    }
});



// function getIndex(list, id) {
//     for (var i = 0; i < list.length; i++ ) {
//         if (list[i].id === id) {
//             return i;
//         }
//     }
//
//     return -1;
// }
// //'{{adver.dataStart}},{{adver.dataStop}},{{adver.status}},{{adver.category}},{{adver.user}},{{adver.city}}
//   var adverApi=Vue.resource('/test{/id}')
//
// Vue.component('category-row',{
//     props:['category'],
//     template:'<div><i>({{category.id}})</i>{{category.name}}</div>'
// });
// Vue.component('category-list', {
//
// props:['categorys'],
//     data: function() {
//         return {
//             category : null
//         }
//     },
//     template: '<div>'
//     + '<category-row v-for="category in categorys" :key="category.id" :category="category"/>'
//     + '</div>',
//     create:function(){
//     categoryApi.get().then(result=>
//     result.join().then(data=>
//     data.forEach(category=>this.categorys.push(category))
//     )
//     )
//     }
// });
// var app = new Vue({
//     el: '#app',
//     template:'<category-list :categorys="categorys"/>',
//     data: {
//         categorys:[]
//     }
// });

//
// Vue.component('adver-form', {
//     props: ['advers', 'adverAttr'],
//     data: function() {
//         return {
//
//             id: '',
//              textAdver:'',
//          photo:'',
//        dataStart:'',
//          dataStop:'',
//          status:'',
//          category:'',
//          user:'',
//          city:''
//         }
//     },
//     watch: {
//         adverAttr: function(newVal, oldVal) {
//
//             this.id = newVal.id;
//             this.textAdver = newVal.textAdver;
//             this.photo = newVal.photo;
//             this.dataStart = newVal.dataStart;
//             this.dataStop = newVal.dataStop;
//             this.status = newVal.status;
//             this.category = newVal.category;
//             this.user = newVal.user;
//             this.cty = newVal.cty;
//         }
//     },
//
//     template:
//     ' <form class="col-md-12">'+
//     '<div >' +
//     '<input type="text"  v-model="name" placeholder="Назва категорії" />' +
//     '<input class="btn green accent-2" type="button" value="Зберегти" @click="save" />' +
//     '<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"  />' +
//     '</div></form>',
//     methods: {
//         save: function() {
//             var adver = { name: this.name };
//
//             if (this.id) {
//                 adverApi.update({id: this.id}, adver).then(result =>
//                 result.json().then(data => {
//                     var index = getIndex(this.advers, data.id);
//                 this.advers.splice(index, 1, data);
//                 this.name = ''
//                 this.id = ''
//             })
//             )
//             } else {
//                 adverApi.save({}, adver).then(result =>
//                 result.json().then(data => {
//                     this.advers.push(data);
//                 this.name = ''
//             })
//             )
//             }
//         }
//     }
//
// });
//
// Vue.component('adver-row', {
//     props: ['adver', 'editMethod', 'advers'],
//     template: '<div>' +
//     '<i>({{ adver.id }})</i> {{ adver.textAdver }}' +
//     '<span style="position: absolute;  right: 0">' +
//     '<input class="waves-effect waves-light btn" type="button" value="Змінити" @click="edit" />' +
//     '<input class="waves-effect btn red darken-1" type="button" value="Видалити" @click="del" />' +
//     '</span>' +
//     '</div>',
//     methods: {
//         edit: function() {
//             this.editMethod(this.adver);
//         },
//         del: function() {
//             adverApi.remove({id: this.adver.id}).then(result => {
//                 if (result.ok) {
//                 this.advers.splice(this.advers.indexOf(this.adver), 1)
//             }
//         })
//         }
//     }
//
//
// });
// Vue.component('advers-list', {
//     props: ['advers'],
//     data: function() {
//         return {
//             advers: null
//         }
//     },
//     template:
//     '<div class="collection" style="position: relative; width: 640px;">' +
//     // '<adver-form :advers="advers" :adverAttr="adver" />' +
//     '<adver-row class="collection-item" v-for="adver in advers" :key="adver.id" :adver="adver" ' +
//     ':editMethod="editMethod" :advers="advers" />' +
//     '<a class="btn blue darken-1" href="/admin">Адмінка</a></div>',
//     created: function() {
//         adverApi.get().then(result =>
//         result.json().then(data =>
//         data.forEach(adver => this.advers.push(adver))
//     )
//     )
//     },
//     methods: {
//         editMethod: function(adver) {
//             this.adver = avder;
//         }
//     }
// });
// var app = new Vue({
//     el: '#app',
//     data: {
//         template: '<advers-list :advers="advers" />',
//         data: {
//              advers: [],
//             message: 'Привет, Vue!'
//         }
//     }
// });
// Vue.component('todo-item', {
//     // Компонент todo-item теперь принимает
//     // "prop", то есть входной параметр.
//     // Имя входного параметра todo.
//     props: ['todo'],
//     template: '<li>{{ todo.text }}</li>'
// });
// var app7 = new Vue({
//     el: '#app-7',
//     data: {
//         groceryList: [
//             { id: 0, text: 'Овощи' },
//             { id: 1, text: 'Сыр' },
//             { id: 2, text: 'Что там ещё люди едят?' }
//         ]
//     }
// });

// var prefix = '/test';
//
// var RestGet = function() {
//     $.ajax({
//         type: 'GET',
//         url:  prefix +'/foundView' ,
//         dataType: 'json',
//         async: false,
//         success: function(result) {
//             $.each(result.data,
//                 function(i, dat) {
//                     $("#textAdver").text(dat.textAdver);
//                     $("#dataStart").val(dat.dataStart);
//                     $("#dataStop").val(dat.dataStop);
//                     $("#status").text(dat.status);
//                     $("#photo").val(dat.photo);
//                     $("#category").val(dat.category);
//
//                 });
//
//             alert("Ok");
//         },
//         error: function(jqXHR, textStatus, errorThrown) {
//             $("#photo").text("error");
//             alert(jqXHR.status + ' ' + jqXHR.responseText);
//
//         }
//     });
// }
// var RestGetLost = function() {
//    //  var JSONObject= {
//    //      'textAdver':textAdver,
//    //  'photo' : photo,
//    //  'dataStart' : dataStart,
//    //  'dataStop' : dataStop,
//    // 'status' : status,
//    //  'category' : category,
//    //  'user' : user,
//    //  'city' : city
//    //  data: JSON.stringify(),};
//
//     $.ajax({
//         type: 'GET',
//         url:  prefix +'/lostView' ,
//
//         dataType: 'json',
//         async: true,
//         success: function(result) {
//            $("#RestGetDiv").html(result.data);
//             alert("Ok");
//         },
//         error: function(jqXHR, textStatus, errorThrown) {
//             alert(jqXHR.status + ' ' + jqXHR.responseText);
//
//         }
//     });
// }

// Vue.component('adver-list',{
//     props:['adverList'],
//     data:function(){
//         return{
//
//         }
//     },
//     template:
//     ' <div th:each="adver:${listAdver}">\n' +
//     '\n' +
//     '            <div class="card m-2" >\n' +
//     '                        <div style="overflow: hidden; width: 100%;">\n' +
//     '                            <div style="width: 150px; height: 150px; object-fit: cover;">\n' +
//     '                                <img   th:src="@{\'/img/\'+${adver.getPhoto()}}" alt="Card image cap"  />\n' +
//     '                            </div>\n' +
//     '                         </div>\n' +
//     '\n' +
//     '                        <div class="card-body">\n' +
//     '                            <h5 class="card-title" th:text="#{lang.warning}">Увага<span class=" ml-5" style="color:red " th:text="${adver.getDataStart()}"></span></h5>\n' +
//     '                            <div class="card-text" >\n' +
//     '\n' +
//     '                               <b> <span th:text="#{lang.in.city}">В місті :</span></b>\n' +
//     '                            <br/>\n' +
//     '                             <b><span class=" ml-1" style="color:blue " th:text="${adver.getCity().name}"></span></b>\n' +
//     '                        </div>\n' +
//     '                            <div class="card-text" ><span th:text="#{lang.was}">Було</span>  <span class=" ml-5" style="color:red " th:text="${adver.getStatus().name}"></span></div>\n' +
//     '                            <p class="card-text"><span class=" ml-5" style="color:blue " th:text="${adver.getCategory().name}"></span></p>\n' +
//     '                            <p>\n' +
//     '                                    <button class="btn btn-primary" th:onclick="\'javascript:toggle_show(\\\'\'+${adver.getId()}+\'\\\');\'" th:text="#{lang.btn.read.more}">\n' +
//     '                                        Докладніше\n' +
//     '                                    </button>\n' +
//     '                                    <div  style=" width: 200px;">\n' +
//     '                                    <div th:id="${adver.getId()}" th:text="${adver.getTextAdver()}" style="display:none">\n' +
//     '                                    </div>\n' +
//     '\n' +
//     '                                    </div>\n' +
//     '                            </p>\n' +
//     '                        </div>\n' +
//     '                        <div class="card-footer">\n' +
//     '                            <div class="row">\n' +
//     '\n' +
//     '                                <div class="m-1" th:if="${us} !=0">\n' +
//     '                                <form action="/advers/messageUser"  method="post">\n' +
//     '                                    <input hidden="hidden"  th:name="user" th:value="${adver.user.getId()}"/>\n' +
//     '                                    <button class="btn btn-primary" type="submit" th:text="#{lang.btn.send}">Повідомити</button>\n' +
//     '                                    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />\n' +
//     '                                </form>\n' +
//     '                                </div>\n' +
//     '\n' +
//     '                                <div class="m-1" th:if="${us}==2">\n' +
//     '                                <form action="/advers/adversDelete"  method="post">\n' +
//     '                                <input hidden="hidden"  th:name="messDel" th:value="${adver.getId()}"/>\n' +
//     '                                <input hidden="hidden"  th:name="url" th:value="${url}"/>\n' +
//     '                                <button class="btn btn-primary" type="submit" th:text="#{lang.btn.delete}">Видалити</button>\n' +
//     '                                <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />\n' +
//     '                                </form>\n' +
//     '                                </div>\n' +
//     '                             </div>\n' +
//     '                            <div th:if="${us}==0">\n' +
//     '                            <a href="/login" class="btn btn-primary" th:text="#{lang.btn.login}">Залогінитись</a>\n' +
//     '                            </div>\n' +
//     '                        </div>\n' +
//     '            </div>\n' +
//     '        </div>'
// });
// var messageApi = Vue.resource('/message{/id}');
// Vue.component('messages-list', {
//     props: ['messages'],
//     data: function() {
//         return {
//             message: null
//         }
//     },
//     template:
//     '<div style="position: relative; width: 300px;">' +
//     '<message-form :messages="messages" :messageAttr="message" />' +
//     '<message-row v-for="message in messages" :key="message.id" :message="message" ' +
//     ':editMethod="editMethod" :messages="messages" />' +
//     '</div>',
//     created: function() {
//         messageApi.get().then(result =>
//         result.json().then(data =>
//         data.forEach(message => this.messages.push(message))
//     )
//     )
//     },
//     methods: {
//         editMethod: function(message) {
//             this.message = message;
//         }
//     }
// });



// $(document).ready(
//     function() {
//
//         // SUBMIT FORM
//         $("#lost").click(function(event) {
//             // Prevent the form from submitting via the browser.
//             event.preventDefault();
//             ajaxLost();
//         });
//         $("#found").click(function(event) {
//             // Prevent the form from submitting via the browser.
//             event.preventDefault();
//             ajaxFound();
//         });
//         function ajaxFound() {
//
//             // PREPARE FORM DATA
//
//             // DO POST
//             $.ajax({
//                 type : "GET",
//                 contentType : "application/json",
//                 url : "/foundView",
//                // data : JSON.stringify(formData),
//                 dataType : 'json',
//                 success : function(result) {
//                     // if (result.status == "success") {
//                     //     $("#postResultDiv").html(
//                     //         "" + result.data.bookName
//                     //         + "Post Successfully! <br>"
//                     //         + "---> Congrats !!" + "</p>");
//                     // } else {
//                     //     $("#postResultDiv").html("<strong>Error</strong>");
//                     // }
//                     //console.log(result);
//                 },
//                 error : function(e) {
//                     alert("Error!")
//                     console.log("ERROR: ", e);
//                 }
//             });
//
//         }
//         function ajaxLost() {
//
//             // PREPARE FORM DATA
//
//             // DO POST
//             $.ajax({
//                 type : "GET",
//                 contentType : "application/json",
//                 url : "/lostView",
//                 // data : JSON.stringify(formData),
//                 dataType : 'json',
//                 success : function(result) {
//                     // if (result.status == "success") {
//                     //     $("#postResultDiv").html(
//                     //         "" + result.data.bookName
//                     //         + "Post Successfully! <br>"
//                     //         + "---> Congrats !!" + "</p>");
//                     // } else {
//                     //     $("#postResultDiv").html("<strong>Error</strong>");
//                     // }
//                     //console.log(result);
//                 },
//                 error : function(e) {
//                     alert("Error!")
//                     console.log("ERROR: ", e);
//                 }
//             });
//
//         }
//     //     function ajaxPost() {
//     //
//     //         // PREPARE FORM DATA
//     //         var formData = {
//     //             bookId : $("#bookId").val(),
//     //             bookName : $("#bookName").val(),
//     //             author : $("#author").val()
//     //         }
//     //
//     //         // DO POST
//     //         $.ajax({
//     //             type : "POST",
//     //             contentType : "application/json",
//     //             url : "saveBook",
//     //             data : JSON.stringify(formData),
//     //             dataType : 'json',
//     //             success : function(result) {
//     //                 if (result.status == "success") {
//     //                     $("#postResultDiv").html(
//     //                         "" + result.data.bookName
//     //                         + "Post Successfully! <br>"
//     //                         + "---> Congrats !!" + "</p>");
//     //                 } else {
//     //                     $("#postResultDiv").html("<strong>Error</strong>");
//     //                 }
//     //                 console.log(result);
//     //             },
//     //             error : function(e) {
//     //                 alert("Error!")
//     //                 console.log("ERROR: ", e);
//     //             }
//     //         });
//     //
//     //     }
//     //
//     //
// })