var app = new Vue({
    el: '#app',
    data: {
        administratorId: '',
        username: '',
        realname: '',
        email: '',
        avatarUrl: '',
        password :'',
        selectedStatus:1,
        statuse :[
                {
                value: 0,
                label: '禁用'
              }, {
                value: 1,
                label: '启用'
              },
        ]
    },
 
    mounted() {
        console.log('view mounted');
        var url = new URL(location.href);
        this.administratorId=url.searchParams.get("administratorId");
        if(!this.administratorId){
            alert('administratorId is null');
            return;
        }
        this.getMyProfile();
    },
    methods: {
        handleUpdateClick() {
            console.log('update click');
            this.updateMyProfile();
        },
        getMyProfile() {
            axios.get('/administrator/getById', {
                params: {
                    administratorId: this.administratorId,
                }
              })
              .then(function (response) {
                console.log(response);
                var me=response.data;
                app.administratorId = me.administratorId;
                    app.username = me.username;
                    app.realname = me.realname;
                    app.email = me.email;
                    app.avatarUrl = me.avatarUrl;
                    app.selectedStatus=me.status;
                    app.password-me.password
              })
              .catch(function (error) {
                console.log(error);
              });  
       
        },
        updateMyProfile() {
            axios.post('/administrator/update', {
                administratorId:this.administratorId,
                realname: this.realname,
                email: this.email,
                avatarUrl: this.avatarUrl,
                password:this.password,
                status:this.selectedStatus

            })
                .then(function (response) {
                    console.log(response);
                    alert('更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})