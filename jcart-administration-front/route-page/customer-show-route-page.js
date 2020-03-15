const  CustomerShowRoutePage  = { 

    template: `
    <div id="app">
    客户姓名:{{username}}<br>
    客户姓名：{{realName}} <br>
    客户头像：{{avatarUrl}} <br>
    手机：{{mobile}} <br>
    邮箱：{{email}} <br>
    状态：{{statuses[status].label}} <br>
    注册时间：{{createTimestamp}} <br>
    订阅新闻：<span v-if="newsSubscribed">是</span><span v-else>否</span> <br>
    积分：{{rewordPoints}} <br>
    默认地址：{{defaultAddress}} <br>
    </div>
`,
    data(){
        return  {
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
        }
        
    },
    mounted() {
        console.log('view mounted');
       
        //this.$router.push('/customer/show/' + row.customerId);
        this.customerId=this.$route.params.customerId;
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
                .then((response)=> {
                    console.log(response.data);
                    var customerShow=response.data;
                    this.username=customerShow.username;
                    this.realName=customerShow.realName;
                    this.avatarUrl=customerShow.avatarUrl;
                    this.mobile=customerShow.mobile;
                    this.email=customerShow.email;
                    this.status=customerShow.status;
                    this.createTimestamp=customerShow.createTimestamp;
                    this.newsSubscribed=customerShow.newsSubscribed;
                    this.rewordPoints=customerShow.rewordPoints;
                    this.defaultAddress=customerShow.defaultAddress;
                    
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        
    }
  }