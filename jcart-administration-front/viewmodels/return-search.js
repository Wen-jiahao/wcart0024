var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1,
        returnId: '',
        orderId: '',
        customerName: '',
        productCode: '',
        productName: '',
        selectstatus: '',
        startTime:'',
        endTime:'',
        statuses:[
            { value: 0, label: '待取货' },
            { value: 1, label: '正在处理' },
            { value: 2, label: '完成' },
            { value: 3, label: '拒绝' }
        ]
    },
    mounted() {
        console.log('view mounted');
        this.searchReturn();
    },
    methods: {
        handleSerachClick(){
            this.pageNum=1;
            this.searchReturn();
        },
        handleClearClick(){
            this.returnId = '';
            this.orderId = '';
            this.customerName = '';
            this.productCode = '';
            this.productName = '';
            this.selectedStatus = '';
            this.startTime = '';
            this.endTime = '';
        },
        handlePageChange(val) {
            console.log('page changed', val);
            this.pageNum = val;
            this.searchReturn();
        },
       
        searchReturn() {
            axios.get('/return/search', {
                params: {
                    pageNum: this.pageNum,
                    returnId: this.returnId,
                    orderId: this.orderId,
                    customerName: this.customerName,
                    productCode: this.productCode,
                    productName: this.productName,
                    status: this.selectstatus,
                    startTimestamp:this.startTime?this.startTime.getTime():'',
                    endTimestamp:this.endTime?this.endTime.getTime():'',
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