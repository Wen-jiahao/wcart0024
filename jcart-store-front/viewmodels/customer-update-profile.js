var app = new Vue({
    el: '#app',
    data: {
        username:'',
        realName:'',
        mobile:'',
        mobileVerified:'',
        email:'',
        emailVerified:'',
        customerId:''
    },
    mounted(){
        this.getBycustomerId();
    },
    methods:{
        getBycustomerId(){
            axios.get('/customer/getProfile')
                .then(function (response) {
                    console.log(response);
                    var customerDTO = response.data;
                    app.username=customerDTO.username;
                    app.realName = customerDTO.realName;
                    app.mobile = customerDTO.mobile;
                    app.mobileVerified = customerDTO.mobileVerified;
                    app.email = customerDTO.email;   
                    app.emailVerified = customerDTO.emailVerified;              
                })
                .catch(function (error) {
                    console.log(error);
                });   
        },
        handleUpdateClick(){
            this.updateCustomerProfile();
        },
        updateCustomerProfile(){
            axios.post('/customer/updateProfile', {
                customerId: this.customerId,
                realName: this.realName,
                email: this.email,
                mobile: this.mobile
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