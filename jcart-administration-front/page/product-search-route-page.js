const ProductSearchRoutePage  = { 
    template: `<div id="app">
    <el-input v-model="productCode" placeholder="输入商品代码"></el-input>
    <el-input v-model="returnId" placeholder="请输入退货Id"></el-input>
    <el-input v-model="orderId" placeholder="请输入订单Id"></el-input>
    <el-input v-model="customerName" placeholder="请输入客户姓名"></el-input>
    <el-input v-model="productName" placeholder="请输入商品名称"></el-input>
    <el-select v-model="selectstatus" placeholder="请选择">
        <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
    </el-select>
    <br>
    <el-date-picker v-model="startTime" type="date" placeholder="选择开始日期">
    </el-date-picker>
    <br>

    <el-date-picker v-model="endTime" type="date" placeholder="选择结束日期">
    </el-date-picker>
    <br>

    <el-button type="primary" @click="handleSerachClick">查询</el-button>
    <el-button type="primary" @click="handleClearClick">清空调间</el-button>

    <el-table :data="pageInfo.list" style="width: 100%">
        <el-table-column prop="returnId" label="退货Id">
        </el-table-column>
        <el-table-column prop="orderId" label="订单Id">
        </el-table-column>
        <el-table-column prop="customerName" label="客户姓名">
        </el-table-column>
        <el-table-column prop="productCode" label="商品代号">
        </el-table-column>
        <el-table-column prop="productName" label="商品名称">
        </el-table-column>
        <el-table-column  label="状态">
            <template slot-scope="scope">
               {{statuses[scope.row.status].label}}
            </template>
        </el-table-column>
        <el-table-column  label="申请日期">
            <template slot-scope="scope">
                {{(new Date(scope.row.createTime)).toLocaleString()}}
             </template>
        </el-table-column>
        <el-table-column  label="修改日期">
                <template slot-scope="scope">
                    {{(new Date(scope.row.updateTime)).toLocaleString()}}
                </template>
        </el-table-column>
    </el-table>

    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
    </el-pagination>
</div>` ,
data() {
    return {
        pageInfo: '',
        pageNum: 1,
        productName: '',
        productCode: '',
        price: '',
        status: '',
        stockQuantity: '',
        selectedStatus: '',
        statuses: [
            { value: 0, label: '下架' },
            { value: 1, label: '上架' },
            { value: 2, label: '待审核' }
        ]
    }
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
            .then((response)=> {
                console.log(response);
                this.pageInfo = response.data;
            })
            .catch(function (error) {
                console.log(error);
            });
    }
}
}