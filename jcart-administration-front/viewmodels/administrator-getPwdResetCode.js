var app = new Vue({
    el: '#app',
    data: {
        email: '',
        loading:false,
        buttonEnabled:true,
        counter:60
    },
    methods:{
        validateEmail (email){
            var reg = /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;
            return reg.test(email);
        },
        handleFindBackPwdClick(){
            console.log('find back pwd click');
                if (this.validateEmail(this.email)) {
                   alert('有效');
                  
                }else{
                    alert('无效');                    
                };
            this.loading=true;
            this.buttonEnabled=false;
            this.counter=60;
            setInterval(function(){
                app.counter--;
                if(app.counter<0){
                    app.buttonEnabled=true;
                }
            },1000);
            this.getPwdRecord();
        },

        getPwdRecord(){
            axios.get('/administrator/getPwdResetCode', {
                params: {
                    email: this.email
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.loading=false;
                    alert('重置码已发送到邮箱');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        }

        

    
}) 