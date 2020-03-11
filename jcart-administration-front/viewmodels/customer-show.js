var app = new Vue({
    el: '#app',
    data: {
        customerId:'',
        username:'',
        realName:'',
        avatarUrl:'',
        mobile:'',
        email:'',
        statuses:[
            {value: 0, label: '禁用'},
            {value: 1, label: '启用'},
            {value: 2, label: '不安全'}
        ],
        status:'',
        createTimestamp:'',
        newsSubscribed:'',
        rewordPoints:'',
        defaultAddress:'',
    },

    mounted() {
        console.log('view mounted');
       
        var url=new URL(location.href);
        this.customerId=url.searchParams.get("customerId");
        if(!this.customerId){
            alert('customerId is null');
            return;
        }
        this.getCustomerById();
    },
    methods: {
       
        getCustomerById() {
            axios.get('/customer/getById', {
                params: {
                    customerId: this.customerId,
                }
            })
                .then(function (response) {
                    console.log(response.data);
                    var customerShow=response.data;
                    app.username=customerShow.username;
                    app.realName=customerShow.realName;
                    app.avatarUrl=customerShow.avatarUrl;
                    app.mobile=customerShow.mobile;
                    app.email=customerShow.email;
                    app.status=customerShow.status;
                    app.createTimestamp=customerShow.createTimestamp;
                    app.newsSubscribed=customerShow.newsSubscribed;
                    app.rewordPoints=customerShow.rewordPoints;
                    app.defaultAddress=customerShow.defaultAddress;
                    
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        
    }
}) 