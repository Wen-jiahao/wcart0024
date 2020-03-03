var app = new Vue({
    el: '#app',
    data: {
        pageInfo:'',
        pageNum:1,
        selectedAdministrators: []
    },
    computed:{
        selectedAdministratorIds(){
            return this.selectedAdministrators.map(a=> a.administratorId);
        }
    },
    mounted(){
        console.log('view mouned');
        this.getadministrators();
    },
    methods:{
        handlePageChange(val){
            console.log(val);
            this.pageNum=val;
            this.getadministrators();
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
        },
        handleDelete(index,row){
            if(confirm("确认要删除吗？")){
                this.deleteAdministrator(row.administratorId);
            }
           
        },
        deleteAdministrator(administratorId){
            axios.post('/administrator/delete', administratorId,{
               'Content-Type' : 'application/json'
              })
              .then(function (response) {
                console.log(response);
                alert('删除成功');
                location.reload();
              })
              .catch(function (error) {
                console.log(error);
              });
        },
        handleSelectionChange(val){
            console.log('change select');
            this.selectedAdministrators = val;
        },
        handleBatchDeleteClick(){
            this.batchDeleteAdministrators();
        },
        batchDeleteAdministrators() {
            axios.post('/administrator/batchDelete', this.selectedAdministratorIds)
                .then(function (response) {
                    console.log(response);
                    alert('批删成功');
                    location.reload();
                })
                .catch(function (error) {
                    console.log(error);
                });
        }


    }

    
})