var app = new Vue({
    el: '#app',
    data: {
        productCode: '',
        productId:'',
        productName: '',
        price: '',
        discount: '',
        stockQuantity: '',
        rewordPoints: '',
        sortOrder: '',
        description: '',
        selectedStatus: 1,
        selectedMainPic: '',
        selectedOtherPics: [],
        otherPicUrls: [],
        mainPicUrl: '',
        status: '',
        statuses: [{
            value: 0,
            label: '上架'
        }, {
            value: 1,
            label: '下架'
        }, {
            value: 2,
            label: '待审核'
        }],
        mainfileList: [],
        otherfileList: []
    },
  
    mounted() {
        console.log('view mounted');
       
        var url=new URL(location.href);
        this.productId=url.searchParams.get("productId");
        if(!this.productId){
            alert('product is null');
            return;
        }
        this.getproductById();
    },
    methods: {
        handleCreateClick() {
            console.log('create click');
            this.description = tinyMCE.activeEditor.getContent();
            this.updateProduct();
        },
        handleOnMainChange(val) {
            this.selectedMainPic = val.raw;
        },
        handleUploadMainClick() {
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
        handleOnOtherChange(file, fileList) {
            this.selectedOtherPics = fileList;
        },
        handleUploadOtherClick() {
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
                        alert('上传成功');
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert('上传失败');
                    });
            });


        },
        updateProduct() {
            axios.post('/product/update', {
                productId:this.productId,
                productCode: this.productCode,
                productName: this.productName,
                price: this.price,
                discount: this.discount,
                stockQuantity: this.stockQuantity,
                rewordPoints: this.rewordPoints,
                sortOrder: this.sortOrder,
                description: this.description,
                status: this.status,
                mainPicUrl: this.mainPicUrl,
                othertPicUrls: this.otherPicUrls
            })
                .then(function (response) {
                    console.log('创建成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getproductById() {
            axios.get('/product/getById', {
                params: {
                    productId: this.productId
                }
            })
                .then(function (response) {
                    console.log(response);
                    //赋值
                    var product=response.data;
                    app.productId=product.productId,
                    app.productCode = product.productCode;
                    app.productName = product.productName;
                    app.price = product.price;
                    app.discount = product.discount;
                    app.stockQuantity = product.stockQuantity;
                    app.status = product.status;
                    app.rewordPoints = product.rewordPoints;
                    app.sortOrder = product.sortOrder;
                    app.mainPicUrl = product.mainPicUrl;
                    app.description = product.description;
                    tinymce.init({
                        selector: '#mytextarea'
                    });
                    app.otherPicUrls = product.otherPicUrls;
                })
                .catch(function (error) {
                    console.log(error);
                });

        }

    }
})