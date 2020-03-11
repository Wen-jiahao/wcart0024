var app = new Vue({
    el: '#app',
    data: {
        receiverName: '',
        receiverMobile: '',
        content :'',
        tag:'',
        addressId:''
    },
    mounted(){
        
        var url = new URL(location.href);
        this.addressId = url.searchParams.get("addressId");
        if (!this.addressId) {
            alert('addressId is null');
            return;
        }
        this.getaddressById();
    },
    methods:{
        getaddressById(){
            axios.get('/address/getById', {
                params: {
                    addressId: this.addressId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var addressDTO = response.data;
                    app.addressId=addressDTO.addressId;
                    app.receiverName = addressDTO.receiverName;
                    app.receiverMobile = addressDTO.receiverMobile;
                    app.content = addressDTO.content;
                    app.tag = addressDTO.tag;                 
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handleUpdateClick(){
            console.log('update click');
            this.updateAddress();
        },
        updateAddress(){
            axios.post('/address/update', {
                addressId: this.addressId,
                tag: this.tag,
                receiverName: this.receiverName,
                receiverMobile: this.receiverMobile,
                content: this.content
            })
                .then(function (response) {
                    console.log(response);
                    alert('更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})