var app = new Vue({
    el: '#app',
    data: {
        productCode : '',
        productName : '',
        price : '',
        discount : '',
        stockQuantity : '',
        rewordPoints : '',
        sortOrder : '',
        description : '',
        selectedStatus:1,
        selectedMainPic:'',
        selectedOtherPics: [],
        otherPicUrls: [],
        mainPicUrl : '',
        othertPicUrls :[],
        status :'',
        statuses : [{ 
            value: '0',
            label: '上架'
          }, {
            value: '1',
            label: '下架'
          }, {
            value: '2',
            label: '待审核'
          }],
          mainfileList:[],
          otherfileList :[]
    },
    mounted(){tinymce.init({
        selector: '#mytextarea'
      });},
    methods:{
        handleCreateClick(){
            console.log('create click');
            this.description=tinyMCE.activeEditor.getContent();
            this.createProduct();
        },
        handleOnMainChange(val){
            this.selectedMainPic=val.raw;
        },
        handleUploadMainClick(){
            this.uploadMainImage();
        },
        uploadMainImage() {
            var formData = new FormData();
            formData.append("image", this.selectedMainPic);

            axios.post('/Image/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.mainPicUrl = response.data;
                    alert('上传成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('上传失败');
                });
        },
        handleOnOtherChange(file,fileList){
            this.selectedOtherPics=fileList;
        },
        handleUploadOtherClick(){
                this.uploadOtherImage();
        },
        uploadOtherImage() {
            this.selectedOtherPics.forEach(pic => {
                var formData = new FormData();
                formData.append("image", pic.raw);

                axios.post('/Image/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                })
                    .then(function (response) {
                        console.log(response);
                        var url = response.data;
                        app.otherPicUrls.push(url);
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert('上床失败');
                    });
            }); 


        },
        createProduct() {
            axios.post('/product/create', {
                productId: this.productId,
                productCode:this.productCode,
                productName: this.productName,
                price: this.price,
                discount: this.discount,
                stockQuantity: this.stockQuantity,
                status: this.selectedStatus,
                mainPicUrl: this.mainPicUrl,
                rewordPoints: this.rewordPoints,
                sortOrder: this.sortOrder,
                description: this.description,
                otherPicUrls: this.otherPicUrls
              })
              .then(function (response) {
                console.log('创建成功');
              })
              .catch(function (error) {
                console.log(error);
              });
        }
        
    }
})