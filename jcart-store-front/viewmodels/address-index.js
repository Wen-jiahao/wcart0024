var app = new Vue({
    el: '#app',
    data: {
        addresses:[]
    },
    mounted(){
        this.getMyAddress();
    },
    methods:{

        handleDelete(index, row) {
            console.log('delete click');
            if (confirm('确定删除？')) {
                this.deleteAddress(row.addressId)
            }
        },
        deleteAddress(addressId){
            axios.post('/address/delete', addressId,{
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
       
        getMyAddress(){
            axios.get('/address/getCustomerAddress')
                .then(function (response) {
                    console.log(response);
                    app.addresses=response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }

    
}) 