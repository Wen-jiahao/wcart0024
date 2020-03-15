const returnShowRoutePage  = { 

    template: `
    <div id="app">
        <el-divider>退货基本信息</el-divider>
        退货Id：{{returnId}} <br>
        订单Id：{{orderId}} <br>
        订单时间：{{orderTimestamp}} <br>
        客户Id：{{customerId}} <br>
        客户姓名：{{customerName}} <br>
        手机：{{mobile}} <br>
        邮箱：{{email}} <br>
        状态：{{statuses[status].label}} <br>
        处理方式：{{selectaction[action].label}} <br>
        <br>

        <el-divider>退货商品信息</el-divider>
        商品代号：{{productCode}} <br>
        商品名称：{{productName}} <br>
        数量：{{quantity}} <br>
        原因：{{reasons[reason].label}} <br>
        是否开封：<span v-if="opened">是</span><span v-else>否</span> <br>
        备注：{{comment}} <br>
        申请时间：
        {{(new Date(createTimestamp)).toLocaleString()}}
         <br>
        更新时间：
        {{(new Date(updateTimestamp)).toLocaleString()}}
        <br>
        <br>
        <el-select v-model="action" placeholder="请选择">
            <el-option v-for="item in selectaction" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>

        <el-button type="primary" @click="handleUpdateAction">修改处理方式</el-button>

    </div>
`,
    data(){
        return  {
            returnId: '',
            orderId: '',
            orderTimestamp: '',
            customerId: '',
            customerName: '',
            mobile: '',
            email: '',
            status: '',
            action: '',
            productCode: '',
            productName: '',
            quantity: '',
            reason: '',
            opened: '',
            comment: '',
            createTimestamp: '',
            updateTimestamp: '',
            action:'',
            selectaction:[
                { value: 0, label: '退货' },
                { value: 1, label: '换货' },
                { value: 2, label: '修理' }
            ],
            statuses: [
                { value: 0, label: '待处理' },
                { value: 1, label: '待取货' },
                { value: 2, label: '正在处理' },
                { value: 3, label: '完成' },
                { value: 4, label: '拒绝' }
            ],
            reasons: [
                { value: 0, label: '发货过期' },
                { value: 1, label: '订单错误' },
                { value: 2, label: '收到错误商品' },
                { value: 3, label: '质量问题' }
            ]
        }
        
    },
    mounted() {
        console.log('view mounted');

        this.returnId = this.$route.params.returnId;
        if (!this.returnId) {
            alert('returnId is null');
            return;
        }

        this.getReturnById();
    },
    methods: {
        handleUpdateAction(){
            this.updateActiom();
        },
        updateActiom(){
            axios.post('/return/updateAction', {
                action:this.action,
                returnId: this.returnId,
            })
                .then(function (response) {
                    console.log('创建成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getReturnById() {
            axios.get('/return/getById', {
                params: {
                    returnId: this.returnId
                }
            })
                .then((response)=> {
                    console.log(response);
                    var aReturn = response.data;
                    this.orderId = aReturn.orderId;
                    this.orderTimestamp = aReturn.orderTimestamp;
                    this.customerId = aReturn.customerId;
                    this.customerName = aReturn.customerName;
                    this.mobile = aReturn.mobile;
                    this.email = aReturn.email;
                    this.status = aReturn.status;
                    this.action = aReturn.action;
                    this.productCode = aReturn.productCode;
                    this.productName = aReturn.productName;
                    this.quantity = aReturn.quantity;
                    this.reason = aReturn.reason;
                    this.opened = aReturn.opened;
                    this.comment = aReturn.comment;
                    this.createTimestamp = aReturn.createTimestamp;
                    this.updateTimestamp = aReturn.updateTimestamp;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
  }