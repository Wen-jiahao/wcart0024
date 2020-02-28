var app = new Vue({
    el: '#app',
    data: {
      productId: '',
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        description: '',
        stockQuantity: '',
        mainPicUrl: '',
        otherPicUrls: ''
    },
    mounted(){
        console.log('view monuted');
        var url = new URL(location.href);
        this.productId = url.searchParams.get("productId");
        if (!this.productId) {
            alert('productId is null');
            return;
        }
        this.getProductById();
    },
    methods:{
        getProductById(){
            axios.get('/product/getById', {
                params: {
                  productId: this.productId
                }
              })
              .then(function (response) {
                console.log(response);
                var product = response.data;
                    app.productId = product.productId;
                    app.productCode = product.productCode;
                    app.productName = product.productName;
                    app.price = product.price;
                    app.discount = product.discount;
                    app.mainPicUrl = product.mainPicUrl;
                    app.otherPicUrls = product.otherPicUrls;
                    app.description = product.description;
                    app.stockQuantity = product.stockQuantity;
              })
              .catch(function (error) {
                console.log(error);
              });  
              //分页方法
             
        }, 
        handlePageChange(val){
            console.log(val);
            this.pageNum=val;
            this.searchProduct;
      }
    }
})