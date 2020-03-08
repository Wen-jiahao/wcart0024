
var app = new Vue({
    el: '#app',
    data: {
        returnHistories: [],
        selectedReturnStatus: '',
        returnStatuses: [
            { value: 0, label: '待取货' },
            { value: 1, label: '正在处理' },
            { value: 2, label: '完成' },
            { value: 3, label: '拒绝' }
        ],
        customerNotified: false,
        comment: ''
       
    },
    mounted(){
        console.log('view mounted');
        var url = new URL(location.href);
        this.returnId = url.searchParams.get("returnId");
        if (!this.returnId) {
            alert('returnId is null');
            return;
        }

        this.getreturnHistoriesList();
    },
    methods:{
        getreturnHistoriesList(){
            axios.get('/returnhistort/getListByReturnId', {
                params: {
                    returnId: this.returnId
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.returnHistories = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handleCreateClick(){
            this.createReturnHistory();
        },
        createReturnHistory(){
            axios.post('/returnhistort/create', {
                comment:this.comment,
                returnStatus: this.selectedReturnStatus,
                customerNotify: this.customerNotified,
                returnId:this.returnId
            })
                .then(function (response) {
                    console.log('创建成功');
                    this.getreturnHistoriesList();
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    }



}) 