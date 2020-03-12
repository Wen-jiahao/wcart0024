var app = new Vue({
    el: '#app',
    data: {
        username:'',
        realName:'',
        email:'',
        mobile:'',
        password:'',
        newsSubscribed:false,
        repassword:''
    },
    methods:{
        handleRegisterClick(){
            this.Customerregister();
        },
        Customerregister(){
            if(this.password!=this.repassword){
                    alert('密码不行同');
            }
            axios.post('/customer/register', {
                username: this.username,
                realName: this.realName,
                email: this.email,
                mobile: this.mobile,
                password: this.password,
                newsSubscribed: this.newsSubscribed,
                repassword: this.repassword
            })
                .then(function (response) {
                    console.log(response);
                    alert('注册成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})