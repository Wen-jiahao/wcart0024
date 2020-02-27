var app = new Vue({
    el: '#app',
    data: {
        pageInfo: '',
        pageNum: 1
    },
    mounted(){
        console.log('view mounted');
        this.searchProduct();
    },
    methods: {
        searchProduct() {
            console.log(111)
            axios.get('/product/search', {
                params: {
                    pageNum: this.pageNum
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
        handlePageChange(val){
            console.log(val);
            this.pageNum=val;
            this.searchProduct();
        }

    }
})