var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1,
        orderId:'',
        customerName:'',
        totalPrice:'',
        createTime:'',
        endTime:'',
        selectstatus:'',
        statuses:[
            { value: 0, label: '待处理' },
            { value: 1, label: '处理中' },
            { value: 2, label: '待发货' },
            { value: 3, label: '已发货' },
            { value: 4, label: '待签收' },
            { value: 5, label: '已签收' },
            { value: 6, label: '待支付' },
            { value: 7, label: '已支付' },
            { value: 8, label: '取消' },
            { value: 9, label: '拒绝' },
            { value: 10, label: '完成' },
            { value: 11, label: '待评价' },
            { value: 12, label: '已评价' }
        ]
    },
    mounted() {
        console.log('view mounted');
        this.searchOrder();
    },
    methods: {
        handleSerachClick(){
            this.pageNum=1;
            this.searchOrder();
        },
        handleClearClick(){
            this.orderId = '';
            this.customerName = '';
            this.totalPrice = '';
            this.createTime = '';
            this.endTime = '';
            this.selectstatus = '';
        },
        
        handlePageChange() {
            console.log('page changed', val);
            this.pageNum = val;
            this.searchOrder();
        },
        searchOrder() {
            axios.get('/order/search', {
                params: {
                    orderId: this.orderId,
                    customerId: this.customerId,
                    status: this.selectstatus,
                    customerName: this.customerName,
                    totalPrice: this.totalPrice,
                    createTime: this.createTime?this.createTime.getTime():'',
                    endTime: this.endTime?this.endTime.getTime():'',
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})