var app = new Vue({
    el: '#app',
    data: {
        pageInfo:'',
        pageNum:1
    },
    mounted(){
        console.log('view mouned');
        this.getadministrators();
    },
    methods:{
        handlePageChange(val){
            console.log(val);
            this.pageNum=val;
            this.searchProduct();
        },
        getadministrators(){
            axios.get('/administrator/getList', {
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
        }
    }
})