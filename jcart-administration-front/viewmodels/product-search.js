var app = new Vue({
    el: '#app',
    data: {
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
                .then(function (response) {
                    console.log(response);
                    app.pageInfo = response.data;
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