// Define a new component called button-counter
Vue.component('jc-product-search-page', {

    template:`
    <div id="app">
    <el-input v-model="productCode" placeholder="请输入商品代号"></el-input>
    <el-input v-model="productName" placeholder="请输入商品名称"></el-input>
    <el-input v-model="price" placeholder="请输入价格"></el-input>
    <el-input v-model="stockQuantity" placeholder="请输入库存"></el-input>

    <br>
    <el-select v-model="selectedStatus" placeholder="请选择状态">
        <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
    </el-select>
    <br>
    <el-button type="primary" @click="handleSearchClick">搜索</el-button>
    <el-button type="primary" @click="handleClearClick">清空条件</el-button>

    <el-table :data="pageInfo.list" style="width: 100%">
        <el-table-column  label="主图">
            <template slot-scope="scope">
                <el-image style="width: 100px; height: 100px" :src="scope.row.mainPicUrl" :fit="fit"></el-image>
            </template>           
        </el-table-column>
        <el-table-column prop="productName" label="商品名称">
        </el-table-column>
        <el-table-column prop="productCode" label="商品编号">
        </el-table-column>
        <el-table-column  label="价格">
            <template slot-scope="scope">
                <s>{{scope.row.price}}</s><br>
                {{(scope.row.price*scope.row.discount).toFixed(2)}}

            </template>
        </el-table-column>
        <el-table-column prop="discount" label="打折">
        </el-table-column>
        <el-table-column prop="stockQuantity" label="库存">
        </el-table-column>
        <el-table-column label="状态">
            <template slot-scope="scope">
                {{statuses[scope.row.status].label}}
            </template>
        </el-table-column>
    </el-table>
    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
    </el-pagination>


</div>
    `,

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
        this.searchProduct();
    },
    methods: {

        handleSearchClick() {
            console.log('searck click');
            this.pageNum = 1;
            this.searchProduct();

        },
        handleClearClick() {
                this.productName='',
                this.productCode='',
                this.price='',
                this.selectedStatus='',
                this.stockQuantity=''
        },
        searchProduct() {
            console.log(111)
            axios.get('/product/search', {
                params: {
                    pageNum: this.pageNum,
                    productName: this.productName,
                    productCode: this.productCode,
                    price: this.price,
                    status: this.selectedStatus,
                    stockQuantity: this.stockQuantity
                }
            })
                .then((response)=> {
                    console.log(response);
                    this.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handlePageChange(val) {
            console.log(val);
            this.pageNum = val;
            this.searchProduct();
        }

    }
  })