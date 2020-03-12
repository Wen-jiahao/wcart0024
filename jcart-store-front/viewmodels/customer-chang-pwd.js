var app = new Vue({
    el: '#app',
    data: {
        originPwd: '',
        newPwd: '',
        reNewPwd:''
    },
    methods: {
        handleChangeClick() {
            console.log('login click');
            this.changePwd();
        },
        changePwd() {
            if(this.newPwd!=this.reNewPwd){
                alert('密码不同');
            }
            axios.post('/customer/changePwd', {
                originPwd: this.originPwd,
                newPwd: this.newPwd,
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