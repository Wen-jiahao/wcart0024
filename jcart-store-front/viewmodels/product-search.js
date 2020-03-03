var app = new Vue({
    el: '#app',
    data: {
       pageInfo:'',
       pageNum:1
    },
    mounted(){
        console.log('view monuted');
        this.searchProduct();
    },
    methods:{
        searchProduct(){
            axios.get('/product/search', {
                params: {
                    pageNum:this.pageNum
                }
              })
              .then(function (response) {
                console.log(response);
                app.pageInfo=response.data;
              })
              .catch(function (error) {
                console.log(error);
              });  
              //分页方法
             
        }, 
        handlePageChange(val){
            console.log(val);
            this.pageNum=val;
            this.searchProduct();
      }
    }
})